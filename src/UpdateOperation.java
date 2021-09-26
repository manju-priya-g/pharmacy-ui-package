import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UpdateOperation extends JFrame {

	private JPanel UpdateOperation;
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;
	String query;
	String n;
	String Stock , mg , ml, Cost;	
	public String  MName, fnColName;
	private JTextField textVal;
	public JTextField textName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateOperation frame = new UpdateOperation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public UpdateOperation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 800, 600);
		UpdateOperation = new JPanel();
		UpdateOperation.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(UpdateOperation);
		UpdateOperation.setLayout(null);

		JLabel colLabel = new JLabel("Choose the column to be updated :");
		colLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		colLabel.setBounds(43, 160, 324, 40);
		UpdateOperation.add(colLabel);

		String columnname[] = { "Manufacuturer","Purpose","Manufacture Date","Expiry Date","Stock","Available","Milli Grams","Cost"}; 
		JComboBox comboBox = new JComboBox(columnname);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(423, 160, 255, 40);
		UpdateOperation.add(comboBox);

		JLabel valLabel = new JLabel("Enter Value to be updated :");
		valLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		valLabel.setBounds(43, 241, 309, 23);
		UpdateOperation.add(valLabel);

		textVal = new JTextField();
		textVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textVal.setBounds(423, 237, 254, 40);
		UpdateOperation.add(textVal);
		textVal.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name of Medicine to be updated:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(43, 89, 339, 30);
		UpdateOperation.add(nameLabel);

		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textName.setBounds(423, 85, 255, 40);
		UpdateOperation.add(textName);
		textName.setColumns(10);
		

		JButton updateBtn = new JButton("UPDATE");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String colname = (String) comboBox.getSelectedItem();
				String updateValue = textVal.getText();
				String medName = textName.getText();
				try {
					String url = "jdbc:mysql://localhost:3306/Pharmacy";
					String uname = "root";
					String pswd = "munch";
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url,uname,pswd);
					if(colname.equals("Stock")| colname.equals("mg") | colname.equals("ml")) {
						int updateval  = Integer.parseInt(updateValue);
						fnColName = (String) comboBox.getSelectedItem();
						PreparedStatement pst = conn.prepareStatement("UPDATE Tablet set "+fnColName+" = '"+updateval+"' where MName = '"+medName+"'");
						pst.executeUpdate();
					}else if(colname.equals(Cost)) {
						float updatect = Float.parseFloat(updateValue);
						fnColName = (String) comboBox.getSelectedItem();
						PreparedStatement pst = conn.prepareStatement("UPDATE Tablet set "+fnColName+" = '"+updatect+"' where MName = '"+medName+"'");	
						pst.executeUpdate();
					}else {
						String updatest = updateValue;
						fnColName = (String) comboBox.getSelectedItem();
						PreparedStatement pst = conn.prepareStatement("UPDATE Tablet set "+fnColName+" = '"+updatest+"' where MName = '"+medName+"'");
						pst.executeUpdate();
					}
					
					JOptionPane.showMessageDialog(null, "Details are updated!!");
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				finally{}
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		updateBtn.setBounds(284, 321, 160, 40);
		UpdateOperation.add(updateBtn);
		
		JLabel lblNewLabel = new JLabel("UPDATE MEDICINE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(262, 38, 203, 23);
		UpdateOperation.add(lblNewLabel);
	}
}