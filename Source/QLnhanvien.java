package baiCuoiKi;

	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.table.DefaultTableModel;

	import com.mysql.cj.jdbc.result.ResultSetMetaData;

	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.util.Vector;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import java.awt.Color;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.SwingConstants;

	public class QLnhanvien extends JFrame implements ActionListener, MouseListener {

		
		Connection conn;
		java.sql.Statement stm;
		ResultSet rst;

		Vector vData = new Vector();	// Vector để lưu trữ dữ liệu
		Vector vTitle = new Vector();	// Vector để lưu trữ tiêu đề cột

		JScrollPane tableResult;
		DefaultTableModel model;
		
		JPanel contentPane;
		JTable tb = new JTable();
		JTextField txtSearch;
		int selectedrow = 0;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						QLnhanvien frame = new QLnhanvien("Quản Lí Nhân Viên");
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public QLnhanvien(String s) {
			super(s);
			try {
				String url = "jdbc:mysql://localhost:3306/qlnhanvien";
				String username = "root";
				String password = "Woplaf22.";

				Connection conn = DriverManager.getConnection(url, username, password);
				stm = conn.createStatement();

				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 1322, 728);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(contentPane);
				contentPane.setLayout(null);

				JPanel contentPane_1 = new JPanel();
				contentPane_1.setBackground(new Color(30, 144, 255));
				contentPane_1.setLayout(null);
				contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane_1.setBounds(0, 0, 1298, 691);
				contentPane.add(contentPane_1);

				reload();// Nạp dữ liệu vào 2 vector: vTitle(tên cột) và vData(các hàng dữ liệu) chuẩn bị
							// tạo Jtable
				model = new DefaultTableModel(vData, vTitle);// Tạo bảng hiển thị thông tin lên cửa sổ
				this.setLocationRelativeTo(null);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(128, 128, 128));
				panel.setBounds(34, 68, 240, 368);
				contentPane_1.add(panel);
				panel.setLayout(null);

				JButton btnthem = new JButton("INSERT");
				btnthem.addActionListener(this); // gắn ống nghe cho nút insert
				btnthem.setBounds(46, 36, 150, 40);
				panel.add(btnthem);
				btnthem.setForeground(Color.BLUE);
				btnthem.setFont(new Font("Tahoma", Font.BOLD, 19));
				btnthem.setBackground(new Color(220, 220, 220));

				JButton btnxoa = new JButton("DELETE");
				btnxoa.addActionListener(this);// gắn ống nghe cho nút delete
				btnxoa.setBounds(46, 204, 150, 40);
				panel.add(btnxoa);
				btnxoa.setForeground(Color.BLUE);
				btnxoa.setFont(new Font("Tahoma", Font.BOLD, 19));
				btnxoa.setBackground(new Color(220, 220, 220));

				JButton btnsua = new JButton("EDIT");
				btnsua.addActionListener(this);// gắn ống nghe cho nút edit
				btnsua.setBounds(46, 120, 150, 40);
				panel.add(btnsua);
				btnsua.setForeground(Color.BLUE);
				btnsua.setFont(new Font("Tahoma", Font.BOLD, 19));
				btnsua.setBackground(new Color(220, 220, 220));

				JButton btnresett = new JButton("RESET");
				btnresett.addActionListener(this);// gắn ống nghe cho nút reset
				btnresett.setBounds(46, 288, 150, 40);
				panel.add(btnresett);
				btnresett.setForeground(Color.BLUE);
				btnresett.setFont(new Font("Tahoma", Font.BOLD, 19));
				btnresett.setBackground(new Color(220, 220, 220));

				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(128, 128, 128));
				panel_1.setBounds(332, 68, 934, 564);
				contentPane_1.add(panel_1);
				panel_1.setLayout(null);

				JLabel lblNewLabel_1 = new JLabel("QUẢN LÍ NHÂN VIÊN");
				lblNewLabel_1.setForeground(new Color(0, 0, 0));
				lblNewLabel_1.setBounds(318, 17, 317, 48);
				panel_1.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));

				model = new DefaultTableModel(vData, vTitle);
				tb = new JTable();
				tb.addMouseListener(this);// Gắng ống nghe khi ấn chuột vào hàng
				tb.setModel(model);

				tableResult = new JScrollPane();
				tableResult.setBounds(10, 75, 914, 479);
				panel_1.add(tableResult);
				tableResult.setViewportView(tb);

				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(128, 128, 128));
				panel_2.setBounds(34, 491, 240, 141);
				contentPane_1.add(panel_2);
				panel_2.setLayout(null);

				JButton btntimkiemid = new JButton("SEARCH ID");
				btntimkiemid.addActionListener(this);
				btntimkiemid.setBounds(42, 88, 150, 40);
				panel_2.add(btntimkiemid);
				btntimkiemid.setForeground(Color.BLUE);
				btntimkiemid.setFont(new Font("Tahoma", Font.BOLD, 19));
				btntimkiemid.setBackground(new Color(220, 220, 220));

				txtSearch = new JTextField();
				txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
				txtSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
				txtSearch.setBounds(20, 23, 196, 45);
				panel_2.add(txtSearch);
				txtSearch.setColumns(10);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		

		public void reload() {
			try {
		
				vData.clear();
				vTitle.clear();

				ResultSet rst = stm.executeQuery("select * from nhanvien");

				java.sql.ResultSetMetaData rstmeta = rst.getMetaData(); 
																		
				int num_column = rstmeta.getColumnCount(); 

				for (int i = 1; i <= num_column; i++) {
					vTitle.add(rstmeta.getColumnLabel(i)); 
				}

				while (rst.next()) {
					Vector row = new Vector(num_column); 
					for (int i = 1; i <= num_column; i++) { 
						row.add(rst.getString(i));
					}
					vData.add(row); 
				}
				rst.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		

		public void Search() {
			if (txtSearch.getText().equals("") == false) {
				try {
					vData.clear();
					vTitle.clear();

					ResultSet rs = stm.executeQuery("select * from nhanvien where ID = '" + txtSearch.getText() + "'");

					ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();

					int num_column = rsm.getColumnCount();

					for (int i = 1; i <= num_column; i++) {
						vTitle.add(rsm.getColumnLabel(i));
					}

					while (rs.next()) {
						Vector st = new Vector(num_column);
						for (int i = 1; i <= num_column; i++) {
							st.add(rs.getString(i));
						}
						vData.add(st);
					}

					model.fireTableDataChanged();
					txtSearch.setText("");
					rs.close();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn cần điền id cần tìm.");
			}
		}


		public void Insert() {
			CapNhat Upd = new CapNhat("Insert form", this, "", "", "", "", "", "");
			Upd.setVisible(true);
			Upd.setLocationRelativeTo(null);
		}
		

		public void Update() {
			if (selectedrow > 0) {
				Vector st = (Vector) vData.elementAt(selectedrow);
				CapNhat Upd = new CapNhat("Cập Nhật Thông Tin", this, (String) st.elementAt(0), (String) st.elementAt(1),
						(String) st.elementAt(2), (String) st.elementAt(3), (String) st.elementAt(4),
						(String) st.elementAt(5));
				Upd.setVisible(true);
				Upd.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Chọn hàng một hàng để edit !");
			}
		}
		

		public void Delete() {
			if (selectedrow > 0) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						Vector st = (Vector) vData.elementAt(selectedrow);

						String sql = "Delete from nhanvien where id = \"" + st.elementAt(0) + "\"";
						stm.executeUpdate(sql);

						vData.remove(selectedrow);

						model.fireTableDataChanged(); 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chọn hàng để xóa !");
			}
		}
		

		public void Reset() {
			reload();
			model.fireTableDataChanged(); 
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("DELETE")) {
				Delete();
			}
			if (e.getActionCommand().equals("SEARCH ID")) {
				Search();
			}
			if (e.getActionCommand().equals("INSERT")) {
				Insert();
			}
			if (e.getActionCommand().equals("EDIT")) {
				Update();
			}
			if (e.getActionCommand().equals("RESET")) {
				Reset();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			selectedrow = tb.getSelectedRow();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}


