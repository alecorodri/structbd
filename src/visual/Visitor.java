package visual;

import java.awt.Color;
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
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import services.CdrService;
import services.DistrictService;
import services.MunicipalityService;
import util.TextPrompt;
import dto.CDRDto;
import dto.DistrictDto;
import dto.Electoral_CollegeDto;
import dto.MunicipalityDto;

import javax.swing.JTextField;

public class Visitor {

	private JFrame frmSistemaDeVotacin;
	private JMenuItem close;
	private JPanel elector;
	private JLabel label;
	private JComboBox mun;
	private JComboBox district;
	private JComboBox college;
	private JComboBox cdr;
	private JTextField nameVoter;
	private JTextField adress;

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
					}
				}
			}
		});
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		JLabel lblCdr = new JLabel("CDR:");
		lblCdr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdr.setForeground(new Color(17, 24, 63));
		lblCdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblCdr.setBounds(10, 161, 156, 32);
		elector.add(lblCdr);
		
		label = new JLabel("");
		label.setBounds(341, -17, 333, 385);
		frmSistemaDeVotacin.getContentPane().add(label);
		label.setIcon(new ImageIcon(Visitor.class.getResource("/resources/undraw_voting_nvu7.png")));
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeVotacin.setJMenuBar(menuBar);
		menuBar.setBackground(new Color(140, 145, 168));
		menuBar.setForeground(new Color(140, 145, 168));
		
		JMenu mnNewMenu = new JMenu("Sistema");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		menuBar.add(mnNewMenu);
		mnNewMenu.setBackground(new Color(140, 145, 168));
		
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
