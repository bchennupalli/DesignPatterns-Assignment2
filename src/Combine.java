import java.util.*;

public class Combine {

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

    /*public static void main(String[] args) {
        Set<Set<Character>> vowelSet = new HashSet<>(Arrays.asList(
                new HashSet<>(Arrays.asList('u', 'e', 'i')),
                new HashSet<>(Arrays.asList('a', 'i', 'y')),
                new HashSet<>(Arrays.asList('a', 'u', 'o')),
                new HashSet<>(Arrays.asList('a', 'e')),
                new HashSet<>(Arrays.asList('e', 'i', 'y')),
                new HashSet<>(Arrays.asList('u', 'e', 'o')),
                new HashSet<>(Arrays.asList('a', 'i')),
                new HashSet<>(Arrays.asList('e', 'i')),
                new HashSet<>(Arrays.asList('a', 'o')),
                new HashSet<>(Arrays.asList('a', 'u', 'e', 'i', 'o')),
                new HashSet<>(Arrays.asList('u', 'e', 'y')),
                new HashSet<>(Arrays.asList('e', 'o')),
                new HashSet<>(Arrays.asList('i', 'o')),
                new HashSet<>(Arrays.asList('e', 'u')),
                new HashSet<>(Arrays.asList('a', 'y')),
                new HashSet<>(Arrays.asList('u', 'i')),
                new HashSet<>(Arrays.asList('a', 'e', 'i', 'o')),
                new HashSet<>(Arrays.asList('e', 'y')),
                new HashSet<>(Arrays.asList('a')),
                new HashSet<>(Arrays.asList('a', 'u', 'e', 'i')),
                new HashSet<>(Arrays.asList('u', 'o')),
                new HashSet<>(Arrays.asList('e')),
                new HashSet<>(Arrays.asList('a', 'e', 'i', 'y')),
                new HashSet<>(Arrays.asList('i')),
                new HashSet<>(Arrays.asList('a', 'u', 'e', 'o')),
                new HashSet<>(Arrays.asList('o')),
                new HashSet<>(Arrays.asList('a', 'e', 'i')),
                new HashSet<>(Arrays.asList('a', 'e', 'o')),
                new HashSet<>(Arrays.asList('u')),
                new HashSet<>(Arrays.asList('y')),
                new HashSet<>(Arrays.asList('a', 'i', 'o')),
                new HashSet<>(Arrays.asList('a', 'e', 'u')),
                new HashSet<>(Arrays.asList('u', 'e', 'i', 'y')),
                new HashSet<>(Arrays.asList('e', 'i', 'o')),
                new HashSet<>(Arrays.asList('a', 'u', 'i'))
        ));

        Set<Set<Character>> combinations = getCombinationSet(vowelSet);

        System.out.println("Unique Combinations:");
        for (Set<Character> combination : combinations) {
            System.out.println(combination);
        }
    }*/
}
