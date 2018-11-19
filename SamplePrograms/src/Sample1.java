import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String text1 = "asz--1231;'//asdfaseqq";
		//String text2 = text1.replaceAll("[^a-zA-Z]", "");
		Date dt = new Date();
		int d = dt.getHours();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
	    
		System.out.println(dt.getHours());
		
		System.out.println(formatter.format(dt));
		
		if(d>12 && d<14) {
			System.out.println("Batch 1");
		}else {
		
		}
	}

}
