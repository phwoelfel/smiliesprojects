package ip_rechner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rechner_bin extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField tf_ip;
	protected JTextField tf_snm;
	protected JButton bt_go;
	protected JLabel[] lb_cont;
	protected JLabel lb_ip;
	protected JLabel lb_snm;
	
	public Rechner_bin(){
		setTitle("IP Rechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel pa_up = new JPanel();
		JPanel pa_midcont = new JPanel();
		JPanel pa_mid = new JPanel();
		pa_mid.setLayout(new GridLayout(13, 1));
		JPanel pa_bot = new JPanel();
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		tf_ip = new JTextField(20);
		tf_snm = new JTextField(20);
		
		bt_go = new JButton("Berechnen");
		
		lb_cont = new JLabel[13];
		lb_cont[0] = new JLabel("Hallo!\nGib eine IP Adresse und eine Subnetzmaske ein und druecke dann auf berechnen!"); //IP
		lb_cont[1] = new JLabel(""); //Klassennetz
		lb_cont[2] = new JLabel(""); //SNM
		lb_cont[3] = new JLabel(""); //SN Adr
		lb_cont[4] = new JLabel(""); //1. Adr
		lb_cont[5] = new JLabel(""); //l.g. Adr
		lb_cont[6] = new JLabel(""); //Broadcast
		lb_cont[7] = new JLabel(""); //1. SN
		lb_cont[8] = new JLabel(""); //2. SN
		lb_cont[9] = new JLabel(""); //l. SN
		lb_cont[10] = new JLabel(""); //Anz Hosts
		lb_cont[11] = new JLabel(""); //Anz SN
		lb_cont[12] = new JLabel(""); //Magic Number
		lb_ip = new JLabel("IP-Adresse:");
		lb_snm  = new JLabel("Subnetzmaske:");
		
		tf_ip.setText("10.150.200.200");
		tf_snm.setText("255.255.252.0");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		bt_go.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		pa_up.add(lb_ip);
		pa_up.add(tf_ip);
		pa_up.add(lb_snm);
		pa_up.add(tf_snm);
		
		for(int i=0;i<lb_cont.length;i++){
			pa_mid.add(lb_cont[i]);
		}
		pa_midcont.add(pa_mid);
		
		pa_bot.add(bt_go);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(pa_up, BorderLayout.NORTH);
		cp.add(pa_midcont, BorderLayout.CENTER);
		cp.add(pa_bot, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Rechner_bin();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt_go){
			IP ip = null;
			IP snm = null;
			IP cl_net = null;
			IP sn_adr = null;
			IP sn_firstadr = null;
			IP sn_lgadr = null;
			IP sn_broadc = null;
			IP first_snadr = null;
			IP sec_snadr = null;
			IP l_snadr = null;
			try {
				ip = new IP(tf_ip.getText());
				snm = new IP(tf_snm.getText());
				cl_net = getClassNet(ip);
				sn_adr = getSNAdress(ip, snm);
				int[]sn_adrar = sn_adr.getAdress();
				sn_adrar[3]+=1;
				sn_firstadr = new IP(sn_adrar);
				
				sn_lgadr = getLastAdress(sn_adr, snm);
				sn_broadc = getBCAdress(sn_adr, snm);
				first_snadr = getClassNet(ip);
				
				sec_snadr = new IP();
				l_snadr = new IP();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			lb_cont[0].setText("IP: " +ip);
			lb_cont[1].setText("Kl. Netz: " +cl_net);
			lb_cont[2].setText("SNM: " +snm);
			lb_cont[3].setText("SN Adr: " +sn_adr);
			lb_cont[4].setText("1. Adr: " +sn_firstadr);
			lb_cont[5].setText("l.g. Adr: " +sn_lgadr);
			lb_cont[6].setText("Broadc Adr: " +sn_broadc);
			lb_cont[7].setText("1. SN:" +first_snadr);
			lb_cont[8].setText("2. SN:" +sec_snadr);
			lb_cont[9].setText("l. SN:" +l_snadr);
			lb_cont[10].setText("Anz H.: " +(getFullMN(snm)-2) +" (2^" +getHostBits(snm) +")");
			lb_cont[11].setText("Anz SN: " +getSNNo(cl_net, snm));
			lb_cont[11].setText("MN: " +getMN(snm) +"  FullMN: " +getFullMN(snm));
		}
		
	}
	
	public IP getClassNet(IP adr){
		int[]adrar = adr.getAdress();
		if(adrar[0]<128){
			try {
				return new IP(adrar[0] +".0.0.0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(adrar[0]<192){
			try {
				return new IP(adrar[0] +"." +adrar[1] +".0.0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(adrar[0]<224){
			try {
				return new IP(adrar[0] +"." +adrar[1] +"." +adrar[2] +".0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return new IP();
	}
	//subnetzadr = floor((spez. Quad der ip)/MZ)*MZ
	public IP getSNAdress(IP ip, IP snm) throws Exception{
		int[][]snadr = new int[4][8];
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				snadr[i][j] = ip.getBinary()[i][j]&snm.getBinary()[i][j];
			}
		}
		/*
		print2dArray.Int("ip", ip.getBinary());
		print2dArray.Int("snm", snm.getBinary());
		print2dArray.Int("snadr", snadr);
		*/
		try {
			return new IP(snadr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IP();
	}
	public IP getLastAdress(IP sn, IP snm) throws Exception{
		int[][]snadr = new int[4][8];
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				if(snm.getBinary()[i][j]==0){
					snadr[i][j] = 1;
				}
				else{
					snadr[i][j] = sn.getBinary()[i][j];
				}
			}
		}
		
		try {
			IP zw = new IP(snadr);
			zw.setQuad(3, zw.getAdress()[3]-1);
			return new IP(zw.getAdress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IP();
	}
	public IP getBCAdress(IP sn, IP snm) throws Exception{
		int[][]snadr = new int[4][8];
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				if(snm.getBinary()[i][j]==0){
					snadr[i][j] = 1;
				}
				else{
					snadr[i][j] = sn.getBinary()[i][j];
				}
			}
		}
		try {
			return new IP(snadr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IP();
	}
	public int getFullMN(IP snm){
		int[][]snmar = snm.getBinary();
		int a = 0;
		for(int i=0;i<snmar.length;i++){
			for(int j=0;j<snmar[i].length;j++){
				if(snmar[i][j]==0){
					a++;
				}
			}
		}
		return (int)Math.pow(2, a);
	}
	public int getHostBits(IP snm){
		int[][]snmar = snm.getBinary();
		int a = 0;
		for(int i=0;i<snmar.length;i++){
			for(int j=0;j<snmar[i].length;j++){
				if(snmar[i][j]==0){
					a++;
				}
			}
		}
		return a;
	}
	
	public int getMN(IP snm){
		int[]adr = snm.getAdress();
		int q = 0;
		for(int i=0;i<adr.length;i++){
			if(adr[i]!=255 && adr[i]!=0){
				q=adr[i];
				break;
			}
		}
		return 256-q;
	}
	
	public int[] getSexyQuad(IP ip, IP snm){
		int[] out = new int[2];
		int[]snmar = snm.getAdress();
		for(int i=0;i<snmar.length;i++){
			if(snmar[i]!=255 && snmar[i]!=0){
				out[0]=ip.getAdress()[i];
				out[1]=i;
				break;
			}
		}
		return out;
	}
	public int getSNNo(IP classnet, IP snm){
		int [] cl_snmar = new int[4];
		int [] cl_ar = classnet.getAdress();
		for(int i=0;i<cl_ar.length;i++){
			if(cl_ar[i]!=0){
				cl_snmar[i]=255;
			}
		}
		IP cl_snm = null;
		try {
			cl_snm = new IP(cl_snmar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[][] cl_snm_bin = cl_snm.getBinary();
		int[][] snm_bin = snm.getBinary();
		int[][] out = new int[4][8];
		
		for(int i=0;i<out.length;i++){
			for(int j=0;j<out[i].length;j++){
				out[i][j]=cl_snm_bin[i][j]^snm_bin[i][j];
			}
		}
		
		int q=0;
		for(int i=0;i<out.length;i++){
			for(int j=0;j<out[i].length;j++){
				if(out[i][j]==1){
					q++;
				}
			}
		}
		
		return (int)Math.pow(2, q);
	}
	//erstes SN = Klassennetz
	//zweites SN: hinterste Stelle im Netzteil wird auf 1 gesetzt
}
