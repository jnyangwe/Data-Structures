/**Main class that tests the spatial and time perfomance of different
 * trie and patricia trie implementations*/
import java.io.*;
import java.util.Scanner;

public class Driver {
	
	//manager class
	static Manager manager = new Manager();
	
	//trie and patricia trie class
	static PatriciaTrie patrieLA ;
	static PatriciaTrieLinkedList patrieL ;
	static AsciiTrieArray trieArr ;
	static AsciiTrieDLB trieDLB ;
	static PatriciaTrieArr patrieArr ;
	
	
    public static void main(String [] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int n = 1; 
        int file_no = 1;
        
        System.out.println("Trie VS Patricia ANALYSIS\n");
        
        while( n != 4){
        	
        	System.out.println("1. Trie: Array vs De La Brandais");
        	System.out.println("2. Patricia: Array vs  Linked List");
        	System.out.println("3. Spatial analysis Trie vs Patricia");
        	System.out.println("4. Exit");
	        System.out.println("Enter a number: ");
	        n = reader.nextInt();
	        
	        if ( n!= 4){
		        System.out.println("Choose File 1, 2, 3 or 4: ");
		        file_no = reader.nextInt();
	        }
	        
	        switch(n){
	        case 1:trieArrVsDLB(file_no);
	        	break;
	        case 2:PatriciaArrVslinkedList(file_no);
	        	break;
	        case 3:trieVsPatricia(file_no);
	        	break;
	        }
	        
	        System.out.println("\n\n");
        }   
    }
   
    /**Returns the size obj in bytes
     * @param obj - object whose size is to be determined*/
    private static int getSize(Object obj) {
    	ByteArrayOutputStream bytesOut = null;
    	try {
    	bytesOut = new ByteArrayOutputStream();
    	ObjectOutputStream objectOut = new ObjectOutputStream(bytesOut);
    	objectOut.writeObject(obj);
    	objectOut.close();
    	} catch (IOException e) {} // Does nothing in this case
    	return bytesOut.size();
    	}
    
    /**Method analyzes performance of a trie vs a De La Brandais trie 
     * @param file - file to read words from*/
    public static void trieArrVsDLB(int file){
    	trieArr = new AsciiTrieArray();
    	trieDLB = new AsciiTrieDLB();
    	
    	
    	System.out.println("Trie Array vs De la Brandais");
    	System.out.println("FILE #: " + file + "\n");
    	
    	System.out.println("TIME Analysis\n");
    	System.out.println("1. Insert");
    	
    	System.out.print("Array:            ");
        trieArr = (AsciiTrieArray) manager.operation(trieArr, file, 1, true);
        
        System.out.print("De la Brandais:   ");
        trieDLB = (AsciiTrieDLB) manager.operation(trieDLB, file, 1, true);
        
        int sizeArr = trieArr.size();
        int sizeDLB = trieDLB.size();
        
        System.out.println("\n");
        System.out.println("2. search");
    	
    	System.out.print("Array:            ");
        trieArr = (AsciiTrieArray) manager.operation(trieArr, file, 3, true);
        
        System.out.print("De la Brandais:   ");
        trieDLB = (AsciiTrieDLB) manager.operation(trieDLB, file, 3, true);
        
        System.out.println("\n");
        System.out.println("3. Delete");
    	
    	System.out.print("Array:            ");
        trieArr = (AsciiTrieArray) manager.operation(trieArr, file, 3, true);
        
        System.out.print("De la Brandais:   ");
        trieDLB = (AsciiTrieDLB) manager.operation(trieDLB, file, 3, true);
        
        
        System.out.println("\n");
        System.out.println("SPATIAL Analysis\n");
        System.out.println("Array:            " + sizeArr);
        System.out.println("De la Brandais:   " + sizeDLB);
        
    }
    
    /**Method analyzes perfomance of Patricia Array vs Patricia linked List 
     * @param file - file to read words from*/
    public static void PatriciaArrVslinkedList(int file){
    	patrieL = new PatriciaTrieLinkedList();
    	patrieArr = new PatriciaTrieArr();
    	
    	
    	System.out.println("Patricia Array vs Patricia LinkedList");
    	System.out.println("FILE #: " + file + "\n");
    	
    	System.out.println("TIME Analysis\n");
    	System.out.println("1. Insert");
    	
    	System.out.print("Patricia Array:            ");
        patrieArr = (PatriciaTrieArr) manager.operation(patrieArr, file, 1, true);
        
        System.out.print("Patricia De la Brandais:   ");
        patrieL = (PatriciaTrieLinkedList) manager.operation(patrieL, file, 1, true);
        
        int sizeArr = patrieArr.size();
        int sizeDLB = patrieL.size();
        
        System.out.println("\n");
        System.out.println("2. search");
    	
    	System.out.print("Patricia Array:            ");
    	patrieArr = (PatriciaTrieArr) manager.operation(patrieArr, file, 3, true);
        
        System.out.print(" Patricia De la Brandais:   ");
        patrieL = (PatriciaTrieLinkedList) manager.operation(patrieL, file, 3, true);
        
        System.out.println("\n");
        System.out.println("3. Delete");
    	
    	System.out.print("Patricia Array:            ");
        patrieArr = (PatriciaTrieArr) manager.operation(patrieArr, file, 3, true);
        
        System.out.print("Patricia De la Brandais:   ");
        patrieL = (PatriciaTrieLinkedList) manager.operation(patrieL, file, 3, true);
        
        
        System.out.println("\n");
        System.out.println("SPATIAL Analysis\n");
        System.out.println("Patricia Array:            " + sizeArr);
        System.out.println("Patricia De la Brandais:   " + sizeDLB);
        
    }
    
    /**Method performs spatial analysis between a trie and a Patricia trie 
     * @param file - file to read words from*/
    public static void trieVsPatricia(int file){
    	trieArr = new AsciiTrieArray();
    	trieDLB = new AsciiTrieDLB();
    	patrieL = new PatriciaTrieLinkedList();
    	patrieArr = new PatriciaTrieArr();
    	
    	
    	System.out.println("SPATIAL ANALYSIS\n");
    	
    	System.out.println("ARRAY(buffer) implementation");
    	
    	trieArr = (AsciiTrieArray) manager.operation(trieArr, file, 1, false);
    	System.out.println("Trie:       " + trieArr.size());    	
    	patrieArr = (PatriciaTrieArr) manager.operation(patrieArr, file, 1, false);	
    	System.out.println("Patricia:   " + patrieArr.size());
    	
    	//trieArr = (AsciiTrieArray) manager.operation(trieArr, file, 2, false);
    	//patrieArr = (PatriciaTrieArr) manager.operation(patrieArr, file, 2, false);
    	
    	System.out.println("\nDe La Brandais implementation");
    	trieDLB = (AsciiTrieDLB) manager.operation(trieDLB, file, 1, false);
    	System.out.println("Trie:       " + trieDLB.size());
    	patrieL = (PatriciaTrieLinkedList) manager.operation(patrieL, file, 1, false);
    	System.out.println("Patricia:   " + patrieL.size());
    	
    	//trieDLB = (AsciiTrieDLB) manager.operation(trieDLB, file, 2, false);
    	//patrieL = (PatriciaTrieLinkedList) manager.operation(patrieL, file, 2, false);
    }
}



