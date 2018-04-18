//BINARY SEARCH TREE
//Greater numbers go to the right
import java.*;
public class BST<T> {
	BSTNode<T> root = new BSTNode<T>();
	BSTNode<T> curr = new BSTNode<T>();

	public void insert(T insertMe) {
		if (root == null) {
			root.set(insertMe);
			return;
		}

		curr = root;
		BSTNode<T> obj = new BSTNode<T>();
		obj.set(insertMe);
		while (curr != null) {
			if (obj.getc().compareTo(curr.getc()) < 0) {
				// LESS THAN
				// move there, or make a new one
				if (curr.getLeft() != null) {
					curr = curr.getLeft();
				} else {
					curr.setLeft(obj);
					return;
				}
			} else if (obj.getc().compareTo(curr.getc()) >= 0) {
				// GREATER THAN OR EQUAL TO
				if (curr.getRight() != null) {
					curr = curr.getRight();
				} else {
					curr.setRight(obj);
					return;
				}
			}
		}

	}

	public void inOrderPrint() {
		//start at root, recursively go all the way to the left, print, and then go right
		printNode(root);
	}
	public void printNode(BSTNode<T> curr) {
		if (curr == null) {
			return;
		}
		printNode(curr.getLeft());
		System.out.println(curr.get());
		printNode(curr.getRight());
		
	}

	public boolean exists(T checkMe) {

		return false;
	}
}

class BSTNode<X> {
	X val;
	BSTNode left;
	BSTNode right;

	BSTNode getLeft() {
		return left;
	}

	BSTNode getRight() {
		return right;
	}

	void setLeft(BSTNode bn) {
		left = bn;
	}

	void setRight(BSTNode bn) {
		right = bn;
	}

	X get() {
		return val;
	}

	void set(X v) {
		val = v;
	}

	// need a version of get that returns a comparable object,
	// because compareTo won't work on generic types by default
	// use get when you need to access the value, use getc
	// when you need to do a comparison
	// This will crash if a non-comparable object is used.
	Comparable getc() {
		return (Comparable) val;
	}
}