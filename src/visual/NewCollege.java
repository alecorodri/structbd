<<<<<<< HEAD
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

import services.CollegeService;
import services.DistrictService;
import util.TextPrompt;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import dto.DistrictDto;
import dto.Electoral_CollegeDto;

@SuppressWarnings("serial")
public class NewCollege extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_college;
	private JTextField name_direccion;
	private JLabel lblNombreDelColegio;
	private JLabel lblDireccionColegio;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JLabel lblDistrito;
	private JComboBox<String> comboBox;
	private Electoral_CollegeDto collegioD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCollege dialog = new NewCollege(null);
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
	public NewCollege(Electoral_CollegeDto d) throws SQLException {
		collegioD = d;
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewCollege.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Nuevo Colegio Electoral");
		setBounds(170, 170, 491, 281);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		name_college = new JTextField();
		name_college.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_college.setFocusable(false);
				}
				name_college.setFocusable(true);
			}
		});
		TextPrompt nameCollege = new TextPrompt("ej: A-98", name_college);
		nameCollege.setForeground(Color.LIGHT_GRAY);
		nameCollege.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		name_college.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_college.setColumns(10);
		name_college.setBounds(217, 33, 237, 23);
		contentPanel.add(name_college);

		name_direccion = new JTextField();
		name_direccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_direccion.setFocusable(false);
				}
				name_direccion.setFocusable(true);
			}
		});
		TextPrompt nameDireccion = new TextPrompt("ej: Calle 41", name_direccion);
		nameDireccion.setForeground(Color.LIGHT_GRAY);
		nameDireccion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		name_direccion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_direccion.setColumns(10);
		name_direccion.setBounds(217, 91, 237, 23);
		contentPanel.add(name_direccion);


		lblNombreDelColegio = new JLabel("Nombre:");
		lblNombreDelColegio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelColegio.setForeground(new Color(17, 24, 63));
		lblNombreDelColegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelColegio.setBounds(61, 24, 146, 37);
		contentPanel.add(lblNombreDelColegio);

		lblDireccionColegio = new JLabel("Direcci\u00F3n:");
		lblDireccionColegio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccionColegio.setForeground(new Color(17, 24, 63));
		lblDireccionColegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDireccionColegio.setBounds(61, 82, 146, 37);
		contentPanel.add(lblDireccionColegio);

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCollege.this.setVisible(false);
			}
		});

		lblDistrito = new JLabel("Circunscripci\u00F3n:");
		lblDistrito.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDistrito.setForeground(new Color(17, 24, 63));
		lblDistrito.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDistrito.setBounds(23, 139, 184, 37);
		contentPanel.add(lblDistrito);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		comboBox.setBounds(217, 149, 237, 23);
		contentPanel.add(comboBox);
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(363, 199, 91, 31);
		contentPanel.add(cancel_button);
		ArrayList<DistrictDto> distritos = DistrictService.getDistrict();
		for(int i = 0; i < distritos.size(); i++){
			comboBox.addItem(distritos.get(i).getNamDis());
		}

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(collegioD == null){
					if(name_college.getText().isEmpty() || name_direccion.getText().isEmpty()){
						JOptionPane.showMessageDialog(NewCollege.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
					}else{
						String e = null;
						DistrictDto d = null;
						try {
							d = DistrictService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							e = CollegeService.createCollege(name_college.getText(), name_direccion.getText(), d.getCodDis());
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewCollege.this,e,"ERROR",0);
							name_college.setText("");
							name_direccion.setText("");
						}else{
							JOptionPane.showMessageDialog(NewCollege.this,"Colegio Electoral creado correctamente.","ACCIÓN COMPLETADA",1);
							NewCollege.this.setVisible(false);
						}
					}
				}else{
					if(name_college.getText().isEmpty() || name_direccion.getText().isEmpty()){
						JOptionPane.showMessageDialog(NewCollege.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
					}else{
						String e = null;
						DistrictDto d = null;
						try {
							d = DistrictService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						collegioD.setNameCollege(name_college.getText());
						collegioD.setAdress(name_direccion.getText());
						collegioD.setId_district(d.getCodDis());
						try {
							e = CollegeService.updateCollege(collegioD);
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewCollege.this,e,"ERROR",0);
							name_college.setText("");
							name_direccion.setText("");
						}else{
							JOptionPane.showMessageDialog(NewCollege.this,"Colegio Electoral modificado correctamente.","ACCIÓN COMPLETADA",1);
							NewCollege.this.setVisible(false);
						}
				}}
			}
		});
		ok_button.setForeground(Color.WHITE);
		ok_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_button.setBorderPainted(false);
		ok_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_button.setBackground(new Color(73, 78, 107));
		ok_button.setAlignmentX(0.5f);
		ok_button.setBounds(217, 199, 91, 31);
		contentPanel.add(ok_button);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 542, 276);
		contentPanel.add(fondo);
		
		if(collegioD != null){
			name_college.setText(collegioD.getNameCollege());
			name_direccion.setText(collegioD.getAdress());
			DistrictDto d1 = DistrictService.find_by_Id(collegioD.getId_district());
			comboBox.setSelectedItem(d1.getNamDis());
			setTitle("Modificar Colegio Electoral");
		}
	}
}
=======
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

