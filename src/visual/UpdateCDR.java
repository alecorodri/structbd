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
import services.VoterService;
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

import dto.CDRDto;
import dto.Electoral_CollegeDto;
import dto.VoterDto;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class UpdateCDR extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_cdr;
	private JLabel lblNombreDelCDR;
	private JButton cancel_button;
	private JButton ok_button;
	private JLabel fondo;
	private JLabel lblColegio;
	private JComboBox<String> comboBox;
	private JLabel lblPresidente;
	private JComboBox<String> pp;
	private CDRDto cdrP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateCDR dialog = new UpdateCDR(null);
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
	public UpdateCDR(CDRDto c) throws SQLException {
		cdrP = c;
		setType(Type.POPUP);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewCDR.class.getResource("/resources/icons8_Marker_16.png")));
		setTitle("Actualizar CDR");
		setBounds(170, 170, 450, 287);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		name_cdr = new JTextField();
		name_cdr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c == KeyEvent.VK_ENTER){
					name_cdr.setFocusable(false);
				}
				name_cdr.setFocusable(true);
			}
		});
		TextPrompt nameCDR = new TextPrompt("ej: Antonio Maceo", name_cdr);
		nameCDR.setForeground(Color.LIGHT_GRAY);
		nameCDR.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		name_cdr.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name_cdr.setColumns(10);
		name_cdr.setBounds(166, 33, 237, 23);
		contentPanel.add(name_cdr);

		lblNombreDelCDR = new JLabel("Nombre:");
		lblNombreDelCDR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelCDR.setForeground(new Color(17, 24, 63));
		lblNombreDelCDR.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblNombreDelCDR.setBounds(10, 24, 146, 37);
		contentPanel.add(lblNombreDelCDR);

		cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCDR.this.setVisible(false);
			}
		});

		lblColegio = new JLabel("Colegio:");
		lblColegio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColegio.setForeground(new Color(17, 24, 63));
		lblColegio.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblColegio.setBounds(10, 80, 146, 37);
		contentPanel.add(lblColegio);
		
		lblPresidente = new JLabel("Presidente:");
		lblPresidente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPresidente.setForeground(new Color(17, 24, 63));
		lblPresidente.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		lblPresidente.setBounds(10, 137, 146, 37);
		contentPanel.add(lblPresidente);
		
		pp = new JComboBox<String>();
		pp.setPreferredSize(new Dimension(50, 50));
		pp.setMaximumSize(new Dimension(33010, 33005));
		pp.setMaximumRowCount(20);
		pp.setForeground(Color.DARK_GRAY);
		pp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		pp.setBounds(166, 145, 237, 23);
		contentPanel.add(pp);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		comboBox.setBounds(166, 89, 237, 23);
		contentPanel.add(comboBox);
		
		ArrayList<VoterDto> voter = VoterService.getVoters();
		for(int i = 0; i < voter.size(); i++){
			if (voter.get(i).getCdr() == cdrP.getCodCDR()) {
				System.out.println(voter.get(i).getNamVot());
				pp.addItem(voter.get(i).getNamVot().toString());
			}
		}
		cancel_button.setForeground(Color.WHITE);
		cancel_button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		cancel_button.setBorderPainted(false);
		cancel_button.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		cancel_button.setBackground(new Color(140, 145, 168));
		cancel_button.setBounds(312, 197, 91, 31);
		contentPanel.add(cancel_button);
		ArrayList<Electoral_CollegeDto> colegios = CollegeService.getVoters();
		for(int i = 0; i < colegios.size(); i++){
			comboBox.addItem(colegios.get(i).getNameCollege());
		}

		ok_button = new JButton("Aceptar");
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name_cdr.getText().isEmpty()){
					JOptionPane.showMessageDialog(UpdateCDR.this,"Complete todos los campos antes de continuar.","CAMPOS VACÍOS",0);
				}else{
					Electoral_CollegeDto d = null;
					VoterDto v = null;
					try {
						d = CollegeService.find_by_Name((String) comboBox.getSelectedItem());
						v = VoterService.find_by_Name((String) pp.getSelectedItem());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					cdrP.setNam_presidentCDR(v.getNumID());
					cdrP.setId_college(d.getId_district());
					cdrP.setNamCDR(name_cdr.getText());
					SQLException error = null;
					
					try {
						CdrService.update_CDR(cdrP);
					} catch (SQLException e1) {
						System.out.println("Error: " + e1.getMessage());
						error = e1;
					}
					if(error!=null){
						JOptionPane.showMessageDialog(UpdateCDR.this,error.getMessage(),"ERROR",0);
						name_cdr.setText("");
					}else{
						JOptionPane.showMessageDialog(UpdateCDR.this,"CDR actualizado correctamente.","ACCIÓN COMPLETADA",1);
						UpdateCDR.this.setVisible(false);
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
		ok_button.setBounds(166, 197, 91, 31);
		contentPanel.add(ok_button);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(NewUser.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 542, 276);
		contentPanel.add(fondo);

		name_cdr.setText(cdrP.getNamCDR());
		Electoral_CollegeDto dd = CollegeService.find_by_Id(cdrP.getId_college());
		comboBox.setSelectedItem(dd.getNameCollege());
	}
}
