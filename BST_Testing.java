
public class BST_Testing {
	public static void main(String [] args) {
		BST<Integer> a = new BST<Integer>();
		
		a.insert(10);
		a.insert(14);
		a.insert(13);
		a.insert(8);
		a.insert(3);
		a.insert(6);
		a.insert(1);
		a.insert(4);
		a.insert(7);
		a.insert(23);
		a.printTree();
		System.out.println("adddadsa");
		a.balance();
		a.printTree();
	}		
}
