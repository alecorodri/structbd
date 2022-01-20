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
import services.ElectoralProcessService;
import services.MunicipalityService;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

import dto.MunicipalityDto;

@SuppressWarnings("serial")
public class NewElectoralProcess extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JComboBox<String> comboBox;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewElectoralProcess dialog = new NewElectoralProcess();
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
	public NewElectoralProcess() throws SQLException {
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewElectoralProcess.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Nuevo Proceso Electoral");
		setBounds(170, 170, 446, 167);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElectoralProcess.this.setVisible(false);
			}
		});


		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		comboBox.setBounds(186, 33, 237, 28);
		contentPanel.add(comboBox);
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(293, 88, 91, 31);
		contentPanel.add(cancel_button);
		ArrayList<MunicipalityDto> municipality = MunicipalityService.getMunicipality();
		for(int i = 0; i < municipality.size(); i++){
			comboBox.addItem(municipality.get(i).getNamMun());
		}

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MunicipalityDto mnc = null;
				try {
					mnc = MunicipalityService.find_by_Name((String) comboBox.getSelectedItem());
					ElectoralProcessService.create_EP(mnc.getCodMun(), 1);
					JOptionPane.showMessageDialog(NewElectoralProcess.this,"Proceso Electoral creado correctamente.","ACCIÓN COMPLETADA",1);
					NewElectoralProcess.this.setVisible(false);				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
			}
		});
		ok_button.setForeground(Color.WHITE);
		ok_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_button.setBorderPainted(false);
		ok_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_button.setBackground(new Color(73, 78, 107));
		ok_button.setAlignmentX(0.5f);
		ok_button.setBounds(101, 88, 91, 31);
		contentPanel.add(ok_button);
		
		JLabel lbmunicpality = new JLabel("Municipios:");
		lbmunicpality.setHorizontalAlignment(SwingConstants.RIGHT);
		lbmunicpality.setForeground(new Color(17, 24, 63));
		lbmunicpality.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lbmunicpality.setBounds(30, 27, 146, 37);
		contentPanel.add(lbmunicpality);
		
				fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
				fondo.setBounds(0, -26, 542, 276);
				contentPanel.add(fondo);
		
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
import services.ElectoralProcessService;
import services.MunicipalityService;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

import dto.MunicipalityDto;

@SuppressWarnings("serial")
public class NewElectoralProcess extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JComboBox<String> comboBox;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewElectoralProcess dialog = new NewElectoralProcess();
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
	public NewElectoralProcess() throws SQLException {
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewElectoralProcess.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Nuevo Proceso Electoral");
		setBounds(170, 170, 446, 167);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElectoralProcess.this.setVisible(false);
			}
		});


		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		comboBox.setBounds(186, 33, 237, 28);
		contentPanel.add(comboBox);
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(293, 88, 91, 31);
		contentPanel.add(cancel_button);
		ArrayList<MunicipalityDto> municipality = MunicipalityService.getMunicipality();
		for(int i = 0; i < municipality.size(); i++){
			comboBox.addItem(municipality.get(i).getNamMun());
		}

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MunicipalityDto mnc = null;
				try {
					mnc = MunicipalityService.find_by_Name((String) comboBox.getSelectedItem());
					ElectoralProcessService.create_EP(mnc.getCodMun(), 1);
					JOptionPane.showMessageDialog(NewElectoralProcess.this,"Proceso Electoral creado correctamente.","ACCIÓN COMPLETADA",1);
					NewElectoralProcess.this.setVisible(false);				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
			}
		});
		ok_button.setForeground(Color.WHITE);
		ok_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		ok_button.setBorderPainted(false);
		ok_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		ok_button.setBackground(new Color(73, 78, 107));
		ok_button.setAlignmentX(0.5f);
		ok_button.setBounds(101, 88, 91, 31);
		contentPanel.add(ok_button);
		
		JLabel lbmunicpality = new JLabel("Municipios:");
		lbmunicpality.setHorizontalAlignment(SwingConstants.RIGHT);
		lbmunicpality.setForeground(new Color(17, 24, 63));
		lbmunicpality.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lbmunicpality.setBounds(30, 27, 146, 37);
		contentPanel.add(lbmunicpality);
		
				fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
				fondo.setBounds(0, -26, 542, 276);
				contentPanel.add(fondo);
		
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
