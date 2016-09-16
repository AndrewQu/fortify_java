/**
 * The main HelloJava class
 */
public class HelloJava {
	public static void main( String args[] ) {
		if (args.length == 0)
		{
			System.out.println("Hello Java!");
		} else {
			System.out.println("Hello " + args[0] + "!");
		}
	}
}
