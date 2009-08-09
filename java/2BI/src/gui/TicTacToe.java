package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected int[][] dahinter = new int[3][3];
	protected int spieler=1;
	protected int klicks=0;
	protected String name1="X";
	protected String name2="O";
	protected int sp1=0;
	protected int sp2=0;
	
	protected JButton[][] felder;
	protected JButton clear;
	protected JTextField nam1;
	protected JTextField nam2;
	protected JButton setname;
	protected JLabel werisdrann;
	protected JLabel punkte;
	
	
	
	public void init(){
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(410, 400);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel spiel = new JPanel();
		spiel.setLayout(new GridLayout(3,3));
		JPanel werkz = new JPanel();
		werkz.setLayout(new FlowLayout());
		JPanel namen = new JPanel();
		namen.setLayout(new FlowLayout());
		
		felder = new JButton[3][3];
		for(int i=0; i<felder.length;i++){
			for(int j=0;j<felder[i].length;j++){
				felder[i][j] = new JButton("");
				felder[i][j].addActionListener(this);
				spiel.add(felder[i][j]);
			}
		}
		
		clear = new JButton("neues Spiel");
		nam1 = new JTextField(10);
		nam2 = new JTextField(10);
		setname = new JButton("neue Namen");
		werisdrann = new JLabel(name1 +" ist drann!");
		punkte =new JLabel(name1 +" " +sp1 +":" +sp2 +" " +name2);
		
		clear.addActionListener(this);
		setname.addActionListener(this);
		
		namen.add(nam1);
		namen.add(nam2);
		namen.add(setname);
		werkz.add(clear);
		werkz.add(werisdrann);
		werkz.add(punkte);
		
		cp.add(namen, BorderLayout.NORTH);
		cp.add(spiel, BorderLayout.CENTER);
		cp.add(werkz, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TicTacToe().init();
	}

	public void actionPerformed(ActionEvent e) {
		int win=0;
		
		if(e.getSource()==setname){
			String eing = JOptionPane.showInputDialog(this, "Bitte Kreditkartennummer vom ersten zur bestaetigung eingeben", "Kreditkartennummer", JOptionPane.QUESTION_MESSAGE);
			String eing2 = JOptionPane.showInputDialog(this, "Bitte Kreditkartennummer vom zweiten zur bestaetigung eingeben", "Kreditkartennummer", JOptionPane.QUESTION_MESSAGE);
			if(!eing.equals("") && !eing2.equals("")){
				name1 = nam1.getText();
				name2 = nam2.getText();
				punkte.setText(name1 +" " +sp1 +":" +sp2 +" " +name2);
				JOptionPane.showMessageDialog(this, "Namen wurden auf " +name1 +" und " +name2 +" geŠndert!", "Namen wurden geŠndert", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==clear){
			for(int i=0; i<felder.length;i++){
				for(int j=0;j<felder[i].length;j++){
					felder[i][j].setText("");
					felder[i][j].addActionListener(this);
					dahinter[i][j]=0;
				}
			}
			klicks=0;
			spieler=1;
			setTitle("Tic Tac Toe");
			werisdrann.setText(name1 +" ist drann!");
		}
		
		for(int i=0; i<felder.length;i++){
			for(int j=0;j<felder[i].length;j++){
				if(e.getSource()==felder[i][j]){
					if(spieler==1){
						felder[i][j].setText(name1);
						felder[i][j].removeActionListener(this);
						dahinter[i][j]=1;
						spieler=2;
						werisdrann.setText(name2 +" ist drann!");
						win=killerIf(dahinter);
						klicks++;
					}
					else{
						felder[i][j].setText(name2);
						felder[i][j].removeActionListener(this);
						dahinter[i][j]=2;
						spieler=1;
						werisdrann.setText(name1 +" ist drann!");
						win=killerIf(dahinter);
						klicks++;
					}
				}
			}
		}
		if(win==1){
			setTitle(name1 +" hat gewonnen!");
			werisdrann.setText(name1 +" hat gewonnen!");
			sp1++;
			punkte.setText(name1 +" " +sp1 +":" +sp2 +" " +name2);
			JOptionPane.showMessageDialog(this, name1 +" hat gewonnen!");
			for(int i=0; i<felder.length;i++){
				for(int j=0;j<felder[i].length;j++){
					felder[i][j].removeActionListener(this);
					klicks=0;
				}
			}
		}
		if(win==2){
			setTitle(name2 +" hat gewonnen!");
			werisdrann.setText(name2 +" hat gewonnen!");
			sp2++;
			punkte.setText(name1 +" " +sp1 +":" +sp2 +" " +name2);
			JOptionPane.showMessageDialog(this, name2 +" hat gewonnen!");
			for(int i=0; i<felder.length;i++){
				for(int j=0;j<felder[i].length;j++){
					felder[i][j].removeActionListener(this);
					klicks=0;
				}
			}
		}
		if(klicks==9){
			JOptionPane.showMessageDialog(this, "Unentschieden!");
			setTitle("Unentschieden");
			werisdrann.setText("Unentschieden!");
		}
		
	}
	public int killerIf(int[][] arr){
		int out=0;
		
		for(int i=0;i<arr.length;i++){
			if(arr[i][0]==1 && arr[i][1]==1 && arr[i][2]==1){
				out=1;
			}
			if(arr[i][0]==2 && arr[i][1]==2 && arr[i][2]==2){
				out=2;
			}
			if(arr[0][i]==1 && arr[1][i]==1 && arr[2][i]==1){
				out=1;
			}
			if(arr[0][i]==2 && arr[1][i]==2 && arr[2][i]==2){
				out=2;
			}
		}
		if(arr[0][0]==1 && arr[1][1]==1 && arr[2][2]==1){
			out=1;
		}
		if(arr[0][0]==2 && arr[1][1]==2 && arr[2][2]==2){
			out=2;
		}
		if(arr[2][0]==1 && arr[1][1]==1 && arr[0][2]==1){
			out=1;
		}
		if(arr[2][0]==2 && arr[1][1]==2 && arr[0][2]==2){
			out=2;
		}
		
		
		return out;
	}

}
