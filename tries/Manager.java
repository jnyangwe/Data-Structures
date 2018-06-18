import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**@author Joseph Nyangwechi
 * Class manages the different trie and patricia trie implementations
 * */
public class Manager {
	
	ArrayList<String> file1 ;	
	ArrayList<String> file2;
	ArrayList<String> file3;
	ArrayList<String> file4;
	
	public Manager(){
		 file1 = getfileStrings("C:\\Users\\Owner\\workspace\\PatriciaTrie\\src\\temp.txt");		
		 file2 = getfileStrings("C:\\Users\\Owner\\workspace\\PatriciaTrie\\src\\temp2.txt");
		 file3 = getfileStrings("C:\\Users\\Owner\\workspace\\PatriciaTrie\\src\\temp3.txt");
		 file4 = getfileStrings("C:\\Users\\Owner\\workspace\\PatriciaTrie\\src\\temp4.txt");
	}
	
	/**Method performs type of operation(insert, delete, search) specified by the user
	 * @param trie data structure to perform operation on
	 * @param fileIndex indicates which file is to operated on
	 * @param operation type of operation. 1.Insert 2.Delete 3.Search */
	public Patrie operation(Patrie trie, int fileIndex, int operation, boolean output){
		
		ArrayList<String> file;
		switch(fileIndex){
		case 1: file = file1;
			break;
		case 2: file = file2;
			break;
		case 3: file = file3;
			break;
		case 4: file = file4;
		break;
			default:
				file = file1;
		}
		
		long startTime = System.currentTimeMillis();
		if (operation == 1){
			for (String str: file){
				trie.insert(str);
			}
			
		}else if(operation == 2){
			for (String str: file){
				trie.delete(str);
			}
			
		}else{
			for (String str: file){
				trie.search(str);
			}
		
		}
		long endTime = System.currentTimeMillis();
		if (output)
			System.out.println((endTime - startTime) + " milliseconds");
		return trie;
		
	}
	
	/**gets all the string from a file and stores it in arrayList
	 * @param path absolute path to file
	 * @return list of all the strings in the file*/
	public ArrayList<String> getfileStrings(String path){
		
		ArrayList<String> fileStrings = new ArrayList<String>();
    	String fileName = path;
      
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] words = line.split(" ");
            	
            	for (String word: words){
            		fileStrings.add(word);
            	}           	
            }  
            bufferedReader.close(); 
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
		return fileStrings;
	}
	

}
