/**
 * This is an example program showing how the Stack class
 * should work with our Node class using integers.
 * It won't compile or work unles you write the Stack
 * class correctly. (Assuming you're using my Node)
 */
public class StackUseExample
{
    public static void main(String [] args) {
        Stack blah = new Stack();
        
        blah.push(new Node(25));
        blah.push(new Node(56));

        /**
         * After this, blarg should be POINTING at the same
         * Node as the top of the stack.
         */
        Node blarg = blah.peek();
        blarg.set(12);  //So, this should actually change the top
            // of the stack
        System.out.println(blarg.get());
        
        /**
         * After this, wee should be pointing at the same
         * Node as blarg, but that Node should no longer be
         * on the stack. (The Node with 25 should be the
         * top of the stack)
         */
        Node wee = blah.pop();

    }
}