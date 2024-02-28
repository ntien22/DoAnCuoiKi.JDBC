package baiCuoiKi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import week10_bai8.studentManagement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DangNhap extends JFrame {

	JPanel contentPane;
	JPasswordField jfPass;
	JTextField txtdn;
	
	Connection conn;
	Statement stm;
	ResultSet rst;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("QUẢN LÍ NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(196, 25, 334, 57);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(69, 116, 187, 39);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(69, 183, 187, 39);
		contentPane.add(lblNewLabel_1_1);

		jfPass = new JPasswordField();
		jfPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jfPass.setBounds(278, 185, 325, 40);
		contentPane.add(jfPass);

		txtdn = new JTextField();
		txtdn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtdn.setColumns(10);
		txtdn.setBounds(278, 118, 325, 40);
		contentPane.add(txtdn);

		JButton btnThmAccount = new JButton("Tạo tài khoản mới");
		btnThmAccount.setForeground(Color.WHITE);
		btnThmAccount.setBackground(new Color(50, 205, 50));
		btnThmAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemTaiKhoan chuyen = new ThemTaiKhoan();
				chuyen.setVisible(true);
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
			
		});
		btnThmAccount.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThmAccount.setBounds(207, 372, 291, 47);
		contentPane.add(btnThmAccount);

		JButton btndn = new JButton("Đăng nhập");
		btndn.setForeground(Color.WHITE);
		btndn.setBackground(new Color(0, 0, 255));
		btndn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ket noi voi csdl
					String url = "jdbc:mysql://localhost:3306/dangnhap";
					String username = "root";
					String password = "Woplaf22.";
					
					Connection conn = DriverManager.getConnection(url, username, password);

					String sql = ("select * from dangnhap.account where USERNAME =? and PASS=?");
					PreparedStatement ps = conn.prepareCall(sql);
					ps.setString(1, txtdn.getText());
					ps.setString(2, jfPass.getText());
					rst = ps.executeQuery(); 

					
					if (txtdn.getText().equals("") || jfPass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập username hoặc pass.");

					} else if (rst.next()) {
						// JOptionPane.showMessageDialog(null, "Đăng nhập thành công.");
						QLnhanvien qlnv = new QLnhanvien("Quan li sinh vien");
						qlnv.setVisible(true);
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại.");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}

			
		});
		btndn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btndn.setBounds(137, 264, 440, 57);
		contentPane.add(btndn);

	}
}
