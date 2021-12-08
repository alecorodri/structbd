package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import services.CdrService;
import services.VoterService;

import com.toedter.calendar.JCalendar;

import dto.CDRDto;
import dto.VoterDto;

@SuppressWarnings("serial")
public class NewElector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JLabel lblNombreDelUsuario;
	private JLabel lblContraseaDelUsuario;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JLabel lblCumpleaos;
	private JLabel lblOcupacin;
	private JTextField adrsField;
	private JComboBox<String> comboBox;
	private JCalendar calendar;
	private VoterDto vvv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewElector dialog = new NewElector(null);
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
	public NewElector(VoterDto xxx) throws SQLException {
		vvv = xxx;
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewNominated.class.getResource("/resources/icons8_Add_User_Male_16.png")));
		setTitle("Nuevo Elector");
		setBounds(170, 170, 443, 419);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name.setFocusable(false);
				}
				name.setFocusable(true);
			}
		});
		name.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name.setColumns(10);
		name.setBounds(176, 90, 225, 23);
		contentPanel.add(name);
		
		lblOcupacin = new JLabel("CDR:");
		lblOcupacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOcupacin.setForeground(new Color(17, 24, 63));
		lblOcupacin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblOcupacin.setBounds(20, 24, 146, 37);
		contentPanel.add(lblOcupacin);
		
		lblCumpleaos = new JLabel("Cumplea\u00F1os:");
		lblCumpleaos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCumpleaos.setForeground(new Color(17, 24, 63));
		lblCumpleaos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblCumpleaos.setBounds(10, 178, 156, 37);
		contentPanel.add(lblCumpleaos);
		
		adrsField = new JTextField();
		adrsField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		adrsField.setColumns(10);
		adrsField.setBounds(176, 139, 225, 23);
		contentPanel.add(adrsField);
		
		lblNombreDelUsuario = new JLabel("Nombre:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setForeground(new Color(17, 24, 63));
		lblNombreDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelUsuario.setBounds(60, 81, 106, 37);
		contentPanel.add(lblNombreDelUsuario);
		
		calendar = new JCalendar();
		calendar.setBounds(195, 187, 184, 150);
		contentPanel.add(calendar);
		
		lblContraseaDelUsuario = new JLabel("Direcci\u00F3n:");
		lblContraseaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDelUsuario.setForeground(new Color(17, 24, 63));
		lblContraseaDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblContraseaDelUsuario.setBounds(51, 130, 115, 37);
		contentPanel.add(lblContraseaDelUsuario);
		
		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElector.this.setVisible(false);
			}
		});
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(261, 348, 91, 31);
		contentPanel.add(cancel_button);
		
		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().isEmpty() || adrsField.getText().isEmpty()){
					JOptionPane.showMessageDialog(NewElector.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else if(2021 - calendar.getYearChooser().getYear() <= 16){
					JOptionPane.showMessageDialog(NewElector.this,"El elector debe ser mayor de 16 años.","ERROR",0);
				}else{
					if (vvv!=null) {
						CDRDto cdr = null;
						try {
							cdr = CdrService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						Timestamp time = new Timestamp(calendar.getDate().getTime());
						vvv.setAdrVot(adrsField.getText());
						vvv.setCdr(cdr.getCodCDR());
						vvv.setNamVot(name.getText());
						vvv.setDateVot(time);
						String e = null;
						try {
							e = VoterService.updateVoter(vvv);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewElector.this,e,"ERROR",0);
						}else{
							JOptionPane.showMessageDialog(NewElector.this,"Elector modificado correctamente.","ACCIÓN COMPLETADA",1);
							NewElector.this.setVisible(false);
						}
					}else {
						String e = null;
						CDRDto cdr = null;
						try {
							cdr = CdrService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						Timestamp time = new Timestamp(calendar.getDate().getTime());
						try {
							e = VoterService.createVoter(name.getText(), time, adrsField.getText(), cdr.getCodCDR());
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewElector.this,e,"ERROR",0);
						}else{
							JOptionPane.showMessageDialog(NewElector.this,"Elector creado correctamente.","ACCIÓN COMPLETADA",1);
							NewElector.this.setVisible(false);
						}
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
		ok_button.setBounds(85, 348, 91, 31);
		contentPanel.add(ok_button);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		comboBox.setBounds(176, 34, 226, 23);
		contentPanel.add(comboBox);
		ArrayList<CDRDto> cdrs = CdrService.getCDRDto();
		for(int i = 0; i < cdrs.size(); i++){
			comboBox.addItem(cdrs.get(i).getNamCDR());
		}
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewNominated.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 458, 276);
		contentPanel.add(fondo);
		
		if (vvv!=null) {
			name.setText(vvv.getNamVot());
			adrsField.setText(vvv.getAdrVot());
			calendar.setDate(vvv.getDateVot());
			setTitle("Modificar Elector");
		}
	}
}
