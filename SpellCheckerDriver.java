import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SpellCheckerDriver {

	public static List<String> getWordsFromFile(String file) throws IOException {
		File input = new File(file);
		BufferedReader br = new BufferedReader( new FileReader(input));
		String line;
		List<String> tokenized = new ArrayList<String>();
		while((line = br.readLine()) != null) {
			tokenized.addAll(Arrays.asList(line.split(" ")));
		}
		br.close();
		return tokenized;
	}

	public static void main(String[] args) {
		try {
			if (args.length == 2) {
				List<String> words = getWordsFromFile(args[0]);
				SpellChecker sp = new SpellChecker(words);
				sp.tree.search("master", sp.tree.root);
			}
			else
				System.out.println("Please enter 2 strings: the dictionary file name and then the file to spell check");

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
