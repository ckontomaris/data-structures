import java.util.*;

/**
 * A class for a Binary Search Tree (BST) 
 * It is generic, and can be printed in-order or by level
 * Can be balanced, checked for the existence of a particular value, and inserted to
 * 
 * @author Chris Kontomaris
 * @version 5-5-18
 */


/*
 * useful data structures playlist for review of terms/concepts
 * https://www.youtube.com/playlist?list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P
 */
public class BST<T> {
	BSTNode root;

	public void insert(T insertMe) {
		// root is a special case
		if (root == null) {
			root = new BSTNode();
			root.set(insertMe);
		} else {
			insert_re(root, insertMe);
		}
	}

	/**
	 * recursive method called by insert
	 */
	private void insert_re(BSTNode n, T ins) {
		// compare what we want to insert with current node value using compareTo
		if (n.getc().compareTo(ins) > 0) {
			// LESS THAN
			if (n.getLeft() == null) {
				n.setLeft(new BSTNode());
				n = n.getLeft();
				n.set(ins);
			} else {
				insert_re(n.getLeft(), ins);
			}
		} else {
			// GREATER THAN OR EQUAL TO
			if (n.getRight() == null) {
				n.setRight(new BSTNode());
				n = n.getRight();
				n.set(ins);
			} else {
				insert_re(n.getRight(), ins);
			}
		}
	}

	/**
	 * print the whole tree in order
	 */
	public void inOrderPrint() {
		inOrderPrint_re(root);
	}

	private void inOrderPrint_re(BSTNode n) {
		if (n == null) {
			return;
		}
		inOrderPrint_re(n.getLeft());
		System.out.println(n.get());
		inOrderPrint_re(n.getRight());
	}

	/**
	 * Just for fun, this version of exists will be NOT recursive
	 */
	public boolean exists(T checkMe) {
		BSTNode curr = root;
		// when we use a loop, we need a curr
		while (curr != null) {
			if (curr.getc().compareTo(checkMe) == 0) {
				return true;
			} else if (curr.getc().compareTo(checkMe) > 0) {
				// curr > checkMe, go left
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		// if we reach the end of the loop without returning, checkMe isn't in
		// the tree
		return false;
	}

	/**
	 * printTree prints a level-order traversal, with a tab space between each leaf or branch 
	 * A new line indicates a new level of the tree
	 * 
	 * @return Prints the Comparable contents of every node in the tree
	 */
	public void printTree() {

		// create a queue with the root, print it and add its children
		// do this until there are no more children
		// by adding the children to the end of a queue, we preserve the level order
		// we CAN'T end up having something from a previous level come after the current
		// level
		// it's pretty cool to visualize the queue filling up exponentially with a huge tree

		if (root == null) {
			return;
		} else {
			ArrayList<BSTNode> q = new ArrayList<BSTNode>(1);
			q.add(root);
			q.trimToSize();
			while (!q.isEmpty()) {
				System.out.print(q.get(0).getc() + "\t");
				// add the children, if they aren't null
				// we have to put the getLeft first so that it prints left to right
				if (q.get(0).getLeft() != null) {
					q.add(q.get(0).getLeft());
				}
				if (q.get(0).getRight() != null) {
					q.add(q.get(0).getRight());
				}

				// if what we just printed has a different level than what comes next,
				// we know it's time for a new line
				if (q.size() >= 2) {
					if (getDepth(q.get(0)) != getDepth(q.get(1))) {
						System.out.println(" ");
					}
				}
				q.remove(0);

			}
			return;
		}
	}

	/**
	 * Calculates the depth of a node from the root
	 * 
	 * @param node A node to calculate the depth of
	 * @return The number of links from the root to the node (the depth)
	 */
	public int getDepth(BSTNode node) {

		/*
		 * depth is just the # of links from the root to a node so we are doing a
		 * search, but tracking how many links we have to jump
		 */
		int depth = 0;
		BSTNode curr = root;
		while (curr != null) {
			if (curr.getc().compareTo(node.getc()) == 0) {
				return depth;
			} else if (curr.getc().compareTo(node.getc()) > 0) {
				// curr > checkMe, go left
				curr = curr.getLeft();
				depth++;
			} else {
				curr = curr.getRight();
				depth++;
			}
		}

		// if we reach the end of the loop without returning, checkMe isn't in
		// the tree
		return depth;
	}

	/**
	 * Balances the tree such that it has the minimum number of levels 
	 * Readjusts pointers of the existing nodes into a new tree
	 */
	public void balance() {
		// start at root, and put the tree inorder into an array

		ArrayList<BSTNode> inOrder = new ArrayList<BSTNode>();
		toAl_re(root, inOrder);
		// now inOrder contains the nodes, in order

		// find the middle element to set as the new root and branch off from
		int middle = inOrder.size() / 2;
		BSTNode parent = inOrder.get(middle);
		root = parent;

		balance_re(root, inOrder);

	}

	/**
	 * Recursively divides a sorted list of nodes into a balanced binary search tree
	 * 
	 * @param topParent A node to add to the balanced tree
	 * @param inOrder A List of potential children of the topParent
	 */
	private void balance_re(BSTNode topParent, List<BSTNode> inOrder) {

		// if we get a List with a single node, we know it has to be a leaf
		// we still need to remove its previous connection from the old tree
		if (inOrder.size() <= 1) {
			topParent.setLeft(null);
			topParent.setRight(null);
			return;
		}

		// split the list into 2
		// take the middle of each of those lists and set it to the right and left of
		// the parent
		// repeat on each of the two remaining lists
		int middle = inOrder.size() / 2;
		// left and right lists
		List<BSTNode> greater = inOrder.subList((middle + 1), inOrder.size());
		List<BSTNode> less = inOrder.subList(0, middle);

		// our parents' left and right nodes
		//this will crash is less is empty

		
		if(less.isEmpty()) {
			topParent.setLeft(null);
			return;
		} else {
			BSTNode left = less.get(less.size() / 2);
			// connect the parent to its children
			topParent.setLeft(left);
			// call it again on each remaining list
			balance_re(left, less);
		}
		
		
		if(greater.isEmpty()) {
			topParent.setRight(null);
			return;
		} else {
		BSTNode right = greater.get(greater.size() / 2);
		topParent.setRight(right);
		balance_re(right, greater);
		}


		
	}

	/**
	 * Recursively follows subtrees of the BST and populates an ArrayList with
	 * 
	 * @param n The beginning of a subtree to recursively follow
	 * @param al An array list to fill with nodes
	 * 
	 * @return a sorted ArrayList containing
	 */
	private void toAl_re(BSTNode n, ArrayList al) {
		if (n == null) {
			return;
		}
		// the array list will start from smallest
		toAl_re(n.getLeft(), al);
		al.add(n);
		toAl_re(n.getRight(), al);
	}

	public class BSTNode {
		T value;

		BSTNode left;
		BSTNode right;

		public T get() {
			return value;
		}

		public Comparable getc() {
			return (Comparable) value;
		}

		public void set(T val) {
			value = val;
		}

		public BSTNode getLeft() {
			return left;
		}

		public void setLeft(BSTNode par) {
			left = par;
		}

		public BSTNode getRight() {
			return right;
		}

		public void setRight(BSTNode par) {
			right = par;
		}

	}

}