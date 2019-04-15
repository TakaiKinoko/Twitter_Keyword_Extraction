package io;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;
import java.util.List;

public class CsvReader {

    public static ArrayList<String> filterTweet (String filepath, String pattern, int col){
        File csv = new File(filepath);
        csv.setReadable(true);
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(csv));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String line = "";
        //String everyLine = "";
        ArrayList<String> allString = new ArrayList<>();
        try{
            while ((line = br.readLine()) != null){
                // use comma as separator
                String[] cols = line.split(",");

                if(cols[col].contains(pattern))
                    allString.add(line);
            }
            System.out.println("The number of rows filtered by this given pattern is: " + allString.size());
        }catch(IOException e) {
            e.printStackTrace();
        }
        return allString;
    }

    public static ArrayList<String> readCsvColumn (String filepath, int col){
        File csv = new File(filepath);
        csv.setReadable(true);
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(csv));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        ArrayList<String> allString = new ArrayList<>();
        try{
            while ((line = br.readLine()) != null){
                everyLine = line;

                // use comma as separator
                String[] cols = line.split(",");
                // testing if file is read in successfully
                //System.out.println("Column 5=" + cols[5]);

                allString.add(cols[col]);
                //System.out.println(everyLine);
                //allString.add(everyLine);
            }
            System.out.println("The number of rows in the csv file is: " + allString.size());
        }catch(IOException e) {
            e.printStackTrace();
        }
        return allString;
    }

    public static void saveToTxt (ArrayList<String> data, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (String str : data)
            writer.write(str + "\n");
        writer.close();
    }
}