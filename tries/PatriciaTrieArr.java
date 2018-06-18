

/**@author Joseph Nyangwechi
 * Buffer based implementation of a patricia trie*/
public class PatriciaTrieArr implements Patrie{

	/**Node class for patricia Trie*/
	public class PatNode{
		private PatNode[] next = null;
		String keyRef;
		byte eosFlag;
		 
		/**Constructor creates buffer*/
		public PatNode(){
			next = new PatNode[128];
			eosFlag = (byte) 0;
			keyRef = "";
		}
		
		public PatNode(PatNode n){
			this.next = n.next;
			this.keyRef = n.keyRef;
			this.eosFlag = n.eosFlag;
		}
		
		public PatNode(String key, int eosFlag){
			this.next = new PatNode[128];
			this.eosFlag = (byte) eosFlag;
			this.keyRef = key;
		}
	}
	
	public PatNode root;
	
	public PatriciaTrieArr(){
		root = new PatNode();
	}
	
	/** Inserts <tt>key</tt> into the trie.
	 * @param key The input {@link String} key.
	 */
	public void insert(String key) {
						
		//make sure string to be inserted is valid
		if (key != null && key.length() > 0){
			PatNode node = root;
			boolean done = false;
			int commonInd = 0;
			while(node != null && !done){
				commonInd = commonPrefix(node, key);
				
				//case 1
				if (node.keyRef.length() == 0 || commonInd == node.keyRef.length() ){
					
					if (key.length() > commonInd){
						if (node.next[key.charAt(commonInd)] == null){
							node.next[key.charAt(commonInd)] = new PatNode(key, 1);
							done = true;
						}else{
							node = node.next[key.charAt(commonInd)];
						}
					}else if (key.length() == commonInd){
						node.eosFlag = (byte)1;
						done = true;
					}
				}
				
				//case 2:
				/*else if (commonInd == node.keyRef.length()){
					node.eosFlag = (byte)1;
					done = true;
				}*/
				else if(commonInd < node.keyRef.length()){
					PatNode child = new PatNode(node);
					node.next = new PatNode[128];
					node.next[node.keyRef.charAt(commonInd)] = child;
					
					node.keyRef = node.keyRef.substring(0, commonInd);
					if (key.length() > commonInd){
						node.next[key.charAt(commonInd)] = new PatNode(key, 1);
						node.eosFlag = (byte) 0;
					}else
						node.eosFlag = (byte) 1;
					done = true;
				}
			}
		}
	}
	
	/**Finds the index up to which two string share the same character
	 * @param node node whose string is going to be compared
	 * @param key string to compare with
	 * @return the index up to which the two strings share a common character,
	 * returns 0 if the two strings do not share any common starting characters*/
	private int commonPrefix(PatNode node, String key) {
		if (node == null || node.keyRef == null || key == null)
			return 0;
		else{
			int commonIndex = 0;
			
			while( commonIndex < node.keyRef.length() && commonIndex < key.length() &&
					node.keyRef.charAt(commonIndex) == key.charAt(commonIndex))
				commonIndex++;
			
			return commonIndex;
		}
	}

	/**Searches the trie for a given key.
	 * @param key The input {@link String} key.
	 * @return <tt>true</tt> if and only if <tt>key</tt> is in the trie, <tt>false</tt> otherwise.
	 */
	public boolean search(String key) {
		//start searching from the root
		PatNode node = root;
		boolean found = false;
		
		if (key != null && key.length() > 0){
			
			//traverse the tree looking for the string
			while(node != null && !found){
				
				if (node.keyRef.length() == 0 || key.startsWith(node.keyRef)){
					if (key.length() == node.keyRef.length() )
						if (node.eosFlag == (byte) 1)
							found = true;
						else
							return false;
					else if(key.length() > node.keyRef.length())
						node = node.next[key.charAt(node.keyRef.length())];
				}
				else
					return false;
			}
		}
		return found;
	}
	
	/** Searches the trie for a given key and deletes it
	 * @param key string to be deleted
	 */
	public void delete(String key) {
		//start searching from the root
		PatNode node = root;
		boolean found = false;
		
		if (key != null && key.length() > 0){
			
			//traverse the tree looking for the string
			while(node != null && !found){
				
				if (node.keyRef.length() == 0 || key.startsWith(node.keyRef)){
					if (key.length() == node.keyRef.length() ){
						node.eosFlag = (byte) 0;	
						found = true;
					}
					else if(key.length() > node.keyRef.length())
						node = node.next[key.charAt(node.keyRef.length())];
				}
				else
					 found = true;
			}
		}
		
	}
	
	/**Finds the size of the trie
	 * @return size of trie in bytes */
	public int size(){
		return size(root, 0);
	}
	
	/**Finds the size of the trie.
	 * @param node current node of trie.
	 * @param number of nodes so far
	 * @return size of trie in bytes */
	private int size (PatNode node, int count){
		if (node == null)
			return count;
		else{// calculate the size of each node
			
			//get the size of keyRef of each node
			//size needs to be a multiple of 8
			if (node.keyRef != null){
				count = count + (8 * (int)((node.keyRef.length() * 2)/8));
			}else
				count = count + 8;
			
			//calculate the size of all pointers
			//Every pointer is assumed to be 8 bytes in length
			if (node.next != null){
				count  = count + ( 8 * 128);
			}else
				count  = count + 8;
			
			//add the size of the byte used to store 'eosFlag' in each node
			count = count + 2;
			
			
			for(PatNode n: node.next){
				if (n != null)
					count = size(n, count);
			}
			return count;
		}
		
	}
}
