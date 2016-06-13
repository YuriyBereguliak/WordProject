package wordproject.Run;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RunProject {  

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, 
	InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
		// TODO Auto-generated method stub 
	/*	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName()); 
		            break;
		        }
		    } 
		} catch (Exception e) { 
 
		}
	 */
	
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view, model);
				
		view.setVisible(true);
	}

}
