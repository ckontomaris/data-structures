
public class BST_Testing {
	public static void main(String [] args) {
		BST<Integer> ayy = new BST<Integer>();
		
		ayy.insert(1);
		ayy.insert(23);
		ayy.insert(0);
		ayy.insert(134);
		ayy.insert(-23);
		ayy.insert(21);
		ayy.insert(123123);
		ayy.inOrderPrint();
	}
}
