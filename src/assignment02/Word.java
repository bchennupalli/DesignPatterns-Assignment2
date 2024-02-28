package assignment02;

import java.util.HashSet;
import java.util.*;

public class Word extends Decorator {
	private String thisWord;

	public Word(Component delegate, String str) {
		super(delegate);
		thisWord = str;
	}

	@Override
	public Set<Character> getConsonantsInWord() {
		Set<Character> consonants = new HashSet<>();
		String consonantString = "bcdfghjklmnpqrstvwxz";
		char[] consonantArray = thisWord.toCharArray();
		for (char ch : consonantArray) {
			if (consonantString.indexOf(ch) != -1) {
				consonants.add(ch);
			}
		}
		if(thisWord.contains("y")) {
			if (Ywords.hasYconsonant.contains(thisWord)) {
				consonants.add('y');
			}
		}
		return consonants;
	}

	@Override
	public Set<Character> getVowelsInWord() {
		Set<Character> vowels = new HashSet<>();
		String vowelString = "aeiou";
		char[] vowelArray = thisWord.toCharArray();
		for (char ch : vowelArray) {
			if (vowelString.indexOf(ch) != -1) {
				vowels.add(ch);
			}
		}
		if(thisWord.contains("y")) {
			if (Ywords.hasYvowel.contains(thisWord)) {
				vowels.add('y');
			}
		}
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
