package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dto.MunicipalityDto;
import services.MunicipalityService;
import util.LoadTable;

@SuppressWarnings("serial")
public class MunicipalityList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_mun;
	private JButton delete_mun;
	private JLabel refresh;
	private JButton update_mun;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MunicipalityList dialog = new MunicipalityList();
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
	public MunicipalityList() throws SQLException {
		setModal(true);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MunicipalityList.class.getResource("/resources/icons8_Map_Pinpoint_16.png")));
		setTitle("Municipios");
		setResizable(false);
		setBounds(170, 170, 586, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(37, 21, 486, 281);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if(seleccion != -1){
					update_mun.setEnabled(true);
					delete_mun.setEnabled(true);
				}
			}
		});
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(21);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		table.setMinimumSize(new Dimension(2, 2));
		table.setMaximumSize(new Dimension(2, 2));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setInheritsPopupMenu(true);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(0, 0, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(this.table);
		
		add_mun = new JButton("Nuevo Municipio");
		add_mun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newMun = null;
				newMun = JOptionPane.showInputDialog(MunicipalityList.this, "Escriba el nuevo municipio:", "NUEVO MUNICIPIO",1);
				if(newMun != null){
					if(newMun.isEmpty()){
						JOptionPane.showMessageDialog(MunicipalityList.this, "El nombre del municipio no puede ser vacío.", "CAMPO VACÍO", 0);
						add_mun.doClick();
					}
					else{
						String e = null;
						try {
							e = MunicipalityService.create_Municipality(newMun);
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(MunicipalityList.this,e,"ERROR",0);
						}else{
							try {
								LoadTable.Load_Municipality(date, table);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update_mun.setEnabled(false);
							delete_mun.setEnabled(false);
						}
					}
				}
			}
		});
		add_mun.setForeground(Color.WHITE);
		add_mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_mun.setBorderPainted(false);
		add_mun.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_mun.setBackground(new Color(73, 78, 107));
		add_mun.setAlignmentX(0.5f);
		add_mun.setBounds(17, 316, 170, 31);
		contentPanel.add(add_mun);
		
		delete_mun = new JButton("Eliminar Municipio");
		delete_mun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String municipality = (String) table.getValueAt(selection, 0);
				try {
					MunicipalityDto mun = MunicipalityService.find_by_Name(municipality);
					MunicipalityService.delete_municipality(mun.getCodMun());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(MunicipalityList.this,"Municipio eliminado correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_Municipality(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_mun.setEnabled(false);
				delete_mun.setEnabled(false);
			}
		});
		delete_mun.setEnabled(false);
		delete_mun.setForeground(Color.WHITE);
		delete_mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_mun.setBorderPainted(false);
		delete_mun.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_mun.setBackground(new Color(140, 145, 168));
		delete_mun.setAlignmentX(0.5f);
		delete_mun.setBounds(391, 316, 170, 31);
		contentPanel.add(delete_mun);
		
		LoadTable.Load_Municipality(date, table);
		
		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_Municipality(date, table);
					update_mun.setEnabled(false);
					delete_mun.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(524, 267, 37, 38);
		contentPanel.add(refresh);
		
		update_mun = new JButton("Modificar Municipio");
		update_mun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newMun = null;
				int selection = table.getSelectedRow();
				String nameMun = (String) table.getValueAt(selection, 0);
				newMun = JOptionPane.showInputDialog(MunicipalityList.this, "Escriba el nuevo nombre del municipio:", "NUEVO NOMBRE", 1);
				if(newMun != null){
					if(newMun.isEmpty()){
						JOptionPane.showMessageDialog(MunicipalityList.this, "El nombre del municipio no puede ser vacío.", "CAMPO VACÍO", 0);
						update_mun.doClick();
					}
					else{
						String e = null;
						try {
							MunicipalityDto munn = MunicipalityService.find_by_Name(nameMun);
							munn.setNamMun(newMun);
							e = MunicipalityService.update_Municipality(munn);
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(MunicipalityList.this,e,"ERROR",0);
						}else{
							try {
								LoadTable.Load_Municipality(date, table);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update_mun.setEnabled(false);
							delete_mun.setEnabled(false);
						}
					}
				}
			}
		});
		update_mun.setForeground(Color.WHITE);
		update_mun.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		update_mun.setEnabled(false);
		update_mun.setBorderPainted(false);
		update_mun.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		update_mun.setBackground(new Color(140, 145, 168));
		update_mun.setAlignmentX(0.5f);
		update_mun.setBounds(204, 316, 170, 31);
		contentPanel.add(update_mun);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 580, 396);
		contentPanel.add(fondo);
	}
}
