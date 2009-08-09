package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SplashScreen extends JFrame implements KeyListener, MouseListener {
	static final long serialVersionUID = 8450847887550371560L;
	private JButton but;

	public SplashScreen() {

		setUndecorated(true); // window without frame

		JLabel labelWithPicture = new JLabel(new ImageIcon("comedian.png"));
		labelWithPicture.setBorder(new LineBorder(Color.ORANGE, 4));
		Container win = getContentPane();
		win.setLayout(new BorderLayout());
		win.add(labelWithPicture, BorderLayout.CENTER);

		Dimension label = labelWithPicture.getPreferredSize();
		setBounds(0, 0, label.width, label.height+43); // new frame size
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - label.width) / 2,
				(screen.height - label.height) / 2);
		// pack(); <== must not be used !!!

		ImageIcon img = new ImageIcon("tryandbuy.jpg");

		but = new JButton(img);
		but.addMouseListener(this);
		but.setBorder(new LineBorder(Color.ORANGE, 4));

		win.add(but, BorderLayout.SOUTH);

		addKeyListener(this); // register to frame
		addMouseListener(this); // register to frame
		setVisible(true);
	}

	public void keyReleased(KeyEvent e) { /* ignored */
	}

	public void keyTyped(KeyEvent e) { /* ignored */
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			openApplication(e.getSource());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			closeSplashScreen();
			System.exit(0);
		}
		// ignore all other keys
	}

	public void mouseReleased(MouseEvent e) { /* ignored */
	}

	public void mouseClicked(MouseEvent e) { /* ignored */
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void mousePressed(MouseEvent e) {
		openApplication(e.getSource());
	}

	private void openApplication(Object obj) {
		closeSplashScreen();
		if (obj == this) {
			new ImageChangingButton();
		} else if (obj == but) {
			new MouseListenerTest();
		}
	}

	private void closeSplashScreen() {
		setVisible(false);
		dispose();
	}

	public static void main(String[] args) {
		new SplashScreen();
	}
}
