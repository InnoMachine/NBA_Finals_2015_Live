package ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LiveFrame extends JFrame {

	private static final long serialVersionUID = 1L;
//	static StartPanel startPanel = null;
	public static LiveInfoPanel liveInfoPanel;

	public static String currentPanel = "";

	public static void main(String[] args) {

		LiveFrame lf = new LiveFrame();
		lf.setVisible(true);

	}

	public LiveFrame() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);
		this.setBounds(bounds);

		liveInfoPanel = new LiveInfoPanel(this);
		this.add(liveInfoPanel);

	}
}
