import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class PharmacyFunctionalities {
	Connection conn;
	ResultSet rs, rs1;
	Statement stmt, st1;
	PreparedStatement pst;
	String query;
	String n;
	private JFrame FunctionalityFrame;
	JTable t1 = new JTable();
	JScrollPane scroll;
	String url = "jdbc:mysql://localhost:3306/Pharmacy";
	String uname = "root";
	String pswd = "munch";
	
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try {
					PharmacyFunctionalities window = new PharmacyFunctionalities();
					window.FunctionalityFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public PharmacyFunctionalities() {
		initializeFrame();
	}
	void initializeFrame() {
		FunctionalityFrame = new JFrame("Functionalities Page");
		ImageIcon img1;
		FunctionalityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FunctionalityFrame.getContentPane().setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		FunctionalityFrame.getContentPane().setBounds(0, 0, 800, 800);
		FunctionalityFrame.getContentPane().setForeground(new Color(135, 206, 250));
		FunctionalityFrame.getContentPane().setBackground(Color.pink);	
		FunctionalityFrame.getContentPane().setLayout(null);
		FunctionalityFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JLabel titleL = new JLabel("PHARMACY MANGEMENT SYSTEM");
		titleL.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleL.setBounds(174, 25, 535, 31);
		FunctionalityFrame.getContentPane().add(titleL);
		img1 = new ImageIcon("Pharmacy2.jpg");
		JLabel img = new JLabel(img1); 
		img.setBounds(30,30 , 100, 100);
		FunctionalityFrame.getContentPane().add(img);
		JButton addBtn = new JButton("Add Data");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOperation add = new AddOperation();
				add.setVisible(true);
			}
		});
		addBtn.setBounds(421, 90, 165, 31);
		FunctionalityFrame.getContentPane().add(addBtn);

		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SearchOperation search = new SearchOperation();
				search.setVisible(true);
			}
		});
		
		searchBtn.setBounds(421, 136, 165, 31);
		FunctionalityFrame.getContentPane().add(searchBtn);

		JButton delBtn = new JButton("Delete");
		delBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteOperation delete = new DeleteOperation();
				delete.setVisible(true);
			}
		});
		delBtn.setBounds(421, 191, 165, 36);
		FunctionalityFrame.getContentPane().add(delBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateOperation update = new UpdateOperation();
				update.setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		updateBtn.setBounds(421, 250, 165, 31);
		FunctionalityFrame.getContentPane().add(updateBtn);
		
		JLabel addL = new JLabel("Click to Add an item : ");
		addL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addL.setBounds(139, 95, 196, 17);
		FunctionalityFrame.getContentPane().add(addL);
		
		JLabel searchL = new JLabel("Click to Search item : ");
		searchL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchL.setBounds(139, 141, 215, 17);
		FunctionalityFrame.getContentPane().add(searchL);
		
		JLabel delL = new JLabel("Click to Delete item : ");
		delL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delL.setBounds(139, 199, 202, 17);
		FunctionalityFrame.getContentPane().add(delL);
		
		JLabel updateL = new JLabel("Click to Update item : ");
		updateL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateL.setBounds(139, 248, 196, 31);
		FunctionalityFrame.getContentPane().add(updateL);
		
		JButton expdataBtn = new JButton("Expiration Data");
		expdataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ExpirationDetails expire = new ExpirationDetails();
				expire.setVisible(true);
			}
		});
		expdataBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		expdataBtn.setBounds(421, 296, 165, 36);
		FunctionalityFrame.getContentPane().add(expdataBtn);
		
		JLabel expL = new JLabel("Click to get Expired Item : ");
		expL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		expL.setBounds(139, 299, 272, 27);
		FunctionalityFrame.getContentPane().add(expL);
		
		JButton btnBillGenerate = new JButton("Bill Calculation");
		btnBillGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				BillGeneration bill = new BillGeneration();
				bill.setVisible(true);
			}
		});
		btnBillGenerate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBillGenerate.setBounds(421, 359, 165, 31);
		FunctionalityFrame.getContentPane().add(btnBillGenerate);
		
		JLabel billL = new JLabel("Click here to calculate bill:");
		billL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		billL.setBounds(139, 357, 272, 31);
		FunctionalityFrame.getContentPane().add(billL);
	}
}
