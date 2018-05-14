import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

	RBTree tree;

	public SpellChecker(List<String> words) {
		List<RBNode> wordList = new ArrayList<RBNode>();
		words.forEach(e -> wordList.add(new RBNode(e)));
		tree = new RBTree();
		tree.insertNodes(wordList);
	}

	//given a list of words, spellcheck them against the dictionary that is preloaded into the tree
	public List<String> spellCheck(List<String> words) {
		List<String> misspelled = new ArrayList<String>();
		words.forEach(e -> {
			RBNode n = null;
			if ((n = tree.search(e, tree.root)) == null)
				misspelled.add(e);
		});
		return misspelled;
	}

	public void printWords(){
		tree.printTree(tree.root, 3);
	}

}
