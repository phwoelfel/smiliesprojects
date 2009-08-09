import java.util.Random;

import javax.swing.*;


class Philosoph extends Thread{
	public int id;
	private DasProgramm parent;
	private JLabel mylab;
	private static final int SMAX = 6000;
	private static final int SMIN = 3000;
	
	
	public Philosoph(int id, DasProgramm par, JLabel lab){
		this.id = id;
		parent = par;
		mylab = lab;
	}
	
	
	public void run(){
		int runde = 1;
		while(true){
			//System.out.println("Runde #" +runde);
			think();
			mylab.setText("main");
			parent.take_forks(id);
			mylab.setText("main");
			eat();
			mylab.setText("main");
			parent.put_forks(id);
			mylab.setText("main");
			runde++;
		}
	}
	
	public void think(){
		mylab.setText("think");
		Random zu = new Random();
		int zeit = zu.nextInt(SMAX-SMIN)+SMIN;
		try {
			//System.out.println("Philosoph #" +id +" schlaft zeit " +zeit);
			Thread.sleep(zeit);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	
	public void eat(){
		mylab.setText("eat");
		Random zu = new Random();
		int zeit = zu.nextInt(SMAX-SMIN)+SMIN;
		try {
			//System.out.println("Philosoph #" +id +" schlaft zeit " +zeit);
			Thread.sleep(zeit);
			
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	
	
}
