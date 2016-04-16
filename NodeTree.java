
public class NodeTree {

	private int info;
	private NodeTree rightSide;
	private NodeTree leftSide;

	public NodeTree(int info) {
		this.setInfo(info);
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public NodeTree getRightSide() {
		return rightSide;
	}

	public void setRightSide(NodeTree rightSide) {
		this.rightSide = rightSide;
	}

	public NodeTree getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(NodeTree leftSide) {
		this.leftSide = leftSide;
	}

	/*public void setLeaf(NodeTree node) {
		if (node == null) {
			return;
		} else if (node.getInfo() == this.info) {
			return;
		} else if (node.getInfo() > this.info) {
			this.rightSide = node;
		} else {
			this.leftSide = node;
		}
	}*/

}
