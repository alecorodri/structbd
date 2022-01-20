<<<<<<< HEAD
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
import reports.NoVoters;
import reports.RepeatDistrict;
import services.UserService;
import util.Encrypt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage {

	private User userLocator;
	private JFrame frmSistema;
	private JMenuItem change_pass;
	private JMenuItem usss;
	private JMenuItem close;
	private JLabel photo;

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
		mntmListaDeProcesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectoralProcess e = null;
				try {
					e = new ElectoralProcess();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.setVisible(true);
			}
		});
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
		mntmCdrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectoralCollegeList e = null;
				try {
					e = new ElectoralCollegeList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.setVisible(true);
			}
		
		});
		mntmCdrs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Classroom_16.png")));
		mntmCdrs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK));
		mntmCdrs.setForeground(new Color(17, 24, 63));
		mntmCdrs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCdrs.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmCdrs);
		
		JMenuItem mntmCdr = new JMenuItem("CDR");
		mntmCdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CDRList c = null;
				try {
					c = new CDRList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.setVisible(true);
			}
		});
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
		mntmElectores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectorList ee = null;
				try {
					ee = new ElectorList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ee.setVisible(true);
			}
		});
		mntmElectores.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_People_16.png")));
		mntmElectores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmElectores.setForeground(Color.WHITE);
		mntmElectores.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmElectores.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmElectores);
		
		JMenuItem mntmNominados = new JMenuItem("Nominados");
		mntmNominados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nominated nn = null;
				try {
					nn = new Nominated();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nn.setVisible(true);
			}
		});
		mntmNominados.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Businessman_16.png")));
		mntmNominados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmNominados.setForeground(Color.WHITE);
		mntmNominados.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmNominados.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmNominados);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		mnReportes.setBackground(new Color(140, 145, 168));
		menuBar.add(mnReportes);
		
		JMenuItem mntmDeLosProcesos = new JMenuItem("Partes de los procesos activos");
		mntmDeLosProcesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PartList p = null;
				try {
					p = new PartList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setVisible(true);
			}
		});
		mntmDeLosProcesos.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Brochure_16.png")));
		mntmDeLosProcesos.setForeground(new Color(17, 24, 63));
		mntmDeLosProcesos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmDeLosProcesos.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmDeLosProcesos);
		
		JMenuItem mntmMunicipiosConMs = new JMenuItem("Municipios con m\u00E1s nominados");
		mntmMunicipiosConMs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_User_Groups_16.png")));
		mntmMunicipiosConMs.setForeground(new Color(17, 24, 63));
		mntmMunicipiosConMs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmMunicipiosConMs.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmMunicipiosConMs);
		
		JMenuItem mntmProcesosFinalizados = new JMenuItem("Procesos finalizados");
		mntmProcesosFinalizados.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_True_False_16.png")));
		mntmProcesosFinalizados.setForeground(new Color(17, 24, 63));
		mntmProcesosFinalizados.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmProcesosFinalizados.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmProcesosFinalizados);
		
		JMenuItem mntmCircunscripcionesConMs = new JMenuItem("Circunscripciones con otra vuelta");
		mntmCircunscripcionesConMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RepeatDistrict rr = null;
				try {
					rr = new RepeatDistrict();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rr.setVisible(true);
			}
		});
		mntmCircunscripcionesConMs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Map_Pinpoint_16.png")));
		mntmCircunscripcionesConMs.setForeground(new Color(17, 24, 63));
		mntmCircunscripcionesConMs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCircunscripcionesConMs.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmCircunscripcionesConMs);
		
		JMenuItem mntmElectoresQueNo = new JMenuItem("Electores que no votaron");
		mntmElectoresQueNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NoVoters v = null;
				try {
					v = new NoVoters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				v.setVisible(true);
			}
		});
		mntmElectoresQueNo.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Conference_16.png")));
		mntmElectoresQueNo.setForeground(new Color(17, 24, 63));
		mntmElectoresQueNo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmElectoresQueNo.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmElectoresQueNo);
		
		JMenuItem mntmDelegadosElectos = new JMenuItem("Delegados electos");
		mntmDelegadosElectos.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Add_User_Male_16.png")));
		mntmDelegadosElectos.setForeground(new Color(17, 24, 63));
		mntmDelegadosElectos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmDelegadosElectos.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmDelegadosElectos);
	}
}
=======
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
<<<<<<< HEAD

import javax.swing.JLabel;

import dto.User;
import reports.NoVoters;
import reports.RepeatDistrict;
=======
import javax.swing.JLabel;

