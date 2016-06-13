package wordproject.frames;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import wordproject.Run.Controller.AddVocabularyToDB;

public class FrameNewVocabulary extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textVocabulary;
	private JButton btnNewVocabulary;
	public FrameNewVocabulary() {
		// TODO Auto-generated constructor stub
		
		setTitle("Новий словник...");
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 283, 142);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Назва нового словника");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(5, 14, 161, 23);
		contentPane.add(lblNewLabel);
		
		textVocabulary = new JTextField();
		textVocabulary.setBounds(31, 48, 235, 23);
		contentPane.add(textVocabulary);
		textVocabulary.setColumns(10);
		
		btnNewVocabulary = new JButton("Додати");
		btnNewVocabulary.setBounds(158, 82, 108, 23);
		contentPane.add(btnNewVocabulary);
	}
	public void addNewVocabularyToDB(AddVocabularyToDB addVocabularyToDB) {
		// TODO Auto-generated method stub
		btnNewVocabulary.addActionListener(addVocabularyToDB);
	}
	public String getVocabularyName() {
		// TODO Auto-generated method stub
		return textVocabulary.getText();
	}

}
