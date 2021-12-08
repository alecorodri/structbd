package visual;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;

import services.CdrService;
import services.CollegeService;
import services.DistrictService;
import services.ElectoralProcessService;
import services.MunicipalityService;
import services.NominatedService;
import services.VoterService;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JEditorPane;

import com.toedter.calendar.JCalendar;

import dto.CDRDto;
import dto.Electoral_ProcessDto;
import dto.VoterDto;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class NewNominated extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JLabel lblNombreDelUsuario;
	private JLabel lblContraseaDelUsuario;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JLabel lblCumpleaos;
	private JLabel lblOcupacin;
	private JLabel lblProfesin;
	private JLabel lblTelfono;
	private JLabel lblIntegridadRevolucionaria;
	private JLabel lblBiografia;
	private JTextField adrsField;
	private JTextField ocupField;
	private JTextField profField;
	private JTextField integField; 
	private JTextField phoneField;
	private JComboBox<String> comboBox;
	private JCalendar calendar;
	private JEditorPane biograPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewNominated dialog = new NewNominated();
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
	public NewNominated() throws SQLException {
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewNominated.class.getResource("/resources/icons8_Add_User_Male_16.png")));
		setTitle("Nuevo Nominado");
		setBounds(170, 170, 590, 501);
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
		name.setBounds(166, 33, 225, 23);
		contentPanel.add(name);
		
		lblOcupacin = new JLabel("Ocupaci\u00F3n:");
		lblOcupacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOcupacin.setForeground(new Color(17, 24, 63));
		lblOcupacin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblOcupacin.setBounds(0, 120, 146, 37);
		contentPanel.add(lblOcupacin);
		
		lblCumpleaos = new JLabel("Cumplea\u00F1os:");
		lblCumpleaos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCumpleaos.setForeground(new Color(17, 24, 63));
		lblCumpleaos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblCumpleaos.setBounds(390, 232, 156, 37);
		contentPanel.add(lblCumpleaos);
		
		adrsField = new JTextField();
		adrsField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		adrsField.setColumns(10);
		adrsField.setBounds(166, 86, 225, 23);
		contentPanel.add(adrsField);
		
		ocupField = new JTextField();
		ocupField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		ocupField.setColumns(10);
		ocupField.setBounds(166, 134, 225, 23);
		contentPanel.add(ocupField);
		
		profField = new JTextField();
		profField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		profField.setColumns(10);
		profField.setBounds(166, 182, 225, 23);
		contentPanel.add(profField);
		
		integField = new JTextField();
		integField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		integField.setColumns(10);
		integField.setBounds(166, 222, 225, 23);
		contentPanel.add(integField);
		
		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfono.setForeground(new Color(17, 24, 63));
		lblTelfono.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblTelfono.setBounds(409, 144, 115, 37);
		contentPanel.add(lblTelfono);
		
		lblNombreDelUsuario = new JLabel("Nombre:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setForeground(new Color(17, 24, 63));
		lblNombreDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelUsuario.setBounds(40, 24, 106, 37);
		contentPanel.add(lblNombreDelUsuario);
		
		calendar = new JCalendar();
		calendar.setBounds(390, 269, 184, 150);
		contentPanel.add(calendar);
		
		lblIntegridadRevolucionaria = new JLabel("Integridad:");
		lblIntegridadRevolucionaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntegridadRevolucionaria.setForeground(new Color(17, 24, 63));
		lblIntegridadRevolucionaria.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblIntegridadRevolucionaria.setBounds(14, 213, 132, 37);
		contentPanel.add(lblIntegridadRevolucionaria);
		
		lblContraseaDelUsuario = new JLabel("Direcci\u00F3n:");
		lblContraseaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDelUsuario.setForeground(new Color(17, 24, 63));
		lblContraseaDelUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblContraseaDelUsuario.setBounds(31, 77, 115, 37);
		contentPanel.add(lblContraseaDelUsuario);
		
		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewNominated.this.setVisible(false);
			}
		});
		
		lblProfesin = new JLabel("Profesi\u00F3n:");
		lblProfesin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfesin.setForeground(new Color(17, 24, 63));
		lblProfesin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblProfesin.setBounds(10, 168, 136, 37);
		contentPanel.add(lblProfesin);
		
		biograPane = new JEditorPane();
		biograPane.setBounds(31, 296, 299, 154);
		contentPanel.add(biograPane);
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(483, 430, 91, 31);
		contentPanel.add(cancel_button);
		
		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().isEmpty() || adrsField.getText().isEmpty() || ocupField.getText().isEmpty() || profField.getText().isEmpty() || integField.getText().isEmpty() || phoneField.getText().isEmpty() ){
					JOptionPane.showMessageDialog(NewNominated.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					String e = null;
					CDRDto cdr = null;
					try {
						cdr = CdrService.find_by_Name((String) comboBox.getSelectedItem());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Timestamp time = new Timestamp(calendar.getDate().getTime());
						e = VoterService.createVoter(name.getText(), time, adrsField.getText(), cdr.getCodCDR());
						VoterDto vv = VoterService.find_by_Name(name.getText());
						Electoral_ProcessDto ee = ElectoralProcessService.find_by_Municipality((MunicipalityService.find_by_Id((DistrictService.find_by_Id((CollegeService.find_by_Id(cdr.getId_college()).getId_district()))).getIdMunicipality()).getCodMun()));
						NominatedService.create_nominated(ocupField.getText(), profField.getText(), phoneField.getText(), integField.getText(), biograPane.getText(), vv.getNumID(), ee.getId_EProcess(), 0);
					} catch (SQLException e1) {
						System.out.println("Error: " + e1.getMessage());
					}
					if(e!=null){
						JOptionPane.showMessageDialog(NewNominated.this,e,"ERROR",0);
					}else{
						JOptionPane.showMessageDialog(NewNominated.this,"Nominado creado correctamente.","ACCIÓN COMPLETADA",1);
						NewNominated.this.setVisible(false);
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
		ok_button.setBounds(382, 430, 91, 31);
		contentPanel.add(ok_button);
		
		lblBiografia = new JLabel("Biograf\u00EDa:");
		lblBiografia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBiografia.setForeground(new Color(17, 24, 63));
		lblBiografia.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblBiografia.setBounds(31, 261, 115, 37);
		contentPanel.add(lblBiografia);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		comboBox.setBounds(412, 34, 162, 23);
		contentPanel.add(comboBox);
		ArrayList<CDRDto> cdrs = CdrService.getCDRDto();
		for(int i = 0; i < cdrs.size(); i++){
			comboBox.addItem(cdrs.get(i).getNamCDR());
		}
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		phoneField.setColumns(10);
		phoneField.setBounds(411, 182, 156, 23);
		contentPanel.add(phoneField);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewNominated.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 633, 276);
		contentPanel.add(fondo);
	}
}