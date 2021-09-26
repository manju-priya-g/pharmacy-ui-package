import java.awt.EventQueue;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class ExpirationDetails extends JFrame {
	private JPanel DeleteOperation;
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;    
	String query;
	String n;
	public int i=1;
	public  int j =1;
	JTable t1;
	JScrollPane scroll;
	private JPanel ExpirationDetails;
	private JLabel expLabel;
	private JLabel titleL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpirationDetails frame = new ExpirationDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ExpirationDetails() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1000, 800 );
		ExpirationDetails = new JPanel();
		ExpirationDetails.setBorder(new CompoundBorder());
		setContentPane(ExpirationDetails);
		ExpirationDetails.setLayout(null);
		JButton expButton = new JButton("GET EXPIRY MEDICINES");
		expButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		expButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ct = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				LocalDate today = LocalDate.now();
				try {
					String url = "jdbc:mysql://localhost:3306/Pharmacy";
					String uname = "root";
					String pswd = "munch";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url,uname,pswd);
					stmt = conn.createStatement();
					String query = "SELECT *  FROM Tablet";
					ResultSet rs = stmt.executeQuery(query);
					while(rs.next()){

						int Id = rs.getInt("Id");
						String name = rs.getString("MName");
						String ManuName = rs.getString("Manufacuturer");
						String purpose = rs.getString("Purpose");
						Date Mdate = rs.getDate("ManuDate");
						Date Edate = rs.getDate("expDate");

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

						String d1 = dateFormat.format(Mdate);  
						String d2 = dateFormat.format(Edate);  

						int stock = rs.getInt("Stock");
						int mg = rs.getInt("mg");
						int cost = rs.getInt("Cost");
						Calendar c = Calendar.getInstance();
						c.setTime(Edate);
						int yr = c.get(Calendar.YEAR);
						int m = c.get(Calendar.MONTH);
						int mth = m+1;
						int day = c.get(Calendar.DAY_OF_MONTH);
						LocalDate exp = LocalDate.of(yr,mth, day);
						Period diff = Period.between(today, exp);
						yr =  diff.getYears();
						mth = diff.getMonths();
						day = diff.getYears();
						if(yr < 0|m < 0|day<0) {
							//System.out.println(Id + " " + name + " " + ManuName+ " "+ purpose + " "+ Mdate + " "+ Edate + " "+mg + " "+ stock+ " "+ cost);	

							DefaultTableModel model = (DefaultTableModel)t1.getModel();
							model.addRow(new Object [] {j , name , ManuName , purpose , d1 , d2 , mg , stock , cost});
							++j;
						}
						++i;
					}
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		expButton.setBounds(549, 118, 267, 45);
		ExpirationDetails.add(expButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 239, 923, 330);


		t1 = new JTable();
		t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		t1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"S.No", "Medicine Name", "Manufacturer" ,"Purpose" , "Manufacture date" , "Expiry date", "Rate", "Stock"
				}
				) {
			Class[] columnTypes = new Class[] {
					Integer.class, Integer.class, String.class, String.class, String.class , String.class , Integer.class,  Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(t1);
		ExpirationDetails.add(scrollPane);	

		expLabel = new JLabel("Click here to get the Expired Medicines List");
		expLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		expLabel.setBounds(27, 118, 495, 45);
		ExpirationDetails.add(expLabel);

		titleL = new JLabel("FINDING EXPIRY LIST");
		titleL.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleL.setBounds(323, 44, 267, 25);
		ExpirationDetails.add(titleL);
	}
}
