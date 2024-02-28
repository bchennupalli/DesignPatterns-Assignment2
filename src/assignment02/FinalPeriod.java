package assignment02;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FinalPeriod extends Component {

	@Override
	public Set<Character> getConsonants() {
		return new TreeSet<>();
	}

	@Override
	public Set<Set<Character>> getVowels() {
		var retVal = new HashSet<Set<Character>>();
		retVal.add(new TreeSet<>());
		return retVal;
	}

}
