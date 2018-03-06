public class SubString {
    public static void main(String [] args){
        //you can extract part of a string with substring
        String x= "bees are amazing";
        //a string is an array of chars, zero indexed
        System.out.println(x.substring(0,3)); //prints "bee"
        //the second index number is NOT inclusive
        //this is why this does not print bees, only bee

        //will print x one char at a time
        for( int i=0; i< x.length(); i++){
            System.out.println(x.substring(i,i+1));
        }
    }
}