package baiCuoiKi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

import baiCuoiKi.QLnhanvien;

public class CapNhat extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField txtTennv;
	JTextField txtNs;
	JTextField txtSdt;
	JTextField txtTdhv;
	JTextField txtDc;
	JLabel errorlb;
	JLabel errordetails;

	// Nhận đối tượng cửa sổ chính truyền đến
	QLnhanvien qlnv;

	// Nhận id của 1 record của bảng nhanvien khi có hiệu chỉnh
	String id;

	public CapNhat(String s, QLnhanvien ql, String i, String ten, String ns, String sdt, String tdhv, String dc) {
		super(s);

		qlnv = ql;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CẬP NHẬT THÔNG TIN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(122, 10, 288, 48);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 54, 473, 488);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên nhân viên: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 43, 198, 45);
		panel.add(lblNewLabel);

		JLabel lblNgySinh = new JLabel("Ngày sinh: ");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNgySinh.setBounds(10, 131, 198, 45);
		panel.add(lblNgySinh);

		JLabel lblSinThoi = new JLabel("Số điện thoại:\r\n");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSinThoi.setBounds(10, 219, 198, 45);
		panel.add(lblSinThoi);

		JLabel lblTrnhHc = new JLabel("Trình độ học vấn:");
		lblTrnhHc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrnhHc.setBounds(10, 307, 198, 45);
		panel.add(lblTrnhHc);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblaCh.setBounds(10, 395, 198, 45);
		panel.add(lblaCh);

		txtTennv = new JTextField(ten);
		txtTennv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTennv.setBounds(205, 43, 248, 35);
		panel.add(txtTennv);
		txtTennv.setColumns(10);

		txtNs = new JTextField(ns);
		txtNs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNs.setColumns(10);
		txtNs.setBounds(205, 131, 248, 35);
		panel.add(txtNs);

		txtSdt = new JTextField(sdt);
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSdt.setColumns(10);
		txtSdt.setBounds(205, 219, 248, 35);
		panel.add(txtSdt);

		txtTdhv = new JTextField(tdhv);
		txtTdhv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTdhv.setColumns(10);
		txtTdhv.setBounds(205, 307, 248, 35);
		panel.add(txtTdhv);

		txtDc = new JTextField(dc);
		txtDc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDc.setColumns(10);
		txtDc.setBounds(205, 395, 248, 35);
		panel.add(txtDc);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(44, 552, 156, 45);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(this);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancel.setBounds(284, 552, 156, 45);
		contentPane.add(btnCancel);

		id = i;
	}

	public void insertDB() {
		if (txtTennv.getText().equals("") || txtNs.getText().equals("") || txtSdt.getText().equals("")
				|| txtTdhv.getText().equals("") || txtDc.getText().equals("")) {
			
			errorlb.setText("Error");
			errorlb.setForeground(Color.RED);
			errorlb.setVisible(true);
			
			errordetails.setText("Empty value");
			errordetails.setForeground(Color.RED);
			errordetails.setVisible(true);
		} else {
			try {
				 // Lấy nội dung đã nhập từ giao diện người dùng
				String ten = txtTennv.getText();
				String ns = txtNs.getText();
				String sdt = txtSdt.getText();
				String tdhv = txtTdhv.getText();
				String dc = txtDc.getText();
				String sql = "";
				
				
				if (this.getTitle().equals("Insert form")) {
					// Neu la nhap moi
					sql = "Insert into nhanvien(HOVATEN, NGAYSINH, SODIENTHOAI, TRINHDOHOCVAN, DIACHI) values (\"" + ten
							+ "\",'" + ns + "','" + sdt + "','" + tdhv + "','" + dc + "')";
				}else { 
					// neu la hieu chinh
					sql = "Update nhanvien SET HOVATEN = \"" + ten + "\", NGAYSINH = '" + ns + "', SODIENTHOAI = '"
							+ sdt + "', TRINHDOHOCVAN = '" + tdhv + "', DIACHI = '" + dc + "' WHERE ID = \"" + id
							+ "\"";
				}

				qlnv.stm.executeUpdate(sql); // Thực hiện câu lệnh SQL để cập nhật cơ sở dữ liệu
				qlnv.reload();// Cập nhật giao dien của cửa sổ chính
				qlnv.model.fireTableDataChanged();// cập nhật lại nội dung bảng hiển thị trên màn hình

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	// Goi khi an nut ok hoac cancel
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {
			insertDB();
			this.dispose();
		} else {
			this.dispose();// Tat cua so khi an cancel
		}
	}
}
