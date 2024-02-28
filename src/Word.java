import java.util.HashSet;
import java.util.Set;

public class Word extends Decorator {
	private String thisWord;

	public static Set<Set<Character>> getAllVowelSets() {
		return allVowelSets;
	}

	public static void setAllVowelSets(Set<Set<Character>> allVowelSets) {
		Word.allVowelSets = allVowelSets;
	}

	public static Set<Set<Character>> allVowelSets = new HashSet<>();



	public Word(Component delegate, String str) {
		super(delegate);
		thisWord = str;
	}

	@Override
	public Set<Character> getConsonantsInWord() {
		Set<Character> consonants = new HashSet<>();
		String consonantString = "bcdfghjklmnpqrstvwxz";
		char[] consonantArray = thisWord.toLowerCase().toCharArray();

		for (char ch : consonantArray) {
			if (consonantString.indexOf(ch) != -1) {
				consonants.add(ch);
			}
		}

		if (thisWord.toLowerCase().contains("y")) {
			if (Ywords.hasYconsonant.contains(thisWord.toLowerCase())) {
				consonants.add('y');
			}
		}


		return consonants;
	}

	@Override
	public Set<Character> getVowelsInWord() {
		Set<Character> vowels = new HashSet<>();
		String vowelString = "aeiou";
		char[] vowelArray = thisWord.toLowerCase().toCharArray();

		for (char ch : vowelArray) {
			if (vowelString.indexOf(ch) != -1) {
				vowels.add(ch);
			}
		}

		if (thisWord.toLowerCase().contains("y")) {
			if (Ywords.hasYvowel.contains(thisWord.toLowerCase())) {
				vowels.add('y');
			}
		}

		allVowelSets.add(vowels);

		//System.out.println("At word: "+allVowelSets);

		return vowels;
	}

	@Override
	public String toString() {
		return thisWord;
	}

	@Override
	public void print() {
		super.print();
		System.out.print("->" + this);
	}
}
