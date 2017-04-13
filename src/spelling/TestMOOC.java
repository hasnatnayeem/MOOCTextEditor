package spelling;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class TestMOOC {
	public static AutoCompleteDictionaryTrie emptyDict;
	static AutoCompleteDictionaryTrie smallDict;
	AutoCompleteDictionaryTrie largeDict;

	public static void main(String[] args) {
		emptyDict = new AutoCompleteDictionaryTrie();
		smallDict = new AutoCompleteDictionaryTrie();

		
		smallDict.addWord("he");
		smallDict.addWord("h");
		smallDict.addWord("hello");
		List<String> completions;
		completions = smallDict.predictCompletions("", 0);
		System.out.println(completions.toString());
		

	}

}