import dto.User;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
import services.UserService;
import util.Encrypt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage {

	private User userLocator;
	private JFrame frmSistema;
	private JMenuItem change_pass;
	private JMenuItem usss;
<<<<<<< HEAD
	private JMenuItem close;
	private JLabel photo;
=======
	private JMenuItem trace;
	private JMenuItem close;
	private JLabel photo;
	private JLabel help;
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda

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
<<<<<<< HEAD
					
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
		
<<<<<<< HEAD
=======
		help = new JLabel("");
		help.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Help_32.png")));
		help.setBounds(541, 292, 39, 51);
		frmSistema.getContentPane().add(help);
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
		
<<<<<<< HEAD
=======
		trace = new JMenuItem("Historial de usuarios");
		trace.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Brochure_16.png")));
		trace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		trace.setForeground(new Color(17, 24, 63));
		trace.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		trace.setBackground(new Color(238, 242, 236));
		mnNewMenu.add(trace);
		
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
		mntmListaDeProcesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectoralProcess e = null;
				try {
					e = new ElectoralProcess();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.setVisible(true);
			}
		});
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
		mntmCdrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectoralCollegeList e = null;
				try {
					e = new ElectoralCollegeList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.setVisible(true);
			}
		
		});
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		mntmCdrs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Classroom_16.png")));
		mntmCdrs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK));
		mntmCdrs.setForeground(new Color(17, 24, 63));
		mntmCdrs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCdrs.setBackground(new Color(238, 242, 236));
		mnProcesoElectoral.add(mntmCdrs);
		
		JMenuItem mntmCdr = new JMenuItem("CDR");
<<<<<<< HEAD
		mntmCdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CDRList c = null;
				try {
					c = new CDRList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.setVisible(true);
			}
		});
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
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
<<<<<<< HEAD
		mntmElectores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElectorList ee = null;
				try {
					ee = new ElectorList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ee.setVisible(true);
			}
		});
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		mntmElectores.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_People_16.png")));
		mntmElectores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmElectores.setForeground(Color.WHITE);
		mntmElectores.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmElectores.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmElectores);
		
		JMenuItem mntmNominados = new JMenuItem("Nominados");
<<<<<<< HEAD
		mntmNominados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nominated nn = null;
				try {
					nn = new Nominated();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nn.setVisible(true);
			}
		});
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
		mntmNominados.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Businessman_16.png")));
		mntmNominados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
		mntmNominados.setForeground(Color.WHITE);
		mntmNominados.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmNominados.setBackground(new Color(140, 145, 168));
		mnVotantes.add(mntmNominados);
<<<<<<< HEAD
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		mnReportes.setBackground(new Color(140, 145, 168));
		menuBar.add(mnReportes);
		
		JMenuItem mntmDeLosProcesos = new JMenuItem("Partes de los procesos activos");
		mntmDeLosProcesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PartList p = null;
				try {
					p = new PartList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setVisible(true);
			}
		});
		mntmDeLosProcesos.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Brochure_16.png")));
		mntmDeLosProcesos.setForeground(new Color(17, 24, 63));
		mntmDeLosProcesos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmDeLosProcesos.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmDeLosProcesos);
		
		JMenuItem mntmMunicipiosConMs = new JMenuItem("Municipios con m\u00E1s nominados");
		mntmMunicipiosConMs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_User_Groups_16.png")));
		mntmMunicipiosConMs.setForeground(new Color(17, 24, 63));
		mntmMunicipiosConMs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmMunicipiosConMs.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmMunicipiosConMs);
		
		JMenuItem mntmProcesosFinalizados = new JMenuItem("Procesos finalizados");
		mntmProcesosFinalizados.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_True_False_16.png")));
		mntmProcesosFinalizados.setForeground(new Color(17, 24, 63));
		mntmProcesosFinalizados.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmProcesosFinalizados.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmProcesosFinalizados);
		
		JMenuItem mntmCircunscripcionesConMs = new JMenuItem("Circunscripciones con otra vuelta");
		mntmCircunscripcionesConMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RepeatDistrict rr = null;
				try {
					rr = new RepeatDistrict();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rr.setVisible(true);
			}
		});
		mntmCircunscripcionesConMs.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Map_Pinpoint_16.png")));
		mntmCircunscripcionesConMs.setForeground(new Color(17, 24, 63));
		mntmCircunscripcionesConMs.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmCircunscripcionesConMs.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmCircunscripcionesConMs);
		
		JMenuItem mntmElectoresQueNo = new JMenuItem("Electores que no votaron");
		mntmElectoresQueNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NoVoters v = null;
				try {
					v = new NoVoters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				v.setVisible(true);
			}
		});
		mntmElectoresQueNo.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Conference_16.png")));
		mntmElectoresQueNo.setForeground(new Color(17, 24, 63));
		mntmElectoresQueNo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmElectoresQueNo.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmElectoresQueNo);
		
		JMenuItem mntmDelegadosElectos = new JMenuItem("Delegados electos");
		mntmDelegadosElectos.setIcon(new ImageIcon(HomePage.class.getResource("/resources/icons8_Add_User_Male_16.png")));
		mntmDelegadosElectos.setForeground(new Color(17, 24, 63));
		mntmDelegadosElectos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		mntmDelegadosElectos.setBackground(new Color(238, 242, 236));
		mnReportes.add(mntmDelegadosElectos);
=======
>>>>>>> 1de9d82a65ccda5f15c3e4ce8db8ae324f1b1bda
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
