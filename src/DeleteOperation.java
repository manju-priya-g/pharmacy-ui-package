import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeleteOperation extends JFrame {

	private JPanel DeleteOperation;
	 Connection conn;
	    ResultSet rs, rs1;
	    Statement stmt, st1;
	    PreparedStatement pst;
	    String query;
	    String n;
		private JTextField textDelete;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteOperation frame = new DeleteOperation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DeleteOperation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 800, 500);
		DeleteOperation = new JPanel();
		DeleteOperation.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(DeleteOperation);
		DeleteOperation.setLayout(null);
		
		JLabel deleteL = new JLabel("Enter Medicine Name:");
		deleteL.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		deleteL.setBounds(58, 86, 263, 31);
		DeleteOperation.add(deleteL);
		
		textDelete = new JTextField();
		textDelete.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		textDelete.setBounds(348, 87, 248, 34);
		DeleteOperation.add(textDelete);
		textDelete.setColumns(10);
		

		JButton deleteButton = new JButton("DELETE ITEM");
		deleteButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		deleteButton.setBounds(225, 164, 168, 42);
		DeleteOperation.add(deleteButton);
		
		JLabel titleL = new JLabel("DELETE AN ITEM");
		titleL.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleL.setBounds(261, 32, 204, 25);
		DeleteOperation.add(titleL);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delName = textDelete.getText();
				//int dId = Integer.parseInt(delName);
				try {
	 					String url = "jdbc:mysql://localhost:3306/Pharmacy";
	 					String uname = "root";
	 					String pswd = "munch";
	 					Class.forName("com.mysql.cj.jdbc.Driver");
	 					conn = DriverManager.getConnection(url,uname,pswd);
	 					PreparedStatement pst = conn.prepareStatement("DELETE FROM Tablet where MName = "+ "'"+delName+"'");
	 					pst.executeUpdate();
	 					JOptionPane.showMessageDialog(null,"Entered value has been deleted");
	 			    }
	 			    catch(Exception e2) {
	 			    	e2.printStackTrace();
	 			    }
	 			    finally{}
			}
		});		
	}
}
