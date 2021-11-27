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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import services.DistrictService;
import util.LoadTable;
import util.TextPrompt;
import dto.DistrictDto;

@SuppressWarnings("serial")
public class DistrictList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JTextField find;
	private JButton clean;
	private JButton add_dist;
	private JButton delete_dist;
	private JLabel refresh;
	private JButton update_dist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DistrictList dialog = new DistrictList();
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
	public DistrictList() throws SQLException {
		setModal(true);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DistrictList.class.getResource("/resources/icons8_Location_16.png")));
		setTitle("Circunscripci\u00F3n");
		setResizable(false);
		setBounds(170, 170, 590, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 76, 292, 266);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if(seleccion != -1){
					update_dist.setEnabled(true);
					delete_dist.setEnabled(true);
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
		
		JLabel find_picture = new JLabel("");
		find_picture.setIcon(new ImageIcon(UserList.class.getResource("/resources/icons8_Search_32.png")));
		find_picture.setBounds(20, 11, 37, 46);
		contentPanel.add(find_picture);
		
		find = new JTextField();
		TextPrompt userHelp = new TextPrompt("Buscar circunscripci\u00F3n", find);
		userHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		find.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		find.setBounds(58, 24, 225, 23);
		contentPanel.add(find);
		find.setColumns(10);
		
		clean = new JButton("Cancelar B\u00FAsqueda");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		clean.setForeground(Color.WHITE);
		clean.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		clean.setBorderPainted(false);
		clean.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		clean.setBackground(new Color(73, 78, 107));
		clean.setBounds(342, 21, 225, 31);
		contentPanel.add(clean);
		
		add_dist = new JButton("Nueva Circunscripci\u00F3n");
		add_dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				District d = null;
				try {
					d = new District();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d.setVisible(true);
			}
		});
		add_dist.setForeground(Color.WHITE);
		add_dist.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_dist.setBorderPainted(false);
		add_dist.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_dist.setBackground(new Color(73, 78, 107));
		add_dist.setAlignmentX(0.5f);
		add_dist.setBounds(342, 129, 225, 31);
		contentPanel.add(add_dist);
		
		delete_dist = new JButton("Eliminar Circunscripci\u00F3n");
		delete_dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String d = (String) table.getValueAt(selection, 0);
				try {
					DistrictDto distr = DistrictService.find_by_Name(d);
					DistrictService.delete_district(distr.getCodDis());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(DistrictList.this," Circunscripción eliminada correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_District(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_dist.setEnabled(false);
				delete_dist.setEnabled(false);
			}
		});
		delete_dist.setEnabled(false);
		delete_dist.setForeground(Color.WHITE);
		delete_dist.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_dist.setBorderPainted(false);
		delete_dist.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_dist.setBackground(new Color(140, 145, 168));
		delete_dist.setAlignmentX(0.5f);
		delete_dist.setBounds(342, 246, 225, 31);
		contentPanel.add(delete_dist);
		
		LoadTable.Load_District(date, table);
		
		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_District(date, table);
					update_dist.setEnabled(false);
					delete_dist.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(311, 304, 37, 38);
		contentPanel.add(refresh);
		
		update_dist = new JButton("Modificar Circunscripci\u00F3n");
		update_dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newDist = null;
				int selection = table.getSelectedRow();
				String nameDist = (String) table.getValueAt(selection, 0);
				newDist = JOptionPane.showInputDialog(DistrictList.this, "Escriba el nuevo nombre de la circunscripción:", "NUEVO NOMBRE", 1);
				if(newDist != null){
					if(newDist.isEmpty()){
						JOptionPane.showMessageDialog(DistrictList.this, "El nombre de la circunscripción no puede ser vacío.", "CAMPO VACÍO", 0);
						update_dist.doClick();
					}
					else{
						String e = null;
						try {
							DistrictDto dist = DistrictService.find_by_Name(nameDist);
							dist.setNamDis(newDist);
							e = DistrictService.update_district(dist);
						} catch (SQLException e1) {
							System.out.println("Error: " + e1.getMessage());
						}
						if(e!=null){
							JOptionPane.showMessageDialog(DistrictList.this,e,"ERROR",0);
						}else{
							try {
								LoadTable.Load_District(date, table);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update_dist.setEnabled(false);
							delete_dist.setEnabled(false);
						}
					}
				}
			}
		});
		update_dist.setForeground(Color.WHITE);
		update_dist.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		update_dist.setEnabled(false);
		update_dist.setBorderPainted(false);
		update_dist.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		update_dist.setBackground(new Color(140, 145, 168));
		update_dist.setAlignmentX(0.5f);
		update_dist.setBounds(342, 187, 225, 31);
		contentPanel.add(update_dist);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -63, 629, 406);
		contentPanel.add(fondo);
	}

}
