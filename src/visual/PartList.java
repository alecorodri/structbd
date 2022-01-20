<<<<<<< HEAD
package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import dto.DistrictDto;
import dto.Electoral_CollegeDto;
import dto.Electoral_ProcessDto;
import dto.VoterDto;
import services.CdrService;
import services.CollegeService;
import services.DistrictService;
import services.ElectoralProcessService;
import services.MunicipalityService;
import services.PartService;
import services.VoterService;
import util.LoadTable;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PartList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PartList dialog = new PartList();
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
	public PartList() throws SQLException {
		setModal(true);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PartList.class.getResource("/resources/icons8_Layers_16.png")));
		setTitle("Parte");
		setResizable(false);
		setBounds(170, 170, 586, 388);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCrearParte = new JButton("Crear Parte");
		btnCrearParte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Electoral_ProcessDto e = null;
				try {
					e = ElectoralProcessService.find_by_Municipality(MunicipalityService.find_by_Name((String) comboBox.getSelectedItem()).getCodMun());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int quan_elect = 0, quan_deleted = 0, quan_add = 0, total = 0;
				ArrayList<VoterDto> voters = null;
				try {
					voters = VoterService.getVoters();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				for (int i = 0; i < voters.size(); i++) {
					try {
						CDRDto cdr = CdrService.find_by_Id(voters.get(i).getCdr());
						Electoral_CollegeDto col = CollegeService.find_by_Id(cdr.getId_college());
						DistrictDto d = DistrictService.find_by_Id(col.getId_district());
						if(d.getIdMunicipality()==e.getIdMunicipality()){
							total++;
							if(voters.get(i).getVote()==1){
								quan_elect++;
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				quan_add = (int) (Math.random()*10+1);
				quan_deleted = (int) (Math.random()*10+1);
				Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());
				try {
					PartService.createPart(quan_elect, quan_deleted, quan_add, total, e.getId_EProcess(), time);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCrearParte.setForeground(Color.WHITE);
		btnCrearParte.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnCrearParte.setBorderPainted(false);
		btnCrearParte.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		btnCrearParte.setBackground(new Color(73, 78, 107));
		btnCrearParte.setAlignmentX(0.5f);
		btnCrearParte.setBounds(435, 317, 122, 31);
		contentPanel.add(btnCrearParte);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		comboBox.setBounds(195, 19, 200, 23);
		contentPanel.add(comboBox);
		ArrayList<Electoral_ProcessDto> ep = ElectoralProcessService.getElectoral_ProcessDto();
		for(int i = 0; i < ep.size(); i++){
			comboBox.addItem(MunicipalityService.find_by_Id(ep.get(i).getIdMunicipality()).getNamMun());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(29, 57, 528, 249);
		contentPanel.add(scrollPane);

		table = new JTable();
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
		
		/*create_part = new JButton("Crear Parte");
		create_part.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String part = (String) table.getValueAt(selection, 0);
				try {
					PartService.createPart(quan_elect, quan_delet, quan_add, total, electProces, time));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(PartList.this,"Parte creado correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_Part(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		/*create_part.setEnabled(false);
		create_part.setForeground(Color.WHITE);
		create_part.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		create_part.setBorderPainted(false);
		create_part.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		create_part.setBackground(new Color(140, 145, 168));
		create_part.setAlignmentX(0.5f);
		create_part.setBounds(387, 316, 170, 31);
		contentPanel.add(create_part);*/
		
		LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
		
		JLabel lblProcesoElectoral = new JLabel("Proceso Electoral:");
		lblProcesoElectoral.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcesoElectoral.setForeground(new Color(17, 24, 63));
		lblProcesoElectoral.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblProcesoElectoral.setBounds(29, 14, 156, 32);
		contentPanel.add(lblProcesoElectoral);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
}
=======
package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import dto.DistrictDto;
import dto.Electoral_CollegeDto;
import dto.Electoral_ProcessDto;
import dto.VoterDto;
import services.CdrService;
import services.CollegeService;
import services.DistrictService;
import services.ElectoralProcessService;
import services.MunicipalityService;
import services.PartService;
import services.VoterService;
import util.LoadTable;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PartList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PartList dialog = new PartList();
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
	public PartList() throws SQLException {
		setModal(true);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PartList.class.getResource("/resources/icons8_Layers_16.png")));
		setTitle("Parte");
		setResizable(false);
		setBounds(170, 170, 586, 388);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCrearParte = new JButton("Crear Parte");
		btnCrearParte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Electoral_ProcessDto e = null;
				try {
					e = ElectoralProcessService.find_by_Municipality(MunicipalityService.find_by_Name((String) comboBox.getSelectedItem()).getCodMun());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int quan_elect = 0, quan_deleted = 0, quan_add = 0, total = 0;
				ArrayList<VoterDto> voters = null;
				try {
					voters = VoterService.getVoters();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				for (int i = 0; i < voters.size(); i++) {
					try {
						CDRDto cdr = CdrService.find_by_Id(voters.get(i).getCdr());
						Electoral_CollegeDto col = CollegeService.find_by_Id(cdr.getId_college());
						DistrictDto d = DistrictService.find_by_Id(col.getId_district());
						if(d.getIdMunicipality()==e.getIdMunicipality()){
							total++;
							if(voters.get(i).getVote()==1){
								quan_elect++;
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				quan_add = (int) (Math.random()*10+1);
				quan_deleted = (int) (Math.random()*10+1);
				Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());
				try {
					PartService.createPart(quan_elect, quan_deleted, quan_add, total, e.getId_EProcess(), time);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCrearParte.setForeground(Color.WHITE);
		btnCrearParte.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnCrearParte.setBorderPainted(false);
		btnCrearParte.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		btnCrearParte.setBackground(new Color(73, 78, 107));
		btnCrearParte.setAlignmentX(0.5f);
		btnCrearParte.setBounds(435, 317, 122, 31);
		contentPanel.add(btnCrearParte);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		comboBox.setBounds(195, 19, 200, 23);
		contentPanel.add(comboBox);
		ArrayList<Electoral_ProcessDto> ep = ElectoralProcessService.getElectoral_ProcessDto();
		for(int i = 0; i < ep.size(); i++){
			comboBox.addItem(MunicipalityService.find_by_Id(ep.get(i).getIdMunicipality()).getNamMun());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(29, 57, 528, 249);
		contentPanel.add(scrollPane);

		table = new JTable();
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
		
		/*create_part = new JButton("Crear Parte");
		create_part.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String part = (String) table.getValueAt(selection, 0);
				try {
					PartService.createPart(quan_elect, quan_delet, quan_add, total, electProces, time));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(PartList.this,"Parte creado correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_Part(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		/*create_part.setEnabled(false);
		create_part.setForeground(Color.WHITE);
		create_part.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		create_part.setBorderPainted(false);
		create_part.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		create_part.setBackground(new Color(140, 145, 168));
		create_part.setAlignmentX(0.5f);
		create_part.setBounds(387, 316, 170, 31);
		contentPanel.add(create_part);*/
		
		LoadTable.Load_Part(date, table, (String) comboBox.getSelectedItem());
		
		JLabel lblProcesoElectoral = new JLabel("Proceso Electoral:");
		lblProcesoElectoral.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcesoElectoral.setForeground(new Color(17, 24, 63));
		lblProcesoElectoral.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblProcesoElectoral.setBounds(29, 14, 156, 32);
		contentPanel.add(lblProcesoElectoral);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
