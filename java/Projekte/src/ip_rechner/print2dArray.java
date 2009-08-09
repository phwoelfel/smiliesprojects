package ip_rechner;


public class print2dArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public static void Int(String s, int[][] arr){
		System.out.println(s);
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void Double(double[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
	public static void Float(float[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
	public static void Long(long[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
	public static void Bool(boolean[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
	public static void String(String[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
	public static void Char(char[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j] +", ");
			}
			System.out.println();
		}
	}
	
}
