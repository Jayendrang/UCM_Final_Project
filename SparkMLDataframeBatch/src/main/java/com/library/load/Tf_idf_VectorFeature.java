package com.library.load;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature.IDFModel;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.ml.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class Tf_idf_VectorFeature {

	public void loadHDFSFiles(SparkSession sparkSession) {
		try {
			// SparkSession sparkSession = SparkSession.builder().appName("Sample1")
			// .config("spark.some.config.option", "some-value").getOrCreate();
			// SparkConf configuration = new SparkConf().setAppName("Demo
			// Load").setMaster("spark://jayendrang-GS63VR-6RF:7077");
			//
			// JavaSparkContext jSparkContext = new JavaSparkContext();
			// JavaRDD<String> textfile =
			// jSparkContext.textFile("hdfs://localhost:9000/library/output/test1/24.text-r-00000");
			// SQLContext sqlcontext = new SQLContext(jSparkContext);

			String stopwords = "\\s(?:ll|ve|se|a|about|above|across|after|again|against|all|almost|alone|along|already|also|although|always|among|an|and|another|any|anybody|anyone|anything|anywhere|are|area|areas|around|as|ask|asked|asking|asks|at|away|back|backed|backing|backs|be|but|became|because|become|becomes|been|before|began|behind|being|beings|best|better|between|big|both|but|by|came|can|cannot|case|cases|certain|certainly|clear|clearly|come|could|did|differ|different|differently|do|does|done|down|down|downed|downing|downs|during|each|early|either|end|ended|ending|ends|enough|even|evenly|ever|every|everybody|everyone|everything|everywhere|face|faces|fact|facts|far|felt|few|find|finds|first|for|four|from|full|fully|further|furthered|furthering|furthers|gave|general|generally|get|gets|give|given|gives|go|going|good|goods|got|great|greater|greatest|group|grouped|grouping|groups|had|has|have|having|he|her|here|herself|high|high|high|higher|highest|him|himself|his|how|however|if|important|in|interest|interested|interesting|interests|into|is|it|its|itself|just|keep|keeps|kind|knew|know|known|knows|large|largely|last|later|latest|least|less|let|lets|like|likely|long|longer|longest|made|make|making|man|many|may|me|member|members|men|might|more|most|mostly|mr|mrs|much|must|my|myself|necessary|need|needed|needing|needs|never|new|new|newer|newest|next|no|nobody|non|noone|not|nothing|now|nowhere|number|numbers|o|of|off|often|old|older|oldest|on|once|one|only|open|opened|opening|opens|or|order|ordered|ordering|orders|other|others|our|out|over|part|parted|parting|parts|per|perhaps|place|places|point|pointed|pointing|points|possible|present|presented|presenting|presents|problem|problems|put|puts|quite|rather|really|right|right|room|rooms|said|same|saw|say|says|second|seconds|see|seem|seemed|seeming|seems|sees|several|shall|she|should|show|showed|showing|shows|side|sides|since|small|smaller|smallest|so|some|somebody|someone|something|somewhere|state|states|still|still|such|sure|take|taken|than|that|the|their|them|then|there|therefore|these|they|thing|things|think|thinks|this|those|though|thought|thoughts|three|through|thus|to|today|together|too|took|toward|turn|turned|turning|turns|two|under|until|up|upon|us|use|used|uses|very|want|wanted|wanting|wants|was|way|ways|we|well|wells|went|were|what|when|where|whether|which|while|who|whole|whose|why|will|with|within|without|work|worked|working|works|would|x|y|year|years|yet|you|young|younger|youngest|your|yours|1)(?=\\s|$)"
					+ "|^(?:ll|ve|se|a|about|above|across|after|again|against|all|almost|alone|along|already|also|although|always|among|an|and|another|any|anybody|anyone|anything|anywhere|are|area|areas|around|as|ask|asked|asking|asks|at|away|back|backed|backing|backs|be|but|became|because|become|becomes|been|before|began|behind|being|beings|best|better|between|big|both|but|by|came|can|cannot|case|cases|certain|certainly|clear|clearly|come|could|did|differ|different|differently|do|does|done|down|down|downed|downing|downs|during|each|early|either|end|ended|ending|ends|enough|even|evenly|ever|every|everybody|everyone|everything|everywhere|face|faces|fact|facts|far|felt|few|find|finds|first|for|four|from|full|fully|further|furthered|furthering|furthers|gave|general|generally|get|gets|give|given|gives|go|going|good|goods|got|great|greater|greatest|group|grouped|grouping|groups|had|has|have|having|he|her|here|herself|high|high|high|higher|highest|him|himself|his|how|however|if|important|in|interest|interested|interesting|interests|into|is|it|its|itself|just|keep|keeps|kind|knew|know|known|knows|large|largely|last|later|latest|least|less|let|lets|like|likely|long|longer|longest|made|make|making|man|many|may|me|member|members|men|might|more|most|mostly|mr|mrs|much|must|my|myself|necessary|need|needed|needing|needs|never|new|new|newer|newest|next|no|nobody|non|noone|not|nothing|now|nowhere|number|numbers|o|of|off|often|old|older|oldest|on|once|one|only|open|opened|opening|opens|or|order|ordered|ordering|orders|other|others|our|out|over|part|parted|parting|parts|per|perhaps|place|places|point|pointed|pointing|points|possible|present|presented|presenting|presents|problem|problems|put|puts|quite|rather|really|right|right|room|rooms|said|same|saw|say|says|second|seconds|see|seem|seemed|seeming|seems|sees|several|shall|she|should|show|showed|showing|shows|side|sides|since|small|smaller|smallest|so|some|somebody|someone|something|somewhere|state|states|still|still|such|sure|take|taken|than|that|the|their|them|then|there|therefore|these|they|thing|things|think|thinks|this|those|though|thought|thoughts|three|through|thus|to|today|together|too|took|toward|turn|turned|turning|turns|two|under|until|up|upon|us|use|used|uses|very|want|wanted|wanting|wants|was|way|ways|we|well|wells|went|were|what|when|where|whether|which|while|who|whole|whose|why|will|with|within|without|work|worked|working|works|would|x|y|year|years|yet|you|young|younger|youngest|your|yours|1)(?=\\s)"
							.trim();

			Dataset<Row> featuredataset = sparkSession.read()
					.text("hdfs://localhost:9000/library/output/test3/LatestOutput-r-00000");
			List<Row> featuredataframe = new ArrayList<Row>();
			int count = 0;
			for (Row row1 : featuredataset.collectAsList()) {
				if (!row1.anyNull()) {
					String temp[] = row1.mkString().split(",");
					if (!temp[1].equals("")) {
						String templine = temp[1].toLowerCase().replaceAll(stopwords, "").trim();
						String templine2 = templine.replaceAll("[^a-zA-Z]", " ");
						featuredataframe.add(new RowFactory().create(temp[0], templine2.trim()));

					}

				}
			}

			StructType dataschema = new StructType(
					new StructField[] { new StructField("fileId", DataTypes.StringType, false, Metadata.empty()),
							new StructField("words", DataTypes.StringType, false, Metadata.empty())
					/* , new StructField("tf", DataTypes.IntegerType, false, Metadata.empty()) */ });

			Dataset<Row> actualdata = sparkSession.createDataFrame(featuredataframe, dataschema);
			int numFeatures = 32;

			Tokenizer tokens = new Tokenizer().setInputCol("words").setOutputCol("words1");
			Dataset<Row> data = tokens.transform(actualdata);

			for (Row rows : data.collectAsList()) {
				System.out.println(rows.mkString());
			}

			HashingTF hashingTF = new HashingTF().setInputCol("words1").setOutputCol("rawfeatures")
					.setNumFeatures(numFeatures);
			Dataset<Row> featurizedData = hashingTF.transform(data);

			System.out.println("feature frame" + featuredataframe.size());
			System.out.println("TF Vector---------------------");
//			for (Row row : featurizedData.collectAsList()) {
//				System.out.println(row.get(3));
//			}
			System.out.println(featurizedData.toJSON());
			IDF idf = new IDF().setInputCol("rawfeatures").setOutputCol("features");
			IDFModel idfModel = idf.fit(featurizedData);

			Dataset<Row> rescaledData = idfModel.transform(featurizedData);

			System.out.println("TF-IDF -------------------------");
			for (Row row : rescaledData.collectAsList()) {
				System.out.println(row.get(4));
			}
				System.out.println("Transformations -------------------------------");
			//for (Row row : rescaledData.collectAsList()) {
			//	System.out.println(row);
			//}
			// actualdata.printSchema();
			rescaledData.show();

		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	
	public static void main(String args[]) throws IOException {
		Tf_idf_VectorFeature ll = new Tf_idf_VectorFeature();
		SparkSession sparkSession = SparkSession.builder().appName("Sample1").config("spark.master", "local")
				.getOrCreate();

		ll.loadHDFSFiles(sparkSession);
	}
}
