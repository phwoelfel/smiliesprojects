package ip_rechner;

public class IP_Anding {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IP i1 = new IP("172.16.225.93");
			IP i2 = new IP("255.255.248.0");
			
			int[][] ar = new int[4][8];
			for(int i=0;i<i1.getBinary().length;i++){
				for(int j=0;j<i1.getBinary()[i].length;j++){
					ar[i][j] = i1.getBinary()[i][j] & i2.getBinary()[i][j];
				}
			}
			IP i3 = new IP(ar);
			System.out.println(i3 +"\n" +i3.getBinaryString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
