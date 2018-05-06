import java.util.*;

public class ArrayListTest {
	public static void main(String [] args) {
		ArrayList<Integer> ayy = new ArrayList<Integer>();
		ayy.add(1);
		ayy.add(2);
		ayy.add(3);
		ayy.add(4);
		
		System.out.println(ayy);
		ayy.remove(2);
		System.out.println(ayy);
		System.out.println(ayy.get(2));
	}
}
