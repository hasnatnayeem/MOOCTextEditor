package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}


	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		TrieNode node = root;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); i++) {
	           Character character = word.charAt(i);
	           node.insert(character);
	            node = node.getChild(character);
	    }

		if (node.endsWord())
			return false;

		size++;

		node.setEndsWord(true);
	    return true;
	}

	/**
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}


	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s)
	{
	    // TODO: Implement this method
		s = s.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < s.length(); i++) {
	            Character character = s.charAt(i);
	            if (!node.getValidNextCharacters().contains(character))
	            	return false;

	            node = node.getChild(character);

	            if (node.endsWord() && i == s.length() - 1)
	            	return true;
	    }
        return false;
	}

	/**
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions

    	 List<String> myList = new ArrayList<>();
    	 prefix = prefix.toLowerCase();
    	 TrieNode node = root;
    	 Queue<TrieNode> myQueue = new LinkedList<>();


    	 if (!prefix.isEmpty())
    	 {
	    	 for (int i = 0; i < prefix.length(); i++) {
		            Character character = prefix.charAt(i);
		            if (!node.getValidNextCharacters().contains(character))
		            	return myList;

		            node = node.getChild(character);
		     }
    	 }

         myQueue.add(node);
         int counter = 0;
         while (!myQueue.isEmpty() && counter < numCompletions)
         {
        	 node = myQueue.remove();
        	 if (node.endsWord())
        	 {
        		 myList.add(node.getText());
        		 counter++;
        	 }

        	 for (Character c : node.getValidNextCharacters())
    		 {
    			 myQueue.add(node.getChild(c));
    		 }
         }
         return myList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}

 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null)
 			return;

 		System.out.println(curr.getText());

 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}



}