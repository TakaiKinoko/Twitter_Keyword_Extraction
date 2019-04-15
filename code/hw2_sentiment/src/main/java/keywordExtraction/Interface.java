package keywordExtraction;

import java.io.*;
import java.util.*;

public class Interface {

    /**
     * take in a raw file, return pos tagged string list
     * */
    public static List<String> runPOS(String file) throws IOException {
        tagText(readRawText(file));
        // filter out nouns
        return tokenize("output/pos.txt", " ");
    }

    public  static List<String> filter(List<String> tokens, String pattern, String file) throws IOException{
        List<String> filtered = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for(String s: tokens) {
            if(s.contains(pattern)){
                filtered.add(s);
                writer.write(s + "\n");
            }
        }
        return filtered;
    }
    /**
     * @param file that contains the pos tagged string
     * @return
     * */
    public static List<String> tokenize (String file, String pattern) {
        //List<String> filtered = new ArrayList<>();
        // save filtered nounlist to output/pos_nouns.txt
        //BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        List<String> tok_lst = new ArrayList<>();

        String text = readRawText(file);
        String[] tokens = text.split(pattern);
        for(String s: tokens) {
            tok_lst.add(s);
            //if(s.contains(pattern)){
                //filtered.add(s);
                //writer.write(s + "\n")
        }
        //writer.close();
        return tok_lst;
    }

    public static String readRawText(String file){
        String fullText = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                fullText = fullText.concat("\n");
                fullText = fullText.concat(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullText;
    }

    private static void tagText(String text) throws IOException {
        Tagger.posTagger(text);
    }
}
