//Dane Zeeb daz33@cornell.edu
//Collab with Alizeh Khan

package proj_2_similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;

public class Similarity {

	/*
	 * Reads the specified file of N lines and returns a list of N names and a list
	 * of N lists of rankings (each of length M). It is assumed that each of the N
	 * rows of the text file starts with a name and is followed by M integers, as
	 * shown below where N=3 and M=4 (the blank lines are added so that Eclipse's
	 * auto-formatter does not convert the example into a paragraph):
	 * 
	 * john 3 0 2 1
	 *
	 * jane 0 1 2 3
	 * 
	 * ann 1 2 3 0
	 */
	public static void readRankings(//
			String path, //
			List<String> names, //
			List<List<Integer>> rankings //
	) throws Exception {
		// Opening the file
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int numOfTokens = -1;
		while (line != null) {
			String[] tokens = line.split(" |,");
			assert tokens.length > 0;
			if (numOfTokens < 0)
				numOfTokens = tokens.length;
			else
				assert numOfTokens == tokens.length;
			names.add(tokens[0]);
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i < tokens.length; i++) {
				int value = Integer.parseInt(tokens[i]);
				list.add(value);
			}
			rankings.add(list);
			line = br.readLine();
		}
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	/*
	 * This mode of the program assumes that the numbering of the movies are
	 * according to the rankings of a person X who is not in the input file. The
	 * goal is to consider all N rows of the input and decide which row is most
	 * consistent with the rankings of X. Stated algorithmically, the goal is the
	 * figure out which row of the N rows of input has the minimum number of
	 * inversions.
	 */
	private static void modeImplicit(List<String> names, List<List<Integer>> rankings) {
		int total = 0;
		for(List<Integer> sublist: rankings) {
			total =+ sublist.size();
			//System.out.println(sublist);
		}
		
		String[] name = new String[names.size()];
		name = names.toArray(name);
		Integer[] ranks = new Integer[total];
		Integer[] invCount = new Integer[rankings.size()];
		int i = 0;
		int lo = 1000000000;

		// Make a counts array to store count values in after sorting.
		// then add count to the array and reset counter
		for (List<Integer> list : rankings) {
			ranks = list.toArray(ranks);
			// System.out.println(Arrays.toString(ranks));
			Merge.inverCount = 0;
			Merge.sort(ranks);
			invCount[i] = Merge.inverCount;
			// System.out.println(Merge.inverCount);
			// System.out.println(Arrays.toString(invCount));
			i++;
		}

		// Make index of inverCounts array correspond to index of name array
		// print name for whichever index(es) hold(s) the smallest value
		for (Integer inver : invCount) {
			if (inver < lo) {
				lo = inver;
			} else
				continue;
		}
		for (int j = 0; j < invCount.length; j++) {
			if (invCount[j] == lo) {
				System.out.println(name[j]);
			} else
				continue;
		}
	}

	/*
	 * This mode of the program assumes that person X is in position 0 (i.e. the
	 * first line of the input file) and that the numbering of the movies is not
	 * based on the rankings of person X.
	 */
	private static void modeFirst(List<String> names, List<List<Integer>> rankings) {
		
		List<Integer> firstRankings = rankings.get(0);
		Map<Integer, Integer> firstRank = new HashMap<>();
		Map<String, List<Integer>> pair = new HashMap();
		Map<String, Integer> invCounts = new HashMap();
		String match = "";
		int rank = 0;
		int lo = 1000000000;
		//puts the first person's rankings in a map to be compared to later
		for (int i = 0; i < firstRankings.size(); i++) {
			firstRank.put(firstRankings.get(i), i);
		}
		//refers others' ranks to the first's ranks and sets them accordingly
		for (List<Integer> others: rankings) {
			for (int i = 0; i < others.size(); i++) {
				rank = others.get(i);
				others.set(i,  firstRank.get(rank));
			}
		}
		//Put pairs of ranks and names together for further processing
		for (int j = 1; j < names.size(); j++) {
			pair.put(names.get(j), rankings.get(j));
		}
		
		//Name + invCount mapped pairs
		for (String name: pair.keySet()) {
			invCounts.put(name, null);
		}
		//run mergesort to get inv count
		for (String set: pair.keySet()) {
			List<Integer> r = pair.get(set);
			Integer[] a = r.toArray(new Integer[r.size()]);
			Merge.inverCount = 0;
			Merge.sort(a);
			invCounts.put(set, Merge.inverCount);
		}
		//Like in implicit, lowest number of inversion = match and print the name
		for (String s : invCounts.keySet()) {
			if (invCounts.get(s) < lo) {
				lo = invCounts.get(s);
				match = s;
			}

		}
		System.out.println(match);
	}

	/*
	 * Given N names and N rankings (both indexed from 0 to N-1), this mode of the
	 * program determines which two people are most consistent with each other.
	 */
	private static void modeAny(List<String> names, List<List<Integer>> rankings) {
		// ...YOUR WORK GOES HERE...
	}

	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			String mode = args[0];
			String fileName = args[1];
			List<String> names = new ArrayList<>();
			List<List<Integer>> rankings = new ArrayList<List<Integer>>();
			readRankings(Defaults.pkg + File.separatorChar + fileName, names, rankings);

			if (mode.equals("implicit")) {
				modeImplicit(names, rankings);
			} else if (mode.equals("first")) {
				modeFirst(names, rankings);
			} else if (mode.equals("any")) {
				modeAny(names, rankings);
			} else {
				System.err.println("We should never see this!");
			}
		}
	}
}
