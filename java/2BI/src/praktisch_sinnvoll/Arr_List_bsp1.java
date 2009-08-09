package praktisch_sinnvoll;

import java.util.ArrayList;
import java.util.Scanner;


public class Arr_List_bsp1 {
	public static void main (String[]args){
		ArrayList<String> arrr = new ArrayList<String>();
		ArrayList<String> arr2 = new ArrayList<String>();
		Scanner tast = new Scanner(System.in);
		String in;
		
		System.out.println("Bitte Satz eingeben");
		in = tast.nextLine();
		
		String[]arr = in.split(" ");
		arrr = machALDraus(arr);
		printAL(arrr);
		
		System.out.println("\nUmgekehrt:");
		
		arr2=reverse(arrr);
		printAL(arr2);
	}
	
	public static ArrayList<String> machALDraus(String[]in){
		ArrayList<String> out = new ArrayList<String>();
		
		for(int i=0;i<in.length;i++){
			out.add(in[i]);
		}
		
		return out;
	}
	
	public static void printAL(ArrayList<String> in){
		for(int i=0;i<in.size();i++){
			System.out.println(in.get(i));
		}
	}
	
	public static ArrayList<String> reverse(ArrayList<String> in){
		ArrayList<String> out = new ArrayList<String>();
		
		for(int i=in.size()-1;i>=0;i--){
			out.add(in.get(i));
		}
		
		return out;
	}
}
