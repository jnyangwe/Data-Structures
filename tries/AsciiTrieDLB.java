/**Class Ascii implementation of a De la Briandais trie */
public class AsciiTrieDLB implements Patrie{
	
	/**
	 * Class is a node in a De la Briandais trie*/
	private class NodeDLB{
		private NodeDLB peerNode = null;
		private NodeDLB childNode = null;
		private char c;
		byte eosFlag;
		
		/**Constructor which does nothing*/
		public NodeDLB(){
			this.c = ' ';
		}
		
		/**Constructor initializes Node with char c
		 * @param c character to be inserted in node*/
		public NodeDLB(char c){
			this.c = c;
			peerNode = null;
			childNode = null;
		}
		
		/**Constructor
		 * @param c character to be inserted
		 * @param peerNode next peer node
		 * @param child node of current node */
		public NodeDLB(char c, NodeDLB peerNode, NodeDLB childNode, byte eosFlag){
			this.peerNode = peerNode;
			this.childNode = childNode;
			this.c = c;
			this.eosFlag = eosFlag;
		}

	}
	
	//keeps track of the number of nodes in the trie
	private int size;
	public NodeDLB root;

	/**Constructor initializes root*/
	public AsciiTrieDLB(){
		root = null;
	}
	
	/**Inserts key into the trie
	 * @param key string to be inserted*/
	public void insert(String key){
		if (key.length() > 0){
			
			int currIndex = 0;
			//case 1: if root == null
			if ( root == null)
				root = new NodeDLB(key.charAt(0));
			
			NodeDLB curr = root;			
			
			//case 2: if root != null
			while (currIndex < key.length() - 1 ){	
				
				//insert peerNode and return node that has just been inserted
				curr = insertPeerNode(curr, key.charAt(currIndex++));
				
				if (curr.childNode == null)
					curr.childNode = new NodeDLB(key.charAt(currIndex));
				
				curr = curr.childNode; 
			}
			
			//find and return node that contains the last character
			curr = findPeerNode(curr, key.charAt(currIndex));
			if (curr != null){
				curr.eosFlag = (byte)1;
				size++;
			}
		}
	}

	/**if peer Node with char c doesn't exist, it is 1st inserted then returned
	 * @param curr current node who we are searching for  
	 * @return node whose node.c == ch*/
	private NodeDLB insertPeerNode(NodeDLB curr, char ch) {
		if (curr != null){
			if (curr.c == ch)
				return curr;
			else if (curr.c > ch){
				NodeDLB newNode = new NodeDLB(curr.c, curr.peerNode, curr.childNode, curr.eosFlag);
				curr.c = ch;
				curr.eosFlag = (byte) 0;
				curr.childNode = null;
				curr.peerNode = newNode;
				return curr;			
			}							
			else{
				NodeDLB next = curr.peerNode;
				while (next != null && next.c < ch ){
					curr = next;
					next = next.peerNode;
				}
 			
				if (next != null && next.c != ch){
					curr.peerNode = new NodeDLB(ch);
					curr.peerNode.peerNode = next;
				}
				else if (next == null)
					curr.peerNode = new NodeDLB(ch);			
		  }	
			return curr.peerNode;
			
		}else
			return null;
	}

	
	/**Looks for key in trie
	 * @param key string to search
	 * @return true if key is in trie else returns false*/
	public boolean search(String key){
		NodeDLB curr = root;
		int currInd = 0;
		if (curr != null && key.length() > 0 ){
			
			while(currInd < (key.length() - 1) && curr != null){
				curr = findPeerNode(curr, key.charAt(currInd++));
				if (curr!= null)
					curr = curr.childNode;
			}
			
			if (curr != null ){
				curr = findPeerNode(curr, key.charAt(currInd));
				if (curr != null && curr.eosFlag == (byte) 1)
					return true;				
			}
		}
		
		return false;
	}

	/**Finds the peer node with character ch
	 * @param curr node to search for current node within peers
	 * @param ch character we are searching for in a node
	 * @return returns node which has node.c == ch*/
	private NodeDLB findPeerNode(NodeDLB curr, char ch) {
		while(curr != null && curr.c < ch )
			curr = curr.peerNode;
		
		if (curr != null && curr.c == ch)
			return curr;
		else
			return null;
	}
	
	/**Deletes key from trie
	 * @param key string to be deleted*/
	public void delete(String key){
		NodeDLB curr = root;
		int currInd = 0;
		if (curr != null && key.length() > 0 ){
			
			while(currInd < (key.length()-1) && curr != null){
				curr = findPeerNode(curr, key.charAt(currInd++));
				if (curr!= null)
					curr = curr.childNode;
			}
			
			if (curr != null ){
				curr = findPeerNode(curr, key.charAt(currInd));
				if (curr != null)
					curr.eosFlag = (byte) 0;
			}	
		}
	}
	
	/**Finds the size of the trie
	 * every node has 2 pointers( 2 * 8 bytes)
	 *                1 char( 1 * 2 bytes )
	 *                1 byte
	 *                total: 19 bytes per node
	 * @return size of trie in bytes */
	public int size(){
		return size * 20;
	}
	
	/**Finds the number of node in the trie.
	 * @param node current node of trie.
	 * @param number of nodes so far
	 * @return number of nodes in trie */
	private int size(NodeDLB node, int count){
		
		if (node == null)
			return count;
		else{
			count = size(node.peerNode, count);
			count = size(node.childNode, count);
			return count + 1;
		}
		
	}

}
