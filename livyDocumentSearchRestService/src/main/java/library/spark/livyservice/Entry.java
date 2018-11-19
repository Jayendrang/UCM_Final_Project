package library.spark.livyservice;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.livy.LivyClient;
import org.apache.livy.LivyClientBuilder;

public class Entry {

	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		String uril = "localhost:8998";
		LivyClient client = new LivyClientBuilder().setURI(new URI(uril)).build();
		try {
			
			System.err.println("SparkContext");
			client.uploadJar(new File("")).get();
			System.out.println("Result"+client.submit(new ));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
