package ngram;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * used in Processor class
 * CONSTRUCTOR PARAM: stopwords path
 * GETTER METHODS: for StopWords, files, and filesNames
 *
 * */
public class FilesLoader {
	private List<String> stopWords = new ArrayList<>();
    private List<File> files = new ArrayList<>();
	private List<String> filesNames;
    private static final int NGRAM_THRESHOLD = 3;  // TODO is 3 better than 4?
	private List<List<String>> processed_txt;

	/**
	 * @param    src: path to stopwords.txt
	 * 	* 			is_shortcut: takes on values 0 or 1. if 0 then proprocess raw text, if 1 then load processed.txt
	 * */
    public FilesLoader(String src, int is_shortcut) throws IOException {
    	// load stopWords into field
    	loadStopWords(src);

    	// load files
		if(is_shortcut == 0) {
			//System.out.println("Path to the directory with the documents to analyze:");
			List<File> files = this.loadFiles("src/main/resources/text.txt");
			filesNames = parseFilesNames(files);

			processed_txt = ProcessingAlgs.cleanDocuments(files, NGRAM_THRESHOLD, stopWords);
			// write processed data to a txt file
			FileWriter writer = new FileWriter("src/main/resources/processed.txt");
			for (List<String> str_lst : processed_txt) {
				//writer.write("[");
				for (String str : str_lst) {
					writer.write(str + "\n ");
				}
				//writer.write("]\n");
			}
			writer.close();
		}else if(is_shortcut == 1){
			processed_txt = loadPreprocessedDocuments("processed.txt");
			// TODO: there's no filesNames!!
			filesNames = loadPreprocessedFiles();
		}
	}

	public List<String> getStopWords () { return stopWords;}
	public List<File> getFiles () {return files;}
	public List<String> getFilesNames () {return filesNames; }
	public int getNgramThreshold () {return NGRAM_THRESHOLD; }
	public List<List<String>> getProcessed_txt () {return processed_txt; }

	private List<String> parseFilesNames (List<File> files) {
		return files.stream().map(File::toString).collect(Collectors.toList());
	}

	private void loadStopWords(String src) throws IOException {
		InputStream stream = Preprocessor.class.getClassLoader().getResourceAsStream(src);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		String word;
		while((word = br.readLine()) != null) {
			stopWords.add(word);
		}
		br.close();
	}

	/**
	 * recursively add all files from a directories
	 * used inside of getFiles
	 */
	private void populateFilesList (File file) {
		if (!file.isDirectory()) {
			if (file.toString().endsWith(".txt"))
				files.add(file);
			return;
		}
		// only executes when is file.isDirectory() evaluates true
		for (File subfile: file.listFiles()) {
			populateFilesList(subfile);
		}
	}

	/**
	 * main entry for retrieving raw files
	 * @return List<File>
	 */
	public List<File> loadFiles (String path) {
		File root = new File(path);
		files.clear();
		populateFilesList(root);
		System.out.format("%d text files were found.\n", files.size());
		return files;
	}

	public static List<String> loadOneFile (String src) throws IOException {
		InputStream stream = FilesLoader.class.getClassLoader().getResourceAsStream(src);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		String tok;
		List<String> document = new ArrayList<>();
		while((tok = br.readLine()) != null) {
			tok = tok.replaceAll("[\\[\\]\\,]", "");
			document.add(tok);
		}
		br.close();
		return document;
	}

	public static void loadFileWithStopwords (String src) throws IOException {
		InputStream stream = Preprocessor.class.getClassLoader().getResourceAsStream(src);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		String line;

		FileWriter writer = new FileWriter("src/main/resources/text_withStopwords.txt");

		while((line = br.readLine()) != null) {
			// tokenize each line
			for(String s: line.split(" ")) {
				writer.write(s + "\n ");
			}
		}
		br.close();
		writer.close();
	}

	public List<List<String>> loadPreprocessedDocuments (String src) throws IOException {
		InputStream stream = FilesLoader.class.getClassLoader().getResourceAsStream(src);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		String document;
		List<List<String>> documents = new ArrayList<>();
		while((document = br.readLine()) != null) {
			document = document.replaceAll("[\\[\\]\\,]", "");
			documents.add(Arrays.asList(document.split(" ")));
		}
		br.close();
		return documents;
	}

	/**
	 * @return list of file names
	 */
	//TODO delete
	public List<String> loadPreprocessedFiles () {
		String[] folders = {"C1", "C4", "C7"};
		List<String> names = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			String name = folders[i / 8] + "/article0" + ((i % 8) + 1) + ".txt";
			names.add(name);
		}
		return names;
	}

}
