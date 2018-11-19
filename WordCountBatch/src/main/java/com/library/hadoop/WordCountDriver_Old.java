package com.library.hadoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

public class WordCountDriver_Old {
	private static Set stopWordSet = new HashSet<String>();

	public static String processingFileName;
	public static final String STOPWORDS = "\\s(?:ll|ve|se|a|about|above|across|after|again|against|all|almost|alone|along|already|also|although|always|among|an|and|another|any|anybody|anyone|anything|anywhere|are|area|areas|around|as|ask|asked|asking|asks|at|away|back|backed|backing|backs|be|but|became|because|become|becomes|been|before|began|behind|being|beings|best|better|between|big|both|but|by|came|can|cannot|case|cases|certain|certainly|clear|clearly|come|could|did|differ|different|differently|do|does|done|down|down|downed|downing|downs|during|each|early|either|end|ended|ending|ends|enough|even|evenly|ever|every|everybody|everyone|everything|everywhere|face|faces|fact|facts|far|felt|few|find|finds|first|for|four|from|full|fully|further|furthered|furthering|furthers|gave|general|generally|get|gets|give|given|gives|go|going|good|goods|got|great|greater|greatest|group|grouped|grouping|groups|had|has|have|having|he|her|here|herself|high|high|high|higher|highest|him|himself|his|how|however|if|important|in|interest|interested|interesting|interests|into|is|it|its|itself|just|keep|keeps|kind|knew|know|known|knows|large|largely|last|later|latest|least|less|let|lets|like|likely|long|longer|longest|made|make|making|man|many|may|me|member|members|men|might|more|most|mostly|mr|mrs|much|must|my|myself|necessary|need|needed|needing|needs|never|new|new|newer|newest|next|no|nobody|non|noone|not|nothing|now|nowhere|number|numbers|o|of|off|often|old|older|oldest|on|once|one|only|open|opened|opening|opens|or|order|ordered|ordering|orders|other|others|our|out|over|part|parted|parting|parts|per|perhaps|place|places|point|pointed|pointing|points|possible|present|presented|presenting|presents|problem|problems|put|puts|quite|rather|really|right|right|room|rooms|said|same|saw|say|says|second|seconds|see|seem|seemed|seeming|seems|sees|several|shall|she|should|show|showed|showing|shows|side|sides|since|small|smaller|smallest|so|some|somebody|someone|something|somewhere|state|states|still|still|such|sure|take|taken|than|that|the|their|them|then|there|therefore|these|they|thing|things|think|thinks|this|those|though|thought|thoughts|three|through|thus|to|today|together|too|took|toward|turn|turned|turning|turns|two|under|until|up|upon|us|use|used|uses|very|want|wanted|wanting|wants|was|way|ways|we|well|wells|went|were|what|when|where|whether|which|while|who|whole|whose|why|will|with|within|without|work|worked|working|works|would|x|y|year|years|yet|you|young|younger|youngest|your|yours)(?=\\s|$)"
			.trim();

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private String regexsymbol = "[^a-zA-Z]";

		// Removing stop words and mapping the keywords
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			processingFileName = ((org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit()).getPath()
					.getName().replaceAll(".txt", "");

			// loadStopWords(context);
			// System.err.println("Working
			// directory+++1"+context.getWorkingDirectory().getName());
			// System.err.println("Working file+++++2"+processingFileName);

			while (itr.hasMoreTokens()) {
				String tempString = itr.nextToken().toString().toLowerCase();

				String tempString1 = tempString.replaceAll(regexsymbol, "").trim();

				String tempString2 = tempString1.replaceAll(STOPWORDS, "");

				if (!tempString2.equals("")) {
					word.set(processingFileName + "," + tempString2.trim());
					context.write(word, one);

				}
			}
		}

	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();
		String processingfileName;

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {

			loadStopWords(context);
			System.err.println("stopwordsize----->" + stopWordSet.size());

			if (stopWordSet.contains(key.toString().trim()) == false) {
				int sum = 0;
				for (IntWritable val : values) {
					sum += val.get();
				}
				result.set(sum);
				context.write(key, result);
			}
		}

		// load stop words from hdfs to distributed cache
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
				for (String str : stopwordsarray) {
					stopWordSet.add(str.trim());
				}

			} catch (Exception ex) {
				System.err.println("Error in text mapper" + ex.getMessage() + "--->" + WordCountDriver_Old.class);
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		WordCountDriver_Old classobject = new WordCountDriver_Old();
		SimpleDateFormat folderName = new SimpleDateFormat("MM_dd_yyyy");
		Date creationdate = new Date();
		String dayfolder = folderName.format(creationdate);
		String batchfoldername = classobject.getBatchFolderName();
		String filename = "LatestOutput";
		
		
		Configuration conf = new Configuration();
		FileSystem filesystem = FileSystem.get(conf);
		Job job = Job.getInstance(conf, "uniary word count with stop words");
		
		
		job.setJarByClass(WordCountDriver_Old.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.getConfiguration().set("mapreduce.output.textoutputformat.separator", ",");
		job.getConfiguration().set("mapreduce.output.basename", filename);
		DistributedCache.addCacheFile(new Path(args[2]).toUri(), job.getConfiguration());
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1] + "/" + folderName.format(creationdate).toString()+"/"+batchfoldername));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public String getBatchFolderName() {
		Date dt = new Date();
		String batch = null;
		if (dt.getHours() < 12 ) {
			batch = "Batch1";
		} else if (dt.getHours() > 12 && dt.getHours() <=18) {
			batch = "Batch2";
		} else {
			batch = "Batch3";
		}
		return batch;
	}

}