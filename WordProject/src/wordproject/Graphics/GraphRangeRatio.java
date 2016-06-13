package wordproject.Graphics;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class GraphRangeRatio extends JFrame{

	Connection connection;
	int count = 0;
	
	public GraphRangeRatio(Connection connection, int count) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.count = count;
		
		JFreeChart barChart = ChartFactory.createBarChart(
		         "Кращі гравці",           
		         "Гравці",            
		         "Рейтинг",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
		ChartPanel chartPanel = new ChartPanel( barChart );        
		      
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		setLayout(new BorderLayout());
		add(chartPanel, BorderLayout.CENTER); 
		setSize(800, 700);
		setLocationRelativeTo(null); 
	}

	private CategoryDataset createDataset() {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset  = new DefaultCategoryDataset();
		
		try {
			connection.setAutoCommit(false);
			String query = "SELECT UserName, UserPerformanceRatio "+
							"FROM Users "+
							"ORDER BY UserPerformanceRatio DESC;"; 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				dataset.addValue(resultSet.getDouble(2), resultSet.getString(1), "");
				count--;
				if(count == 0)
					break;
			}
			connection.commit();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataset;
	}

}
