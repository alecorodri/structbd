package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JLabel;

import dto.User;
import services.UserService;
import util.Encrypt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage {

	private User userLocator;
	private JFrame frmSistema;
	private JMenuItem change_pass;
	private JMenuItem usss;
	private JMenuItem trace;
	private JMenuItem close;
	private JLabel photo;
	private JLabel help;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage(10);
					window.frmSistema.setVisible(true);
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
	public HomePage(int user) throws SQLException {
		initialize();
		frmSistema.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmSistema.setLocation((screenSize.width - frmSistema.getWidth()) / 2,(screenSize.height - frmSistema.getHeight()) / 2);
		userLocator = UserService.find_by_Id(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistema = new JFrame();
		frmSistema.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSistema,"¿Está seguro que desea salir?","SALIR DEL SISTEMA",2,2) == 0){
					System.exit(0);
				}
			}
		});
		frmSistema.setTitle("Sistema Gestor del Proceso Electoral");
		frmSistema.setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/resources/icons8_True_False_16.png")));
		frmSistema.setExtendedState(Frame.MAXIMIZED_HORIZ);
		frmSistema.setResizable(false);
		frmSistema.setBounds(100, 100, 586, 399);
		frmSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistema.setBackground(new Color(238, 242, 236));
		frmSistema.getContentPane().setLayout(null);
		
		photo = new JLabel("");
		photo.setIcon(new ImageIcon(HomePage.class.getResource("/resources/undraw_voting_nvu7.png")));
		photo.setBounds(65, 11, 426, 321);
		frmSistema.getContentPane().add(photo);
		
		help = new JLabel("");
		help.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Help_32.png")));
		help.setBounds(541, 292, 39, 51);
		frmSistema.getContentPane().add(help);
		
		JMenuBar menuBar = new JMenuBar();
		frmSistema.setJMenuBar(menuBar);
		menuBar.setBackground(new Color(140, 145, 168));
		menuBar.setForeground(new Color(140, 145, 168));
		
		JMenu mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		menuBar.add(mnNewMenu);
		mnNewMenu.setBackground(new Color(140, 145, 168));
		
		change_pass = new JMenuItem("Cambiar contrase\u00F1a");
		change_pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newPass = null;
				newPass = JOptionPane.showInputDialog(frmSistema, "Escriba la nueva contraseña:", "NUEVA CONTRASEÑA",1);
				if(newPass != null){
					if(newPass.isEmpty()){
						JOptionPane.showMessageDialog(frmSistema, "Debe escribir algo, la contraseña no puede estar vacía.", "CAMPO VACÍO", 0);
						change_pass.doClick();
					}
					else{
						userLocator.setPassword(Encrypt.getMd5(newPass));
						try {
							UserService.update_User(userLocator);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(frmSistema, "Contraseña cambiada satisfactoriamente.", "ACCIÓN COMPLETADA", 1);
					}
				}
			}
		});
		change_pass.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Secure_16.png")));
		change_pass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		change_pass.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		change_pass.setBackground(new Color(238, 242, 236));
		change_pass.setForeground(new Color(17, 24, 63));
		mnNewMenu.add(change_pass);
		
		usss = new JMenuItem("Listado de usuarios");
		usss.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Conference_16.png")));
		usss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserList users = null;
				try {
					users = new UserList(userLocator);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				users.setVisible(true);
			}
		});
		usss.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_MASK));
		usss.setForeground(new Color(17, 24, 63));
		usss.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		usss.setBackground(new Color(238, 242, 236));
		mnNewMenu.add(usss);
		
		trace = new JMenuItem("Historial de usuarios");
		trace.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Brochure_16.png")));
		trace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		trace.setForeground(new Color(17, 24, 63));
		trace.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		trace.setBackground(new Color(238, 242, 236));
		mnNewMenu.add(trace);
		
		close = new JMenuItem("Cerrar sesi\u00F3n");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSistema,"¿Está seguro que desea salir?","SALIR DEL SISTEMA",2,2) == 0){
					Login lg = new Login();
					lg.setVisible(true);
					frmSistema.setVisible(false);
				}
			}
		});
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		close.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Cancel_16.png")));
		close.setForeground(new Color(17, 24, 63));
		close.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		close.setBackground(new Color(238, 242, 236));
		mnNewMenu.add(close);
		
		JMenu mnProcesoElectoral = new JMenu("Proceso Electoral");
		mnProcesoElectoral.setForeground(Color.WHITE);
		mnProcesoElectoral.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		mnProcesoElectoral.setBackground(new Color(140, 145, 168));
		menuBar.add(mnProcesoElectoral);
		
		JMenuItem mntmListaDeProcesos = new JMenuItem("Procesos activos");
		mntmListaDeProcesos.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Brochure_16.png")));
		mntmListaDeProcesos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
		mntmListaDeProcesos.setForeground(new Color(17, 24, 63));
		mntmListaDeProcesos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmListaDeProcesos.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmListaDeProcesos);
		
		JMenuItem mntmMunicipios = new JMenuItem("Municipios");
		mntmMunicipios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MunicipalityList mun = null;
				try {
					mun = new MunicipalityList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mun.setVisible(true);
			}
		});
		mntmMunicipios.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Map_Pinpoint_16.png")));
		mntmMunicipios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.SHIFT_MASK));
		mntmMunicipios.setForeground(new Color(17, 24, 63));
		mntmMunicipios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmMunicipios.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmMunicipios);
		
		JMenuItem mntmCircunscripcin = new JMenuItem("Circunscripci\u00F3n");
		mntmCircunscripcin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DistrictList d = null;
				try {
					d = new DistrictList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d.setVisible(true);
			}
		});
		mntmCircunscripcin.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Location_16.png")));
		mntmCircunscripcin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
		mntmCircunscripcin.setForeground(new Color(17, 24, 63));
		mntmCircunscripcin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCircunscripcin.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmCircunscripcin);
		
		JMenuItem mntmCdrs = new JMenuItem("Colegios electorales");
		mntmCdrs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Classroom_16.png")));
		mntmCdrs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK));
		mntmCdrs.setForeground(new Color(17, 24, 63));
		mntmCdrs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCdrs.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmCdrs);
		
		JMenuItem mntmCdr = new JMenuItem("CDR");
		mntmCdr.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Marker_16.png")));
		mntmCdr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCdr.setForeground(new Color(17, 24, 63));
		mntmCdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCdr.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmCdr);
		
		JMenu mnVotantes = new JMenu("Votantes");
		mnVotantes.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_User_Groups_16.png")));
		mnVotantes.setForeground(new Color(17, 24, 63));
		mnVotantes.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mnVotantes.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mnVotantes);
		
		JMenuItem mntmElectores = new JMenuItem("Electores");
		mntmElectores.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_People_16.png")));
		mntmElectores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmElectores.setForeground(Color.WHITE);
		mntmElectores.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmElectores.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmElectores);
		
		JMenuItem mntmNominados = new JMenuItem("Nominados");
		mntmNominados.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Businessman_16.png")));
		mntmNominados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmNominados.setForeground(Color.WHITE);
		mntmNominados.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmNominados.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmNominados);
	}
}
