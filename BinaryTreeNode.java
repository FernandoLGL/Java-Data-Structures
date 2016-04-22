package arvore;

public class BinaryTreeNode {

	private int info;
	private BinaryTreeNode right;
	private BinaryTreeNode left;

	public BinaryTreeNode(int info) {
		this.setInfo(info);
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
}
