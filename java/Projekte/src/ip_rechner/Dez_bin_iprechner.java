package ip_rechner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dez_bin_iprechner extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField tf_ip;
	protected JButton bt_go;
	protected JButton bt_sv;
	protected JTextField tf_cont;
	protected JLabel lb_ip;
	protected PLabel lb_sv;
	
	public Dez_bin_iprechner(){
		setTitle("IP Umrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(355, 125);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel pa_up = new JPanel();
		JPanel pa_mid = new JPanel();
		JPanel pa_bot = new JPanel();
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		tf_ip = new JTextField(20);
		
		bt_go = new JButton("Berechnen");
		bt_sv = new JButton("Speichern");
		
		tf_cont = new JTextField(25);
		tf_cont.setEditable(false);
		lb_ip = new JLabel("IP-Adresse:");
		lb_sv = new PLabel();
		
		//tf_ip.setText("172.31.50.50");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		bt_go.addActionListener(this);
		bt_sv.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		pa_up.add(lb_ip);
		pa_up.add(tf_ip);
		pa_mid.add(tf_cont);
		
		pa_bot.add(bt_go);
		pa_bot.add(bt_sv);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(pa_up, BorderLayout.NORTH);
		cp.add(pa_mid, BorderLayout.CENTER);
		cp.add(pa_bot, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Dez_bin_iprechner();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt_go){
			try {
				IP ip = new IP(tf_ip.getText());
				tf_cont.setText(ip.getBinaryString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==bt_sv){
			lb_sv.append(tf_ip.getText());
		}
		
		
	}
	
}

class PLabel extends JLabel{
	public String append(String s){
		this.setText(this.getText()+s+"\n");
		return this.getText();
	}
}
