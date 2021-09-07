import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CedarDatabase {
	private JFrame frame;
	private JTextField txtFullName;
	private JTextField txtPrice;
	private JTextField txtPhoneNumber;
	private JTextField txtCustomerID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CedarDatabase window = new CedarDatabase();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CedarDatabase() {
		initialize();
		
		/* Initialize connection and write at the bottom */
		Connect();
		table_load();
	}

	/* SQL class names 'Connection, PreparedStatement, ResultSet' */
	Connection connect;
	PreparedStatement prepare;
	ResultSet data;
	
	private JTable table;
	
	public void Connect() {
		String uname="root";
		try {
			/* Register for the driver */
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/* Telling the path where the database url is at. Connection with the cedar database */
			/* Fix the credential */
			connect = DriverManager.getConnection("jdbc:mysql://localhost/cedardatabase", uname, "Dkswlstjr!234");
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*Don't see any difference between public and no-public */
	void table_load()
	{
		try
		{
			
			
			prepare = connect.prepareStatement("select * from customer");
			
			/* Stack overflow reference */
			data = prepare.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(data));
			/* initial error with DbUtil, need to download rs2xml.jar from hacksmile.com */
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 831, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEDAR HILLS COIN LAUNDRY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 460, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 55, 460, 234);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Full Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 20, 78, 43);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Service:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 119, 78, 43);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 160, 78, 43);
		panel.add(lblNewLabel_1_1_1);
		
		txtFullName = new JTextField();
		txtFullName.setBounds(94, 29, 210, 29);
		panel.add(txtFullName);
		txtFullName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(94, 169, 210, 29);
		panel.add(txtPrice);
		
		JRadioButton radio_wash_dry = new JRadioButton("Wash & Dry");
		radio_wash_dry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_wash_dry.setBounds(252, 131, 103, 21);
		panel.add(radio_wash_dry);
		
		JRadioButton radio_transfer = new JRadioButton("Transfer");
		radio_transfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_transfer.setBounds(370, 131, 103, 21);
		panel.add(radio_transfer);
		
		JRadioButton radio_wash_dry_fold = new JRadioButton("Wash, Dry and Fold");
		radio_wash_dry_fold.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_wash_dry_fold.setBounds(94, 132, 140, 21);
		panel.add(radio_wash_dry_fold);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Paid?");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 192, 78, 43);
		panel.add(lblNewLabel_1_1_1_1);
		
		JRadioButton radio_paid_yes = new JRadioButton("Yes");
		radio_paid_yes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_paid_yes.setBounds(94, 204, 103, 21);
		panel.add(radio_paid_yes);
		
		JRadioButton radio_paid_no = new JRadioButton("No");
		radio_paid_no.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_paid_no.setBounds(252, 204, 103, 21);
		panel.add(radio_paid_no);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone #:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 73, 78, 43);
		panel.add(lblNewLabel_1_2);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(94, 82, 210, 29);
		panel.add(txtPhoneNumber);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*GOAL: store data into cedar database when clicking on "SAVE" */
				String fullName, phoneNumber, price;
				
				/* Remember the text box variables to make a connections! */
				
				fullName = txtFullName.getText();
				/* getText method to retrieve the texts inside the text boxes */
				phoneNumber = txtPhoneNumber.getText();
				price = txtPrice.getText();
				
				/* Stackoverflow reference */
				try {
					prepare = connect.prepareStatement("insert into customer(name,phoneNum,price)values(?,?,?)");
					/* Each question mark connects to fullName, phoneNumber, price */
					prepare.setString(1, fullName);
					prepare.setString(2, phoneNumber);
					prepare.setString(3, price);
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Added");
					table_load();
					txtFullName.setText("");
					txtPhoneNumber.setText("");
					txtPrice.setText("");
					txtFullName.requestFocus(); /* Focus on the txtFullName method for display purpose. Still not sure what it does as it only states
					in stack overflow to write it */
				}
				catch(SQLException error) {
					error.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 299, 100, 31);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(193, 299, 100, 31);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFullName.setText("");
				txtPhoneNumber.setText("");
				txtPrice.setText("");
				txtFullName.requestFocus();
			}
		});
		btnClear.setBounds(370, 299, 100, 31);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 340, 460, 51);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Customer ID:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(77, 5, 111, 43);
		panel_1.add(lblNewLabel_1_2_1);
		
		txtCustomerID = new JTextField();
		txtCustomerID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String customerID = txtCustomerID.getText();
					prepare = connect.prepareStatement("select name, phoneNum, price from customer where id = ?");
					prepare.setString(1, customerID);
					ResultSet data = prepare.executeQuery();
					
					if(data.next() == true) {
						String name = data.getString(1);
						String phoneNum = data.getString(2);
						String price = data.getString(3);
						
						txtFullName.setText(name);
						txtPhoneNumber.setText(phoneNum);
						txtPrice.setText(price);
					}
					else {
						txtFullName.setText("");
						txtPhoneNumber.setText("");
						txtPrice.setText("");
					}
				}
				catch (SQLException ex){
				}
			}
		});
		txtCustomerID.setColumns(10);
		txtCustomerID.setBounds(177, 14, 210, 29);
		panel_1.add(txtCustomerID);
		
		JButton btnUpdate = new JButton("Update");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fullName, phoneNumber, price, update;
				fullName = txtFullName.getText();
				phoneNumber = txtPhoneNumber.getText();
				price = txtPrice.getText();
				update = txtCustomerID.getText();
				
				try {
					prepare = connect.prepareStatement("update customer set name = ?, phoneNum = ?, price = ? where id = ?");
					prepare.setString(1, fullName);
					prepare.setString(2, phoneNumber);
					prepare.setString(3, price);
					prepare.setString(4, update);
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Record Updated");
					table_load();
					txtFullName.setText("");
					txtPhoneNumber.setText("");
					txtPrice.setText("");
					txtFullName.requestFocus();
				}
				catch(SQLException error) {
					error.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(514, 352, 100, 39);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String update;
				
				update = txtCustomerID.getText();
				
				try {
					prepare = connect.prepareStatement("delete from customer where id = ?");
					
					prepare.setString(1, update);
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Record Deleted");
					table_load();
					txtFullName.setText("");
					txtPhoneNumber.setText("");
					txtPrice.setText("");
					txtFullName.requestFocus();
				}
				catch(SQLException error) {
					error.printStackTrace();
				}
			}
		});
		btnClear_1_1.setBounds(674, 352, 100, 39);
		frame.getContentPane().add(btnClear_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(480, 24, 327, 318);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
