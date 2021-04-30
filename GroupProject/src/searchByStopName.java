import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;

public class searchByStopName {

	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		createTST();
		Scanner s= new Scanner(System.in);
		String input=s.nextLine();
		functionality(input);

	}*/
	public static TST<Integer> searchTree = new TST<Integer>();
	public static String[] dataTypes;
	public static void printResult(String data[], String namesOfData[]) {
		namesOfData[0]="stop_id";
		System.out.println();
		for(int i=0; i<data.length;i++) {
			System.out.println(namesOfData[i]+": "+ data[i]);
		}
	}
	
	public static void createTST() throws FileNotFoundException {

		File file =new File("stops.txt");
		Scanner s= new Scanner(file);
		String namesOfData=s.nextLine();
		dataTypes=namesOfData.split(",");
		while(s.hasNextLine()) {
			String key;
			String[] value= s.nextLine().split(",");

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
		s.close();

	}
	public static void functionality( String userInput) {
		
		
		ArrayList<String> list = new ArrayList<String>();
			
			Spliterator<String> split =searchTree.keysWithPrefix(userInput.toUpperCase()).spliterator();
			split.forEachRemaining((n)->list.add(n));
			list.forEach((n)->printResult(searchTree.get(n), dataTypes));
		
	}

}
