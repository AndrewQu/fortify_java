/**
 * The print interface
 */
 interface print_class {
	 void Print();
 }
/**
 * The Studen class
 */
public class Student implements print_class {
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
	
	public void Print() 
	{
		System.out.println(Name + " Age=" + Age + " Sex=" + Sex + " Class=" + ClassNumber);
	}
	
	public static void main( String args[] ) {
		Student student1 = new Student("John", 15, 'M', 4);
		Student student2 = new Student("Mary", 16, 'F', 4);
		System.out.println("Hello Java!");
		student1.Print();
		student2.Print();
	}
}
