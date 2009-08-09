package multithreading;

public class MyThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Threadie th = new Threadie("Hallo");
		th.start();
		Threadie th2 = new Threadie("Hallo2");
		th2.start();
		Threadie th3 = new Threadie("Hallo3");
		th3.start();
	}

}

class Threadie extends Thread{
	public String TName = ""; 
	
	public Threadie(String name){
		TName = name;
	}
	
	public void run(){
		int i = 0;
		while(true){
			System.out.println(TName +": " +i);
			i++;
		}
	}
	
}
