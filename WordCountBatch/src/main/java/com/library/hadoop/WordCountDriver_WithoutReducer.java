package com.library.hadoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver_WithoutReducer {
	private static final Set stopWordSet = new HashSet<String>();
	public static String processingFileName ;
	
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private String regexsymbol="[^a-zA-Z]";
		//Removing stop words and mapping the keywords
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		//	StringTokenizer itr = new StringTokenizer(value.toString());
			loadStopWords(context);
			processingFileName=((org.apache.hadoop.mapreduce.lib.input.FileSplit)context.getInputSplit()).getPath().getName().replaceAll(".txt", "");
			
			String tempString = value.toString();
			
				if(!stopWordSet.isEmpty()) {
					String tempString2=tempString.replaceAll(regexsymbol, " ");
					
					word.set(processingFileName+","+tempString2.trim());
					context.write(word, one);
				
				}else {
					 new StopLoaderException("Stop words bag is empty... Check the file path");
				}
			
		}

		//load stop words from hdfs to distributed cache
		public void loadStopWords(Context context) throws IOException, InterruptedException, FileNotFoundException {
			try {

				BufferedReader reader;
				String stopwords = null;
				String[] stopwordsarray = null;
				Path[] cachefile = DistributedCache.getLocalCacheFiles(context.getConfiguration());
				if ((!cachefile.equals(null)) && (cachefile.length > 0)) {
					for (Path tempCachefile : cachefile) {
						reader = new BufferedReader(new FileReader(tempCachefile.toString()));
						while ((stopwords = reader.readLine()) != null) {
							stopwordsarray = stopwords.split(",");
						}
					}
				}
				for (String temp : stopwordsarray) {
					System.out.println("StopWords-->"+temp);
					stopWordSet.add(temp.toLowerCase());
				}

			} catch (Exception ex) {
				System.err.println("Error in text mapper" + ex.getMessage() + "--->" + WordCountDriver_WithoutReducer.class);
				ex.printStackTrace();
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();
		String processingfileName;
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat folderName = new SimpleDateFormat("MM_dd_yyyy_HH_mm");
		Date creationdate = new Date();
		String folder = folderName.format(creationdate);
		String filename = "LatestOutput";
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "uniary word count with stop words");
		job.setJarByClass(WordCountDriver_WithoutReducer.class);
		job.setMapperClass(TokenizerMapper.class);
		//job.setCombinerClass(IntSumReducer.class);
		//job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//job.getConfiguration().set("mapreduce.output.textoutputformat.separator",",");
		job.getConfiguration().set("mapreduce.output.basename",filename);
		DistributedCache.addCacheFile(new Path(args[2]).toUri(), job.getConfiguration());
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}