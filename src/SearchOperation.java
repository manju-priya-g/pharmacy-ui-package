import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
public class SearchOperation extends JFrame {
	private JPanel SearchOperation;
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;
	String query;
	String n;
	JTable table;
	JScrollPane scrollPane;
	private JTextField MName;
	private JTextField addType;
	private JTextField textName;
	private JTextField textPurpose;
	private JTextField textMdate;
	private JTextField textEdate;
	private JTextField textstock;
	private JTextField textMg;
	private JTextField textMl;
	private JTextField textCost;
	private JTextField textmname;
	int i = 0;
	public  int j =1;
	private JTextField textDelete;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchOperation frame2 = new SearchOperation();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SearchOperation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 800, 800);
		SearchOperation = new JPanel();
		SearchOperation.setBorder(new CompoundBorder());
		setContentPane(SearchOperation);
		SearchOperation.setLayout(null);
		
		JLabel SearchL = new JLabel(" Search by Purpose:");
		SearchL.setBounds(53, 123, 230, 31); SearchL.setFont(new Font("Yu Gothic UI",Font.BOLD, 25)); 
		SearchL.setBackground(new Color(102, 205, 170));
		SearchOperation.add(SearchL, BorderLayout.NORTH);
		
		JTextField SearchField = new JTextField(); 
		SearchField.setBounds(121, 164,358, 33); 
		SearchField.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		SearchOperation.add(SearchField); SearchField.setColumns(10);
	
		SearchOperation.setBounds(0,0, 1600, 800);
		JLabel sResult = new JLabel("Search Results :"); 
		sResult.setBounds(68, 232,156, 31); 
		sResult.setFont(new Font("Yu Gothic UI", Font.BOLD, 19));
		SearchOperation.add(sResult);
		
		JButton searchButton = new JButton("SEARCH"); 
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					String url = "jdbc:mysql://localhost:3306/Pharmacy";
					String uname = "root";
					String pswd = "munch";
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url,uname,pswd);
					stmt = conn.createStatement();
					String n = SearchField.getText();
					String query = "select * from Tablet where Purpose ="+ "'" +n+ "'" ;
					ResultSet rs = stmt.executeQuery(query);	
					while(rs.next()){
						int Id = rs.getInt("Id");
						String name = rs.getString("MName");
						String ManuName = rs.getString("Manufacuturer");
						String purpose = rs.getString("Purpose");
						Date Mdate = rs.getDate("ManuDate");
						Date Edate = rs.getDate("expDate");
						int stock = rs.getInt("Stock");
						int mg = rs.getInt("mg");
						int cost = rs.getInt("Cost");
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						model.addRow(new Object [] {j , name , ManuName , stock , cost});
						++j;
						++i;
					}
				}
				catch(Exception e1){}
				}
		});
		searchButton.setBounds(475, 165, 156, 31);
		searchButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		SearchOperation.add(searchButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 296, 716, 268);
		
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No", "Description", "Manufacturer Name" , "Rate", "Stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class,  Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);	
		SearchOperation.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("SEARCH MEDICINE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(275, 54, 230, 25);
		SearchOperation.add(lblNewLabel);
	}
}
