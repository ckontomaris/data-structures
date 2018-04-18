public class BST <T> {
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
            //LESS THAN
            if (n.getLeft() == null) {
                n.setLeft(new BSTNode());
                n = n.getLeft();
                n.set(ins);
            } else {
                insert_re(n.getLeft(),ins);
            }
        } else {
            //GREATER THAN OR EQUAL TO
            if (n.getRight() == null) {
                n.setRight(new BSTNode());
                n = n.getRight();
                n.set(ins);
            } else {
                insert_re(n.getRight(),ins);
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
        if (n == null) { return; }
        inOrderPrint_re(n.getLeft());
        System.out.println(n.get());
        inOrderPrint_re(n.getRight());
    }

    /**
     * Just for fun, this version of exists will be NOT recursive
     */
    public boolean exists(T checkMe) {
        BSTNode curr = root;
        //when we use a loop, we need a curr
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
    public void printTree(){
        //we need to change  insert and BSTNode to have a level 
        
        //make a queue 
    }
    public void balance(){
        //now when we balance, we need to make sure the levels work
    }
    
    

    public class BSTNode {
        T value;
        BSTNode left;
        BSTNode right;
        public T get() { return value; }

        public Comparable getc() { return (Comparable) value; }

        public void set (T val) { value = val; }

        public BSTNode getLeft() { return left; }

        public void setLeft(BSTNode par) { left = par; }

        public BSTNode getRight() { return right; }

        public void setRight(BSTNode par) { right = par; }
    }
}