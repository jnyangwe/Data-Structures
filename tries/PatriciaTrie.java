/**Class represents a Patricia tree data structure
 * @author Joseph Nyangwechi*/

import java.util.ArrayList;



public class PatriciaTrie implements Patrie {
	
	/**class that represents a Patricia Trie node 
	 * @author Joseph Nyangwechi
	 * */
	public class TrieNode implements Comparable<String>{
		public String str;
		public ArrayList<TrieNode> children;
		public boolean terminal;
		
		
		/**Constructor initializes elements in the list*/
		public TrieNode(String str){
			this.str = str;
			this.terminal = true;
		}

		/**Method compares two nodes by comparing the strings of the nodes
		 * @param String 0 is the string to be compared to
		 * @return 1 if current object is greater than the other object, 0 if equal and -1 if less than the other object*/
		@Override
		public int compareTo(String o) {
			
			return str.compareTo(o);
		}
		
		public boolean equals(Object n){
			
			return (str.compareTo(n.toString()) == 0);
		}
		
		/**Method overrides tostring method
		 * @return a string representation of the current object*/
		public String toString(){
			return this.str;
		}
		
		
	}
		
	private ArrayList<TrieNode> root;
	
	/**Constructor
	 * Initializes the root of the data structure*/
	public PatriciaTrie(){
		this.root = null;
	}

	/**Method inserts a string into the Patricia Trie Data Structure
	 * @param str is the string to be inserted into the data structure.*/
	public void insert(String str){
		if (str != null && str.length() > 0){
			str = str.toLowerCase();
			root = insert(root, str);
		}
	}

	/**Method returns the root of the data structure
	 * @return root of data structure*/
	public ArrayList<TrieNode> getRoot(){
		return this.root;
	}
	/**Method inserts a string into the Patricia Trie Data Structure
	 * @param rootList array list of Patricia Trie nodes
	 * @param str is the string to be inserted into the data structure.
	 * @return returns an arrayList of nodes representing the children of the parent node*/
	public ArrayList<TrieNode> insert(ArrayList<TrieNode> rootList, String str){
		
			/*Check if root of data structure is empty*/
			if (rootList == null){
				rootList = new ArrayList<TrieNode>();
				rootList.add(new TrieNode(str));
			}
			/*If root is not empty*/
			else{
				boolean found = false;
				for(TrieNode node: rootList){
					int indexPrefix = 0;
					while(indexPrefix < node.str.length() && indexPrefix < str.length() 
						&& str.charAt(indexPrefix) == node.str.charAt(indexPrefix)){
							indexPrefix++;
					}
					
					if (indexPrefix == (node.str.length())){
						found = true;
						//if string to be inserted has a greater length than string 
						//at current node then insert substring into the node's children
						//else do nothing
						if (str.length() > node.str.length()){
							node.children = insert(node.children, str.substring(node.str.length()));
						}
						//else if str.length == node.str.length, then current node is a terminal node
						else{
							node.terminal = true;
							break;
						}
						
					}else if(indexPrefix > 0 && indexPrefix < node.str.length()){
						found = true;
						String newStr = node.str.substring(0, indexPrefix);
						
						TrieNode newChild_1 = new TrieNode(node.str.substring(indexPrefix));
						newChild_1.children = node.children;
						newChild_1.terminal = node.terminal;
						
						node.str = newStr;
						if (newStr.length() == str.length())
							node.terminal = true;
						else
							node.terminal = false;
						node.children = new ArrayList<TrieNode>();
						node.children.add(newChild_1);
						
						if (indexPrefix < str.length()){
							TrieNode newChild_2 = new TrieNode(str.substring(indexPrefix));
							node.children.add(newChild_2);
						}
					}
				}
				
				//if we don't find any node in rootlist that contains starting 
				// characters that are similar to str then
				if (!found)
					rootList.add(new TrieNode(str));
			}
		
		return rootList;
	}
	
	/**Method searches for the string
	 * @param str string that is searched for in the data structure.
	 * @return returns true if string str is found in the data structure*/
	public boolean search(String str){
		if (str != null)
			return search(root, str.toLowerCase());
		else
			return false;
	}
	
	/**Method searches for the string
	 * @param str string that is searched for in the data structure.
	 * @param rootList arrayList of Patrcia Trie nodes
	 * @return returns true if string str is found in the data structure*/
	public boolean search(ArrayList<TrieNode> rootList, String str){
		
		boolean found = false;
		/*Check if root of data structure is empty*/
		if (rootList != null){			
			for(TrieNode node: rootList){					
				//if string fully matches current node
				if (str.startsWith(node.str)){
					if (str.length() == node.str.length()&& node.terminal == true){
						found = true;
						break;
					}else if (str.length() > node.str.length()){
						found = search(node.children, str.substring(node.str.length()));
						break;
					}						
				}else
					found = false;				
			}	
		}
	
		return found;
	}

	/**@param str string to be deleted from the trie*/
	public void delete(String str){
		if (str != null)
			root =  delete(root, str);
	}
	
	/**removes node with string str
	 * @param rootList node we are currently traversing as we search for string to be deleted
	 * @param str string to be deleted
	 * @return new updated Arraylist after deleting node with str*/
	private ArrayList<TrieNode> delete(ArrayList<TrieNode> rootList, String str){
		
		
		/*Check if root of data structure is empty*/
		if (rootList != null){	
	
			for(TrieNode node: rootList){					
				//if string fully matches current node
				if (str.startsWith(node.str)){
					if (str.length() == node.str.length()&& node.terminal == true){
						if (node.children == null || node.children.size() == 0){
							rootList.remove(node);
						}
						else
							node.terminal = false;
						break;     //exit for loop
					}else if ( str.length() > node.str.length() ){
						
						node.children =  delete(node.children, str.substring(node.str.length()));
						if (node.children.size() == 1 && !node.terminal){
							node.str = node.str.concat(node.children.get(0).str);
							node.terminal = node.children.get(0).terminal;
							node.children = node.children.get(0).children;
						}
						break;		//exit for loop
					}						
				}				
			}	
		}
		
		return rootList;
		
		
	}
	
	public int size(){
		return 0;
	}
	
	
}
