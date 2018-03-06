//using the GenericSchool class and setting the number of students to a string

public class GenericSchoolCheck{
	public static void main(String [] args) {
		GenericSchool<String> conrad = new GenericSchool<String>();
		
		conrad.setStudents("a thousand");
		System.out.println(conrad.getStudents());
	}
}