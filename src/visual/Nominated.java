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

import dto.VoterDto;
import services.VoterService;
import util.LoadTable;

@SuppressWarnings("serial")
public class Nominated extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel date;
	private JButton add_nom;
	private JButton delete_nom;
	private JLabel refresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Nominated dialog = new Nominated();
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
	public Nominated() throws SQLException {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nominated.class.getResource("/resources/icons8_Classroom_16.png")));
		setTitle("Nominados");
		setResizable(false);
		setBounds(170, 170, 590, 457);
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
		scrollPane.setBounds(30, 24, 506, 333);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int seleccion = -1;
				seleccion = table.getSelectedRow();
				if(seleccion != -1){
					delete_nom.setEnabled(true);
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
		
		add_nom = new JButton("Nuevo Nominado");
		add_nom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewNominated n = null;
				try {
					n = new NewNominated();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n.setVisible(true);
			}
		});
		add_nom.setForeground(Color.WHITE);
		add_nom.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		add_nom.setBorderPainted(false);
		add_nom.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		add_nom.setBackground(new Color(73, 78, 107));
		add_nom.setAlignmentX(0.5f);
		add_nom.setBounds(346, 381, 149, 31);
		contentPanel.add(add_nom);
		
		delete_nom = new JButton("Eliminar Nominado");
		delete_nom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = table.getSelectedRow();
				String nombre = (String) table.getValueAt(selection, 0);
				try {
					VoterDto v = VoterService.find_by_Name(nombre);
					VoterService.deleteVoter(v.getNumID());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(Nominated.this,"Nominado eliminado correctamente.","ACCIÓN COMPLETADA",1);
				try {
					LoadTable.Load_Nominated(date, table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				delete_nom.setEnabled(false);
			}
		});
		delete_nom.setEnabled(false);
		delete_nom.setForeground(Color.WHITE);
		delete_nom.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		delete_nom.setBorderPainted(false);
		delete_nom.setBorder(new LineBorder(new Color(140, 145, 168), 5, true));
		delete_nom.setBackground(new Color(140, 145, 168));
		delete_nom.setAlignmentX(0.5f);
		delete_nom.setBounds(88, 381, 170, 31);
		contentPanel.add(delete_nom);
		
		LoadTable.Load_Nominated(date, table);
		
		refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					LoadTable.Load_Nominated(date, table);
					delete_nom.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setIcon(new ImageIcon(UserList.class.getResource("/resources/icons8_Synchronize_32.png")));
		refresh.setBounds(537, 319, 37, 38);
		contentPanel.add(refresh);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(UserList.class.getResource("/resources/Morado.png")));
		fondo.setBounds(0, -26, 636, 396);
		contentPanel.add(fondo);
	}
}
