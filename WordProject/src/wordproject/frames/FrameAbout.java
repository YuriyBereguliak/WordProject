package wordproject.frames;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameAbout extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameAbout() {
		// TODO Auto-generated constructor stub
		setTitle("Про програму");
		setSize(600, 300);
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("icons\\about.png"));
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
	}

}
