package ngram;

import java.io.*;
import java.util.List;

/**
 * Interface to TextAnalysis class
 **/
public class Preprocessor {
    private FilesLoader fl;
    //private List<List<String>> keyWords = new ArrayList<>();

    public Preprocessor (String stopWords_path, int is_shortcut) throws IOException {
        fl = new FilesLoader(stopWords_path, is_shortcut);
    }

    public List<String> getStopWords () { return fl.getStopWords();}
    public List<File> getFiles () {return fl.getFiles();}
    public List<String> getFilesNames () {return fl.getFilesNames(); }
    public int getNgramThreshold () {return fl.getNgramThreshold(); }
    public List<List<String>> getProcessed_txt () {return fl.getProcessed_txt(); }

    public DocumentTermMatrix printProcessedResult(List<List<String>> keyWords) {
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("|               PREPROCESSING RESULTS                   |");
        System.out.println("---------------------------------------------------------");
        System.out.println("Top 10 keywords in each document according to the TF-IDF matrix:");
        DocumentTermMatrix dtm = new DocumentTermMatrix(getProcessed_txt());
        for (int i = 0; i < getFilesNames().size(); i++) {
            List<String> kw = dtm.getKeyWords(i, 10);
            keyWords.add(kw);
            System.out.println("\t" + getFilesNames().get(i) + "\t" + kw);
        }

        return dtm;
    }
}