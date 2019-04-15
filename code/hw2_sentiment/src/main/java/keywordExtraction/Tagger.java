package keywordExtraction;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;

public class Tagger {

    public static void posTagger (String src) throws IOException {
        MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
        String taggedSample = tagger.tagString(src);
        //Long final_time = System.nanoTime();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output/pos.txt"));
        writer.write(taggedSample);
        writer.close();
    }
}
