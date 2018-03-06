public class Digit
{
    int digit;
    //we need a pointer to the next AND last digits
    Digit next;
    Digit prev;
    
    //same commands as before
    public int getDigit() {
        return digit;
    }
    public void setDigit(int d) {
        digit = d;
    }
    public Digit getNext() {
        return next;
    }
    public void setNext(Digit d) {
        next = d;
    }
    public Digit getPrev() {
        return prev;
    }
    public void setPrev(Digit d) {
        prev = d;
    }
}