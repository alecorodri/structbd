package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dto.Electoral_CollegeDto;
import services.CollegeService;
import util.LoadTable;

@SuppressWarnings("serial")
public class ElectoralCollegeList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_coll;
	private JButton delete_colegio;
	private JLabel refresh;
	private JButton update_college;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElectoralCollegeList dialog = new ElectoralCollegeList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public ElectoralCollegeList() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ElectoralCollegeList.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Colegios Electorales");
		setResizable(false);
		setBounds(170, 170, 525, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(24, 21, 275, 321);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if (seleccion != -1) {
					update_college.setEnabled(true);
					delete_colegio.setEnabled(true);
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

		add_coll = new JButton("Nuevo Colegio");
		add_coll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCollege n = null;
				try {
					n = new NewCollege(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n.setVisible(true);
			}
		});
		add_coll.setForeground(Color.WHITE);
		add_coll.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_coll.setBorderPainted(false);
		add_coll.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_coll.setBackground(new Color(73, 78, 107));
		add_coll.setAlignmentX(0.5f);
		add_coll.setBounds(331, 106, 170, 31);
		contentPanel.add(add_coll);

		delete_colegio = new JButton("Eliminar Colegio");
		delete_colegio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String college = (String) table.getValueAt(selection, 0);
				try {
					Electoral_CollegeDto col = CollegeService.find_by_Name(college);
					CollegeService.deleteCollege(col.getCodCollege());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(ElectoralCollegeList.this,
						"Colegio Electoral eliminado correctamente.",
						"ACCIÓN COMPLETADA", 1);
				try {
					LoadTable.Load_College(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_college.setEnabled(false);
				delete_colegio.setEnabled(false);
			}
		});
		delete_colegio.setEnabled(false);
		delete_colegio.setForeground(Color.WHITE);
		delete_colegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_colegio.setBorderPainted(false);
		delete_colegio.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_colegio.setBackground(new Color(140, 145, 168));
		delete_colegio.setAlignmentX(0.5f);
		delete_colegio.setBounds(331, 218, 170, 31);
		contentPanel.add(delete_colegio);

		LoadTable.Load_College(date, table);

		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_College(date, table);
					update_college.setEnabled(false);
					delete_colegio.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(301, 304, 37, 38);
		contentPanel.add(refresh);

		update_college = new JButton("Modificar Colegio");
		update_college.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCollege n = null;
				int selection = table.getSelectedRow();
				String college = (String) table.getValueAt(selection, 0);
				Electoral_CollegeDto col = null;
				try {
					col = CollegeService.find_by_Name(college);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(col.getNameCollege());
				try {
					n = new NewCollege(col);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				n.setVisible(true);
			}
		});
		update_college.setForeground(Color.WHITE);
		update_college.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		update_college.setEnabled(false);
		update_college.setBorderPainted(false);
		update_college.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		update_college.setBackground(new Color(140, 145, 168));
		update_college.setAlignmentX(0.5f);
		update_college.setBounds(331, 162, 170, 31);
		contentPanel.add(update_college);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
}