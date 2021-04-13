import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Hello people");
		TST<Integer> searchTree = new TST<Integer>();
		
		File file =new File("");
		Scanner s= new Scanner(file);
		s.nextLine();
		while(s.hasNextLine()) {
			String key;
			int value;
			String arr[]= s.nextLine().split(",");
			key=arr[2];
			value=Integer.parseInt(arr[1]);
			System.out.println(value);
		}
	}

}
