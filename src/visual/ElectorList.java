package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

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
import dto.VoterDto;
import services.CollegeService;
import services.VoterService;
import util.LoadTable;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ElectorList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JButton delete;
	private JButton modd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElectorList dialog = new ElectorList();
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
	public ElectorList() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ElectorList.class.getResource("/resources/icons8_Conference_16.png")));
		setTitle("Electores");
		setResizable(false);
		setBounds(170, 170, 586, 423);
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
		scrollPane.setBounds(17, 63, 515, 265);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if (seleccion != -1) {
					modd.setEnabled(true);
					delete.setEnabled(true);
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
		
		add = new JButton("Nuevo Elector");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElector n = null;
				try {
					n = new NewElector(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n.setVisible(true);
			}
		});
		add.setForeground(Color.WHITE);
		add.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add.setBorderPainted(false);
		add.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add.setBackground(new Color(73, 78, 107));
		add.setAlignmentX(0.5f);
		add.setBounds(17, 339, 170, 31);
		contentPanel.add(add);
		
		modd = new JButton("Modificar Elector");
		modd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElector n = null;
				int selection = table.getSelectedRow();
				String nameV = (String) table.getValueAt(selection, 1);
				VoterDto vot = null;
				try {
					vot = VoterService.find_by_Name(nameV);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					n = new NewElector(vot);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n.setVisible(true);
			}
		});
		modd.setForeground(Color.WHITE);
		modd.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		modd.setEnabled(false);
		modd.setBorderPainted(false);
		modd.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		modd.setBackground(new Color(140, 145, 168));
		modd.setAlignmentX(0.5f);
		modd.setBounds(204, 339, 170, 31);
		contentPanel.add(modd);
		
		delete = new JButton("Eliminar Elector");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String nameV = (String) table.getValueAt(selection, 1);
				try {
					VoterDto vot = VoterService.find_by_Name(nameV);
					VoterService.deleteVoter(vot.getNumID());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(ElectorList.this,
						"Elector eliminado correctamente.",
						"ACCIÓN COMPLETADA", 1);
				try {
					LoadTable.Load_Elector(date, table, (String) comboBox.getSelectedItem());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				modd.setEnabled(false);
				delete.setEnabled(false);
			}
		});
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete.setEnabled(false);
		delete.setBorderPainted(false);
		delete.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete.setBackground(new Color(140, 145, 168));
		delete.setAlignmentX(0.5f);
		delete.setBounds(391, 339, 170, 31);
		contentPanel.add(delete);
				
				lblNewLabel = new JLabel("Colegio Electoral:");
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
				lblNewLabel.setBounds(40, 11, 181, 41);
				contentPanel.add(lblNewLabel);
				
				JLabel label = new JLabel("");
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							LoadTable.Load_Elector(date, table, (String) comboBox.getSelectedItem());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						modd.setEnabled(false);
						delete.setEnabled(false);
					}
				});
				label.setIcon(new ImageIcon(ElectorList.class.getResource("/resources/icons8_Synchronize_32.png")));
				label.setBounds(534, 280, 36, 41);
				contentPanel.add(label);
				
				comboBox = new JComboBox<String>();
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							LoadTable.Load_Elector(date, table, (String) comboBox.getSelectedItem());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						modd.setEnabled(false);
						delete.setEnabled(false);
					}
				});
				comboBox.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
				comboBox.setBounds(220, 21, 253, 25);
				contentPanel.add(comboBox);
				ArrayList<Electoral_CollegeDto> college = CollegeService.getVoters();
				for (int i = 0; i < college.size(); i++) {
					comboBox.addItem(college.get(i).getNameCollege());
				}
				
				JLabel fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
				fondo.setBounds(0, -26, 580, 396);
				contentPanel.add(fondo);
				modd.setEnabled(false);
				delete.setEnabled(false);
				
				LoadTable.Load_Elector(date, table, (String) comboBox.getSelectedItem());
	}
}

