package wordproject.Run;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import wordproject.Graphics.GraphUserDESC;
import wordproject.Graphics.GraphPieVocabularies;
import wordproject.Graphics.GraphRangeRatio;
import wordproject.frames.FrameAbout;
import wordproject.frames.FrameFilterRating;
import wordproject.frames.FrameNewUser;
import wordproject.frames.FrameNewVocabulary;
import wordproject.frames.FrameNewWord;

public class Controller {
	
	View view;
	Model model;
	FrameNewUser frameNewUser;
	FrameNewVocabulary frameVocabulary;
	FrameNewWord frameNewWord;
	FrameAbout frameAbout;
	FrameFilterRating frameFilterRating;
	Connection connection;
		
	public Controller(View view, Model model) throws SQLException {
		// TODO Auto-generated constructor stub
		
		String db_connect_string = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName = Word Project; integratedSecurity = true";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			connection = DriverManager.getConnection(db_connect_string);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.view = view;
		this.model = model;
		frameNewUser = new FrameNewUser();
		frameVocabulary = new FrameNewVocabulary();
		frameNewWord = new FrameNewWord();
		frameAbout = new FrameAbout();
		frameFilterRating = new FrameFilterRating(connection);
		//���� ���������� ��� ��������
		view.aboutInfo(new AboutInfo());
	
		
		//����� ��� ���� ������ ������������
		view.panelChooseUsers.startGame(new StartGame());
		
		//����� ���� ��� 
		view.startNewGame(new NewGame());
		
		//��������� ������ �������� �� ��������� �� �� ComboBoxModel
		view.panelChooseUsers.setComboBoxModelVocabularies(model.getVocabulariesComboBoxModel(model.getAllVocabularies(connection)));
		
		//��������� ������� ������������ � �� 
		//��������� �� �� ComboBoxModel
		view.panelChooseUsers.setComboBoxModelUsers(model.getUsersComboBoxModel(model.getAllUsers(connection)));

		//���� ����������� ��� ���
		view.panelChooseUsers.userChoice(new UserChoice());
		
		//������ ������ �� ��������� ������� ������ ������������ ��� ���
		view.panelChooseUsers.addUserToPlayListTemp(new UserToPlayListTemp());
		//��������� ������ � ���
		view.panelChooseUsers.removeUserFromPlayList(new RemoveUserFromList());
		
		//����� ��� ������� ������ �����������
		view.panelChooseUsers.addNewUserFrame(new CreateNewUser());
		view.addNewUserFrame(new CreateNewUser());
		frameNewUser.addNewUserToDB(new AddUserToDB());
		
		//����� ��� ��������� ������ ��������
		view.addNewVocabulary(new FrameVocabulary());
		frameVocabulary.addNewVocabularyToDB(new AddVocabularyToDB());
		
		//����� ��� ������� ������ ����� �� ��
		view.addNewWord(new FrameAddNewWord());
		frameNewWord.setComboBoxModelVocabulariesList(model.getVocabulariesComboBoxModel(model.getAllVocabularies(connection)));
		frameNewWord.addNewWord(new AddWordToDB());
		
		//���������� ���������� ���
		view.panelGame.cancelGame(new CancelGame());
		view.cancelGame(new CancelGame());
		
		//����� � ��������
		view.exit(new Exit());
		view.exitListener(new Exit());
		
		/**
		 * ���
		 * ������� ����� ��������� �� ��������� ������ � ���
		 */
		view.panelGame.doMove(new DoMove());
		view.panelGame.passUser(new UserPass());
		
		//����� ��� ����������� ����������
		view.panelChooseUsers.apllyFilter(new FrameFilter());
		view.apllyFilter(new FrameFilter());
		
		//������ �������� ������� 
		frameFilterRating.apllyFilter(new CreateDiagramRange());
		frameFilterRating.buildPieChartVocabularies(new BuildPieChartUsingVocabularies());
		frameFilterRating.buildGraphRangeRatio(new BuildRahgeRatioGraph());
	}
	
