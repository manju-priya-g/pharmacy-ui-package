import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.lang.Math;
import java.time.LocalDate;
import java.time.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class BillGeneration extends JFrame {
	private JPanel Billgen;
	private JTextField textBillNum;
	private JTextField textDate;
	private JTextField textDesp;
	private JTextField textBatch;
	private JTextField textRate;
	private JTextField textQty;
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;
	String query;
	double finalprice;
	int finalqty, cost;
    public JTable tablefinal;
	public int i=1;
	public JTextField textSum;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillGeneration frame = new BillGeneration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BillGeneration() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 934, 700);
		Billgen = new JPanel();
		Billgen.setBorder(new CompoundBorder());
		setContentPane(Billgen);
		Billgen.setLayout(null);
		
		JLabel lblBillNum = new JLabel("Bill No:");
		lblBillNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBillNum.setBounds(20, 78, 64, 21);
		Billgen.add(lblBillNum);

		textBillNum = new JTextField();
		textBillNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textBillNum.setBounds(94, 78, 96, 30);
		Billgen.add(textBillNum);
		textBillNum.setColumns(10);

		JLabel lblHeading = new JLabel("Billing System");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblHeading.setBounds(287, 20, 211, 44);
		Billgen.add(lblHeading);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(580, 82, 64, 13);
		Billgen.add(lblDate);

		textDate = new JTextField();
		textDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textDate.setBounds(633, 74, 155, 34);
		Billgen.add(textDate);
		textDate.setColumns(10);

		JLabel lblDesp = new JLabel("Item Description");
		lblDesp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDesp.setBounds(20, 126, 155, 30);
		Billgen.add(lblDesp);

		textDesp = new JTextField();
		textDesp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textDesp.setBounds(163, 118, 237, 35);
		Billgen.add(textDesp);
		textDesp.setColumns(10);
		 

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(20, 193, 45, 13);
		Billgen.add(lblId);

		textBatch = new JTextField();
		textBatch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textBatch.setBounds(69, 181, 96, 30);
		Billgen.add(textBatch);
		textBatch.setColumns(10);

		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate.setBounds(175, 195, 45, 13);
		Billgen.add(lblRate);

		textRate = new JTextField();
		textRate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textRate.setBounds(211, 181, 96, 32);
		Billgen.add(textRate);
		textRate.setColumns(10);
		

		JLabel lblQty = new JLabel("Qty.");
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQty.setBounds(317, 190, 51, 19);
		Billgen.add(lblQty);

		textQty = new JTextField();
		textQty.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textQty.setBounds(357, 181, 87, 32);
		Billgen.add(textQty);
		textQty.setColumns(10);  
		JButton Search = new JButton("Search");
		Search.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate d =  LocalDate.now();
				String d1 = d.toString();
				String no = String.valueOf(Math.random());
				textBillNum.setText(no);
				textDate.setText(d1);
				try {
					String url = "jdbc:mysql://localhost:3306/Pharmacy";
					String uname = "root";
					String pswd = "munch";
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url,uname,pswd);
					stmt = conn.createStatement();
					String n = textDesp.getText();
					String query = "select * from Tablet where MName ="+ "'" +n+ "'" ;
					ResultSet rs = stmt.executeQuery(query);
					int i = 0;
					if(rs.next()){
						int Id = rs.getInt("Id");
						cost = rs.getInt("Cost");
						++i;
						String idNum = Integer.toString(Id);
						String costNum = Integer.toString(cost);
						textBatch.setText(idNum);
						textRate.setText(costNum);	
					}
	
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		Search.setBounds(397, 118, 125, 34);
		Billgen.add(Search);
		Search.setVisible(true);
		JButton adBtn = new JButton("Add");
		adBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String qty = textQty.getText();
				finalqty = Integer.parseInt(qty);
				finalprice = cost*finalqty;
				String amount = Double.toString(finalprice);
				DefaultTableModel model = (DefaultTableModel)tablefinal.getModel();
				model.addRow(new Object [] {i , textDesp.getText(), textRate.getText(), textQty.getText(), amount});
				 textDesp.setText("");
				 textRate.setText(""); 
				 textQty.setText("");				 
						double sum =0;
						for(int j =0;j<tablefinal.getRowCount();j++) {
							String[] arr = new String[tablefinal.getRowCount()];
							arr[j]= (String) tablefinal.getValueAt(j, 4);
							double totalamount = Double.parseDouble(arr[j]);
							sum = sum + totalamount;
						}
						textSum.setText(Double.toString(sum));				
				 ++i;
			}
		});
		adBtn.setBounds(494, 179, 87, 34);
		Billgen.add(adBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 297, 770, 203);
		Billgen.add(scrollPane);
		
		tablefinal = new JTable();
		tablefinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablefinal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No", "Description", "Rate", "Quantity", "Amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tablefinal);	
		
		JLabel lblTotal = new JLabel("Total Amount");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotal.setBounds(547, 532, 137, 26);
		Billgen.add(lblTotal);
		
		textSum = new JTextField();
		textSum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textSum.setBounds(694, 531, 137, 34);
		Billgen.add(textSum);
		textSum.setColumns(10);	
	}	
}
