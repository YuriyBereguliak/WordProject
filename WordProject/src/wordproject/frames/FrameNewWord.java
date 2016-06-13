package wordproject.frames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import wordproject.Run.Controller.AddWordToDB;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FrameNewWord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEnglishWord;
	private JTextField textFieldUkraineWord;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	JButton btnAddWord;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public FrameNewWord() {
		setResizable(false);
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		setTitle("\u041D\u043E\u0432\u0435 \u0441\u043B\u043E\u0432\u043E");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 227);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0441\u043B\u043E\u0432\u043E \u043D\u0430 \u0430\u043D\u0433\u043B\u0456\u0439\u0441\u044C\u043A\u0456\u0439");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 23, 167, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u043F\u0435\u0440\u0435\u043A\u043B\u0430\u0434 \u0441\u043B\u043E\u0432\u0430");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 65, 167, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldEnglishWord = new JTextField();
		textFieldEnglishWord.setBounds(233, 21, 146, 20);
		contentPane.add(textFieldEnglishWord);
		textFieldEnglishWord.setColumns(10);
		
		textFieldUkraineWord = new JTextField();
		textFieldUkraineWord.setBounds(233, 63, 146, 20);
		contentPane.add(textFieldUkraineWord);
		textFieldUkraineWord.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u0441\u043B\u043E\u0432\u043D\u0438\u043A");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(21, 107, 135, 14);
		contentPane.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(233, 105, 146, 20);
		contentPane.add(comboBox);
		
		btnAddWord = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
		btnAddWord.setBounds(290, 164, 89, 23);
		contentPane.add(btnAddWord);
		
		JButton btnClear = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u0438");
		btnClear.setBounds(188, 164, 89, 23);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textFieldEnglishWord.setText("");
				textFieldUkraineWord.setText("");
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void setComboBoxModelVocabulariesList(DefaultComboBoxModel<String> vocabulariesComboBoxModel) {
		// TODO Auto-generated method stub
		comboBox.setModel(vocabulariesComboBoxModel);
	}

	public void addNewWord(AddWordToDB addWordToDB) {
		// TODO Auto-generated method stub
		btnAddWord.addActionListener(addWordToDB);
	}

	public String getEnglishWord() {
		// TODO Auto-generated method stub
		return textFieldEnglishWord.getText();
	}

	public String getUkraineWord() {
		// TODO Auto-generated method stub
		return textFieldUkraineWord.getText();
	}

	public String getSelectedVocabulary() {
		// TODO Auto-generated method stub
		return String.valueOf(comboBox.getSelectedItem());
	}
}
