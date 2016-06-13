package wordproject.Graphics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class GraphPieVocabularies extends JFrame{

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	Connection connection;
	
	public GraphPieVocabularies(Connection connection) {
		// TODO Auto-generated constructor stub
		setTitle("Статистика словників");
		setSize(700, 700);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		
		this.connection = connection;
		
		setContentPane(createPiePanel( ));
	}

	private  PieDataset createDataset( ) 
	   {
	      DefaultPieDataset dataset = new DefaultPieDataset( );   
	      Statement statement = null;
	      try {
	    	  String query = "SELECT V.VocabularyName, COUNT(DISTINCT G.GameID) "+
	    			  			"FROM Games G "+ 
	    			  			"INNER JOIN Vocabularies V ON G.VocabularyID = V.VocabularyID "+
	    			  			"GROUP BY V.VocabularyName "+
								"ORDER BY COUNT(DISTINCT G.GameID)";
	    	  connection.setAutoCommit(false);
	    	  statement = connection.createStatement();
	    	  String allGames = "SELECT COUNT(Games.GameID) FROM Games";
	    	  ResultSet resultCount = statement.executeQuery(allGames);
	    	  int countGames = 0;
	    	  while(resultCount.next())
		    	  countGames = resultCount.getInt(1);
	    	  statement.clearBatch();
	    	  ResultSet resultSet = statement.executeQuery(query);
	    	  connection.commit();
	    	  while(resultSet.next()) {
	    		  String name = resultSet.getString(1);
	    		  double value = (resultSet.getDouble(2) * 100) / countGames;
	    		  dataset.setValue(name, value);
	    	  }
	    	  statement.close();
	    	  resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      return dataset;         
	   }
	   private  JFreeChart createChart( PieDataset dataset )
	   {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "Статистика користування словниками",  // chart title 
	         dataset,        // data    
	         true,           // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   public  JPanel createPiePanel( )
	   {
	      JFreeChart chart = createChart(createDataset( ) );  
	      return new ChartPanel( chart ); 
	   }

}
