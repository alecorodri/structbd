package visual;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;

import services.UserService;
import util.Encrypt;
import util.TextPrompt;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class NewUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_user;
	private JPasswordField pass;
	private JLabel lblNombreDelUsuario;
	private JLabel lblContraseaDelUsuario;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUser dialog = new NewUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUser() {
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/resources/icons8_Add_User_Male_16.png")));
		setTitle("Nuevo Usuario");
		setBounds(170, 170, 431, 228);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		name_user = new JTextField();
		name_user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_user.setFocusable(false);
				}
				name_user.setFocusable(true);
			}
		});
		TextPrompt name_userHelp = new TextPrompt("ej:Maria", name_user);
		name_userHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		name_user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_user.setColumns(10);
		name_user.setBounds(166, 33, 237, 23);
		contentPanel.add(name_user);
		
		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					pass.setFocusable(false);
					ok_button.doClick();
				}
				pass.setFocusable(true);
			}
		});
		TextPrompt passHelp = new TextPrompt("ej:Maria03", pass);
		passHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		pass.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		pass.setColumns(10);
		pass.setEchoChar('*');
		pass.setBounds(166, 91, 237, 23);
		contentPanel.add(pass);
		
		lblNombreDelUsuario = new JLabel("Nombre:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setForeground(new Color(17, 24, 63));
		lblNombreDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelUsuario.setBounds(10, 24, 146, 37);
		contentPanel.add(lblNombreDelUsuario);
		
		lblContraseaDelUsuario = new JLabel("Contrase\u00F1a:");
		lblContraseaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDelUsuario.setForeground(new Color(17, 24, 63));
		lblContraseaDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblContraseaDelUsuario.setBounds(10, 82, 146, 37);
		contentPanel.add(lblContraseaDelUsuario);
		
		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser.this.setVisible(false);
			}
		});
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(166, 146, 91, 31);
		contentPanel.add(cancel_button);
		
		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(name_user.getText().isEmpty() || pass.getText().isEmpty()){
					JOptionPane.showMessageDialog(NewUser.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					String e = null;
					try {
						e = UserService.create_User(name_user.getText(), Encrypt.getMd5(pass.getText()));
					} catch (SQLException e1) {
						System.out.println("Error: " + e1.getMessage());
					}
					if(e!=null){
						JOptionPane.showMessageDialog(NewUser.this,e,"ERROR",0);
						name_user.setText("");
						pass.setText("");
					}else{
						JOptionPane.showMessageDialog(NewUser.this,"Usuario creado correctamente.","ACCIÓN COMPLETADA",1);
						NewUser.this.setVisible(false);
					}
				}
			}
		});
		ok_button.setForeground(Color.WHITE);
		ok_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_button.setBorderPainted(false);
		ok_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_button.setBackground(new Color(73, 78, 107));
		ok_button.setAlignmentX(0.5f);
		ok_button.setBounds(312, 146, 91, 31);
		contentPanel.add(ok_button);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 542, 276);
		contentPanel.add(fondo);
	}

}
