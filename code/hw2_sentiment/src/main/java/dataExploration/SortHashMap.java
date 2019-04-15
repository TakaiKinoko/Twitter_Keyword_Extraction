package dataExploration;

//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
import java.util.*;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

/*
 * Java Program to sort a Map by values in Java 8
 *
 */
public class SortHashMap {

    public static void getMostFrequent(Map<String, Integer> wordCount, int topN) {

        // now let's sort the map in decreasing order of value
        Map<String, Integer> sorted = wordCount
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        //System.out.println("map after sorting by values in descending order: " + sorted);

        int i = 0;

        System.out.println("\nPrinting the top " + topN + " words:");

        for(Map.Entry<String, Integer> entry : sorted.entrySet()){
            if (entry.getKey().length() > 1 && i < topN) {
                // TODO: print to file
                System.out.println(entry.getKey() + " : " + entry.getValue().toString());
                i++;
            }
        }
    }

    public static void getMostFrequentWithFilter(Map<String, Integer> wordCount, int topN, String filter) {

        // now let's sort the map in decreasing order of value
        Map<String, Integer> sorted = wordCount
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        //System.out.println("map after sorting by values in descending order: " + sorted);

        topNword(sorted, filter, topN);
    }


    public static void topNword(Map<String, Integer> wordCount, String filter, int topN){
        int i = 0;
        Iterator it = wordCount.entrySet().iterator();

        System.out.println("\nPrinting the top " + topN + " words starting with " + filter + "\n");

        while (it.hasNext() && i < topN) {
            Map.Entry<String, Integer> pair = (Map.Entry)it.next();
            if(pair.getKey().startsWith(filter, 0)){
                System.out.println(pair.getKey() + " : " + pair.getValue());
                i++;
            }
            it.remove(); // avoids ConcurrentModificationException
        }

        /*
        int i = 0;

        System.out.println("\nPrinting the top " + topN + " words starting with " + filter + "\n");

        System.out.println("TESTING: size " + wordCount.entrySet().size());
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()){
            if (entry.getKey().startsWith(filter) && i < topN) {
                // TODO: print to file
                System.out.println(entry.getKey() + " : " + entry.getValue().toString());
                i++;
            }
        }
        */

    }

}