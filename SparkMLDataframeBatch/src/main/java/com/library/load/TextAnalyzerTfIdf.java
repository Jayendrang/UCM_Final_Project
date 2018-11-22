package com.library.load;

import java.util.Properties;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

@SuppressWarnings("deprecation")
public class TextAnalyzerTfIdf {

	private static SparkSession sparkSession;

	private static String hive_connection = "jdbc:hive2://localhost:10000/";
	private static String hive_username = "spark_username";
	private static String hive_password = "spark_password";

	public static void main(String[] args) {
		TextAnalyzerTfIdf self = new TextAnalyzerTfIdf();
		try {
			sparkSession = SparkSession.builder().appName("Spark TF-IDF")
					// .config("spark.sql.warehouse.dir",
					// "hdfs://localhost:50070/user/hive/warehouse")
					.config("hive.metastore.uris", "thrift://localhost:9083")
					.config("spark.driver.allowMultipleContexts", "true").master("local[*]").enableHiveSupport()
					.getOrCreate();

			SparkContext sContext = sparkSession.sparkContext();
			sContext.setLogLevel("ERROR");
			SQLContext sqlContext = sparkSession.sqlContext();

			Properties connectionProps = new Properties();
			connectionProps.put("user", hive_username);
			connectionProps.put("password", hive_password);
			connectionProps.put("dbtable", "library_dataset");

			// loading from hive table
			JavaRDD<Row> tabledata = sparkSession.sqlContext()
					.sql("SELECT bookid,words,tf_count from library_dataset.tf_table").javaRDD();

			// creating spark schema structure
			StructType dataschema = new StructType(
					new StructField[] { new StructField("bookid", DataTypes.StringType, false, Metadata.empty()),
							new StructField("words", DataTypes.StringType, false, Metadata.empty()),
							new StructField("count", DataTypes.FloatType, false, Metadata.empty()) });

			// create dataframe by applying schema on hive data
			Dataset<Row> tftable = sqlContext.createDataFrame(tabledata, dataschema).orderBy("bookid");

			// count of individual docs
			final double documentcount = tftable.groupBy("bookid").agg(functions.approx_count_distinct("bookid"))
					.count();

			// create document frequency(df) from hive data(tftable)
			Dataset<Row> dftable = tftable.groupBy("words")
					.agg(functions.countDistinct("bookid").cast("float").alias("df"));

			// create inverse document frequency(idf) from dataset dftable
			sqlContext.udf().register("idfcalc", new UDF1<Float, Float>() {

				private static final long serialVersionUID = 1L;

				public Float call(Float documentfreq) throws Exception {
					Float dfvalue = (float) Math.log(documentcount / documentfreq + 1);
					return dfvalue;
				}
			}, DataTypes.FloatType);

			Dataset<Row> idftable = dftable.withColumn("idf",
					functions.callUDF("idfcalc", dftable.col("df")).cast("float"));

			// generate idf value from tf idf
			sqlContext.udf().register("tfidfcalc", new UDF2<Float, Float, Float>() {
				private static final long serialVersionUID = 1L;

				public Float call(Float tfvalue, Float idfvalue) throws Exception {
					Float dfvalue = (float) tfvalue * idfvalue;
					return dfvalue;
				}
			}, DataTypes.FloatType);

			@SuppressWarnings("unchecked")
			Dataset<Row> tf_idf_table = idftable
					.withColumn("tf_idf",
							functions.callUDF("tfidfcalc", dftable.col("df"), idftable.col("idf")).cast("float"))
					.join(tftable, "words");

			tf_idf_table.show();
			
			tf_idf_table.createOrReplaceTempView("temp_prod");
			sqlContext.sql("drop table if exists library_dataset.prod_library_data");
			sqlContext.sql("create table if not exists library_dataset.prod_library_data as select * from temp_prod");
			sqlContext.sql("insert into library_dataset.prod_library_data  select * from temp_prod"); 
			
			System.out.println("Corpus size(Total books)-->" + documentcount);
			sparkSession.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void searchCorpus(Dataset<Row> dataset, String searchString) {
		Dataset<Row> rr = dataset.where(dataset.col("words").equalTo(searchString)).orderBy(dataset.col("count"));
				rr.show();
		
	}
	
}

