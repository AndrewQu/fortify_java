import java.util.Arrays;
import java.util.Comparator;
/**
 * The print interface
 */
 interface print_class {
	 void Print();
 }
/**
 * The Studen class
 */
class Student implements print_class, Comparable <Student> {
	int Age;
	char Sex;
	int ClassNumber;
	String Name;
	
  /**
   * Construct a student class
   * @name - Name of the student
   * @age - Age of the student
   * @sex - Sex (M,F)
   * @class_num - The class number
   */
	public Student(String name, int age, char sex, int class_num)
	{
		Name = name;
		Age = age;
		Sex = sex;
		ClassNumber = class_num;
	}
	public void IncrementAge() {
		Age++;
	}
	
	public void Print() 
	{
		System.out.println(Name + " Age=" + Age + " Sex=" + Sex + " Class=" + ClassNumber);
	}
	/**
	* Compare Student by name so that objects can be sorted by name
	*/
	public int compareTo(Student s) {
		return this.Name.compareTo(s.Name);
	}
	/**
	* Compare by student age
	*/
	public static Comparator<Student> CompareByAge =
	new Comparator<Student>() {
		public int compare(Student s1, Student s2) {
			return s1.Age - s2.Age;
		}
	};
}
public class StudentArray {
	public static void main( String args[] ) {
		Student[] studentArray = new Student[5];
		studentArray[0] = new Student("John", 15, 'M', 4);
		studentArray[1] = new Student("Mary", 16, 'F', 4);
		studentArray[2] = new Student("Peter", 15, 'M', 4);
		studentArray[3] = new Student("Dianna", 14, 'F', 4);
		studentArray[4] = new Student("David", 17, 'M', 4);

		System.out.println("Students data:");
		for(int i=0; i<5; i++) {
			studentArray[i].Print();
		}

		Student[] copyArray = new Student[studentArray.length];
		System.arraycopy(studentArray, 0, copyArray, 0, studentArray.length);
		Arrays.sort(copyArray);
		System.out.println("\nCopied students sorted by name:");
		for(int i=0; i<5; i++) {
			copyArray[i].IncrementAge();
			copyArray[i].Print();
		}
		Arrays.sort(copyArray, Student.CompareByAge);
		System.out.println("\nCopied students sorted by age:");
		for(int i=0; i<5; i++) copyArray[i].Print();

		Student[] students2to4 = Arrays.copyOfRange(studentArray, 2, 4);
		System.out.println("\nSub array 2-4:");
		for(int i=0; i<students2to4.length; i++) students2to4[i].Print();
	}
}
