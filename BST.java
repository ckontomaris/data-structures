//BINARY SEARCH TREE
//Greater numbers go to the right

public class BST<T>{
    BSTNode<T> root = new BSTNode<T>();
    public void insert(T insertMe){
        //insert object insertMe into the current tree 
        //(if the tree is empty, it becomes the root)
    }
    public void inOrderPrint(){
        //prints tree in in-order traversal order
        //https://www.tutorialspoint.com/data_structures_algorithms/tree_traversal.htm
        System.out.println();
    }
    public boolean exists(T checkMe){
        //returns true if an element in the BST is equivalent to checkMe; 
        //returns false otherwise.
        
        //traverse the tree, and use compare 
        return false;
    }
}
class BSTNode <X>
{
    X val;
    BSTNode left;
    BSTNode right;

    BSTNode getLeft() {return left;}

    BSTNode getRight() {return right;}

    void setLeft(BSTNode bn) {left = bn;}

    void setRight(BSTNode bn) {right = bn;}

    X get() {return val;}

    void set(X v) {val = v;}

    //need a version of get that returns a comparable object,
    //because compareTo won't work on generic types by default
    //use get when you need to access the value, use getc
    //when you need to do a comparison
    //This will crash if a non-comparable object is used.
    Comparable getc() {return (Comparable) val;}
}