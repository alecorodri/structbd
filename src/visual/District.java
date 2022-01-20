<<<<<<< HEAD
package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.MunicipalityDto;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import services.DistrictService;
import services.MunicipalityService;
import util.TextPrompt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class District extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	@SuppressWarnings("rawtypes")
	private JComboBox mun;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			District dialog = new District();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public District() throws SQLException {
		setAlwaysOnTop(true);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(District.class.getResource("/resources/icons8_Add_New_16.png")));
		setTitle("Nueva Circunscripci\u00F3n");
		setResizable(false);
		setBounds(100, 100, 420, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Nombre:");
			label.setBounds(48, 27, 100, 32);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setForeground(new Color(17, 24, 63));
			label.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
			contentPanel.add(label);
		}
		{
			JLabel lblMunicipio = new JLabel("Municipio:");
			lblMunicipio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMunicipio.setForeground(new Color(17, 24, 63));
			lblMunicipio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
			lblMunicipio.setBounds(10, 76, 138, 32);
			contentPanel.add(lblMunicipio);
		}

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
		TextPrompt nameHelp = new TextPrompt("ej:La Coronela", name);
		nameHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		name.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name.setColumns(10);
		name.setBounds(158, 34, 237, 23);
		contentPanel.add(name);

		mun = new JComboBox();
		mun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(mun.getItemAt(0).equals("Seleccione el municipio")){
					mun.removeItem("Seleccione el municipio");
				}
			}
		});
		mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		mun.addItem("Seleccione el municipio");
		ArrayList<MunicipalityDto> m = MunicipalityService.getMunicipality();
		for(int i=0; i<m.size(); i++){
			mun.addItem(m.get(i).getNamMun());
		}
		mun.setBackground(new Color(238, 242, 236));
		mun.setBounds(158, 85, 237, 23);
		contentPanel.add(mun);

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				District.this.setVisible(false);
			}
		});
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(158, 129, 91, 31);
		contentPanel.add(cancel_button);

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().isEmpty() || mun.getSelectedItem().equals("Seleccione el municipio")){
					JOptionPane.showMessageDialog(District.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{

					String m = (String) mun.getSelectedItem();
					String e = null;
					try {
						e = DistrictService.create_district(name.getText(), MunicipalityService.find_by_Name(m).getCodMun());
					} catch (SQLException e1) {
						System.out.println("Error: " + e1.getMessage());
					}
					if(e!=null){
						JOptionPane.showMessageDialog(District.this,e,"ERROR",0);
						name.setText("");
						mun.removeAllItems();
						mun.addItem("Seleccione el municipio");
						ArrayList<MunicipalityDto> m1 = null;
						try {
							m1 = MunicipalityService.getMunicipality();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i=0; i<m1.size(); i++){
							mun.addItem(m1.get(i).getNamMun());
						}
					}else{
						JOptionPane.showMessageDialog(District.this,"Circunscripción creada correctamente.","ACCIÓN COMPLETADA",1);
						District.this.setVisible(false);
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
		ok_button.setBounds(304, 129, 91, 31);
		contentPanel.add(ok_button);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(District.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -24, 414, 294);
		contentPanel.add(fondo);
	}
}
=======
package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.MunicipalityDto;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import services.DistrictService;
import services.MunicipalityService;
import util.TextPrompt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class District extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	@SuppressWarnings("rawtypes")
	private JComboBox mun;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			District dialog = new District();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public District() throws SQLException {
		setAlwaysOnTop(true);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(District.class.getResource("/resources/icons8_Add_New_16.png")));
		setTitle("Nueva Circunscripci\u00F3n");
		setResizable(false);
		setBounds(100, 100, 420, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Nombre:");
			label.setBounds(48, 27, 100, 32);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setForeground(new Color(17, 24, 63));
			label.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
			contentPanel.add(label);
		}
		{
			JLabel lblMunicipio = new JLabel("Municipio:");
			lblMunicipio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMunicipio.setForeground(new Color(17, 24, 63));
			lblMunicipio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
			lblMunicipio.setBounds(10, 76, 138, 32);
			contentPanel.add(lblMunicipio);
		}

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
		TextPrompt nameHelp = new TextPrompt("ej:La Coronela", name);
		nameHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		name.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name.setColumns(10);
		name.setBounds(158, 34, 237, 23);
		contentPanel.add(name);

		mun = new JComboBox();
		mun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(mun.getItemAt(0).equals("Seleccione el municipio")){
					mun.removeItem("Seleccione el municipio");
				}
			}
		});
		mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		mun.addItem("Seleccione el municipio");
		ArrayList<MunicipalityDto> m = MunicipalityService.getMunicipality();
		for(int i=0; i<m.size(); i++){
			mun.addItem(m.get(i).getNamMun());
		}
		mun.setBackground(new Color(238, 242, 236));
		mun.setBounds(158, 85, 237, 23);
		contentPanel.add(mun);

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				District.this.setVisible(false);
			}
		});
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(158, 129, 91, 31);
		contentPanel.add(cancel_button);

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().isEmpty() || mun.getSelectedItem().equals("Seleccione el municipio")){
					JOptionPane.showMessageDialog(District.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{

					String m = (String) mun.getSelectedItem();
					String e = null;
					try {
						e = DistrictService.create_district(name.getText(), MunicipalityService.find_by_Name(m).getCodMun());
					} catch (SQLException e1) {
						System.out.println("Error: " + e1.getMessage());
					}
					if(e!=null){
						JOptionPane.showMessageDialog(District.this,e,"ERROR",0);
						name.setText("");
						mun.removeAllItems();
						mun.addItem("Seleccione el municipio");
						ArrayList<MunicipalityDto> m1 = null;
						try {
							m1 = MunicipalityService.getMunicipality();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i=0; i<m1.size(); i++){
							mun.addItem(m1.get(i).getNamMun());
						}
					}else{
						JOptionPane.showMessageDialog(District.this,"Circunscripción creada correctamente.","ACCIÓN COMPLETADA",1);
						District.this.setVisible(false);
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
		ok_button.setBounds(304, 129, 91, 31);
		contentPanel.add(ok_button);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(District.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -24, 414, 294);
		contentPanel.add(fondo);
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
