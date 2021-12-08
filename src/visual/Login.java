package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import dto.User;
import services.ServiceConnection;
import services.UserService;
import util.Encrypt;
import util.TextPrompt;

import java.awt.Cursor;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel purpure_background;
	private JLabel tittle;
	private JTextField user;
	private JPasswordField passwordField;
	private JButton cancel_Button;
	private JButton ok_Button;
	private JLabel lock_picture;
	private JLabel visitorLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				System.exit(0);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/icons8_Fingerprint_Scan_16.png")));
		setTitle("Login");
		setResizable(false);
		setBounds(200, 180, 586, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tittle = new JLabel("Proceso Electoral");
			tittle.setFont(new Font("Segoe UI Symbol", Font.BOLD, 36));
			tittle.setForeground(new Color(17, 24, 63));
			tittle.setBounds(42, 33, 306, 64);
			contentPanel.add(tittle);
		}

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
		TextPrompt userHelp = new TextPrompt("Usuario", user);
		userHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		userHelp.setForeground(new Color(238, 242, 236));
		user.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		user.setBackground(new Color(140, 145, 168));
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		user.setBounds(42, 196, 191, 31);
		contentPanel.add(user);
		user.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					passwordField.setFocusable(false);
					ok_Button.doClick();
				}
				passwordField.setFocusable(true);
			}
		});
		TextPrompt passHelp = new TextPrompt("Usuario", passwordField);
		passHelp.setText("Contrase\u00F1a");
		passHelp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		passHelp.setForeground(new Color(238, 242, 236));
		passwordField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		passwordField.setBorder(new LineBorder(new Color(140, 145, 168), 7, true));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(new Color(140, 145, 168));
		passwordField.setEchoChar('*');
		passwordField.setBounds(42, 251, 191, 31);
		contentPanel.add(passwordField);

		cancel_Button = new JButton("Cancelar");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		cancel_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancel_Button.setBorderPainted(false);
		cancel_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_Button.setBackground(new Color(140, 145, 168));
		cancel_Button.setForeground(Color.WHITE);
		cancel_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_Button.setBounds(42, 317, 79, 31);
		contentPanel.add(cancel_Button);

		ok_Button = new JButton("Aceptar");
		ok_Button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(user.getText().isEmpty() || passwordField.getText().isEmpty()){
					JOptionPane.showMessageDialog(Login.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					try {
						ServiceConnection.getConnection();
						User uss = UserService.find_by_Name(user.getText());
						if(uss != null && uss.getPassword().equals(Encrypt.getMd5(passwordField.getText()))){
							JOptionPane.showMessageDialog(Login.this,"Bienvenid@ al Sistema Gestor del Proceso Electoral.","AUTENTICACIÓN VÁLIDA",1);
							Login.this.setVisible(false);
							new HomePage(uss.getIdUser());
						}else{
							JOptionPane.showMessageDialog(Login.this,"Credenciales Incorrectas.","AUTENTICACIÓN FALLIDA",0);
							passwordField.setText("");
						}
					} catch (ClassNotFoundException | IOException | SQLException e) {
						e.printStackTrace();
						if(JOptionPane.showConfirmDialog(Login.this,"No logramos establecer conexión con la Base de Datos.\n¿Desea realizar la conexión de manera manual?","CONEXIÓN FALLIDA",2,1) == 0){
							Login.this.setVisible(false);
							ManualConnection manual = new ManualConnection();
							manual.setVisible(true);
						}
					}
				}
			}
		});
		ok_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		ok_Button.setBorderPainted(false);
		ok_Button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_Button.setBackground(new Color(73, 78, 107));
		ok_Button.setForeground(Color.WHITE);
		ok_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ok_Button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_Button.setBounds(152, 317, 81, 31);
		contentPanel.add(ok_Button);
		
		visitorLabel = new JLabel("Entrar como votante");
		visitorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login.this.setVisible(false);
				try {
					new Visitor();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		visitorLabel.setOpaque(true);
		visitorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visitorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		visitorLabel.setForeground(new Color(17, 24, 63));
		visitorLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		visitorLabel.setBounds(70, 286, 135, 20);
		contentPanel.add(visitorLabel);

		lock_picture = new JLabel("");
		lock_picture.setIcon(new ImageIcon(Login.class.getResource("/resources/Group 23.png")));
		lock_picture.setBounds(290, 172, 244, 176);
		contentPanel.add(lock_picture);

		purpure_background = new JLabel("");
		purpure_background.setIcon(new ImageIcon(Login.class.getResource("/resources/Morado.png")));
		purpure_background.setBounds(0, -26, 580, 396);
		contentPanel.add(purpure_background);
	}
}
