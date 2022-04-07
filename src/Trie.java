package src;


class TrieNode {
    static final int ALPHABET_SIZE = 26;
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
	
		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;
		
		TrieNode(){
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	};

public class Trie {
	
	// Alphabet size (# of symbols)

	TrieNode root;

  Trie () {
    root = new TrieNode();
  }
	
	void insert(String key) {
		int level;
		int length = key.length();
		int index;
	
		TrieNode pCrawl = root;
	
		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();
	
			pCrawl = pCrawl.children[index];
		}
	
		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}
	
	// Returns true if key presents in trie, else false
	boolean search(String key) {
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;
	
		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
	
			if (pCrawl.children[index] == null)
				return false;
	
			pCrawl = pCrawl.children[index];
		}
	
		return (pCrawl.isEndOfWord);
	}

  int findPrefix(String key) {
    int level;
    int length = key.length();
    int index;
    TrieNode pCrawl = root;
    for (level = 0; level < length; level ++)  {
      index = key.charAt(level) - 'a';
      if (pCrawl.children[index] == null) {
        return -1;
      }
      if (pCrawl.children[index].isEndOfWord == true) {
        return level;
      }
      pCrawl = pCrawl.children[index];
    }
    return -1;
  }
}