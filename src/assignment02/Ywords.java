package assignment02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

public class Ywords {
	public static Set<String> hasYvowel = new TreeSet<>();
	public static Set<String> hasYconsonant = new TreeSet<>();
	static {
		try {
			String vowelWords = Files.readString(Paths.get("yvowel.txt"));
			vowelWords = vowelWords.toLowerCase();
			String[] split = vowelWords.split("\\s+");
			for(String str : split) hasYvowel.add(str); 
			System.out.println("Loading Y vowel words: " + hasYvowel);

			String consonantWords = Files.readString(Paths.get("yconsonant.txt"));
			consonantWords = consonantWords.toLowerCase();
			split = consonantWords.split("\\s+");
			for(String str : split) hasYconsonant.add(str); 
			System.out.println("Loading Y consonant words: " + hasYconsonant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
