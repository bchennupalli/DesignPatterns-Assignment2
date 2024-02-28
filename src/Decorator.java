import java.util.*;

public abstract class Decorator extends Component {
	private Component delegate;

	public Decorator(Component delegateIn) {
		delegate = delegateIn;
	}

	public abstract Set<Character> getConsonantsInWord();
	public abstract Set<Character> getVowelsInWord();

	@Override
	public Set<Character> getConsonants() {
		// TODO Auto-generated method stub


		Set<Character> result = new HashSet<>(delegate.getConsonants());
		result.addAll(getConsonantsInWord());
		//result.remove("");

		return new TreeSet<>(result);

		//return null;
	}

	private static Set<Set<Character>> generateCombinationsRecursive(Set<Set<Character>> remainingSets, Set<Character> currentCombination) {
		if (remainingSets.isEmpty()) {
			// If no more sets to process, return the current combination as a new set of sets
			Set<Set<Character>> baseCaseSet = new HashSet<>();
			baseCaseSet.add(new HashSet<>(currentCombination));
			return baseCaseSet;
		} else {
			Set<Set<Character>> result = new HashSet<>();
			// Separate the first set from the remaining ones
			Set<Character> firstSet = remainingSets.iterator().next();
			Set<Set<Character>> newRemaining = new HashSet<>(remainingSets);
			newRemaining.remove(firstSet);

			// Combine each element of the first set with the combinations from the recursive call
			for (Character c : firstSet) {
				Set<Character> newCombination = new HashSet<>(currentCombination);
				newCombination.add(c);
				result.addAll(generateCombinationsRecursive(newRemaining, newCombination));
			}
			return result;
		}
	}

	public static Set<Set<Character>> generateCombinations(Set<Set<Character>> inputSets) {
		// Call the recursive function with an initial empty accumulator set
		return generateCombinationsRecursive(inputSets, new HashSet<>());
	}

	private static Set<Set<Character>> getCombinationSetHelper(List<Set<Character>> vowelSets, int index, Set<Character> current) {
		Set<Set<Character>> combinations = new HashSet<>();
		if (index == vowelSets.size()) {
			combinations.add(new HashSet<>(current));
			return combinations;
		}

		combinations.addAll(getCombinationSetHelper(vowelSets, index + 1, new HashSet<>(current)));

		for (Character ch : vowelSets.get(index)) {
			if (!current.contains(ch)) {
				current.add(ch);
				combinations.addAll(getCombinationSetHelper(vowelSets, index + 1, current));
				current.remove(ch);
			}
		}

		return combinations;
	}

	public static Set<Set<Character>> getCombinationSet(Set<Set<Character>> vowelSet) {
		List<Set<Character>> vowelSetsList = new ArrayList<>(vowelSet);
		Set<Set<Character>> combinations = getCombinationSetHelper(vowelSetsList, 0, new HashSet<>());
		int maxSubsetLength = combinations.stream().mapToInt(Set::size).max().orElse(0);
		Set<Set<Character>> finalSet = new HashSet<>();
		for(Set<Character> set: combinations){
			if(set.size() == maxSubsetLength){
				finalSet.add(new TreeSet<>(set));
			}
		}
		return finalSet;
	}

	public static Set<Set<Character>> sortSetOfSet(Set<Set<Character>> vowelSets) {
		// Initialize the outer set with LinkedHashSet to maintain insertion order
		Set<Set<Character>> sortedSets = new LinkedHashSet<>();

		// Iterate through each subset
		for (Set<Character> subset : vowelSets) {
			// Sort the subset by adding its elements to a TreeSet
			Set<Character> sortedSubset = new TreeSet<>(subset);
			// Add the sorted subset to the outer set
			sortedSets.add(sortedSubset);
		}

		return sortedSets;
	}

	public boolean containsAllVowels(Set<Set<Character>> vowelSet, String vowel){
		return false;
	}

	@Override
	public Set<Set<Character>> getVowels() {
		// TODO Auto-generated method stub


		Set<Set<Character>> result = new HashSet<>(delegate.getVowels());
		Set<Character> vowelsInWord = getVowelsInWord();

		result.add(vowelsInWord);
		result.removeIf(Set::isEmpty);

		Set<Set<Character>> vowelSets = Word.getAllVowelSets();



		//System.out.println("At Decorator: "+ vowelSets + "\nCombinations"+ new Combine().getCombinationSet(vowelSets));
		//System.out.println(vowelSets);
		//return sortSetOfSet(new Combine().getCombinationSet(vowelSets));
		return getCombinationSet(vowelSets);
	}

	@Override
	public void print() {
		delegate.print();
	}}

