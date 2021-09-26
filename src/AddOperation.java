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
import java.awt.Color;

public class AddOperation extends JFrame {

	private JPanel AddOperation;
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;
	String query;
	private JTextField MName;
	private JTextField textName;
	private JTextField textPurpose;
	private JTextField textMdate;
	private JTextField textEdate;
	private JTextField textstock;
	private JTextField textMg;
	private JTextField textCost;
	private JTextField textmname;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOperation frame = new AddOperation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AddOperation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 800, 800);
		AddOperation = new JPanel();
		AddOperation.setForeground(Color.LIGHT_GRAY);
		AddOperation.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(AddOperation);
		AddOperation.setLayout(null);
		
		JLabel addItem = new JLabel("Please enter values in below fields");
		addItem.setBounds(49, 47, 341, 51);
		addItem.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		AddOperation.add(addItem);
	
		JButton addButton = new JButton("ADD ITEM");
		addButton.setBounds(509, 494, 224, 42);
		addButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		AddOperation.add(addButton);

		JLabel addName = new JLabel("Medicine Name :");
		addName.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addName.setBounds(49, 108, 188, 42);
		AddOperation.add(addName);

		textmname = new JTextField();
		textmname.setBounds(332, 117, 213, 31);
		AddOperation.add(textmname);
		textmname.setColumns(10);

		JLabel addManufacture = new JLabel("Manufaturer Name :");
		addManufacture.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addManufacture.setBounds(49, 171, 204, 31);
		AddOperation.add(addManufacture);

		JLabel addPurpose = new JLabel("Medication Purpose :");
		addPurpose.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addPurpose.setBounds(49, 226, 204, 31);
		AddOperation.add(addPurpose);

		JLabel addManuDate = new JLabel("Manufacturing Date :(YYYY/MM/DD)");
		addManuDate.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addManuDate.setBounds(49, 285, 273, 26);
		AddOperation.add(addManuDate);

		JLabel addExpDate = new JLabel("Expiry Date : (YYYY/MM/DD)");
		addExpDate.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addExpDate.setBounds(49, 344, 213, 31);
		AddOperation.add(addExpDate);

		JLabel addStock = new JLabel("Stock Count :");
		addStock.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addStock.setBounds(49, 398, 156, 31);
		AddOperation.add(addStock);

		JLabel addMg = new JLabel("Quantity:");
		addMg.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addMg.setBounds(49, 463, 232, 26);
		AddOperation.add(addMg);

		JLabel addCost = new JLabel("Cost : ");
		addCost.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		addCost.setBounds(49, 519, 145, 31);
		AddOperation.add(addCost);

		textName = new JTextField();
		textName.setBounds(332, 174, 213, 31);
		AddOperation.add(textName);
		textName.setColumns(10);

		textPurpose = new JTextField();
		textPurpose.setBounds(332, 229, 213, 31);
		AddOperation.add(textPurpose);
		textPurpose.setColumns(10);

		textMdate = new JTextField();
		textMdate.setBounds(332, 286, 213, 31);
		AddOperation.add(textMdate);
		textMdate.setColumns(10);

		textEdate = new JTextField();
		textEdate.setBounds(331, 347, 214, 31);
		AddOperation.add(textEdate);
		textEdate.setColumns(10);

		textstock = new JTextField();
		textstock.setBounds(332, 401, 96, 31);
		AddOperation.add(textstock);
		textstock.setColumns(10);

		textMg = new JTextField();
		textMg.setBounds(332, 458, 96, 31);
		AddOperation.add(textMg);
		textMg.setColumns(10);

		textCost = new JTextField();
		textCost.setBounds(332, 522, 96, 31);
		AddOperation.add(textCost);
		textCost.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ADDING ITEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(268, 18, 172, 31);
		AddOperation.add(lblNewLabel);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aname = textmname.getText();
				String mname = textName.getText();
				String purpose = textPurpose.getText();
				String md = textMdate.getText();
				String ed = textEdate.getText();
				String s = textstock.getText();
				int stock = Integer.parseInt(s);
				String m = textMg.getText();
				int mg  = Integer.parseInt(m);
				String ct = textCost.getText();
				float cost = Float.parseFloat(ct);
				try {
					String url = "jdbc:mysql://localhost:3306/Pharmacy";
					String uname = "root";
					String pswd = "munch";
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url,uname,pswd);
					int a =1;
					int id = 18;
					PreparedStatement pst = conn.prepareStatement ("INSERT INTO Tablet(Id,MName,Manufacuturer,Purpose,ManuDate,expDate,Stock,Available,mg,Cost) VALUES ('"+id+"','"+aname+"','"+mname+"','"+purpose+"','"+md+"','"+ed+"','"+stock+"','"+a+"','"+mg+"','"+cost+"')"); 
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "The Details has been successfully added");
				}
				catch(Exception e2) { 
					e2.printStackTrace();
				}
				finally{}
			}
		  }
		);
	}
}
