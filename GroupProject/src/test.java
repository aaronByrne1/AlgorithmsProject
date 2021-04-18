import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Hello people");
		TST<Integer> searchTree = new TST<Integer>();

		File file =new File("stops.txt");
		Scanner s= new Scanner(file);
		String namesOfData=s.nextLine();
		System.out.println(namesOfData);
		while(s.hasNextLine()) {
			String key;
			String [] value= s.nextLine().split(",");
			key=value[2];
			if(key.charAt(2)==' ') {
				char first=key.charAt(0);
				char second=key.charAt(1);
				String append= " "+first+second;
				key = key+append;
				key=key.substring(3);
			}
			searchTree.put(key, value);
		}
		//String test[]=searchTree.;
		//Iterable<String> test= searchTree.keysThatMatch("CAULFEILD");
		//System.out.println(Arrays.toString(test));
		
		boolean notAKey=true;
		String userInput="";
		while(notAKey==true) {
			Scanner input =new Scanner(System.in);
			userInput =input.nextLine();
			if(searchTree.contains(userInput)==true) {
				notAKey=false;
			}
			System.out.println(searchTree.keysWithPrefix(userInput));
			
		}
		String test[]= searchTree.get(userInput);
		System.out.println(Arrays.toString(test));

	}

}
