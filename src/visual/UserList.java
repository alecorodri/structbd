package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dto.User;
import services.UserService;
import util.LoadTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class UserList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_user;
	private JButton delete_user;
	private JLabel refresh;
	private User uss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserList dialog = new UserList(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public UserList(User u) throws SQLException {
		uss = u;
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserList.class.getResource("/resources/icons8_Conference_16.png")));
		setTitle("Usuarios del Sistema");
		setResizable(false);
		setBounds(170, 170, 503, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(23, 21, 276, 321);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if(seleccion != -1){
					delete_user.setEnabled(true);
				}
			}
		});
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(21);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		table.setMinimumSize(new Dimension(2, 2));
		table.setMaximumSize(new Dimension(2, 2));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setInheritsPopupMenu(true);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(0, 0, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(this.table);
		
		add_user = new JButton("Nuevo Usuario");
		add_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser newUss = new NewUser();
				newUss.setVisible(true);
			}
		});
		add_user.setForeground(Color.WHITE);
		add_user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_user.setBorderPainted(false);
		add_user.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_user.setBackground(new Color(73, 78, 107));
		add_user.setAlignmentX(0.5f);
		add_user.setBounds(315, 126, 170, 31);
		contentPanel.add(add_user);
		
		delete_user = new JButton("Eliminar Usuario");
		delete_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				int id_user = (int) table.getValueAt(selection, 0);
				try {
					UserService.delete_User(id_user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(UserList.this,"Usuario eliminado correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_User(date, table, uss);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				delete_user.setEnabled(false);
			}
		});
		delete_user.setEnabled(false);
		delete_user.setForeground(Color.WHITE);
		delete_user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_user.setBorderPainted(false);
		delete_user.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_user.setBackground(new Color(140, 145, 168));
		delete_user.setAlignmentX(0.5f);
		delete_user.setBounds(315, 184, 170, 31);
		contentPanel.add(delete_user);
		
		LoadTable.Load_User(date, table, uss);
		
		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_User(date, table, uss);
					delete_user.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(301, 304, 37, 38);
		contentPanel.add(refresh);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
}
