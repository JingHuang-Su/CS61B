public class HelloNumber{
	public static void main(String[] args){
		int x = 0;
		int total = 0;
		while ( x<9 ){
			x =  x + 1;
			total = total + x;
			System.out.print(total + " ");
		}
	}
}