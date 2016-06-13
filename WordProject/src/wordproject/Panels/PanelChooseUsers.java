package wordproject.Panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import wordproject.Run.Controller.CreateNewUser;
import wordproject.Run.Controller.FrameFilter;
import wordproject.Run.Controller.RemoveUserFromList;
import wordproject.Run.Controller.StartGame;
import wordproject.Run.Controller.UserChoice;
import wordproject.Run.Controller.UserToPlayListTemp;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JSplitPane;

public class PanelChooseUsers extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxAllUsers;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxVocabularies;
	public DefaultTableModel modelUsersForGame;
	public JTable table;
	
	JButton btnStartGame;
	JButton buttonNewUser;
	JButton btnAdd;
	JButton btnRemoveUser;
	JButton btnUsersRatio;
	
	JLabel lNumberOfWins;
	JLabel lGamesCounet;
	JLabel lPerformanceRatio;
	JLabel lUserName;
	JLabel labelUserCount;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public PanelChooseUsers() {
					
		setLayout(new BorderLayout(0, 0));
		
		modelUsersForGame = new DefaultTableModel(new Object[][] {}, new Object[]{"Користувачі"});
		
		JPanel panelCenter = new JPanel();
		//add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(0, 1));
		
		Box horizontalBoxChooseUser = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxChooseUser);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBoxChooseUser.add(horizontalStrut_4);
		
		JLabel labelUser = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		horizontalBoxChooseUser.add(labelUser);
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCenter.add(horizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		comboBoxAllUsers = new JComboBox<String>();
		horizontalBox.add(comboBoxAllUsers);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_5);
		
		Box horizontalBoxNumberOfWins = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxNumberOfWins);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBoxNumberOfWins.add(horizontalStrut_9);
		
		JLabel labelNumberOfWins = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043F\u0435\u0440\u0435\u043C\u043E\u0433:");
		horizontalBoxNumberOfWins.add(labelNumberOfWins);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBoxNumberOfWins.add(horizontalStrut_1);
		
		lNumberOfWins = new JLabel("");
		lNumberOfWins.setFont(new Font("SansSerif", Font.PLAIN, 16));
		horizontalBoxNumberOfWins.add(lNumberOfWins);
		
		Box horizontalBoxGamesCounter = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxGamesCounter);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBoxGamesCounter.add(horizontalStrut_8);
		
		JLabel labelGamesCounter = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0456\u0433\u043E\u0440:");
		horizontalBoxGamesCounter.add(labelGamesCounter);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBoxGamesCounter.add(horizontalStrut_2);
		
		lGamesCounet = new JLabel("");
		lGamesCounet.setFont(new Font("SansSerif", Font.PLAIN, 16));
		horizontalBoxGamesCounter.add(lGamesCounet);
		
		Box horizontalBoxRatio = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxRatio);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBoxRatio.add(horizontalStrut_10);
		
		JLabel labelPerformanceRatio = new JLabel("\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u0433\u0440\u0430\u0432\u0446\u044F:");
		horizontalBoxRatio.add(labelPerformanceRatio);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBoxRatio.add(horizontalStrut_3);
		
		lPerformanceRatio = new JLabel(""); 
		lPerformanceRatio.setFont(new Font("SansSerif", Font.PLAIN, 16));
		horizontalBoxRatio.add(lPerformanceRatio);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelCenter.add(verticalStrut); 
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panelCenter.add(horizontalBox_2);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_13);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u0441\u043B\u043E\u0432\u043D\u0438\u043A");
		horizontalBox_2.add(lblNewLabel);
		
		Box horizontalBoxChoosenUserName = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxChoosenUserName);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBoxChoosenUserName.add(horizontalStrut_7);
		
		comboBoxVocabularies = new JComboBox();
		horizontalBoxChoosenUserName.add(comboBoxVocabularies);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBoxChoosenUserName.add(horizontalStrut_12);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelCenter.add(horizontalBox_1);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_2);
		
		Box horizontalBoxUserControll = Box.createHorizontalBox();
		panelCenter.add(horizontalBoxUserControll);
		
		labelUserCount = new JLabel("Кількість гравців 0");
		horizontalBoxUserControll.add(labelUserCount);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		panelCenter.add(horizontalBox_4);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox_4.add(horizontalGlue_4);
		
		btnRemoveUser = new JButton("Видалити гравця");
		horizontalBox_4.add(btnRemoveUser);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_6);
		
		btnAdd = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438 \u0434\u043E \u0433\u0440\u0438");
		horizontalBox_4.add(btnAdd);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelCenter.add(verticalStrut_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panelCenter.add(horizontalBox_3);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_3.add(horizontalGlue_5);
		
		buttonNewUser = new JButton("\u0421\u0442\u0432\u043E\u0440\u0438\u0442\u0438 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		horizontalBox_3.add(buttonNewUser);
		
		btnUsersRatio = new JButton("\u0420\u0435\u0439\u0442\u0438\u043D\u0433\u0438 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432");
		horizontalBox_3.add(btnUsersRatio);
		
		
		JPanel panelTableUser = new JPanel();
		panelTableUser.setLayout(new BorderLayout());
		
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		panelTableUser.add(scroll, BorderLayout.CENTER);
		modelUsersForGame = new DefaultTableModel(new Object[][] {
			},
			new String[] {
				"Гравці"
			});
		table.setModel(modelUsersForGame);
		
		JSplitPane splitPane = new JSplitPane();
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(false);
		splitPane.setDividerLocation(300);
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(panelCenter);
		splitPane.setRightComponent(panelTableUser);
		
		JPanel panelTitle = new JPanel();
		add(panelTitle, BorderLayout.NORTH);
		//add(panel, BorderLayout.NORTH);
		panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.X_AXIS));
		
		Box horizontalBoxNorthTitle = Box.createHorizontalBox();
		panelTitle.add(horizontalBoxNorthTitle);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBoxNorthTitle.add(horizontalGlue);
		
		JLabel labelTitle = new JLabel("WORD Project");
		horizontalBoxNorthTitle.add(labelTitle);
		labelTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBoxNorthTitle.add(horizontalGlue_1);
		
		Box horizontalBoxButtons = Box.createHorizontalBox();
		add(horizontalBoxButtons, BorderLayout.SOUTH);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBoxButtons.add(horizontalGlue_3);
		
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		horizontalBoxButtons.add(verticalStrut_1);
		
		btnStartGame = new JButton("\u041F\u043E\u0447\u0430\u0442\u0438 \u0433\u0440\u0443");
		horizontalBoxButtons.add(btnStartGame);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalBoxButtons.add(horizontalStrut_11);
	}

	public void startGame(StartGame startGame) {
		// TODO Auto-generated method stub
		btnStartGame.addActionListener(startGame);
	}

	@SuppressWarnings("unchecked")
	public void setComboBoxModelUsers(DefaultComboBoxModel<String> usersComboBoxModel) {
		// TODO Auto-generated method stub
		comboBoxAllUsers.setModel(usersComboBoxModel);
	}

	public void userChoice(UserChoice userChoice) {
		// TODO Auto-generated method stub
		comboBoxAllUsers.addItemListener(userChoice);
	}

	public void setUserData(ArrayList<String> userData) {
		// TODO Auto-generated method stub
		for (int i = 0; i < userData.size(); i++) {
			System.out.println(""+userData.get(i));
		}
		lNumberOfWins.setText(userData.get(0));
		lGamesCounet.setText(userData.get(1));
		lPerformanceRatio.setText(userData.get(2));
	}

	public void addUserToPlayListTemp(UserToPlayListTemp userToPlayListTemp) {
		// TODO Auto-generated method stub
		btnAdd.addActionListener(userToPlayListTemp);
	}

	public void setCountUsersToPlay(int size) {
		// TODO Auto-generated method stub
		labelUserCount.setText("Кількість гравців "+size);
	}

	public void removeUserFromPlayList(RemoveUserFromList removeUserFromList) {
		// TODO Auto-generated method stub
		btnRemoveUser.addActionListener(removeUserFromList);
	}

	public void addNewUserFrame(CreateNewUser createNewUser) {
		// TODO Auto-generated method stub
		buttonNewUser.addActionListener(createNewUser);
	}

	@SuppressWarnings("unchecked")
	public void setComboBoxModelVocabularies(DefaultComboBoxModel<String> vocabulariesComboBoxModel) {
		// TODO Auto-generated method stub
		comboBoxVocabularies.setModel(vocabulariesComboBoxModel);
	}

	public void apllyFilter(FrameFilter frameFilter) {
		// TODO Auto-generated method stub
		btnUsersRatio.addActionListener(frameFilter);
	}

	
}
