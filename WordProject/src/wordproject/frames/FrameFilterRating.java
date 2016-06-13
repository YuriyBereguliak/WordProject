package wordproject.frames;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import wordproject.Run.Controller.BuildPieChartUsingVocabularies;
import wordproject.Run.Controller.BuildRahgeRatioGraph;
import wordproject.Run.Controller.CreateDiagramRange;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

public class FrameFilterRating extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnBuildVocabulary;
	JButton btnRangeRatio;
	JButton btnBuildCounter;
	/**
	 * Create the frame.
	 */
	
	private Connection connection;
	JSpinner spinnerCountUsers;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JSpinner spinner;
	
	public FrameFilterRating(Connection connection) {
		this.connection = connection;
		
		setTitle("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438 \u0441\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0438");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 319);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("icons\\icon.png").getImage());	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitledBorder title = new TitledBorder("Кількісна характеристика");
		title.setTitleFont(new Font("Tahoma", Font.PLAIN, 12));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 278, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u043A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 25, 176, 14);
		panel.add(lblNewLabel);
		
		spinnerCountUsers = new JSpinner();
		
		spinnerCountUsers.setBounds(221, 23, 47, 20);
		panel.add(spinnerCountUsers);
		
		btnBuildCounter = new JButton("\u041F\u043E\u0431\u0443\u0434\u0443\u0432\u0430\u0442\u0438");
		btnBuildCounter.setBounds(160, 50, 108, 23);
		panel.add(btnBuildCounter);
		btnBuildCounter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0421\u043B\u043E\u0432\u043D\u0438\u043A\u0438", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 106, 278, 63);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\u041F\u043E\u043F\u0443\u043B\u044F\u0440\u043D\u0456\u0441\u0442\u044C \u0441\u043B\u043E\u0432\u043D\u0438\u043A\u0456\u0432");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 26, 134, 14);
		panel_2.add(lblNewLabel_3);
		
		btnBuildVocabulary = new JButton("\u041F\u043E\u0431\u0443\u0434\u0443\u0432\u0430\u0442\u0438");
		btnBuildVocabulary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuildVocabulary.setBounds(161, 23, 107, 23);
		panel_2.add(btnBuildVocabulary);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0420\u0435\u0439\u0442\u0438\u043D\u0433", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 180, 278, 99);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 24, 124, 20);
		panel_1.add(lblNewLabel_1);
		
		btnRangeRatio = new JButton("\u041F\u043E\u0431\u0443\u0434\u0443\u0432\u0430\u0442\u0438");
		btnRangeRatio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRangeRatio.setBounds(158, 56, 110, 23);
		panel_1.add(btnRangeRatio);
		
		spinner = new JSpinner();
		spinner.setBounds(219, 25, 49, 20);
		panel_1.add(spinner);
	}

	public void getUsersCount() {
		int count = 0;
		String query= "SELECT COUNT(Users.UserID) FROM Users";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			connection.commit();
			while(result.next())
				count = result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		spinnerCountUsers.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(count), new Integer(1)));
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(count), new Integer(1)));
	}
	
	public void apllyFilter(CreateDiagramRange createDiagram) {
		// TODO Auto-generated method stub
		btnBuildCounter.addActionListener(createDiagram);
	}

	public void buildPieChartVocabularies(BuildPieChartUsingVocabularies buildPieChartUsingVocabularies) {
		// TODO Auto-generated method stub
		btnBuildVocabulary.addActionListener(buildPieChartUsingVocabularies);
	}

	public int getFilterCount() {
		// TODO Auto-generated method stub
		return (int) spinnerCountUsers.getValue();
	}

	public void buildGraphRangeRatio(BuildRahgeRatioGraph buildRahgeRatioGraph) {
		// TODO Auto-generated method stub
		btnRangeRatio.addActionListener(buildRahgeRatioGraph);
	}

	public int getUserFilterRatio() {
		// TODO Auto-generated method stub
		return (int) spinner.getValue();
	}
}
