<<<<<<< HEAD
package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import util.TextPrompt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ManualConnection extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField host;
	private JTextField dataBase;
	private JTextField user;
	private JPasswordField pass;
	private JButton cancel_Button;
	private JButton ok_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManualConnection dialog = new ManualConnection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManualConnection() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManualConnection.class.getResource("/resources/icons8_PostgreSQL_16.png")));
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		setTitle("Conexi\u00F3n Manual con la BD");
		setResizable(false);
		setBounds(100, 100, 586, 406);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Propiedades de la BD:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(17, 24, 63));
		lblNewLabel.setBounds(21, 11, 519, 64);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del Host:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNewLabel_1.setForeground(new Color(17, 24, 63));
		lblNewLabel_1.setBounds(36, 81, 275, 37);
		contentPanel.add(lblNewLabel_1);
		
		host = new JTextField();
		host.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					host.setFocusable(false);
				}
				host.setFocusable(true);
			}
		});
		TextPrompt hostHelp = new TextPrompt("ej:Localhost", host);
		hostHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		hostHelp.setForeground(new Color(238, 242, 236));
		host.setForeground(Color.WHITE);
		host.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		host.setColumns(10);
		host.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		host.setBackground(new Color(140, 145, 168));
		host.setBounds(326, 86, 232, 31);
		contentPanel.add(host);
		
		JLabel lblDataBaseName = new JLabel("Nombre de la BD:");
		lblDataBaseName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataBaseName.setForeground(new Color(17, 24, 63));
		lblDataBaseName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDataBaseName.setBounds(36, 129, 275, 37);
		contentPanel.add(lblDataBaseName);
		
		JLabel lblNombreDelUsuario = new JLabel("Nombre del Usuario:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setForeground(new Color(17, 24, 63));
		lblNombreDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelUsuario.setBounds(36, 177, 275, 37);
		contentPanel.add(lblNombreDelUsuario);
		
		JLabel lblContraseaDelUsuario = new JLabel("Contrase\u00F1a del Usuario:");
		lblContraseaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDelUsuario.setForeground(new Color(17, 24, 63));
		lblContraseaDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblContraseaDelUsuario.setBounds(36, 225, 275, 37);
		contentPanel.add(lblContraseaDelUsuario);
		
		dataBase = new JTextField();
		dataBase.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					dataBase.setFocusable(false);
				}
				dataBase.setFocusable(true);
			}
		});
		TextPrompt dataBaseHelp = new TextPrompt("ej:ProcesoElectoral", dataBase);
		dataBaseHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		dataBaseHelp.setForeground(new Color(238, 242, 236));
		dataBase.setForeground(Color.WHITE);
		dataBase.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		dataBase.setColumns(10);
		dataBase.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		dataBase.setBackground(new Color(140, 145, 168));
		dataBase.setBounds(326, 134, 232, 31);
		contentPanel.add(dataBase);
		
		user = new JTextField();
		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					user.setFocusable(false);
				}
				user.setFocusable(true);
			}
		});
		TextPrompt userHelp = new TextPrompt("ej:Postgres", user);
		userHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		userHelp.setForeground(new Color(238, 242, 236));
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		user.setColumns(10);
		user.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		user.setBackground(new Color(140, 145, 168));
		user.setBounds(326, 182, 232, 31);
		contentPanel.add(user);
		
		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					pass.setFocusable(false);
				}
				pass.setFocusable(true);
			}
		});
		TextPrompt passHelp = new TextPrompt("ej:Postgres", pass);
		passHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		passHelp.setForeground(new Color(238, 242, 236));
		pass.setForeground(Color.WHITE);
		pass.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		pass.setColumns(10);
		pass.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		pass.setBackground(new Color(140, 145, 168));
		pass.setEchoChar('*');
		pass.setBounds(326, 230, 232, 31);
		contentPanel.add(pass);
		
		cancel_Button = new JButton("Cancelar");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManualConnection.this.setVisible(false);
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		cancel_Button.setForeground(Color.WHITE);
		cancel_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_Button.setBorderPainted(false);
		cancel_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_Button.setBackground(new Color(140, 145, 168));
		cancel_Button.setBounds(326, 316, 91, 31);
		contentPanel.add(cancel_Button);
		
		ok_Button = new JButton("Aceptar");
		ok_Button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(host.getText().isEmpty() || dataBase.getText().isEmpty() || user.getText().isEmpty() || pass.getText().isEmpty()){
					JOptionPane.showMessageDialog(ManualConnection.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					try {
						saveFile(host.getText(), dataBase.getText(), user.getText(), pass.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ManualConnection.this.setVisible(false);
					Login lg = new Login();
					lg.setVisible(true);
				}
			}
		});
		ok_Button.setForeground(Color.WHITE);
		ok_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_Button.setBorderPainted(false);
		ok_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_Button.setBackground(new Color(73, 78, 107));
		ok_Button.setAlignmentX(0.5f);
		ok_Button.setBounds(467, 316, 91, 31);
		contentPanel.add(ok_Button);
		
		JLabel picture = new JLabel("");
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setIcon(new ImageIcon(ManualConnection.class.getResource("/resources/Grupo 28.png")));
		picture.setBounds(21, 269, 160, 97);
		contentPanel.add(picture);
	}
	
	public void saveFile(String host, String dataBase, String user, String pass) throws IOException{
		PrintWriter write = new PrintWriter("src/util/ConnectionProperties", "UTF-8");
		write.println("Host:" + host);
		write.println("DataBase:" + dataBase);
		write.println("User:" + user);
		write.println("Password:" + pass);
		write.close();
	}
