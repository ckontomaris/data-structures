public class GenericNode <T>
{
    //instead of storing an int, we store the type T
	T value;
    GenericNode next;
    
    public GenericNode(T val) {
        value = val;
    }
    
    public T get() {
        return value;
    }
    public void set(T v) {
        value = v;
    }
    public GenericNode getNext() {
        return next;
    }
    public void setNext(GenericNode n) {
        next = n;
    }
}