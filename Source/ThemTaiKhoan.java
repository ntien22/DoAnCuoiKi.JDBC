package baiCuoiKi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.nio.file.attribute.AclEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ThemTaiKhoan extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtGmail;
	private JPasswordField txtPass;
	private JPasswordField txtComfirm;

	String url = "jdbc:mysql://localhost:3306/dangnhap";
	String username = "root";
	String password = "Woplaf22.";
	Statement stm;
	ResultSet rst;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemTaiKhoan frame = new ThemTaiKhoan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThemTaiKhoan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setBounds(295, 102, 241, 28);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtGmail = new JTextField();
		txtGmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtGmail.setColumns(10);
		txtGmail.setBounds(295, 160, 241, 28);
		contentPane.add(txtGmail);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPass.setBounds(295, 218, 241, 28);
		contentPane.add(txtPass);

		txtComfirm = new JPasswordField();
		txtComfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtComfirm.setBounds(295, 276, 241, 28);
		contentPane.add(txtComfirm);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(53, 102, 164, 28);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Gmail");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(53, 160, 106, 28);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mật khẩu");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(53, 218, 106, 28);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(53, 276, 177, 28);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("Thêm Tài Khoản");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_3_1.setBounds(226, 23, 263, 28);
		contentPane.add(lblNewLabel_1_3_1);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap  l = new DangNhap();
				l.setVisible(true);		//hiển thị manHinhDangNhap
				this.dispose();		//thoát khỏi manHinhDangNhap
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(95, 353, 205, 44);
		contentPane.add(btnLogin);

		JButton btnDangKi = new JButton("Đăng kí");
		btnDangKi.setForeground(new Color(255, 255, 255));
		btnDangKi.setBackground(new Color(50, 205, 50));
		btnDangKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dk = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng kí ", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (dk != JOptionPane.YES_OPTION) {
					return;
				}
				//truy vấn vào csdl
				try {
					Connection conn = DriverManager.getConnection(url, username, password);
					String sql = "Insert into ACCOUNT values(?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, txtUser.getText());
					ps.setString(2, txtGmail.getText());
					ps.setString(3, txtPass.getText());
					ps.setString(4, txtComfirm.getText());
					
					
					int n = ps.executeUpdate();		//update dữ liệu lên
					if(txtUser.getText().equals("") || txtGmail.getText().equals("") || txtPass.getText().equals("") || txtComfirm.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Không để thông tin trống");
					}else if(n!=0){
						JOptionPane.showMessageDialog(null, "Đăng kí thành công");
					}else {
						JOptionPane.showMessageDialog(null, "Đăng kí thất bại");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDangKi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangKi.setBounds(395, 353, 205, 44);
		contentPane.add(btnDangKi);
	}
}
