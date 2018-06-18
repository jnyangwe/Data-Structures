/**Author: Joseph Nyangwechi
 * Class is an implementation of a trie with array as buffers*/

import java.util.ArrayList;
import java.util.Collection;

public class AsciiTrieArray implements Patrie {
	
	/**Class implementation of node class*/
	private class Node{
		private Node[] next = null;
		byte eosFlag;
		
		public Node(){
			next = new Node[128];
			eosFlag = (byte)0;
		}
	}
	
	private int size = 0;
	public Node root;
	
	/**Simple constructor that initializes the root*/
	public AsciiTrieArray(){
		root = new Node();
	}
	
	/**Inserts key into the trie iteratively
	 * @param key string to be inserted*/
	public void insert(String key){
		
		
		if (key.length() > 0){
			int currIndex = 0;
			Node curr = root;
			try{
			//loop through trie inserted the string a character at a time.
			while(currIndex < key.length()){
				if (curr == null)
					curr = new Node();
				
				if (curr.next[key.charAt(currIndex)] == null)
					curr.next[key.charAt(currIndex)] = new Node();
				
				
				
					curr = curr.next[key.charAt(currIndex++)];
				
			}
			
			size++;
			curr.eosFlag = (byte)1;
			}catch(Exception e){
				System.out.println(e);
				System.out.println(key.charAt(currIndex));
			}
		}
		
		
	}
	
	/**Looks for key in the trie
	 * @param key String to be searched for
	 * @return returns true if key is found in trie else false*/
	public boolean search(String key){

		int currIndex = 0;
		Node curr = root;
		
		while(currIndex < key.length() && curr != null){
			curr = curr.next[key.charAt(currIndex++)];			
		}
		
		if (curr != null && curr.eosFlag == (byte)1)
			return true;
		else
			return false;
	}
	
	/**Deletes key from the trie
	 * @param key string to be deleted from trie*/
	public void delete(String key){
		int currIndex = 0;
		Node curr = root;
		
		while(currIndex < key.length() && curr != null){
			curr = curr.next[key.charAt(currIndex++)];
		}
		
		if (curr != null && curr.eosFlag == (byte)1)
			curr.eosFlag = (byte) 0;
			
	}
	
	/**Finds the longest prefix of key stored in the trie
	 * @param key string we want to find the longest prefix of
	 * @return the longest prefix of key*/
	public String longestPrefixOf(String key){
		int longestPrefixLength = getMaxPrefixLength(root, key, 0,0);
		return key.substring(0, longestPrefixLength+1);
	}

	/**gets the length of longest prefix of string key
	 * @param curr node currently being traversed
	 * @param key string which we are looking for its longest prefix length
	 * @currInd string's current Index
	 * @param maxlenRec  current maximum length of string
	 * @return longest prefix length of key*/
	private int getMaxPrefixLength(Node curr, String key, int currInd, int maxLenRec) {
		if (curr == null || currInd == (key.length()-1)) 
			return maxLenRec;
		if (curr.eosFlag == (byte)1)
			maxLenRec = currInd;
		return getMaxPrefixLength(curr.next[key.charAt(currInd)], key, currInd + 1, maxLenRec);
	}
	
	/**Finds all array with a certain prefix
	 * @param prefix string we want to find all prefixes to
	 * @return list of all string which start wuth prefix*/
	public Collection<String> keysWithPrefix(String prefix){
		
		//finds node that is the root of all nodes with a prefix == prefix
		Node prefixNode = fetchNode(root, prefix, 0);
		
		//stores all strings with which begin with prefix
		ArrayList<String> keys = new ArrayList<String>();
		populate(prefixNode, prefix, keys);
		return keys;
	}
	
	/**Populates arraylist keys with all the strings that have prefix == prefix
	 * @param curr  current node being traversed
	 * @param prefix prefix of current node
	 * @param keys arraylist containing strings with the same prefix*/
	private void populate(Node curr, String prefix, ArrayList<String> keys){
		if (curr == null)
			return;
		if (curr.eosFlag == (byte)1 )
			keys.add(prefix);
		
		for (int i = 0; i < curr.next.length; i++)
			populate(curr.next[i], prefix+(char)i, keys);
	}
	
	/**Finds node that marks the end of string prefix
	 * @param curr current node being traversed
	 * @param prefix
	 * @param currIndex keeps track of the index of the string we have traversed
	 * @return node that marks the end of the string prefix*/
	private Node fetchNode(Node curr, String prefix, int currIndex){
		//if (curr.eosFlag == (byte)1) 
		if (curr == null || currIndex == prefix.length())
			return curr;
		return fetchNode(curr.next[prefix.charAt(currIndex)], prefix, currIndex + 1);
	}
	/**Finds the size of the trie
	 * every node has: 128 pointers (128 * 8 bytes)
	 * 				   1 byte
	 * 			       total: 1025 bytes
	 * @return size of trie in bytes */
	public int size(){
		return size* ((8 * 128) + 2 );
	}
	
	/**Finds the size of the trie.
	 * @param node current node of trie.
	 * @param number of nodes so far
	 * @return number of nodes in trie */
	private int size(Node node, int count){
		if (node == null)
			return 0;
		else{
		
			if (node != null){
				for(Node n: node.next){
					if (n != null)
						count = size(node, count);
				}
			}
			return count + 1;
		}
	}
	
	
	
	
}
