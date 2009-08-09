
public class Test {
	public static void main(String [] args){
		int[]ar = {1,2,3,4,5};
		System.out.println(ar[1]);
		bla(ar);
		System.out.println(ar[1]);
	}
	public static void bla(int[]arr){
		arr[1]=100;
	}
}
