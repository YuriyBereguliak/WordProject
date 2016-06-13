package wordproject.frames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import wordproject.Run.Controller.AddUserToDB;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class FrameNewUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
		
	public FrameNewUser() {
		setTitle("\u041D\u043E\u0432\u0438\u0439 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447");
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 283, 142);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u043D\u0456\u043A  \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(5, 14, 161, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 48, 235, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnAddUser = new JButton("Додати");
		btnAddUser.setBounds(158, 82, 108, 23);
		contentPane.add(btnAddUser);
	}

	JButton btnAddUser;

	public void addNewUserToDB(AddUserToDB addUserToDB) {
		// TODO Auto-generated method stub
		btnAddUser.addActionListener(addUserToDB);
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return textField.getText();
	}

}
