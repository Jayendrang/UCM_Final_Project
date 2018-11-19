package library.spark.livyservice;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.livy.Job;
import org.apache.livy.JobContext;
import org.apache.livy.LivyClient;
import org.apache.livy.LivyClientBuilder;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;

class appJob implements Job<Double>, Function<Integer, Integer>, Function2<Integer, Integer, Integer> {

	private final int samples;

	public appJob(int samples) {
		// TODO Auto-generated constructor stub

		this.samples = samples;
	}

	public Double call(JobContext ctx) throws Exception {
		List<Integer> sampleList = new ArrayList<Integer>();
		for (int i = 0; i < samples; i++) {
			sampleList.add(i + 1);
		}

		return 4.0d * ctx.sc().parallelize(sampleList).map(this).reduce(this) / samples;
	}

	public Integer call(Integer v1) {
		double x = Math.random();
		double y = Math.random();
		return (x * x + y * y < 1) ? 1 : 0;
	}

	public Integer call(Integer v1, Integer v2) {
		return v1 + v2;
	}
}

public class sampleLivyService {

	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		String uril = "localhost:8998";
		LivyClient client = new LivyClientBuilder().setURI(new URI(uril)).build();
		try {

			System.err.println("SparkContext");
			client.uploadJar(new File("")).get();
			System.out.println("Result" + client.submit(new appJob(20)).get());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			client.stop(true);
		}
	}
}
