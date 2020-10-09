import java.sql.*;
import javax.swing.*;
public class SQLiteConnection {	
	public static Connection sqliteConnection(){
		Connection conn=null;
		try{
			String url="jdbc:sqlite:C:\\Users\\hitesh pahwa\\workspace\\Chat Messenger\\database.db";
			conn=DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null,"connection established successfully");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"connection not established");
			return conn;
		}
	}
}
