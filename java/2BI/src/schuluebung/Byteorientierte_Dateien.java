package schuluebung;

import java.io.*;

public class Byteorientierte_Dateien {

	public static void main(String[] args) {
		try {
			FileInputStream in = new FileInputStream("seite2.pdf");
			int c;
			byte[]b = new byte[10];
			while((c=in.read(b))!=-1){
				//System.out.print((char)c);
				for(int i=0;i<b.length;i++){
					System.out.print((char)b[i]);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		FileReader f=null;
		try{
			f=new FileReader("hallo.txt");
			int c;
			while((c=f.read())!=-1){
				System.out.print((char)c);
			}
			f.close();
		}
		catch(FileNotFoundException e1){
			System.out.println("FileNotFoundException: " +e1);
		}
		catch(IOException e){
			System.out.println("IOException: " +e);
		}
		finally{
			if(f!=null){
				try{
					f.close();
				}
				catch(IOException e2){
					System.out.println("IOException: " +e2);
				}
			}
		}

	}

}
