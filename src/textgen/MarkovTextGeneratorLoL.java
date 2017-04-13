package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.OutputDeviceAssigned;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	public static int initialized;
	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
		initialized = 0;
	}


	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		initialized++;
		if (initialized < 2)
		{

		// TODO: Implement this method
		String[] tokens = sourceText.split("[\\s]+");
		List<String> myList = new LinkedList<String>();
		for (String s : tokens)
		{
			myList.add(s);
		}

		String prevWord;
		starter = myList.get(0);
		prevWord = starter;
		String w = "";
		for (int i = 1; i < myList.size(); i++)
		{
			w = myList.get(i);

			//System.out.print("Prev = " + prevWord +"\n");
			if (isThere(prevWord) < 0)
			{
				ListNode n = new ListNode(prevWord);
				n.addNextWord(w);
				wordList.add(n);
			}

			else
			{
				wordList.get(isThere(prevWord)).addNextWord(w);
			}
			prevWord = w;
		}

		if (isThere(w) < 0)
		{
			ListNode n = new ListNode(w);
			wordList.add(n);
		}
		wordList.get(wordList.size() - 1).addNextWord(starter);
		//System.out.println("Size = " + wordList.size() + "\n" + wordList.toString());
		}
	}

	int isThere (String prevWord){
		int j = 0;
		for (j = 0; j < wordList.size(); j++)
		{
			if (prevWord.equals(wordList.get(j).getWord()))
			{
				return j;
			}
		}
		return -1;
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String output = "";

		if (numWords == 0)
		{
			return "";
		}


		else if (initialized > 0)
		{

		String currWord = starter;
		output += currWord;

		numWords--;
		int i = 1;
		while (i <= numWords)
		{
			//wordList.get(isThere(currWord)).getRandomNextWord(rnGenerator);
			//wordList.get(isThere(currWord)).getNextWordListSize();

			ListNode n = wordList.get(isThere(currWord));
			//int m = rnGenerator.nextInt(n.getNextWordListSize());

			String w = n.getRandomNextWord(rnGenerator);
			output += " " + w;
			//System.out.print(" " + w);
			currWord = w;
			i++;
		}

		}

		return output;
	}


	public void p(String s) {
		System.out.println(s);
	}

	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		initialized = 0;
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.


	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";

		//String textString = "hi there hi Leo";
        gen.train(input);
        gen.generateText(224);

		/*System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));*/
	}

}

/** Links a word to the next words in the list
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public int getNextWordListSize() {
		return nextWords.size();
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
		//System.out.println(generator.nextInt(getNextWordListSize()));
		return nextWords.get(generator.nextInt(getNextWordListSize()));

	    //return null;
	}

	public String getNextWord(int index)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
	    return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}


