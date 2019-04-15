import dataExploration.SortHashMap;
import dataExploration.WordCount;
import io.CsvReader;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import keywordExtraction.Interface;
import ngram.*;

public class Analysis{

    public static void main(String[] args) throws Exception {
        /**
         * Read a column of the csv file and save it to a txt
         * */
        //TODO change csv file to tweet.csv
        CsvReader.saveToTxt(CsvReader.readCsvColumn("src/main/resources/sentiment.csv", 5), "src/main/resources/text.txt");

        /* read ['0'] and ['4']
        CsvReader.saveToTxt(CsvReader.filterTweet("src/main/resources/sentiment140.csv","0", 0), "src/main/resources/text0.csv");
        CsvReader.saveToTxt(CsvReader.filterTweet("src/main/resources/sentiment140.csv","4", 0), "src/main/resources/text4.csv");
        // save tweet to text
        CsvReader.saveToTxt(CsvReader.readCsvColumn("src/main/resources/text0.csv", 5), "src/main/resources/text0_tweet.txt");
        CsvReader.saveToTxt(CsvReader.readCsvColumn("src/main/resources/text4.csv", 5), "src/main/resources/text4_tweet.txt");
        */
        /**
         * Read the txt file into a HashMap<String, Integer>
         * */
        Map<String, Integer> wordCount = WordCount.getWordCountMap("src/main/resources/text.txt");

        // save stdout to be reused later
        PrintStream stdout = System.out;

        /**
         * Data Exploration part of the project --
         * 1. sort the map by its values
         * 2. generate two files each containing the top 20
         * hashtags and the top 20 @-mentions
         * */
        // sort the hashMap by its value and save the most frequent 20s
        System.out.println("Saving top @-mentions in top@.txt under output directory");
        System.out.println("Saving top hashtags in topHashtags.txt under output directory");
        System.out.println("Size of the map is " + wordCount.size());
        dataExploration(wordCount);

        //set System.out back to stdout?
        System.setOut(stdout);                   // reset to standard output

        /**
         * Remove stop words
         * */
        /* TODO add these lines back
        System.out.println("---------------------------------------------------------");
        System.out.println("|               Removing stopwords                      |");
        System.out.println("---------------------------------------------------------");
        // remove stopwords
        Preprocessor pp = new Preprocessor("stopwords/stopwords.txt", 0);
        */


        /**
         * No stop words removal for Ngrams
         * */
        FilesLoader.loadFileWithStopwords("text.txt");

        // TODO: should feed in unprocessed txt instead?
        List<String> tokens_withStopwords = new ArrayList<>();
        tokens_withStopwords = FilesLoader.loadOneFile("text_withStopwords.txt");
        //tokens = FilesLoader.loadOneFile("processed.txt");
        // testing file is successfully loaded into a List<String>
        //for(String s : tokens) System.out.println(s);
        //System.out.println("Size of the token list: " + tokens.size());

        List<String> tokens_noStopwords = new ArrayList<>();
        tokens_noStopwords = FilesLoader.loadOneFile("processed.txt");

        /**
         * feed tokens into countNgram
         * TODO: stopwords_removed for 1 and 2 grams, withStopwords for 3 and 4
         * */
        /*
        List<String> onegram = ProcessingAlgs.printNgram(tokens_withStopwords, 1, 5);
        List<String> bigram = ProcessingAlgs.printNgram(tokens_withStopwords, 2, 5);
        List<String> trigram = ProcessingAlgs.printNgram(tokens_withStopwords, 3, 3);
        List<String> fourgram = ProcessingAlgs.printNgram(tokens_withStopwords, 4, 2);
        ProcessingAlgs.saveNgram(onegram, 1);
        ProcessingAlgs.saveNgram(bigram, 2);
        ProcessingAlgs.saveNgram(trigram, 3);
        ProcessingAlgs.saveNgram(fourgram, 4);

        Map<String, Integer> oneMap = WordCount.getWordCountMap("output/1gram.txt");
        Map<String, Integer> twoMap = WordCount.getWordCountMap("output/2gram.txt");
        Map<String, Integer> triMap = WordCount.getWordCountMap("output/3gram.txt");
        Map<String, Integer> fourMap = WordCount.getWordCountMap("output/4gram.txt");

        // TODO revise topN
        frequentNgram(oneMap, 1, 5);
        frequentNgram(twoMap, 2, 5);
        frequentNgram(triMap, 3, 3);
        frequentNgram(fourMap, 4, 2);
        */

        /**
         * POS
         *
         * */
        /* get POS-tagged
        List<String> pos_tokens = new ArrayList<>();
        System.out.printf("size of pos_tokens is %d\n", keywordExtraction.Interface.runPOS("src/main/resources/text.txt").size());
        pos_tokens.addAll(Interface.tokenize("output/pos.txt", " "));
        System.out.printf("size of pos_tokens is %d\n", pos_tokens.size());
        List<String> onegram_noun = keywordExtraction.Interface.filter(ProcessingAlgs.printNgram(pos_tokens, 1, 2), "NN", "output/1gram_noun.txt");

        ProcessingAlgs.saveNgram(onegram_noun, 1);

        */
        /**
         * Textrank
         * */
    }

    private static void dataExploration(Map<String, Integer> wordCount) throws Exception {
        PrintStream fileOut1 = new PrintStream("output/top@.txt");
        System.setOut(fileOut1);
        SortHashMap.getMostFrequentWithFilter(wordCount, 20, "@");
        fileOut1.close();

        PrintStream fileOut2 = new PrintStream("output/topHashtags.txt");
        System.setOut(fileOut2);
        SortHashMap.getMostFrequentWithFilter(wordCount, 20, "#");
        fileOut2.close();
    }

    private static void frequentNgram(Map<String, Integer> wordCount, int ngram, int topN) throws FileNotFoundException {
        String filename = "output/top20_" + ngram + "gram.txt";
        PrintStream fileOut = new PrintStream(filename);
        System.setOut(fileOut);
        SortHashMap.getMostFrequent(wordCount, topN);
        fileOut.close();
    }
}