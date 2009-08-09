import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Taschenrechner extends JFrame implements ActionListener {
	JTextField text = new JTextField();

	JButton but0 = new JButton("0");

	JButton but1 = new JButton("1");

	JButton but2 = new JButton("2");

	JButton but3 = new JButton("3");

	JButton but4 = new JButton("4");

	JButton but5 = new JButton("5");

	JButton but6 = new JButton("6");

	JButton but7 = new JButton("7");

	JButton but8 = new JButton("8");

	JButton but9 = new JButton("9");

	JButton komma = new JButton(".");

	JButton gleich = new JButton("=");

	JButton div = new JButton("/");

	JButton mult = new JButton("*");

	JButton minus = new JButton("-");

	JButton plus = new JButton("+");

	JButton clear = new JButton("Clear");

	public void init() {
		setTitle("Taschenrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(210, 220);
		Container cp = getContentPane();
		JPanel p1 = new JPanel();
		cp.setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(4, 4));
		cp.add(text, BorderLayout.NORTH);
		cp.add(p1, BorderLayout.CENTER);
		but7.addActionListener(this);
		p1.add(but7);
		but8.addActionListener(this);
		p1.add(but8);
		but9.addActionListener(this);
		p1.add(but9);
		div.addActionListener(this);
		p1.add(div);
		but4.addActionListener(this);
		p1.add(but4);
		but5.addActionListener(this);
		p1.add(but5);
		but6.addActionListener(this);
		p1.add(but6);
		mult.addActionListener(this);
		p1.add(mult);
		but1.addActionListener(this);
		p1.add(but1);
		but2.addActionListener(this);
		p1.add(but2);
		but3.addActionListener(this);
		p1.add(but3);
		minus.addActionListener(this);
		p1.add(minus);
		but0.addActionListener(this);
		p1.add(but0);
		komma.addActionListener(this);
		p1.add(komma);
		gleich.addActionListener(this);
		p1.add(gleich);
		plus.addActionListener(this);
		p1.add(plus);
		clear.addActionListener(this);
		cp.add(clear, BorderLayout.SOUTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Taschenrechner().init();
	}

	double ergeb = 0;

	String operator = "";

	public void actionPerformed(ActionEvent e) {
		double zahl;
		if (e.getSource() == but0) {
			text.setText(text.getText() + "0");
		}
		if (e.getSource() == but1) {
			text.setText(text.getText() + "1");
		}
		if (e.getSource() == but2) {
			text.setText(text.getText() + "2");
		}
		if (e.getSource() == but3) {
			text.setText(text.getText() + "3");
		}
		if (e.getSource() == but4) {
			text.setText(text.getText() + "4");
		}
		if (e.getSource() == but5) {
			text.setText(text.getText() + "5");
		}
		if (e.getSource() == but6) {
			text.setText(text.getText() + "6");
		}
		if (e.getSource() == but7) {
			text.setText(text.getText() + "7");
		}
		if (e.getSource() == but8) {
			text.setText(text.getText() + "8");
		}
		if (e.getSource() == but9) {
			text.setText(text.getText() + "9");
		}
		if (e.getSource() == komma) {
			text.setText(text.getText() + ".");
		}
		if (e.getSource() == plus) {
			if (ergeb == 0) {
				ergeb = Double.parseDouble(text.getText());
				operator = "+";
				text.setText("");
			} else {
				zahl = Double.parseDouble(text.getText());
				if (operator.equals("+")) {
					ergeb = ergeb + zahl;
					operator = "+";
					System.out.println(ergeb);
				}
				if (operator.equals("-")) {
					ergeb = ergeb - zahl;
					operator = "+";
				}
				if (operator.equals("*")) {
					ergeb = ergeb * zahl;
					operator = "+";
				}
				if (operator.equals("/")) {
					ergeb = ergeb / zahl;
					operator = "+";
				}
				text.setText("");
			}
		}
		if (e.getSource() == minus) {
			if (ergeb == 0) {
				ergeb = Double.parseDouble(text.getText());
				operator = "-";
				text.setText("");
			} else {
				zahl = Double.parseDouble(text.getText());
				if (operator.equals("+")) {
					ergeb = ergeb + zahl;
					operator = "-";
				}
				if (operator.equals("-")) {
					ergeb = ergeb - zahl;
					operator = "-";
				}
				if (operator.equals("*")) {
					ergeb = ergeb * zahl;
					operator = "-";
				}
				if (operator.equals("/")) {
					ergeb = ergeb / zahl;
					operator = "-";
				}
				text.setText("");
			}
		}
		if (e.getSource() == mult) {
			if (ergeb == 0) {
				ergeb = Double.parseDouble(text.getText());
				operator = "*";
				text.setText("");
			} else {
				zahl = Double.parseDouble(text.getText());
				if (operator.equals("+")) {
					ergeb = ergeb + zahl;
					operator = "*";
				}
				if (operator.equals("-")) {
					ergeb = ergeb - zahl;
					operator = "*";
				}
				if (operator.equals("*")) {
					ergeb = ergeb * zahl;
					operator = "*";
				}
				if (operator.equals("/")) {
					ergeb = ergeb / zahl;
					operator = "*";
				}
				text.setText("");
			}
		}
		if (e.getSource() == div) {
			if (ergeb == 0) {
				ergeb = Double.parseDouble(text.getText());
				operator = "/";
				text.setText("");
			} else {
				zahl = Double.parseDouble(text.getText());
				if (operator.equals("+")) {
					ergeb = ergeb + zahl;
					operator = "/";
				}
				if (operator.equals("-")) {
					ergeb = ergeb - zahl;
					operator = "/";
				}
				if (operator.equals("*")) {
					ergeb = ergeb * zahl;
					operator = "/";
				}
				if (operator.equals("/")) {
					ergeb = ergeb / zahl;
					operator = "/";
				}
				text.setText("");
			}
		}
		if (e.getSource() == gleich) {
			zahl = Double.parseDouble(text.getText());
			if (operator.equals("+")) {
				ergeb = ergeb + zahl;
			}
			if (operator.equals("-")) {
				ergeb = ergeb - zahl;
			}
			if (operator.equals("*")) {
				ergeb = ergeb * zahl;
			}
			if (operator.equals("/")) {
				ergeb = ergeb / zahl;
			}
			text.setText(ergeb + "");
		}
		if (e.getSource() == clear) {
			text.setText("");
			zahl = 0;
			ergeb = 0;
			operator = "";
		}
	}

}
