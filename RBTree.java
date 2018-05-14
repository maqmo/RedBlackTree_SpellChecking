
import java.util.ArrayList;
import java.util.List;

public class RBTree {
	RBNode root;
	static final RBNode NULL_NODE = new RBNode();
	int count;

	public RBTree(RBNode root){
		count = 0;
		this.root = root;
		root.leftChild = new RBNode();
		root.rightChild = new RBNode();
		root.parent = NULL_NODE;
	}

	public RBTree() {
		this.root = null;
	}

	public void insertNodes(List<RBNode> nodes) {
		if (this.root == null) {
			root = nodes.get(0);
			root.leftChild = new RBNode();
			root.rightChild = new RBNode();
			root.parent = NULL_NODE;
			nodes.remove(0);
		}
		nodes.forEach(e -> insert(e));
	}

	public void insert(RBNode node) {
		RBNode y = null;
		RBNode x = root;
		while (x.data != null) {
			y = x;
			x = (node.data.compareTo(x.data) < 0) ? x.leftChild : x.rightChild;
		}
		node.parent = y;
		if (y.data == null)
			root = node;
		else if (node.data.compareTo(y.data) < 0)
			y.leftChild = node;
		else
			y.rightChild = node;
		RBNode dummyLeft = new RBNode();
		dummyLeft.parent = node;
		RBNode dummyRight = new RBNode();
		dummyRight.parent = node;
		node.leftChild = dummyLeft;
		node.rightChild = dummyRight;
		node.color = Color.RED;
		this.insertFixUp(node, y);
	}

	public void insertFixUp(RBNode node, RBNode y) {
		//fix issue regarding null comparisons, either make a universal null sentinel that links root to null leaves
		//or make a dummy null node where we can access its variables without an exception
		while(node.parent.color == Color.RED) {
			if(node.parent.isLeftChild()) {
				y = node.parent.parent.rightChild;
				if (y.color == Color.RED) {
					node.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					node = node.parent.parent;
				}
				else if (!node.isLeftChild()) {
					node = node.parent;
					this.rotateLeft(node);
				}
				if (node != root) {
					node.parent.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					this.rotateRight(node.parent.parent);
				}
			}
			else {
				y = node.parent.parent.leftChild;
				if (y.color == Color.RED) {
					node.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					node = node.parent.parent;
				}
				else if (node.isLeftChild()) {
					node = node.parent;
					this.rotateRight(node);
				}
				node.parent.color = Color.BLACK;
				if (!node.equals(root))
					node.parent.parent.color = Color.RED;
				if (node.parent.parent != null && node.parent.parent.data != null)
					this.rotateLeft(node.parent.parent);
			}
		}
		root.color = Color.BLACK;
		return;
	}

	public RBNode search(String target, RBNode node) {
		if (node == null || node.data == null)
			return null;
		int order = target.compareTo(node.data);
		if (order > 0)
			return search(target, node.rightChild);
		else if (order < 0)
			return search(target, node.leftChild);
		else
			return node;
	}

	public void rotateLeft(RBNode node) {
		if (node != null) {

			RBNode newTop = node.rightChild;
			node.rightChild = newTop.leftChild;
			if (newTop.leftChild != NULL_NODE)
				newTop.leftChild.parent = node;
			newTop.parent = node.parent;
			if (newTop.parent == NULL_NODE)
				root = newTop;
			else if (node.isLeftChild())
				node.parent.leftChild = newTop;
			else node.parent.rightChild = newTop;
			newTop.leftChild = node;
			node.parent = newTop;
		}

	}

	public void rotateRight(RBNode node) {
		if (node != null)
		{
			if (node.rightChild != null && node.leftChild != null)
			{
				RBNode newTop = node.leftChild;
				node.leftChild = newTop.rightChild;
				if (newTop.rightChild != NULL_NODE)
					newTop.rightChild.parent = node;
				newTop.parent = node.parent;
				if (node.parent == NULL_NODE)
					root = newTop;
				else if (!node.isLeftChild())
					node.parent.rightChild = newTop;
				else node.parent.leftChild  = newTop;
				newTop.rightChild = node;
				node.parent = newTop;
			}
		}
	}

	public void printTree(RBNode node, int count) {
		if (node == null)
			return;
		sop(" ");
		sop(node.data);
		sop(" Parent is : " + node.parent.data);
		sop(", color is: "+ node.color.toString() +"\n");
		for(int i =0; i< count; i++)
			sop(" ");
		if (node.leftChild.data != null)
			printTree(node.leftChild, count + 3);
		if (node.rightChild.data != null)
			printTree(node.rightChild, count + 6);
	}

	public void sop(Object x) {
		System.out.print(x);
	}
}
