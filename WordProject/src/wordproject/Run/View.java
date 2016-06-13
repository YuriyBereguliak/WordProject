package wordproject.Run;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import wordproject.Panels.PanelChooseUsers;
import wordproject.Panels.PanelGame;
import wordproject.Run.Controller.FrameAddNewWord;
import wordproject.Run.Controller.FrameFilter;
import wordproject.Run.Controller.AboutInfo;
import wordproject.Run.Controller.CancelGame;
import wordproject.Run.Controller.CreateNewUser;
import wordproject.Run.Controller.Exit;
import wordproject.Run.Controller.FrameVocabulary;
import wordproject.Run.Controller.NewGame;


public class View extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelGame panelGame;
	public PanelChooseUsers panelChooseUsers; 
	
	CardLayout cardLayout;
	JPanel panel;
	
	JMenuItem menuItemNewGame;
	JMenuItem menuItemCreateUser;
	JMenuItem menuItemCreateVocabulary;
	JMenuItem menuItemAddWord;
	JMenuItem menuItemNimbus;
	JMenuItem menuItemSystem;
	JMenuItem menuItemExit ;
	JMenuItem menuItemAboutProgram;
	JMenuItem menuItemUsersRatio;
	
	public View() {
		// TODO Auto-generated constructor stub
		setIconImage(new ImageIcon("icons\\icon.png").getImage());		
		setTitle("Word Project");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//---------------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("Дії");
		menuBar.add(menu);
		
		JMenu menuHelp = new JMenu("Додатково");
		menuBar.add(menuHelp);
		
		menuItemNewGame = new JMenuItem("Нова гра");
		menu.add(menuItemNewGame);
		menu.addSeparator();
		
		menuItemUsersRatio = new JMenuItem("Статистичні дані");
		menu.add(menuItemUsersRatio);
		menu.addSeparator();
		
		menuItemCreateUser = new JMenuItem("Новий користувач");
		menu.add(menuItemCreateUser);
		
		menuItemCreateVocabulary = new JMenuItem("Створити словник");
		menu.add(menuItemCreateVocabulary);
		
		menuItemAddWord = new JMenuItem("Додати слово");
		menu.add(menuItemAddWord);
		menu.addSeparator();
		
		menuItemExit = new JMenuItem("Вихід");
		menu.add(menuItemExit);
				
	//	JMenuItem manuItemHelp = new JMenuItem("Допомога");
		//menuHelp.add(manuItemHelp);
		//menuHelp.addSeparator();
		
		menuItemAboutProgram = new JMenuItem("Про програму");
		menuHelp.add(menuItemAboutProgram);
//---------------------------------------------------------------		
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		
		panelChooseUsers = new PanelChooseUsers();
		panel.add(panelChooseUsers, "START");
		
		panelGame = new PanelGame();
		panel.add(panelGame, "GAME");

	}

	public void displayPanel(String panelName) {
		cardLayout.show(panel, panelName);
	}

	public void startNewGame(NewGame newGame) {
		// TODO Auto-generated method stub
		menuItemNewGame.addActionListener(newGame);
	}

	public void addNewUserFrame(CreateNewUser createNewUser) {
		// TODO Auto-generated method stub
		menuItemCreateUser.addActionListener(createNewUser);
	}

	public void addNewVocabulary(FrameVocabulary frameVocabulary) {
		// TODO Auto-generated method stub
		menuItemCreateVocabulary.addActionListener(frameVocabulary);
	}

	public void addNewWord(FrameAddNewWord addNewWordToDB) {
		// TODO Auto-generated method stub
		menuItemAddWord.addActionListener(addNewWordToDB);
	}

	public void cancelGame(CancelGame cancelGame) {
		// TODO Auto-generated method stub
		menuItemNewGame.addActionListener(cancelGame);
	}

	public void exit(Exit exit) {
		// TODO Auto-generated method stub
		menuItemExit.addActionListener(exit);
	}

	public void exitListener(Exit exit) {
		// TODO Auto-generated method stub
		this.addWindowListener(exit);
	}

	public void aboutInfo(AboutInfo aboutInfo) {
		// TODO Auto-generated method stub
		menuItemAboutProgram.addActionListener(aboutInfo);
	}

	public void apllyFilter(FrameFilter frameFilter) {
		// TODO Auto-generated method stub
		menuItemUsersRatio.addActionListener(frameFilter);
	}


}
