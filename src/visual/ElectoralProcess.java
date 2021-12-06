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

import dto.Electoral_ProcessDto;
import services.ElectoralProcessService;
import services.MunicipalityService;
import util.LoadTable;

@SuppressWarnings("serial")
public class ElectoralProcess extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_process;
	private JLabel refresh;
	private JButton addRound;
	private JButton eliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElectoralProcess dialog = new ElectoralProcess();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public ElectoralProcess() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ElectoralProcess.class.getResource("/resources/icons8_World_Map_16.png")));
		setTitle("Procesos Electorales");
		setResizable(false);
		setBounds(170, 170, 554, 399);
		setBackground(new Color(238, 242, 236));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 242, 236));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(37, 26, 295, 316);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if (seleccion != -1) {
					addRound.setEnabled(true);
					eliminar.setEnabled(true);
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


		add_process = new JButton("Nuevo Proceso");
		add_process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewElectoralProcess nep = null;
				try {
					nep = new NewElectoralProcess();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nep.setVisible(true);
			}
		});
		add_process.setForeground(Color.WHITE);
		add_process.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_process.setBorderPainted(false);
		add_process.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_process.setBackground(new Color(73, 78, 107));
		add_process.setAlignmentX(0.5f);
		add_process.setBounds(357, 96, 170, 31);
		contentPanel.add(add_process);

		LoadTable.Load_ElectoralProcess(date, table);

		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_ElectoralProcess(date, table);
					addRound.setEnabled(false);
					eliminar.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class
				.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(334, 304, 32, 39);
		contentPanel.add(refresh);

		addRound = new JButton("Agregar Vuelta");
		addRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = -1;
				selection = table.getSelectedRow();
				String mun = (String) table.getValueAt(selection, 1);
				Electoral_ProcessDto prcs = null;
				try {
					prcs = ElectoralProcessService.find_by_Municipality(MunicipalityService.find_by_Name(mun).getCodMun());	
					prcs.setRoundNum(prcs.getRoundNum()+1);
					ElectoralProcessService.update_EP(prcs);
					JOptionPane.showMessageDialog(ElectoralProcess.this,"Se ha agregado un nueva vuelta.","ACCIÓN COMPLETADA",1);
					LoadTable.Load_ElectoralProcess(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		addRound.setForeground(Color.WHITE);
		addRound.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		addRound.setEnabled(false);
		addRound.setBorderPainted(false);
		addRound.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		addRound.setBackground(new Color(140, 145, 168));
		addRound.setAlignmentX(0.5f);
		addRound.setBounds(357, 158, 170, 31);
		contentPanel.add(addRound);
		
		eliminar = new JButton("Eliminar Proceso");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = -1;
				selection = table.getSelectedRow();
				int idProces =  (int) table.getValueAt(selection, 0);
				
				
				try {
					ElectoralProcessService.delete_EP(idProces);
					LoadTable.Load_ElectoralProcess(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		eliminar.setForeground(Color.WHITE);
		eliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		eliminar.setEnabled(false);
		eliminar.setBorderPainted(false);
		eliminar.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		eliminar.setBackground(new Color(140, 145, 168));
		eliminar.setAlignmentX(0.5f);
		eliminar.setBounds(357, 224, 170, 31);
		contentPanel.add(eliminar);
		
				JLabel fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(UserList.class
						.getResource("/resources/Morado.png")));
				fondo.setBounds(0, -26, 580, 396);
				contentPanel.add(fondo);
	}
}
