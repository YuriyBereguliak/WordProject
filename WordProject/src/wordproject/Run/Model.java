package wordproject.Run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Model {

	public ArrayList<String> getAllUsers(Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> usersList = new ArrayList<String>();
		
		String query = "SELECT UserName FROM Users;";
		Statement stm = null;
		ResultSet result = null;
		
		try {
			connection.setAutoCommit(false);
			stm = connection.createStatement();
			result = stm.executeQuery(query);
			connection.commit();
			while(result.next())
				usersList.add(result.getString(1));
			result.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			JOptionPane.showMessageDialog(null,
					"Error:"+e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}	
		return usersList;
	}

	public DefaultComboBoxModel<String> getUsersComboBoxModel(ArrayList<String> allUsers) {
		// TODO Auto-generated method stub
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		for (String user : allUsers) 
			comboBoxModel.addElement(user);
		return comboBoxModel;
	}

	public ArrayList<String> getUserData(String name, Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> userList = new ArrayList<String>();
		
		String query = "SELECT UserNumberOfWins, UserGamesCounter,UserPerformanceRatio "
				+ "FROM Users "
				+ "WHERE UserName = ?;";
		PreparedStatement stm = null;
		ResultSet result = null;
		
		try { 
			connection.setAutoCommit(false);
			stm = connection.prepareStatement(query);
			stm.setString(1, name);
			result = stm.executeQuery();
			connection.commit();
			while(result.next()) {
				userList.add(result.getString(1));
				userList.add(result.getString(2));
				DecimalFormat df = new DecimalFormat("0.000");
				userList.add(df.format(result.getDouble(3)));
			}
			result.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			JOptionPane.showMessageDialog(null,
					"Error:"+e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}	
		return userList;
	}

	public ArrayList<String> getAllVocabularies(Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> vocabularyList = new ArrayList<String>();
		
		String query = "SELECT VocabularyName FROM Vocabularies;";
		Statement stm = null;
		ResultSet result = null;
		
		try {
			connection.setAutoCommit(false);
			stm = connection.createStatement();
			result = stm.executeQuery(query);
			connection.commit();
			while(result.next())
				vocabularyList.add(result.getString(1));
			result.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			JOptionPane.showMessageDialog(null,
					"Error:"+e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}	
		return vocabularyList;
	}

	public DefaultComboBoxModel<String> getVocabulariesComboBoxModel(ArrayList<String> allVocabularies) {
		// TODO Auto-generated method stub
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		for (String vocabulary : allVocabularies) 
			comboBoxModel.addElement(vocabulary);
		return comboBoxModel;
	}

}
