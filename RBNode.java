public class RBNode{
	protected RBNode parent;
	protected RBNode leftChild;
	protected RBNode rightChild;
	protected Color color;
	protected String data;

	public RBNode(){
		this.data = null;
		parent = leftChild = rightChild = RBTree.NULL_NODE;
		color = Color.BLACK;
	}

	public RBNode(String word) {
		this.data = word;
		parent = leftChild = rightChild = RBTree.NULL_NODE;
		color = Color.BLACK;
	}

	public boolean isLeftChild() {
		return this.parent.leftChild.equals(this);
	}

	public boolean isLeaf() {
		return (this.leftChild == RBTree.NULL_NODE && this.rightChild == RBTree.NULL_NODE);
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setParen(RBNode parent) {
		this.parent = parent;
	}

	public void setLeftChild(RBNode leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(RBNode rightChild) {
		this.rightChild = rightChild;
	}
}