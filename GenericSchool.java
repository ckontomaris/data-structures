/*
 * When we make a object of this class, we have to do:
 * GenericSchool<String> conrad = new GenericSchool<String>();
 * 
 * So, we are saying that the numberOfStudents will be of type String.
 * We could make it be any other object (not primitives?)
 * Test using multiple T's (have other instance variables with other types
 * So you have to declare GenericSchool<T, U, V>
 */

public class GenericSchool<T> {
	T numberOfStudents;
	
	public T getStudents() {
		return numberOfStudents;
	}
	public void setStudents(T ayy) {
		numberOfStudents= ayy;
	}
}
