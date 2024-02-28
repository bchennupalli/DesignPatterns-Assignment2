package assignment02;

import java.util.Set;

public abstract class Component {
	public abstract Set<Character> getConsonants();
	public abstract Set<Set<Character>> getVowels();
	public void print() {
		// do nothing
	}
}
