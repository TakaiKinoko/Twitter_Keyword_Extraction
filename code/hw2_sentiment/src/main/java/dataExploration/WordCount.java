package dataExploration;

import java.util.*;
import java.io.*;

public class WordCount {
    public static Map<String, Integer> getWordCountMap(String src) throws FileNotFoundException {
        // open the file
        // TODO check
        //Scanner console = new Scanner(System.in);
        //System.out.print("What is the name of the text file? ");
        //String fileName = console.nextLine();
        //Scanner input = new Scanner(new File(fileName));
        Scanner input = new Scanner(new File(src));

        // count occurrences
        //TreeMap<String, Integer> wordCounts = new TreeMap<String, Integer>();
        Map<String, Integer> wordCounts = new HashMap<String, Integer>();
        while (input.hasNextLine()) {
            String next = input.nextLine().toLowerCase();
            if (!wordCounts.containsKey(next)) {
                wordCounts.put(next, 1);
            } else {
                wordCounts.put(next, wordCounts.get(next) + 1);
            }
        }

        // get cutoff and report frequencies
        System.out.println("Total words = " + wordCounts.size());
        //System.out.print("Minimum number of occurrences for printing? ");
        /*
        int min = console.nextInt();
        for (String word : wordCounts.keySet()) {
            int count = wordCounts.get(word);
            if (count >= min) {
                System.out.println(count + "\t" + word);
            }
        }
        */

        return wordCounts;
    }
}