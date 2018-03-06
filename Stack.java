public class Stack {
    Node head;
   
    public void push(Node newHead){
        newHead.setNext(head);
        head= newHead;
    }
    public Node pop(){
    	
    	head= head.getNext();
        return head;
        
    }
    public Node peek(){
        return head;
    }
}