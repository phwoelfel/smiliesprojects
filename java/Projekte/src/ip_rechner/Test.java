package ip_rechner;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*int [] arr = {192, 168, 0, 1};
		IP a = new IP(arr);
		System.out.println(a.toBinaryString());
		int [][] barr = a.toBinary();
		print2dArray.Int(barr);
		int [] darr = a.getAdress();
		printArray.Int(darr);
		System.out.println(a.getSAdress());
		*/
		IP b = new IP("255.255.254.0");
		System.out.println(b.getBinaryString());
		/*int [][] barr2 = b.toBinary();
		print2dArray.Int(barr2);
		int [] darr2 = b.getAdress();
		printArray.Int(darr2);*/
		b.setAdress("172.16.10.120");
		print2dArray.Int("bbinary",b.getBinary());
		System.out.println(b.getAdressString());
		
		IP asd = new IP(b);
		System.out.println(asd);
	}

}
