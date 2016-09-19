/**
 * Test labeled break and continue. console().readLine(), Integer.parseInt(), try...catch
 */
public class Control {
	public static void main( String args[] ) {
		int[][] mat = new int[10][20];
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				mat[i][j] = i*100 + j;
			}
		}
		try {
    		int num2search = 0;
	    	if(args.length > 0) {
		    	num2search = Integer.parseInt(args[0]);
			}
			do {
				if(args.length == 0){
					System.out.print("Enter number to search (0 to end) :");
					String s = System.console().readLine();
					num2search = Integer.parseInt(s);
				}
				int i, j=0;
			iloop:
				for(i=0; i<mat.length; i++) {
					for(j=0; j<mat[0].length; j++) {
						if(mat[i][j] == num2search) break iloop;
					}
				}
				if(i >= mat.length) {
					System.out.println("Number " + num2search + " not found in matrix.");
				} else {
					System.out.println("At row=" + i + " column=" + j);	
				}
			} while(num2search > 0 && args.length == 0);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		Test_Point();
	}
	static void Test_Point(){
		Point2d p1 = new Point2d(10,20);
		System.out.println("p1 = " + p1);
		System.out.println("Creating p2");
		Point3d p2 = new Point3d(10, 20, 22);
		System.out.println("p2 = " + p2);
		Point3d p3 = new Point3d(11, 22);
		System.out.println("p3 = " + p3);
	}
}
