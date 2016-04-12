import java.io.*;
import java.util.*;

public class HashFunction{
	public static int maxHash = 100000;

	//creating hash array
	public static MyLinkedList[] hashArray = new MyLinkedList[maxHash];
	
	// taking input from file and writing to arraylist
	public static ArrayList<String> extractWords(String ipFile) throws IOException{
		File f = new File(ipFile);
		Scanner scan = new Scanner(new FileReader(f));
		ArrayList<String> words = new ArrayList<String>();
		while(scan.hasNext()){
			words.add(scan.next());
		}
		scan.close();
		return words;
	}
	
	//methods
	
	public static int applyHashFunction(String word){
		int hashVal = 0;
		char[] charArray; 
		charArray = word.toCharArray();
		int len = charArray.length;
		for(int j=0; j<len; j++){
			int k = j+1;
			hashVal = hashVal+ (k * k * (int)charArray[j]);
		}
		hashVal = hashVal % maxHash;
		return hashVal;
	}
	
	public static void insert(String key, int value){
		int hashVal = applyHashFunction(key);
		MyLinkedList obj = new MyLinkedList(key, value);
		if(hashArray[hashVal] == null){
			hashArray[hashVal] = obj;
		}
		else{
			MyLinkedList temp = hashArray[hashVal];
			while(temp.getNext() != null){
				temp = temp.getNext();
			}
			temp.setNext(obj);
		}
	}
	
	public static MyLinkedList find(String key){
		for(int i=0; i<maxHash; i++){
			MyLinkedList obj = hashArray[i];
			if(obj != null){
				if(obj.getKey().equals(key) ){
					System.out.println ("Found key " +key);
					return obj;
				}
			}
		}
		return null;
	}
	public static void delete(String key){
		MyLinkedList prevObj = null;
		MyLinkedList obj = find(key);
		if(obj == null){
			System.out.println("Key "+key +" does not exist");
		}
		else if(obj.getNext()==null){
			obj = null;
		}
		else{
			while(obj.getNext()!=null){
				prevObj = obj;
				obj = obj.getNext();
			}
			prevObj.setNext(null);
		}
	}
	
	public static void increase(String key){
		if(find(key) == null){
			System.out.println("Key not found");
		}
		else{
			insert(key, 1);
		}
		
	}
	
	public static String listAllKeys(){
		int value=0;
		MyLinkedList obj;
		String output="";
		for(int i=0; i<maxHash; i++){
			if(hashArray[i] != null){
				obj = hashArray[i];
				if(obj.getNext() == null){
					value = value + obj.getValue();
				}
				else{
					while(obj != null){ 
						value = value + obj.getValue();
						obj = obj.getNext();
					}
				}
				output = output +"Key: "+ hashArray[i].getKey() + " Value: " +value +"\n";
				value = 0;
			}
		}
		System.out.println(output);
		return output;
	}

	public static void main(String[] args) throws IOException {
		
		// separating words from input
		ArrayList<String> words = extractWords("src/test.txt");
		
		//inserting each word to hash array
		int len = words.size();
		for(int i=0; i<len; i++){
			insert(words.get(i), 1);
		}
		System.out.println("array length is " +hashArray.length);
	
		String output = listAllKeys();
		// test functions
		find("this");
		increase("this");
		delete("good");
		increase("this");
		insert("someWord", 5);
		listAllKeys();
		
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter (new FileWriter("src/output.txt"));
			writer.write(output);
		}
		catch (IOException err){
			System.out.println(err);
		}
		finally{
			try{
				if (writer != null)
					writer.close();
			}
			catch(IOException err){
				System.out.println(err);
			}
		}
	}
	
}
