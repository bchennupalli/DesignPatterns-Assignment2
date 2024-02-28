
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public abstract class Decorator extends Component {
	private Component delegate;

	public Decorator(Component delegateIn) {
		this.delegate = delegateIn;
	}

	public abstract Set<Character> getConsonantsInWord();
	public abstract Set<Character> getVowelsInWord();

	@Override
	public Set<Character> getConsonants() {
		Set<Character> delegateConsonants = delegate.getConsonants();
		Set<Character> consonantFinalSet = new TreeSet<>(delegateConsonants);
		Set<Character> consonantsInWord = getConsonantsInWord();
		consonantFinalSet.addAll(consonantsInWord);
		return consonantFinalSet;
	}

	@Override
	public Set<Set<Character>> getVowels() {
		Set<Set<Character>> existingCombinations = delegate.getVowels();
		Set<Character> vowelsInCurrentWord = getVowelsInWord();

		if (!vowelsInCurrentWord.isEmpty() && existingCombinations.isEmpty()) {
			existingCombinations = new HashSet<>();
			for (Character vowel : vowelsInCurrentWord) {
				Set<Character> singleVowelSet = new TreeSet<>();
				singleVowelSet.add(vowel);
				existingCombinations.add(singleVowelSet);
			}
			return existingCombinations;
		}

		Set<Set<Character>> newCombinations = new HashSet<>();
		for (Set<Character> existingSet : existingCombinations) {
			for (Character vowel : vowelsInCurrentWord) {
				Set<Character> newSet = new TreeSet<>(existingSet);
				newSet.add(vowel);
				newCombinations.add(newSet);
			}
		}

		return eliminateSmallerSets(newCombinations);
	}

	private int getMaxSetSize(Set<Set<Character>> sets){
		return sets.stream().mapToInt(Set::size).max().orElse(0);
	}

	private Set<Set<Character>> filterSetsBySize(Set<Set<Character>> combinations, int maxSize){
		Set<Set<Character>> newCombinationsSets = new HashSet<>();
		for(Set<Character> set: combinations){
			if(set.size() == maxSize)
				newCombinationsSets.add(set);
		}
		return newCombinationsSets;
	}

	private Set<Set<Character>> eliminateSmallerSets(Set<Set<Character>> combinations) {
		//Set<Set<Character>> result = new HashSet<>();
		//int maxSize = combinations.stream().mapToInt(Set::size).max().orElse(0);
		int maxSize = getMaxSetSize(combinations);

		/*for (Set<Character> set : combinations) {
			if (set.size() == maxSize) {
				result.add(set);
			}
		}*/

		return filterSetsBySize(combinations, maxSize);
	}


	@Override
	public void print() {
		delegate.print();
	}
}
