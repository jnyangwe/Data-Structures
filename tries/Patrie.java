

/**@author Joseph Nyangwechi
 * Inteface class that defines a contract that all trie and Patricia trie classes must implement*/
public interface Patrie {

	/**Insert key into trie
	 * @param key string to be inserted*/
	public void insert(String key);
	
	/**Delete key from the string
	 * @param key string to be deleted*/
	public void delete(String key);
	
	/**Search for key in trie
	 *@param key string we are searching for
	 *@return true if key is found in trie*/
	public boolean search(String key);
	
	/**Finds the size of the trie
	 * @return size of trie in bytes */
	public int size();
	
	
	
}
