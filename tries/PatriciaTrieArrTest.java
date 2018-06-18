import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatriciaTrieArrTest {

	//patricia trie
	PatriciaTrieArr dict1;
	PatriciaTrieArr dict2;
	PatriciaTrieArr dict3;
	PatriciaTrieArr dict4;
	
	
	
	@Before
	public void setUp() throws Exception {
		dict1 = new PatriciaTrieArr();
		dict2 = new PatriciaTrieArr();
		dict3 = new PatriciaTrieArr();
		dict4 = new PatriciaTrieArr();
		
		
	}

	@After
	public void tearDown() throws Exception {
		dict1 = null;
		dict2 = null;
		dict3 = null;
		dict4 = null;
		
	}

	@Test
	public void testInsertPA() {
		dict1.insert("romane");
		assertEquals(dict1.search("romane") ,true);
		
		dict1.insert("romanus");
		assertEquals(dict1.search("roman") ,false);
		assertEquals(dict1.search("romane") ,true);
		
		dict1.insert("romulus");
		assertEquals(dict1.search("romulus") ,true);
		assertEquals(dict1.search("roman") ,false);
		
		dict1.insert("rubens");
		dict1.insert("ruber");
		dict1.insert("rubicon");
		dict1.insert("rubicundus");
		
		//Check if all the strings inserted are in the Patricia Trie
		assertEquals(dict1.search("romulus") ,true);
		assertEquals(dict1.search("romane") ,true);
		assertEquals(dict1.search("rubens") ,true);
		assertEquals(dict1.search("rubicon") ,true);
		assertEquals(dict1.search("rubicundus") ,true);
		assertEquals(dict1.search("romulus") ,true);
		assertEquals(dict1.search("romanus") ,true);
		
		//test for strings that shouldn't be on the list
		assertEquals(dict1.search("r") ,false);
		assertEquals(dict1.search("om") ,false);
		assertEquals(dict1.search("e") ,false);
		assertEquals(dict1.search("rubic") ,false);
		assertEquals(dict1.search("rub") ,false);
		assertEquals(dict1.search("rom") ,false);
		
		dict1.insert("r");
		dict1.insert("roman");
		
		assertEquals(dict1.search("r") ,true);
		assertEquals(dict1.search("roman") ,true);
	}
	
	@Test
	public void testInsert2PA(){
		dict2.insert("98456");
		dict2.insert("984345");
		dict2.insert("9123");
		dict2.insert("9199");
		dict2.insert("9647");
		dict2.insert("919");
		
		//test for all the strings that are in Patricia Trie
		assertEquals(dict2.search("98456") ,true);
		assertEquals(dict2.search("984345") ,true);
		assertEquals(dict2.search("9123") ,true);
		assertEquals(dict2.search("9199") ,true);
		assertEquals(dict2.search("9647") ,true);
		assertEquals(dict2.search("919") ,true);
		
		//test for strings not in the structure
		assertEquals(dict2.search("984567") ,false);
		assertEquals(dict2.search("984341") ,false);
		assertEquals(dict2.search("91") ,false);
		assertEquals(dict2.search("9") ,false);
		assertEquals(dict2.search("966") ,false);
		assertEquals(dict2.search("912") ,false);
	
	}
	
	@Test
	public void testInsert3PA(){
		dict3.insert("romane");
		dict3.insert("romanus");
		
		dict3.insert("984345");
		dict3.insert("9123");
		
		dict3.insert("romulus");
		dict3.insert("rubens");
		
		dict3.insert("98456");
		
		dict3.insert("ruber");
		dict3.insert("rubicon");
		dict3.insert("rubicundus");

		dict3.insert("9199");
		dict3.insert("9647");
		dict3.insert("919");
		
		//test for all the numbers that are in Patricia Trie
		assertEquals(dict3.search("98456") ,true);
		assertEquals(dict3.search("984345") ,true);
		assertEquals(dict3.search("9123") ,true);
		assertEquals(dict3.search("9199") ,true);
		assertEquals(dict3.search("9647") ,true);
		assertEquals(dict3.search("919") ,true);
																
		//test for numbers not in the structure
		assertEquals(dict3.search("984567") ,false);
		assertEquals(dict3.search("984341") ,false);
		assertEquals(dict3.search("91") ,false);
		assertEquals(dict3.search("9") ,false);
		assertEquals(dict3.search("966") ,false);
		assertEquals(dict3.search("912") ,false);
		
		//Check if all the strings inserted are in the Patricia Trie
		assertEquals(dict3.search("romulus") ,true);
		assertEquals(dict3.search("romane") ,true);
		assertEquals(dict3.search("rubens") ,true);
		assertEquals(dict3.search("rubicon") ,true);
		assertEquals(dict3.search("rubicundus") ,true);
		assertEquals(dict3.search("romulus") ,true);
		assertEquals(dict3.search("romanus") ,true);
		
		//test for strings that shouldn't be on the list
		assertEquals(dict3.search("r") ,false);
		assertEquals(dict3.search("om") ,false);
		assertEquals(dict3.search("e") ,false);
		assertEquals(dict3.search("rubic") ,false);
		assertEquals(dict3.search("rub") ,false);
		assertEquals(dict3.search("rom") ,false);
	}
	
	@Test
	public void testDelete1PA(){
		dict1.insert("romane");
		dict1.insert("romanus");
		dict1.insert("romulus");		
		dict1.insert("rubens");
		dict1.insert("ruber");
		dict1.insert("rubicon");
		dict1.insert("rubicundus");
		dict1.insert("r");
		dict1.insert("roman");
		dict1.insert("");
		
		assertEquals(dict1.search("") ,false);
		dict1.delete("");
		assertEquals(dict1.search("") ,false);
		
		assertEquals(dict1.search("r") ,true);
		dict1.delete("r");
		assertEquals(dict1.search("r") ,false);
		

		assertEquals(dict1.search("romulus") ,true);
		dict1.delete("romulus");
		assertEquals(dict1.search("romulus") ,false);
	
		assertEquals(dict1.search("rubicon") ,true);
		dict1.delete("rubicon");
		assertEquals(dict1.search("rubicon") ,false);
		assertEquals(dict1.search("rubic") ,false);
		
		assertEquals(dict1.search("rubicundus") ,true);
		dict1.delete("rubicundus");
		assertEquals(dict1.search("rubicundus") ,false);
		assertEquals(dict1.search("rube") ,false);
		
		
		dict1.delete("rub");
		assertEquals(dict1.search("rub") ,false);

		dict1.delete("ruber");
		dict1.delete("rubens");
		dict1.delete("romane");
		dict1.delete("romanus");
		dict1.delete("roman");
		assertEquals(dict1.search("ruber") ,false);
		assertEquals(dict1.search("rubens") ,false);
		assertEquals(dict1.search("romane") ,false);
		assertEquals(dict1.search("romanus") ,false);
		assertEquals(dict1.search("ruber") ,false);
		
		
	}

	@Test
	public void testDeletePA(){
		dict2.insert("98456");
		dict2.insert("984345");
		dict2.insert("9123");
		dict2.insert("9199");
		dict2.insert("9647");
		dict2.insert("919");
		dict2.insert("919");
		dict2.insert("919");
		dict2.insert("919");
		dict2.insert("919");
		
		dict2.delete("98456");
		dict2.delete("9123");
		assertEquals(dict2.search("98456") ,false);
		assertEquals(dict2.search("984345") ,true);
		assertEquals(dict2.search("91") ,false);
		
		dict2.delete("984345");
		dict2.delete("9199");
		assertEquals(dict2.search("919") ,true);
		dict2.delete("919");
		
		
		dict2.delete("9647");
		assertEquals(dict2.search("9647") ,false);
		
	}
}
