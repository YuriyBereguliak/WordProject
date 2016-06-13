package wordproject.Panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import wordproject.Run.Controller.CancelGame;
import wordproject.Run.Controller.DoMove;
import wordproject.Run.Controller.UserPass;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel dft;
	public DefaultTableModel modelAllUsers;
	
	JButton btnCancelGame;
	JButton btnAddWord;
	public JCheckBox checkPass;
	public JLabel labelVocabulary;
	public JLabel labelUserName;
	private JLabel labelRound;
	private JTextField textFieldWord;
	private JTextField textFieldUA;
	private JLabel lblNewLabel_4;
	public JLabel labelLetter;
	
	
	/**
	 * Create the panel.
	 */
	public PanelGame() { 
		setLayout(null);
		JLabel label = new JLabel("\u0413\u0420\u0410");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(256, 11, 57, 20);
		add(label);
		
		JLabel lblNewLabel = new JLabel("\u0420\u0410\u0423\u041D\u0414");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 14, 46, 14);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 37, 328, 251); 
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		dft = new DefaultTableModel(new Object[][]{}, new Object[]{"Раунд", "Користувач", "Слово"});
		table.setModel(dft);
		
		JLabel lblNewLabel_1 = new JLabel("\u0425\u0456\u0434 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 39, 111, 14);
		add(lblNewLabel_1);
		
		labelUserName = new JLabel("");
		labelUserName.setForeground(Color.RED);
		labelUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelUserName.setBounds(35, 64, 186, 23);
		add(labelUserName);
		
		labelRound = new JLabel();
		labelRound.setForeground(Color.RED);
		labelRound.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelRound.setBounds(66, 11, 46, 20);
		add(labelRound);
		
		textFieldWord = new JTextField();
		textFieldWord.setBounds(10, 148, 211, 20);
		add(textFieldWord);
		textFieldWord.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0441\u043B\u043E\u0432\u043E (EN)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 123, 201, 14);
		add(lblNewLabel_2);
		
		btnAddWord = new JButton("\u0417\u0440\u043E\u0431\u0438\u0442\u0438 \u0445\u0456\u0434");
		btnAddWord.setForeground(Color.BLACK);
		btnAddWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddWord.setBackground(Color.GREEN);
		btnAddWord.setBounds(99, 256, 122, 32);
		add(btnAddWord);
		
		btnCancelGame = new JButton("\u0417\u0430\u0432\u0435\u0440\u0448\u0438\u0442\u0438 \u0433\u0440\u0443");
		btnCancelGame.setForeground(Color.RED);
		btnCancelGame.setBackground(Color.RED);
		btnCancelGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelGame.setBounds(418, 299, 141, 23);
		add(btnCancelGame);
		
		JLabel lblua = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u043F\u0435\u0440\u0435\u043A\u043B\u0430\u0434 (UA)");
		lblua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblua.setBounds(10, 186, 201, 14);
		add(lblua);
		
		textFieldUA = new JTextField();
		textFieldUA.setBounds(10, 211, 211, 20);
		add(textFieldUA);
		textFieldUA.setColumns(10);
		
		checkPass = new JCheckBox("\u041F\u0430\u0441");
		checkPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkPass.setBounds(10, 265, 64, 23);
		add(checkPass);
	
		modelAllUsers = new DefaultTableModel(new Object[][]{}, new Object[]{"Гравці", "Кількість ходів"});
		
		lblNewLabel_4 = new JLabel("\u0421\u043B\u043E\u0432\u043E \u043D\u0430 \u0431\u0443\u043A\u0432\u0443");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 90, 130, 22);
		add(lblNewLabel_4);
		
		labelLetter = new JLabel("");
		labelLetter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelLetter.setForeground(Color.RED);
		labelLetter.setBounds(121, 89, 46, 23);
		add(labelLetter);
		
		JLabel lblNewLabel_3 = new JLabel("\u0412\u0438\u043A\u043E\u0440\u0438\u0441\u0442\u043E\u0432\u0443\u0454\u0442\u044C\u0441\u044F \u0441\u043B\u043E\u0432\u043D\u0438\u043A");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 305, 160, 14);
		add(lblNewLabel_3);
		
		labelVocabulary = new JLabel("");
		labelVocabulary.setForeground(Color.RED);
		labelVocabulary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelVocabulary.setBounds(180, 305, 201, 14);
		add(labelVocabulary);
		
	}

	public JLabel getLabelRound() {
		return labelRound;
	}

	public void setLabelRound(String labelRound) {
		this.labelRound.setText(labelRound);
	}

	public void cancelGame(CancelGame cancelGame) {
		// TODO Auto-generated method stub
		btnCancelGame.addActionListener(cancelGame);
	}

	public void doMove(DoMove doMove) {
		// TODO Auto-generated method stub
		btnAddWord.addActionListener(doMove);
	}

	public String getWord() {
		return textFieldWord.getText();
	}
	
	public String getWordUA() {
		return textFieldUA.getText();
	}

	public void clearText() {
		// TODO Auto-generated method stub
		textFieldUA.setText("");
		textFieldWord.setText("");
		labelLetter.setText("");

		labelUserName.setText("");
	}

	public void passUser(UserPass userPass) {
		// TODO Auto-generated method stub
		checkPass.addItemListener(userPass);
	}
}
