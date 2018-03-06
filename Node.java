public class Node {
	int value;

	// refer to an instance of a class, inside a class
	// next is a pointer to another Node

	// object VARIABLES are pointers
	//so next is a pointer (memory address) to some node object
	// remember box diagram where each box had a number and a dot
	// the dot POINTED to the next one
	// Node next; is the dot
	
	Node next;

	// constructor
	// remember that when we make a constructor, the default () is gone
	// so we need to make a parameterless constructor to make empty nodes
	public Node(int v) {
		value = v;
	}

	// setters and getters
	public int get() {
		return value;
	}

	public void set(int v) {
		value = v;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node n) {
		next = n;
	}
}