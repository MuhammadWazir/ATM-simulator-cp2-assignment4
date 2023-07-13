package ATM;

public class Node {
	private Account info;
	private Node next;
	//accessors
	public Account getInfo() {
		return info;
	}
	public void setInfo(Account info) {
		this.info = info;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	//constructor
	public Node(Account info) {
		this.info = info;
	}
}
