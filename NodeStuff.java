public class NodeStuff{
    public static void main(String [] args){
        Node head= new Node(3);
        //call set method with parameter 3
        head.set(3);
        //sets the Next in the node Head to a new node
        head.setNext(new Node(7));
        //sets that new node to 7
        head.getNext().set(7);
        
        //pointing to whatever head is pointing to (Node with value 3)
        Node curr= head;
        curr= curr.getNext(); //now curr is pointing at the 7
        curr.setNext(new Node(0));
        curr= curr.getNext();
        curr.set(10);
        //now we have a chain were head is 3, and curr went from 7 to 10
        //at the end of the chain, its pointing to null
        //this is a linked list
        
        //to loop through the whole list
        curr= head;
        //traversing the whole linked list
        while (curr!= null){
            System.out.println(curr.get());
            curr=curr.getNext();
        }
        
        //inserting into a list
        //lets say we want to put something after the 7 
        while(curr.get()!=7){
            curr= curr.getNext();
        }
        //now curr is pointing to the 7
        Node insert= new Node(0);
        insert.set(13);
        //we want to stick this 13 after the 7
        //draw linked list and this will be obvious
        insert.setNext(curr.getNext());
        curr.setNext(insert);
        //accessing a linked list value is slow bc u have to start at the head
        //and traverse to were you want
        //traversal and insertion is better than arrays
        //random access is harder in linked lists
    }
}