import services.CollegeService;
import services.DistrictService;
import util.TextPrompt;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import dto.DistrictDto;
import dto.Electoral_CollegeDto;

@SuppressWarnings("serial")
public class NewCollege extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_college;
	private JTextField name_direccion;
	private JLabel lblNombreDelColegio;
	private JLabel lblDireccionColegio;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JLabel lblDistrito;
	private JComboBox<String> comboBox;
	private Electoral_CollegeDto collegioD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCollege dialog = new NewCollege(null);
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
	public NewCollege(Electoral_CollegeDto d) throws SQLException {
		collegioD = d;
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewCollege.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Nuevo Colegio Electoral");
		setBounds(170, 170, 491, 281);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		name_college = new JTextField();
		name_college.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_college.setFocusable(false);
				}
				name_college.setFocusable(true);
			}
		});
		TextPrompt nameCollege = new TextPrompt("ej: A-98", name_college);
		nameCollege.setForeground(Color.LIGHT_GRAY);
		nameCollege.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		name_college.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_college.setColumns(10);
		name_college.setBounds(217, 33, 237, 23);
		contentPanel.add(name_college);

		name_direccion = new JTextField();
		name_direccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_direccion.setFocusable(false);
				}
				name_direccion.setFocusable(true);
			}
		});
		TextPrompt nameDireccion = new TextPrompt("ej: Calle 41", name_direccion);
		nameDireccion.setForeground(Color.LIGHT_GRAY);
		nameDireccion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		name_direccion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_direccion.setColumns(10);
		name_direccion.setBounds(217, 91, 237, 23);
		contentPanel.add(name_direccion);


		lblNombreDelColegio = new JLabel("Nombre:");
		lblNombreDelColegio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelColegio.setForeground(new Color(17, 24, 63));
		lblNombreDelColegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelColegio.setBounds(61, 24, 146, 37);
		contentPanel.add(lblNombreDelColegio);

		lblDireccionColegio = new JLabel("Direcci\u00F3n:");
		lblDireccionColegio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccionColegio.setForeground(new Color(17, 24, 63));
		lblDireccionColegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDireccionColegio.setBounds(61, 82, 146, 37);
		contentPanel.add(lblDireccionColegio);

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewCollege.this.setVisible(false);
			}
		});

		lblDistrito = new JLabel("Circunscripci\u00F3n:");
		lblDistrito.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDistrito.setForeground(new Color(17, 24, 63));
		lblDistrito.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblDistrito.setBounds(23, 139, 184, 37);
		contentPanel.add(lblDistrito);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		comboBox.setBounds(217, 149, 237, 23);
		contentPanel.add(comboBox);
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(363, 199, 91, 31);
		contentPanel.add(cancel_button);
		ArrayList<DistrictDto> distritos = DistrictService.getDistrict();
		for(int i = 0; i < distritos.size(); i++){
			comboBox.addItem(distritos.get(i).getNamDis());
		}

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(collegioD == null){
					if(name_college.getText().isEmpty() || name_direccion.getText().isEmpty()){
						JOptionPane.showMessageDialog(NewCollege.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
					}else{
						String e = null;
						DistrictDto d = null;
						try {
							d = DistrictService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							e = CollegeService.createCollege(name_college.getText(), name_direccion.getText(), d.getCodDis());
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewCollege.this,e,"ERROR",0);
							name_college.setText("");
							name_direccion.setText("");
						}else{
							JOptionPane.showMessageDialog(NewCollege.this,"Colegio Electoral creado correctamente.","ACCIÓN COMPLETADA",1);
							NewCollege.this.setVisible(false);
						}
					}
				}else{
					if(name_college.getText().isEmpty() || name_direccion.getText().isEmpty()){
						JOptionPane.showMessageDialog(NewCollege.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
					}else{
						String e = null;
						DistrictDto d = null;
						try {
							d = DistrictService.find_by_Name((String) comboBox.getSelectedItem());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						collegioD.setNameCollege(name_college.getText());
						collegioD.setAdress(name_direccion.getText());
						collegioD.setId_district(d.getCodDis());
						try {
							e = CollegeService.updateCollege(collegioD);
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(NewCollege.this,e,"ERROR",0);
							name_college.setText("");
							name_direccion.setText("");
						}else{
							JOptionPane.showMessageDialog(NewCollege.this,"Colegio Electoral modificado correctamente.","ACCIÓN COMPLETADA",1);
							NewCollege.this.setVisible(false);
						}
				}}
			}
		});
		ok_button.setForeground(Color.WHITE);
		ok_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_button.setBorderPainted(false);
		ok_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_button.setBackground(new Color(73, 78, 107));
		ok_button.setAlignmentX(0.5f);
		ok_button.setBounds(217, 199, 91, 31);
		contentPanel.add(ok_button);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 542, 276);
		contentPanel.add(fondo);
		
		if(collegioD != null){
			name_college.setText(collegioD.getNameCollege());
			name_direccion.setText(collegioD.getAdress());
			DistrictDto d1 = DistrictService.find_by_Id(collegioD.getId_district());
			comboBox.setSelectedItem(d1.getNamDis());
			setTitle("Modificar Colegio Electoral");
		}
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