	private int gameID = 0;
	private ArrayList<String> users;
	private ArrayList<String> AllUsersGame;
	private String vocabulary ="";
	private int round = 1;
	private int counter = 0;
	private String winnerUserName = "";
	private String priorWord = "";
	int max_count_to_next_round ;

	
	public class BuildRahgeRatioGraph implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GraphRangeRatio grapg = new GraphRangeRatio(connection, frameFilterRating.getUserFilterRatio());
			grapg.setVisible(true);
		}		
	}
	
	
	public class BuildPieChartUsingVocabularies implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GraphPieVocabularies pie = new GraphPieVocabularies(connection);
			pie.setVisible(true);
		}		
	}
	
	
	public class FrameFilter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			frameFilterRating.getUsersCount();
			frameFilterRating.setVisible(true);
		}		
	}
	
	
	public class CreateDiagramRange implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			GraphUserDESC graph = new GraphUserDESC(connection, frameFilterRating.getFilterCount());
			graph.setVisible(true);
		}		
	}
	
	
	public class UserPass implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(view.panelGame.checkPass.isSelected()) {
				JOptionPane.showMessageDialog(view, "������� "+users.get(counter)+" ����� � ���",
						"������ �� ���",
						JOptionPane.INFORMATION_MESSAGE);
				users.remove(counter); 
				
				if(counter == max_count_to_next_round)
					newRound();
				else { 
					max_count_to_next_round--;
					//�������  ���� Ͳ���
					if(max_count_to_next_round == 0) {
						JOptionPane.showMessageDialog(view, "� ���� �� �� ������ ����. ͳ���", "ͳ���",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							connection.setAutoCommit(false);
							for(int i = 0; i < AllUsersGame.size(); i++) {
								PreparedStatement preparedS = connection.prepareStatement("{call dbo.ProcedureUpdateUsersGameCounter(?)}");
								preparedS.setString(1, AllUsersGame.get(i));
								preparedS.execute();
								preparedS.close();
							} 
							connection.commit();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i =view.panelChooseUsers.modelUsersForGame.getRowCount()-1; i > -1;i--)
							view.panelChooseUsers.modelUsersForGame.removeRow(i);
						view.displayPanel("START");
						view.setResizable(true);
						users.clear();
						AllUsersGame.clear();
						gameID = 0;
						round = 1;					
					} 
					if(counter == max_count_to_next_round)
						newRound();
					else 
						view.panelGame.labelUserName.setText(users.get(counter));
				}
			}
		}
		private void newRound() {
			max_count_to_next_round = users.size(); 
			counter = 0;
			++round;
			view.panelGame.setLabelRound(round+"");
			view.panelGame.labelUserName.setText(users.get(counter));
		}
	}
 
	
	public class DoMove implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.panelGame.checkPass.setSelected(false);
			
			// ������� ���� ��������� 1 �������, ���� � � ����������
			if(max_count_to_next_round != 1) {
							
				if(counter < max_count_to_next_round) {
					String user = users.get(counter);
					String wordEN = view.panelGame.getWord();
					String wordUA = view.panelGame.getWordUA();

					// �������� �� ���������� ����� �� ������� �����
					if(getEqualsFirstLetters(wordEN, priorWord)) {
						//�������� �� ����� �� ���� ����������� � ������ ���
						if(getUseResult(wordEN)){
							if(getCorrectness(wordEN, wordUA)) {
								priorWord = wordEN;
								addWord(user, wordEN, wordUA);
								view.panelGame.dft.addRow(new Object[] {round, user, wordEN});
								view.panelGame.clearText();
								view.panelGame.labelLetter.setText(getLastLetter(wordEN));
								counter++;
								if(counter == max_count_to_next_round)
									newRound();
								else { 
									view.panelGame.labelUserName.setText(users.get(counter));
								}
							} else {
								JOptionPane.showMessageDialog(view, "������� ����� �� �������, ��� �� � ���� ��������!","�����",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(view, "���� ����� ��� ���� ����������� � ������ ���!",
									"�����", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(view, "�� �� �� ����� �����!", "�����",
								JOptionPane.ERROR_MESSAGE);						
					}
										
				} else 
					newRound();
			//�������� ��������� �� ��
			//��������� ���������� ��� ������������
			//����������� ��������	
			} else { 
				addWord(users.get(counter), view.panelGame.getWord(), view.panelGame.getWordUA());
				view.panelGame.clearText();
				for(int i = view.panelGame.dft.getRowCount()-1; i > -1; i--)
					view.panelGame.dft.removeRow(i);				
				
				winnerUserName = users.get(0);
				JOptionPane.showMessageDialog(view, "�������: "+winnerUserName+" ������!", "³�����",
						JOptionPane.INFORMATION_MESSAGE);
				
				//��������� ���������� ��� ������������
				try {
					connection.setAutoCommit(false);
					PreparedStatement preparedStatement = connection.prepareStatement("{call dbo.ProcedureUpdateWinnerInfo(?,?,?)}");
					preparedStatement.setString(1, winnerUserName);
					preparedStatement.setInt(2, round);
					preparedStatement.setInt(3, gameID);
					preparedStatement.execute();
					preparedStatement.close();
					for(int i = 0; i < AllUsersGame.size(); i++) {
						PreparedStatement preparedS = connection.prepareStatement("{call dbo.ProcedureUpdateUsersGameCounter(?)}");
						preparedS.setString(1, AllUsersGame.get(i));
						preparedS.execute();
						preparedS.close();
					} 
					
					connection.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(int i =view.panelChooseUsers.modelUsersForGame.getRowCount()-1; i > -1;i--)
					view.panelChooseUsers.modelUsersForGame.removeRow(i);
				view.displayPanel("START");
				users.clear();
				AllUsersGame.clear();
				gameID = 0;
				round = 1;
				priorWord = "";
			}
		}	
		
		private boolean getCorrectness(String wordEN, String wordUA) { 
			// TODO Auto-generated method stub
			int result = 0; 
			try {
				connection.setAutoCommit(false);
				CallableStatement callableStatement = connection.prepareCall("{? = call dbo.ProcedureVerifyEnglishWord(?,?,?)}");
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, wordUA);
				callableStatement.setString(3, wordEN);
				callableStatement.setString(4, vocabulary);
				callableStatement.execute();
				connection.commit();
				result = callableStatement.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result == 1)
				return true;  
			else
				return false;
		}

		private String getLastLetter(String wordEN) {
			// TODO Auto-generated method stub
			int size = wordEN.length();
			char[] array = wordEN.toCharArray();
			return String.valueOf(array[size-1]).toUpperCase();
		}

		private boolean getUseResult(String wordEN) {
			// TODO Auto-generated method stub
			int result = 0;
			try { 
				connection.setAutoCommit(false);
				CallableStatement callableStatement = connection.prepareCall("{? = call dbo.ProcedureIsWordUsed(?,?)}");
				callableStatement.registerOutParameter(1, Types.INTEGER); 
				callableStatement.setString(2, wordEN);
				callableStatement.setInt(3, gameID);
				callableStatement.execute();
				connection.commit();
				result = callableStatement.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result == 1)
				return true;
			else
				return false;
		}

		private boolean getEqualsFirstLetters(String wordEN, String priorWord) {
			// TODO Auto-generated method stub
			wordEN.replaceAll("[\\s]{1,}", "");
			char[] a = wordEN.toCharArray();
			String letterN = String.valueOf(a[0]).toLowerCase();
			
			if(priorWord != "") {
				int size = priorWord.length();
				char[] b = priorWord.toCharArray();
				String letterP = String.valueOf(b[size-1]).toLowerCase();
				if(letterP.equals(letterN))
					return true;
				else
					return false;
				}
			else return true;
		}

		private void addWord(String user, String wordEN, String wordUA) {
			// TODO Auto-generated method stub
			PreparedStatement preparedStatement;
			try {
				connection.setAutoCommit(false);
				preparedStatement = connection.prepareStatement("{call dbo.ProcedureAddMove(?,?,?,?,?,?)}");
				preparedStatement.setInt(1, gameID);
				preparedStatement.setString(2, user);
				preparedStatement.setString(3, wordEN);
				preparedStatement.setString(4, wordUA); 
				preparedStatement.setString(5, vocabulary);
				preparedStatement.setInt(6, round);
				preparedStatement.execute();
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		private void newRound() {
			max_count_to_next_round = users.size(); 
			counter = 0;
			++round;
			view.panelGame.setLabelRound(round+"");
			view.panelGame.labelUserName.setText(users.get(counter));
		}
	}
	
	public class Exit implements ActionListener , WindowListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			close();
		}

		private void close() {
			Object[] options = {  
					"���",
					"ͳ"
			};
			int res = JOptionPane.showOptionDialog(view, "������� �������?","�����",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			switch (res)
			{
			case JOptionPane.YES_OPTION : 
				try {
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
				break;
			}
		}
		
		public void windowActivated(WindowEvent e) {
		}

		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) { 
			// TODO Auto-generated method stub
			try {
				if(gameID > 0) {
					connection.setAutoCommit(false);
					PreparedStatement preparedStatement = connection.prepareStatement("{call dbo.ProcedureDeleteAbortGameData(?)}");
					preparedStatement.setInt(1, gameID);
					preparedStatement.execute();
					connection.commit();
				}
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		public void windowDeactivated(WindowEvent e) {	}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {			
		}

		public void windowOpened(WindowEvent e) {
		}		
	}
	
	public class CancelGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object[] options = {  
					"���",
					"ͳ"
			};
			int res = JOptionPane.showOptionDialog(view, "��������� ���?","�����",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null, options, options[0]);
			switch (res)
			{
			case JOptionPane.YES_OPTION : 
					try {
						connection.setAutoCommit(false);
						PreparedStatement preparedStatement = connection.prepareStatement("{call dbo.ProcedureDeleteAbortGameData(?)}");
						preparedStatement.setInt(1, gameID);
						preparedStatement.execute();
						connection.commit();
						for(int i = view.panelGame.dft.getRowCount()-1; i > -1; i--)
							view.panelGame.dft.removeRow(i);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					view.displayPanel("START");
					view.setResizable(true);
	
					users.clear();
					AllUsersGame.clear();
					gameID = 0;
					round = 1;
					priorWord = "";
				break;
			}
			
		}		
	}
	
	public class AddWordToDB implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String EnWord = frameNewWord.getEnglishWord();
			String UaWord = frameNewWord.getUkraineWord();
			String Vocabulary = frameNewWord.getSelectedVocabulary();
			
			if(EnWord.length() >= 2){
				if(UaWord.length() >= 2) {
					try {
						connection.setAutoCommit(false);
						CallableStatement statement = connection.prepareCall("{? = call dbo.ProcedureAddWord(?,?,?)}");
						statement.registerOutParameter(1, Types.INTEGER);
						statement.setString(2, EnWord);
						statement.setString(3, UaWord);
						statement.setString(4, Vocabulary);
						statement.execute(); 
						connection.commit();
						if(statement.getInt(1) == 0)
							JOptionPane.showMessageDialog(frameNewWord, "�����: "+EnWord+" ��� � � ���.", "�����",
									JOptionPane.ERROR_MESSAGE);
						else
							if(statement.getInt(1) == 2)
								JOptionPane.showMessageDialog(frameNewWord, "�����: "+EnWord+" �������� �� ��������.", "������",
										JOptionPane.INFORMATION_MESSAGE);
							else	
								JOptionPane.showMessageDialog(frameNewWord, "�����: "+EnWord+" �������� �� ����.", "������",
										JOptionPane.INFORMATION_MESSAGE);
						statement.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						try {
							connection.rollback();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} else 
					JOptionPane.showMessageDialog(frameNewWord, "������ �������� �����.", "�����",
							JOptionPane.WARNING_MESSAGE);
			} else 
				JOptionPane.showMessageDialog(frameNewWord, "������ ��������� �����.", "�����",
						JOptionPane.WARNING_MESSAGE);
		}		
	}
	
	public class FrameAddNewWord implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent e) { 
			// TODO Auto-generated method stub
			frameNewWord.setVisible(true);
		}		
	}
	
	public class AddVocabularyToDB implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String vocabulary = frameVocabulary.getVocabularyName();
			if(vocabulary.length() >= 3) {
				try { 
					connection.setAutoCommit(false);
					CallableStatement preparedStatement = connection.prepareCall("{? = call dbo.ProcedureAddNewVocabulary(?)}");
					preparedStatement.registerOutParameter(1, Types.INTEGER);
					preparedStatement.setString(2, vocabulary);
					preparedStatement.execute();
					connection.commit(); 
					if(preparedStatement.getInt(1) == 0)
						JOptionPane.showMessageDialog(frameVocabulary, "����� ������� ����", "�����", JOptionPane.WARNING_MESSAGE);
					else {
						JOptionPane.showMessageDialog(frameVocabulary, "�������: "+vocabulary+" ��������", "��������...", JOptionPane.INFORMATION_MESSAGE);
						view.panelChooseUsers.setComboBoxModelUsers(model.getUsersComboBoxModel(model.getAllUsers(connection)));
						view.panelChooseUsers.setComboBoxModelVocabularies(model.getVocabulariesComboBoxModel(model.getAllVocabularies(connection)));
						frameNewWord.setComboBoxModelVocabulariesList(model.getVocabulariesComboBoxModel(model.getAllVocabularies(connection)));
					}
					preparedStatement.close(); 
				} catch (SQLException error) {
					// TODO Auto-generated catch block
					error.printStackTrace();
					try {
						connection.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else 
				JOptionPane.showMessageDialog(frameVocabulary, "������ ����� ��������.", "�����",
						JOptionPane.WARNING_MESSAGE);
		}		
	}
	
	public class FrameVocabulary implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frameVocabulary.setVisible(true);
		}		
	}
	
	public class AddUserToDB implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) { 
			// TODO Auto-generated method stub
			String user = frameNewUser.getUserName();
			if(user.length() >= 3) {
				try {
					connection.setAutoCommit(false);
					CallableStatement preparedStatement = connection.prepareCall("{? = call dbo.ProcedureAddNewUser(?)}");
					preparedStatement.registerOutParameter(1, Types.INTEGER);
					preparedStatement.setString(2, user);
					preparedStatement.execute();
					connection.commit(); 
					if(preparedStatement.getInt(1)==0)
						JOptionPane.showMessageDialog(frameNewUser, "����� ���������� ����", "�����", JOptionPane.WARNING_MESSAGE);
					else {
						JOptionPane.showMessageDialog(frameNewUser, "�����������: "+user+" ��������", "��������", JOptionPane.INFORMATION_MESSAGE);
						view.panelChooseUsers.setComboBoxModelUsers(model.getUsersComboBoxModel(model.getAllUsers(connection)));
						view.panelChooseUsers.setComboBoxModelUsers(model.getUsersComboBoxModel(model.getAllUsers(connection)));
					}
					preparedStatement.close(); 
				} catch (SQLException error) {
					// TODO Auto-generated catch block
					error.printStackTrace();
					try {
						connection.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();	
					}
				}
			}else 
				JOptionPane.showMessageDialog(view, "������ �� �����������.", "�����",
						JOptionPane.WARNING_MESSAGE);
		}		
	}
	
	//������ ������ ��� ������� ������ �����������
	public class CreateNewUser implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frameNewUser.setVisible(true);
		}		
	}
	
	public class RemoveUserFromList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int index = view.panelChooseUsers.table.getSelectedRow();
			if(index == -1)
				JOptionPane.showMessageDialog(view, "������� � ������� ����������� ��� ��������� � ������.",
						"�����", JOptionPane.WARNING_MESSAGE);
			else
				view.panelChooseUsers.modelUsersForGame.removeRow(index);
			view.panelChooseUsers.setCountUsersToPlay(view.panelChooseUsers.modelUsersForGame.getRowCount());
		}		
	}
	
	public class UserToPlayListTemp implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user = String.valueOf(
					view.panelChooseUsers.comboBoxAllUsers.getSelectedItem());
			int size = view.panelChooseUsers.modelUsersForGame.getRowCount();
			if(size == 0)
				view.panelChooseUsers.modelUsersForGame.addRow(new Object[] {user});
			boolean flag = false;
			for(int i = 0; i < size ; i++)
				if(view.panelChooseUsers.modelUsersForGame.getValueAt(i, 0) == user){
					flag = false;
					JOptionPane.showMessageDialog(view, "����� ���������� ��� ��������.",
							"�����", JOptionPane.WARNING_MESSAGE);
					break;
				} else 
					flag = true; 
			if(flag)
				view.panelChooseUsers.modelUsersForGame.addRow(new Object[] {user});
			size = view.panelChooseUsers.modelUsersForGame.getRowCount();
			view.panelChooseUsers.setCountUsersToPlay(size);
		}		
	}
	
	public class UserChoice implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub
			ItemSelectable item = event.getItemSelectable();
			Object[] obj = item.getSelectedObjects();
			try {
				view.panelChooseUsers.setUserData(model.getUserData(String.valueOf(obj[0]), connection));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	class NewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.displayPanel("START");
		}		
	}
	
	class AboutInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			frameAbout.setVisible(true);
		}
	}
	
	public class StartGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(view.panelChooseUsers.modelUsersForGame.getRowCount() <= 1) {
				JOptionPane.showMessageDialog(view, "������ �������","�����",
						JOptionPane.WARNING_MESSAGE);
			}
			else {
				view.displayPanel("GAME");
				view.setResizable(false);
				vocabulary = String.valueOf(view.panelChooseUsers.comboBoxVocabularies.getSelectedItem());
				users = new ArrayList<String>();
				int size = view.panelChooseUsers.modelUsersForGame.getRowCount();
				for(int i = 0; i < size; i++) 
					users.add((String) view.panelChooseUsers.modelUsersForGame.getValueAt(i, 0));
				AllUsersGame = new ArrayList<String>();
				for(int i = 0; i < size; i++) 
					AllUsersGame.add((String) view.panelChooseUsers.modelUsersForGame.getValueAt(i, 0));
				
				try {
					connection.setAutoCommit(false);
					CallableStatement callableStatement = connection.prepareCall("{? = call dbo.ProcedureCreateGame(?)}");
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setString(2, vocabulary);
					callableStatement.execute();
					connection.commit();
					gameID = callableStatement.getInt(1);
					System.out.println("GAME: "+gameID);
					callableStatement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				view.panelGame.setLabelRound(round+"");
				view.panelGame.labelUserName.setText(users.get(counter));
				view.panelGame.labelVocabulary.setText(vocabulary);
				max_count_to_next_round = users.size();
			}	
		}
	}
}

