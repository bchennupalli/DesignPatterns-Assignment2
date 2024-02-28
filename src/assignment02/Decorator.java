package assignment02;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Decorator extends Component {
	private Component delegate;

	public Decorator(Component delegateIn) {
		this.delegate = delegateIn;
	}

	public abstract Set<Character> getConsonantsInWord();
	public abstract Set<Character> getVowelsInWord();

	@Override
	public Set<Character> getConsonants() {
		return Stream.concat(delegate.getConsonants().stream(), getConsonantsInWord().stream())
				.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public Set<Set<Character>> getVowels() {
		Set<Set<Character>> existingCombinations = delegate.getVowels();
		Set<Character> vowelsInCurrentWord = getVowelsInWord();

		if (!vowelsInCurrentWord.isEmpty() && existingCombinations.isEmpty()) {
			return vowelsInCurrentWord.stream()
					.map(vowel -> {
						Set<Character> singleVowelSet = new TreeSet<>();
						singleVowelSet.add(vowel);
						return singleVowelSet;
					})
					.collect(Collectors.toSet());
		}

		Set<Set<Character>> newCombinations = existingCombinations.stream()
				.flatMap(existingSet -> vowelsInCurrentWord.stream()
						.map(vowel -> {
							Set<Character> newSet = new TreeSet<>(existingSet);
							newSet.add(vowel);
							return newSet;
						}))
				.collect(Collectors.toCollection(HashSet::new));

		return eliminateSmallerSets(newCombinations);
	}

	@Override
	public void print() {
		delegate.print();
	}

	private int getMaxSetSize(Set<Set<Character>> sets){
		return sets.stream()
				.mapToInt(Set::size)
				.max()
				.orElse(0);
	}

	private Set<Set<Character>> filterSetsBySize(Set<Set<Character>> combinations, int maxSize){
		return combinations.stream()
				.filter(set -> set.size() == maxSize)
				.collect(Collectors.toSet());
	}

	private Set<Set<Character>> eliminateSmallerSets(Set<Set<Character>> combinations) {
		int maxSize = getMaxSetSize(combinations);
		return filterSetsBySize(combinations, maxSize);
	}
}
