package ATM;

public class LinkedList {
	private Node header;
	
	//accessors
	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}
	public void add(Node n) {
		//adds a Node to the linkedlist
		if(header == null)header=n;
		else {
			Node current = header;
			while(current.getNext()!=null) {
				current=current.getNext();
			}
			current.setNext(n);
		}
	}
}