=======
package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import util.TextPrompt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ManualConnection extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField host;
	private JTextField dataBase;
	private JTextField user;
	private JPasswordField pass;
	private JButton cancel_Button;
	private JButton ok_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManualConnection dialog = new ManualConnection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManualConnection() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManualConnection.class.getResource("/resources/icons8_PostgreSQL_16.png")));
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		setTitle("Conexi\u00F3n Manual con la BD");
		setResizable(false);
		setBounds(100, 100, 586, 406);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Propiedades de la BD:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(17, 24, 63));
		lblNewLabel.setBounds(21, 11, 519, 64);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del Host:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNewLabel_1.setForeground(new Color(17, 24, 63));
		lblNewLabel_1.setBounds(36, 81, 275, 37);
		contentPanel.add(lblNewLabel_1);
		
		host = new JTextField();
		host.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					host.setFocusable(false);
				}
				host.setFocusable(true);
			}
		});
		TextPrompt hostHelp = new TextPrompt("ej:Localhost", host);
		hostHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		hostHelp.setForeground(new Color(238, 242, 236));
		host.setForeground(Color.WHITE);
		host.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		host.setColumns(10);
		host.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		host.setBackground(new Color(140, 145, 168));
		host.setBounds(326, 86, 232, 31);
		contentPanel.add(host);
		
		JLabel lblDataBaseName = new JLabel("Nombre de la BD:");
		lblDataBaseName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataBaseName.setForeground(new Color(17, 24, 63));
		lblDataBaseName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDataBaseName.setBounds(36, 129, 275, 37);
		contentPanel.add(lblDataBaseName);
		
		JLabel lblNombreDelUsuario = new JLabel("Nombre del Usuario:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setForeground(new Color(17, 24, 63));
		lblNombreDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelUsuario.setBounds(36, 177, 275, 37);
		contentPanel.add(lblNombreDelUsuario);
		
		JLabel lblContraseaDelUsuario = new JLabel("Contrase\u00F1a del Usuario:");
		lblContraseaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDelUsuario.setForeground(new Color(17, 24, 63));
		lblContraseaDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblContraseaDelUsuario.setBounds(36, 225, 275, 37);
		contentPanel.add(lblContraseaDelUsuario);
		
		dataBase = new JTextField();
		dataBase.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					dataBase.setFocusable(false);
				}
				dataBase.setFocusable(true);
			}
		});
		TextPrompt dataBaseHelp = new TextPrompt("ej:ProcesoElectoral", dataBase);
		dataBaseHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		dataBaseHelp.setForeground(new Color(238, 242, 236));
		dataBase.setForeground(Color.WHITE);
		dataBase.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		dataBase.setColumns(10);
		dataBase.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		dataBase.setBackground(new Color(140, 145, 168));
		dataBase.setBounds(326, 134, 232, 31);
		contentPanel.add(dataBase);
		
		user = new JTextField();
		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					user.setFocusable(false);
				}
				user.setFocusable(true);
			}
		});
		TextPrompt userHelp = new TextPrompt("ej:Postgres", user);
		userHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		userHelp.setForeground(new Color(238, 242, 236));
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		user.setColumns(10);
		user.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		user.setBackground(new Color(140, 145, 168));
		user.setBounds(326, 182, 232, 31);
		contentPanel.add(user);
		
		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					pass.setFocusable(false);
				}
				pass.setFocusable(true);
			}
		});
		TextPrompt passHelp = new TextPrompt("ej:Postgres", pass);
		passHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		passHelp.setForeground(new Color(238, 242, 236));
		pass.setForeground(Color.WHITE);
		pass.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		pass.setColumns(10);
		pass.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		pass.setBackground(new Color(140, 145, 168));
		pass.setEchoChar('*');
		pass.setBounds(326, 230, 232, 31);
		contentPanel.add(pass);
		
		cancel_Button = new JButton("Cancelar");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManualConnection.this.setVisible(false);
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		cancel_Button.setForeground(Color.WHITE);
		cancel_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_Button.setBorderPainted(false);
		cancel_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_Button.setBackground(new Color(140, 145, 168));
		cancel_Button.setBounds(326, 316, 91, 31);
		contentPanel.add(cancel_Button);
		
		ok_Button = new JButton("Aceptar");
		ok_Button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(host.getText().isEmpty() || dataBase.getText().isEmpty() || user.getText().isEmpty() || pass.getText().isEmpty()){
					JOptionPane.showMessageDialog(ManualConnection.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					try {
						saveFile(host.getText(), dataBase.getText(), user.getText(), pass.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ManualConnection.this.setVisible(false);
					Login lg = new Login();
					lg.setVisible(true);
				}
			}
		});
		ok_Button.setForeground(Color.WHITE);
		ok_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_Button.setBorderPainted(false);
		ok_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_Button.setBackground(new Color(73, 78, 107));
		ok_Button.setAlignmentX(0.5f);
		ok_Button.setBounds(467, 316, 91, 31);
		contentPanel.add(ok_Button);
		
		JLabel picture = new JLabel("");
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setIcon(new ImageIcon(ManualConnection.class.getResource("/resources/Grupo 28.png")));
		picture.setBounds(21, 269, 160, 97);
		contentPanel.add(picture);
	}
	
	public void saveFile(String host, String dataBase, String user, String pass) throws IOException{
		PrintWriter write = new PrintWriter("src/util/ConnectionProperties", "UTF-8");
		write.println("Host:" + host);
		write.println("DataBase:" + dataBase);
		write.println("User:" + user);
		write.println("Password:" + pass);
		write.close();
	}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
}