package visual;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.ComponentOrientation;
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
<<<<<<< HEAD
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
=======
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import services.CdrService;
<<<<<<< HEAD
import services.NominatedService;
import services.VoterService;
import util.LoadTable;
import dto.CDRDto;
import dto.NominatedDto;
import dto.VoterDto;

import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
=======
import services.DistrictService;
import services.MunicipalityService;
import util.TextPrompt;
import dto.CDRDto;
import dto.DistrictDto;
import dto.Electoral_CollegeDto;
import dto.MunicipalityDto;

import javax.swing.JTextField;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda

public class Visitor {

	private JFrame frmSistemaDeVotacin;
	private JMenuItem close;
	private JPanel elector;
	private JLabel label;
<<<<<<< HEAD
	private JComboBox<String> comboBox;
	private DefaultTableModel date;
	private DefaultTableModel date_1;
	private JRadioButton rdbtnVotar;
	private JRadioButton rdbtnNoVotar;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_1;
	private JTextPane textPane_1;
	private JButton button;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel cartel;
=======
	private JComboBox mun;
	private JComboBox district;
	private JComboBox college;
	private JComboBox cdr;
	private JTextField nameVoter;
	private JTextField adress;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visitor window = new Visitor();
					window.frmSistemaDeVotacin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Visitor() throws SQLException {
		initialize();
		frmSistemaDeVotacin.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmSistemaDeVotacin.setLocation((screenSize.width - frmSistemaDeVotacin.getWidth()) / 2,(screenSize.height - frmSistemaDeVotacin.getHeight()) / 2);
<<<<<<< HEAD

		rdbtnVotar = new JRadioButton("Votar");
		rdbtnVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnVotar.isSelected()){
					rdbtnNoVotar.setEnabled(false);
					button.setVisible(true);
					scrollPane_1.setVisible(true);
					cartel.setVisible(true);
					table.setEnabled(false);
					cartel.setText("Nominados:");
					try {
						LoadTable.Load_Nominated_1(date_1, table_1, (String) comboBox.getSelectedItem());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					rdbtnNoVotar.setEnabled(true);
					button.setVisible(false);
					table.setEnabled(true);
					scrollPane_1.setVisible(false);
					cartel.setVisible(false);
				}
			}
		});
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		rdbtnVotar.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		rdbtnVotar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		rdbtnVotar.setBounds(48, 394, 109, 23);
		elector.add(rdbtnVotar);

		rdbtnNoVotar = new JRadioButton("No votar");
		rdbtnNoVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNoVotar.isSelected()){
					rdbtnVotar.setEnabled(false);
					textPane_1.setVisible(true);
					button.setVisible(true);
					table.setEnabled(false);
					cartel.setVisible(true);
					cartel.setText("Escriba la Causa:");
				}else{
					rdbtnVotar.setEnabled(true);
					textPane_1.setVisible(false);
					button.setVisible(false);
					table.setEnabled(true);
					cartel.setVisible(false);
				}
			}
		});
		rdbtnNoVotar.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		rdbtnNoVotar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		rdbtnNoVotar.setBounds(201, 394, 109, 23);
		elector.add(rdbtnNoVotar);

		button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNoVotar.isSelected()){
					if(textPane_1.getText().isEmpty()){
						JOptionPane.showMessageDialog(frmSistemaDeVotacin,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
					}else{
						int seleccion = -1;
						seleccion = table.getSelectedRow();
						String name = (String) table.getValueAt(seleccion, 0);
						VoterDto v = null;
						try {
							v = VoterService.find_by_Name(name);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						v.setVote(2);
						v.setCause(textPane_1.getText());
						try {
							VoterService.updateVoter(v);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(frmSistemaDeVotacin,"Se realizo la votacion de manera correcta.","ACCION COMPLETADA",1);
						Login lg = new Login();
						lg.setVisible(true);
						frmSistemaDeVotacin.setVisible(false);
					}
				}else{
					int seleccion = -1;
					seleccion = table_1.getSelectedRow();
					if(seleccion==-1){
					   JOptionPane.showMessageDialog(frmSistemaDeVotacin,"Seleccione el nominado por quien desea votar.","CAMPOS VACÍOS",0);
					}else{
						int selection = -1;
						selection = table.getSelectedRow();
						String name = (String) table.getValueAt(selection, 0);
						VoterDto v = null;
						try {
							v = VoterService.find_by_Name(name);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						v.setVote(1);
						try {
							VoterService.updateVoter(v);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int id = (int)table_1.getValueAt(seleccion, 0);
						NominatedDto nn = null;
						try {
							nn = NominatedService.find_by_Id(id);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						nn.setCantVotes(nn.getCantVotes()+1);
						try {
							NominatedService.update_nominated(nn);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(frmSistemaDeVotacin,"Se realizo la votacion de manera correcta.","ACCION COMPLETADA",1);
						Login lg = new Login();
						lg.setVisible(true);
						frmSistemaDeVotacin.setVisible(false);
=======
		district.setEnabled(false);
		college.setEnabled(false);
		cdr.setEnabled(false);
		nameVoter.setEnabled(false);
		adress.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() throws SQLException {
		frmSistemaDeVotacin = new JFrame();
		frmSistemaDeVotacin.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSistemaDeVotacin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSistemaDeVotacin,"¿Está seguro que desea salir?","SALIR DEL SISTEMA",2,2) == 0){
					System.exit(0);
				}
			}
		});
		frmSistemaDeVotacin.setTitle("Sistema de Votaci\u00F3n del Proceso Electoral");
		frmSistemaDeVotacin.setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/resources/icons8_True_False_16.png")));
		frmSistemaDeVotacin.setResizable(false);
		frmSistemaDeVotacin.setBounds(100, 100, 687, 424);
		frmSistemaDeVotacin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeVotacin.setBackground(new Color(238, 242, 236));
		frmSistemaDeVotacin.getContentPane().setLayout(null);
		
		elector = new JPanel();
		elector.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		elector.setBounds(0, -26, 352, 394);
		frmSistemaDeVotacin.getContentPane().add(elector);
		elector.setLayout(null);
		elector.setBackground(new Color(238, 242, 236));
		
		nameVoter = new JTextField();
		TextPrompt nameHelp = new TextPrompt("Nombre(s) y Apellidos", nameVoter);
		nameHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		nameVoter.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		nameVoter.setColumns(10);
		nameVoter.setBounds(20, 209, 322, 23);
		elector.add(nameVoter);
		
		adress = new JTextField();
		TextPrompt adressHelp = new TextPrompt("Direcci", adress);
		adressHelp.setText("Direcci\u00F3n de residencia");
		adressHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		adress.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		adress.setEnabled(false);
		adress.setColumns(10);
		adress.setBounds(20, 254, 322, 23);
		elector.add(adress);
		
		JLabel label_1 = new JLabel("Municipio:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(new Color(17, 24, 63));
		label_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		label_1.setBounds(10, 32, 156, 32);
		elector.add(label_1);
		
		JLabel lblCircunscripcin_1 = new JLabel("Circunscripci\u00F3n:");
		lblCircunscripcin_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCircunscripcin_1.setForeground(new Color(17, 24, 63));
		lblCircunscripcin_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblCircunscripcin_1.setBounds(10, 75, 156, 32);
		elector.add(lblCircunscripcin_1);
		
		JLabel lblCircunscripcin = new JLabel("Colegio Electoral:");
		lblCircunscripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCircunscripcin.setForeground(new Color(17, 24, 63));
		lblCircunscripcin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblCircunscripcin.setBounds(10, 118, 156, 32);
		elector.add(lblCircunscripcin);
		
		cdr = new JComboBox();
		cdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cdr.isEnabled()){
					nameVoter.setEnabled(true);
					adress.setEnabled(true);
				}
			}
		});
		cdr.setEnabled(false);
		cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cdr.setBackground(new Color(238, 242, 236));
		cdr.setBounds(176, 168, 166, 21);
		elector.add(cdr);
		
		college = new JComboBox();
		college.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Electoral_CollegeDto e = null;
				cdr.removeAllItems();
				/*try {
					e = ElectoralCollegesService.find_by_Name((String) college.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				ArrayList<CDRDto> cdrs = null;
				try {
					cdrs = CdrService.getCDRDto();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(int i=0; i<cdrs.size(); i++){
					if(e!=null && cdrs.get(i).getId_college()==e.getCodCollege()){
						cdr.addItem(cdrs.get(i).getNamCDR());
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
					}
				}
			}
		});
<<<<<<< HEAD
		button.setVisible(false);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		button.setBorderPainted(false);
		button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		button.setBackground(new Color(73, 78, 107));
		button.setAlignmentX(0.5f);
		button.setBounds(431, 361, 91, 31);
		frmSistemaDeVotacin.getContentPane().add(button);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login lg = new Login();
				lg.setVisible(true);
				frmSistemaDeVotacin.setVisible(false);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		btnCancelar.setBackground(new Color(73, 78, 107));
		btnCancelar.setAlignmentX(0.5f);
		btnCancelar.setBounds(551, 361, 91, 31);
		frmSistemaDeVotacin.getContentPane().add(btnCancelar);

		textPane_1 = new JTextPane();
		textPane_1.setVisible(false);
		textPane_1.setBounds(393, 61, 290, 277);
		frmSistemaDeVotacin.getContentPane().add(textPane_1);
		rdbtnNoVotar.setEnabled(false);
		rdbtnVotar.setEnabled(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(24, 82, 305, 301);
		elector.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if(seleccion != -1 && table.isEnabled()){
					rdbtnNoVotar.setEnabled(true);
					rdbtnVotar.setEnabled(true);
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
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoadTable.Load_Elector_1(date, table, (String) comboBox.getSelectedItem());
=======
		college.setEnabled(false);
		college.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		college.setBackground(new Color(238, 242, 236));
		college.setBounds(176, 125, 166, 21);
		elector.add(college);
		
		district = new JComboBox();
		district.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DistrictDto d = null;
				college.removeAllItems();
				try {
					d = DistrictService.find_by_Name((String) district.getSelectedItem());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Electoral_CollegeDto> colleges = null;
				/*try {
					colleges = ElectoralCollegesService.getColleges();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				for(int i=0; i<colleges.size(); i++){
					if(d!=null && colleges.get(i).getId_district()==d.getCodDis()){
						college.addItem(colleges.get(i).getNameCollege());
					}
				}
			}
		});
		district.setEnabled(false);
		district.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		district.setBackground(new Color(238, 242, 236));
		district.setBounds(176, 82, 166, 21);
		elector.add(district);
		
		mun = new JComboBox();
		mun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(mun.getItemAt(0).equals("Municipios")){
					mun.removeItem("Municipios");
					district.setEnabled(true);
				}
			}
		});
		mun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MunicipalityDto municipality = null;
				district.removeAllItems();
				try {
					municipality = MunicipalityService.find_by_Name((String) mun.getSelectedItem());
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
<<<<<<< HEAD
			}
		});
		ArrayList<CDRDto> cdr = CdrService.getCDRDto();
		for(int i = 0; i < cdr.size(); i++){
			comboBox.addItem(cdr.get(i).getNamCDR());
		}
		comboBox.setBounds(132, 48, 186, 20);
		elector.add(comboBox);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		comboBox.setBackground(new Color(238, 242, 236));
		comboBox.setBounds(117, 48, 166, 21);
		
		LoadTable.Load_Elector_1(date, table, (String) comboBox.getSelectedItem());
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(false);
		scrollPane_1.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(393, 61, 290, 289);
		frmSistemaDeVotacin.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setUpdateSelectionOnSort(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowHeight(21);
		table_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_1.setBackground(Color.WHITE);
		table_1.setAutoCreateRowSorter(true);
		table_1.setMinimumSize(new Dimension(2, 2));
		table_1.setMaximumSize(new Dimension(2, 2));
		table_1.setIntercellSpacing(new Dimension(2, 2));
		table_1.setInheritsPopupMenu(true);
		table_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		table_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.setBounds(0, 0, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_1.setColumnHeaderView(table_1);
		scrollPane_1.setViewportView(this.table_1);

				label = new JLabel("");
				label.setBounds(329, 11, 379, 385);
				frmSistemaDeVotacin.getContentPane().add(label);
				label.setIcon(new ImageIcon(Visitor.class.getResource("/resources/undraw_voting_nvu7.png")));
				
				cartel = new JLabel("Nombre:");
				cartel.setVisible(false);
				cartel.setHorizontalAlignment(SwingConstants.CENTER);
				cartel.setForeground(new Color(17, 24, 63));
				cartel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
				cartel.setBounds(393, 11, 290, 37);
				frmSistemaDeVotacin.getContentPane().add(cartel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frmSistemaDeVotacin = new JFrame();
		frmSistemaDeVotacin.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSistemaDeVotacin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSistemaDeVotacin,"¿Está seguro que desea salir?","SALIR DEL SISTEMA",2,2) == 0){
					System.exit(0);
				}
			}
		});
		frmSistemaDeVotacin.setTitle("Sistema de Votaci\u00F3n del Proceso Electoral");
		frmSistemaDeVotacin.setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/resources/icons8_True_False_16.png")));
		frmSistemaDeVotacin.setResizable(false);
		frmSistemaDeVotacin.setBounds(100, 100, 714, 473);
		frmSistemaDeVotacin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeVotacin.setBackground(new Color(238, 242, 236));
		frmSistemaDeVotacin.getContentPane().setLayout(null);

		elector = new JPanel();
		elector.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		elector.setBounds(0, -26, 352, 445);
		frmSistemaDeVotacin.getContentPane().add(elector);
		elector.setLayout(null);
		elector.setBackground(new Color(238, 242, 236));

=======
				ArrayList<DistrictDto> d = null;
				try {
					d = DistrictService.getDistrict();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0; i<d.size(); i++){
					if(municipality!=null && d.get(i).getIdMunicipality()==municipality.getCodMun()){
						district.addItem(d.get(i).getNamDis());
					}
				}
			}
		});
		mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		mun.setBackground(new Color(238, 242, 236));
		mun.setBounds(176, 39, 166, 21);
		elector.add(mun);
		mun.addItem("Municipios");
		ArrayList<MunicipalityDto> m = MunicipalityService.getMunicipality();
		for(int i=0; i<m.size(); i++){
			mun.addItem(m.get(i).getNamMun());
		}
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		JLabel lblCdr = new JLabel("CDR:");
		lblCdr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdr.setForeground(new Color(17, 24, 63));
		lblCdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
<<<<<<< HEAD
		lblCdr.setBounds(-49, 41, 156, 32);
		elector.add(lblCdr);

=======
		lblCdr.setBounds(10, 161, 156, 32);
		elector.add(lblCdr);
		
		label = new JLabel("");
		label.setBounds(341, -17, 333, 385);
		frmSistemaDeVotacin.getContentPane().add(label);
		label.setIcon(new ImageIcon(Visitor.class.getResource("/resources/undraw_voting_nvu7.png")));
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeVotacin.setJMenuBar(menuBar);
		menuBar.setBackground(new Color(140, 145, 168));
		menuBar.setForeground(new Color(140, 145, 168));
<<<<<<< HEAD

=======
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		JMenu mnNewMenu = new JMenu("Sistema");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		menuBar.add(mnNewMenu);
		mnNewMenu.setBackground(new Color(140, 145, 168));
<<<<<<< HEAD

=======
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		close = new JMenuItem("Cerrar sesi\u00F3n");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSistemaDeVotacin,"¿Está seguro que desea salir?","SALIR DEL SISTEMA",2,2) == 0){
					Login lg = new Login();
					lg.setVisible(true);
					frmSistemaDeVotacin.setVisible(false);
				}
			}
		});
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		close.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Cancel_16.png")));
		close.setForeground(new Color(17, 24, 63));
		close.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		close.setBackground(new Color(238, 242, 236));
		mnNewMenu.add(close);
	}
}
