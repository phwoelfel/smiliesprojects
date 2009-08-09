package schuluebung;

import java.io.*;


public class File_handling {
	public static void main(String[] args){
		FileReader fr;
		int c;
		int cr = 0x0D;//carriage return
		int lf = 0x0A;//line feed
		
		try {
			FileWriter fw = new FileWriter("hallo.txt");
			fw.write("hallo wie gehts dir denn???");
			fw.write(cr);
			fw.write(lf);
			fw.write("mir gehts gut.");
			fw.write(cr);
			fw.write(lf);
			fw.write("und dir?");
			fw.write(cr);
			fw.write(lf);
			fw.write("mir gehts auch gut");
			fw.write(cr);
			fw.write(lf);
			fw.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("hallo.txt", true));//new FileWriter("hallo.txt", true)
			//System.out.println("teststest");
			pw.println("heyho");
			pw.println("geht das?");
			pw.print("test");
			pw.write("testtesttest");
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		/*
		try{
			fr = new FileReader("hallo.txt");
			while((c=fr.read())!=-1){
				System.out.print((char)c);
			}
			
			fr.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		*/
	//	System.out.println("\n-------------------------");
		try {
			BufferedReader in = new BufferedReader(new FileReader("hallo.txt"));
			String line;
			while((line=in.readLine())!=null){
				System.out.println(line);
			}
			in.close();
			
			FileReader fr2 = new FileReader("hallo.txt");
			char[]cbuf = new char[6];//Anzahl der Zeichen die ausgelesen werden
			while((c=fr2.read(cbuf))!=0){
				//c=Anzahl der gelesenen Zeichen
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
