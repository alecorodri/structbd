<<<<<<< HEAD
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

import dto.CDRDto;
import services.CdrService;
import util.LoadTable;

@SuppressWarnings("serial")
public class CDRList extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_cdr;
	private JButton delete_cdr;
	private JLabel refresh;
	private JButton update_cdr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CDRList dialog = new CDRList();
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
	public CDRList() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CDRList.class.getResource("/resources/icons8_Marker_16.png")));
		setTitle("CDR");
		setResizable(false);
		setBounds(170, 170, 586, 399);
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
		scrollPane.setBounds(29, 22, 485, 283);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if (seleccion != -1) {
					update_cdr.setEnabled(true);
					delete_cdr.setEnabled(true);
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

		add_cdr = new JButton("Nuevo CDR");
		add_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCDR c = null;
				try {
					c = new NewCDR();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.setVisible(true);
			}
		});
		add_cdr.setForeground(Color.WHITE);
		add_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_cdr.setBorderPainted(false);
		add_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_cdr.setBackground(new Color(73, 78, 107));
		add_cdr.setAlignmentX(0.5f);
		add_cdr.setBounds(17, 316, 170, 31);
		contentPanel.add(add_cdr);

		delete_cdr = new JButton("Eliminar CDR");
		delete_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String cdr = (String) table.getValueAt(selection, 0);
				try {
					CDRDto cdr1 = CdrService.find_by_Name(cdr);
					System.out.println(cdr1.getNamCDR());
					CdrService.delete_CDR(cdr1.getCodCDR());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(CDRList.this,
						"CDR eliminado correctamente.",
						"ACCIÓN COMPLETADA", 1);
				try {
					LoadTable.Load_CDR(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_cdr.setEnabled(false);
				delete_cdr.setEnabled(false);
			}
		});
		delete_cdr.setEnabled(false);
		delete_cdr.setForeground(Color.WHITE);
		delete_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_cdr.setBorderPainted(false);
		delete_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_cdr.setBackground(new Color(140, 145, 168));
		delete_cdr.setAlignmentX(0.5f);
		delete_cdr.setBounds(391, 316, 170, 31);
		contentPanel.add(delete_cdr);

		LoadTable.Load_CDR(date, table);

		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_CDR(date, table);
					update_cdr.setEnabled(false);
					delete_cdr.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(524, 267, 37, 38);
		contentPanel.add(refresh);

		update_cdr = new JButton("Modificar CDR");
		update_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCDR c = null;
				int selection = table.getSelectedRow();
				String cdr = (String) table.getValueAt(selection, 0);
				CDRDto cdr1 = null;
				try {
					cdr1 = CdrService.find_by_Name(cdr);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(cdr1.getNamCDR());
				try {
					c = new UpdateCDR(cdr1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				c.setVisible(true);
			}
		});
		update_cdr.setForeground(Color.WHITE);
		update_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		update_cdr.setEnabled(false);
		update_cdr.setBorderPainted(false);
		update_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		update_cdr.setBackground(new Color(140, 145, 168));
		update_cdr.setAlignmentX(0.5f);
		update_cdr.setBounds(204, 316, 170, 31);
		contentPanel.add(update_cdr);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
=======
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

import dto.CDRDto;
import services.CdrService;
import util.LoadTable;

@SuppressWarnings("serial")
public class CDRList extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_cdr;
	private JButton delete_cdr;
	private JLabel refresh;
	private JButton update_cdr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CDRList dialog = new CDRList();
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
	public CDRList() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CDRList.class.getResource("/resources/icons8_Marker_16.png")));
		setTitle("CDR");
		setResizable(false);
		setBounds(170, 170, 586, 399);
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
		scrollPane.setBounds(29, 22, 485, 283);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if (seleccion != -1) {
					update_cdr.setEnabled(true);
					delete_cdr.setEnabled(true);
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

		add_cdr = new JButton("Nuevo CDR");
		add_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCDR c = null;
				try {
					c = new NewCDR();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.setVisible(true);
			}
		});
		add_cdr.setForeground(Color.WHITE);
		add_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_cdr.setBorderPainted(false);
		add_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_cdr.setBackground(new Color(73, 78, 107));
		add_cdr.setAlignmentX(0.5f);
		add_cdr.setBounds(17, 316, 170, 31);
		contentPanel.add(add_cdr);

		delete_cdr = new JButton("Eliminar CDR");
		delete_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String cdr = (String) table.getValueAt(selection, 0);
				try {
					CDRDto cdr1 = CdrService.find_by_Name(cdr);
					System.out.println(cdr1.getNamCDR());
					CdrService.delete_CDR(cdr1.getCodCDR());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(CDRList.this,
						"CDR eliminado correctamente.",
						"ACCIÓN COMPLETADA", 1);
				try {
					LoadTable.Load_CDR(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_cdr.setEnabled(false);
				delete_cdr.setEnabled(false);
			}
		});
		delete_cdr.setEnabled(false);
		delete_cdr.setForeground(Color.WHITE);
		delete_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_cdr.setBorderPainted(false);
		delete_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_cdr.setBackground(new Color(140, 145, 168));
		delete_cdr.setAlignmentX(0.5f);
		delete_cdr.setBounds(391, 316, 170, 31);
		contentPanel.add(delete_cdr);

		LoadTable.Load_CDR(date, table);

		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_CDR(date, table);
					update_cdr.setEnabled(false);
					delete_cdr.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(524, 267, 37, 38);
		contentPanel.add(refresh);

		update_cdr = new JButton("Modificar CDR");
		update_cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCDR c = null;
				int selection = table.getSelectedRow();
				String cdr = (String) table.getValueAt(selection, 0);
				CDRDto cdr1 = null;
				try {
					cdr1 = CdrService.find_by_Name(cdr);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(cdr1.getNamCDR());
				try {
					c = new UpdateCDR(cdr1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				c.setVisible(true);
			}
		});
		update_cdr.setForeground(Color.WHITE);
		update_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		update_cdr.setEnabled(false);
		update_cdr.setBorderPainted(false);
		update_cdr.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		update_cdr.setBackground(new Color(140, 145, 168));
		update_cdr.setAlignmentX(0.5f);
		update_cdr.setBounds(204, 316, 170, 31);
		contentPanel.add(update_cdr);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
}