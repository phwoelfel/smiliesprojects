import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Semaphore;


import javax.swing.*;


public class DasProgramm  extends JFrame implements ActionListener{
	private static final int N = 5;
	private static final int THINKING = 0;
	private static final int HUNGRY = 1;
	private static final int EATING = 2;
	
	Semaphore mutex = new Semaphore(1);
	Semaphore[]s = new Semaphore[N];
	int[]state = new int[N];
	Philosoph[]phils = new Philosoph[N];
	JLabel[]ph_lab = new JLabel[N];
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	URL bild = getClass().getResource("philosophen.png");
	ImageIcon bg = new ImageIcon("philosophen.png");
	JLabel hintergrund = new JLabel(bg);
	
	public static void main(String[] args) {
		new DasProgramm();
	}
	
	public DasProgramm(){
		
		setTitle("Die " +N +" Philosophen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 560);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		JPanel mitte = new JPanel();
		mitte.setLayout(null);
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		
		for(int i=0;i<N;i++){
			ph_lab[i] = new JLabel("start");
		}
		ph_lab[0].setBounds(300, 50, 100, 20);
		ph_lab[1].setBounds(405, 220, 100, 20);
		ph_lab[2].setBounds(330, 450, 100, 20);
		ph_lab[3].setBounds(120, 450, 100, 20);
		ph_lab[4].setBounds(40, 220, 100, 20);
		
		hintergrund.setBounds(0, 0, 500, 500);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		start.addActionListener(this);
		stop.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		/*
		for(int i=0;i<N;i++){
			oben.add(ph_lab[i]);
		}*/
		oben.add(start);
		oben.add(stop);
		mitte.add(hintergrund);
		for(int i=0;i<N;i++){
			mitte.add(ph_lab[i]);
		}
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(oben, BorderLayout.NORTH);
		cp.add(mitte, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
		
	}
	
	public void take_forks(int id){
		ph_lab[id].setText("take_forks");
		try {
			mutex.acquire();
			state[id] = HUNGRY;
			test(id);
			ph_lab[id].setText("take_forks");
			mutex.release();
			s[id].acquire();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	
	public void put_forks(int id){
		int LEFT = (id+N-1)%N;
		int RIGHT = (id+1)%N;
		ph_lab[id].setText("put_forks");
		try {
			mutex.acquire();
			state[id] = THINKING;
			test(LEFT);
			ph_lab[id].setText("put_forks");
			test(RIGHT);
			ph_lab[id].setText("put_forks");
			mutex.release();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void test(int id){
		int LEFT = (id+N-1)%N;
		int RIGHT = (id+1)%N;
		ph_lab[id].setText("test");
		//System.out.println(LEFT +" " +RIGHT);
		if(state[id]==HUNGRY && state[LEFT] != EATING && state[RIGHT]!=EATING){
			state[id] = EATING;
			s[id].release();
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==start){
			for(int i=0;i<N;i++){
				s[i] = new Semaphore(0);
				phils[i] = new Philosoph(i, this, ph_lab[i]);
				phils[i].start();
			}
		}
		else if(e.getSource()==stop){
			for(int i=0;i<N;i++){
				if(phils[i]!=null){
					phils[i].stop();
				}
			}
		}
	}
	
}


