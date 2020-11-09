import java.util.*;

public class HangmanManager
{
	List<String> words;
	int len, max;
	String ans;
	Set<Character> alpha = new HashSet<Character>();
	public HangmanManager( List<String> dictionary, int length, int max )
	{
		if (length<1 || max<0)  throw new IllegalArgumentException("length or max is invalid");
		words = new ArrayList<String>();
		for (int i = 0; i<dictionary.size(); i++) {
			String s = dictionary.get(i);
			if (s.length() == length) words.add(s);
		}
		len = length;
		this.max = max;
		ans = "";
		for (int i = 0; i<len; i++) {
			ans+='-';
		}
	}
	
	public Set<String> words()
	{
		Set<String> set = new HashSet<String>();
		for (int i =0 ; i<words.size(); i++) {
			set.add(words.get(i));
		}
		return set;
	}	
	
	public int guessesLeft()
	{
		return max;
	}
		
	public Set<Character> guesses()
	{
		return alpha;
	}
	
	public String pattern()
	{
		return ans;
	}
	
	public int record( char guess )
	{
		if (max<=0 || words.isEmpty()) throw new IllegalArgumentException("don't have enough guesses left or no word satisfy this");
		if (alpha.contains(guess)) throw new IllegalArgumentException("letter guessed previously");
		alpha.add(guess);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String word: words) {
			String num = "";
			for (int i =0 ;i<word.length(); i++) {
				if (word.charAt(i) == guess) num+=guess;
				else num+=ans.charAt(i);
			}
			List<String> add = new ArrayList<String>();
			if (map.containsKey(num)) {
				map.get(num).add(word);
				add = map.get(num);
			}
			else {
				add.add(word);
				
			}
			map.put(num, add);
		}
		if (map.size()>0) {
			int num = 0;
			for (String s: map.keySet()) {
				words = map.get(s);
				ans = s;
				for (int i = 0; i<s.length(); i++) {
					if (s.charAt(i) == guess) num++;
				}
				break;
			}
			return num;
		}
		else {
			max = max-1;
			return 0;
		}
	}
}