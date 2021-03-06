package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mundo.Operaciones;
import mundo.Procesos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class Interfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private Dibujos dibujos = null;
	private Dibujos dibujos_1 = null;
	private String[][] matrizRelacion1;
	private String[][] matrizConexo;
	private int[][] productoConex;
	private ArrayList<String[]> permuta = new ArrayList<String[]>();
	private ArrayList<String[][]> matricesPos = new ArrayList<>();
	private ArrayList<int[][]> conexoArray = new ArrayList<>();
	private int[][] matrizSuma;
	private String[][] matrizCaminos;
	private JTable table;
	private JTextPane txtPMatrixA1;
	private JTextPane txtPMatrixA2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1100, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		dibujos = new Dibujos();
		dibujos.setBounds(10, 11, 345, 334);
		panel.add(dibujos);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dibujos.elementos.clear();
				dibujos.relaciones.clear();
				dibujos.repaint();
				txtPMatrixA1.setText("");
				txtPMatrixA2.setText("");
				dibujos_1.elementos.clear();
				dibujos_1.relaciones.clear();
				permuta.clear();
				matricesPos.clear();
				conexoArray.clear();

				dibujos_1.repaint();
				for (int i = 1; i < table.getRowCount(); i++) {
					for (int j = 1; j < table.getColumnCount(); j++) {
						table.setValueAt("", i, j);
					}
				}

			}
		});
		btnClear.setBounds(164, 544, 199, 23);
		panel.add(btnClear);

		dibujos_1 = new Dibujos();
		dibujos_1.setBounds(395, 11, 345, 334);
		panel.add(dibujos_1);

		JButton btnGenerar = new JButton("Generar Tabla");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Procesos p = new Procesos();
				Operaciones op = new Operaciones(dibujos.relaciones, dibujos_1.relaciones);
				String [][]mAdyacencia1 = p.matrizAd(dibujos.relaciones, dibujos.elementos);
				String [][]mAdyacencia2 = p.matrizAd(dibujos_1.relaciones, dibujos_1.elementos);
				String mA1String = p.matrizToString(mAdyacencia1,dibujos.elementos);
				String mA2String = p.matrizToString(mAdyacencia2,dibujos_1.elementos);
				txtPMatrixA1.setText(mA1String);
				txtPMatrixA2.setText(mA2String);
				String in = op.interseccion();
				String un = op.universal();
				String difAB = op.diferenciaAB();
				String difBA = op.diferenciaBA();
				String difsim = op.difSimetrica();
				String complementoA = op.complementoA();
				String complementoB = op.complementoB();
				table.setValueAt("["+in+"]", 0, 1);
				table.setValueAt("["+un+"]", 1, 1);	
				table.setValueAt("["+difAB+"]", 2, 1);	
				table.setValueAt("["+difBA+"]", 3, 1);	
				table.setValueAt("["+difsim+"]", 4, 1);	
				table.setValueAt("["+complementoA+"]", 5, 1);	
				table.setValueAt("["+complementoB+"]", 6, 1);	
			}
		});
		btnGenerar.setBounds(371, 544, 199, 23);
		panel.add(btnGenerar);

		table = new JTable();
		table.setToolTipText("");
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"INTERSECCION(RnS)", null},
				{"UNION(RUS)", null},
				{"DIFERENCIA(R-S)", null},
				{"DIFERENCIA(S-R)", null},
				{"DIFERENCIA SIMETRICA(R | S)", null},
				{"COMPLEMENTO(R', respecto a un conjunto universal AxA)", null},
				{"COMPLEMENTO(S', respecto a un conjunto universal AxA)", null},
			},
			new String[] {
				"CONJUNTOS", "RELACION"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(325);
		table.setBounds(10, 390, 730, 112);
		panel.add(table);
		
		txtPMatrixA1 = new JTextPane();
		txtPMatrixA1.setEnabled(false);
		txtPMatrixA1.setBounds(771, 11, 261, 230);
		txtPMatrixA1.setDisabledTextColor(Color.BLACK);
		panel.add(txtPMatrixA1);
		
		txtPMatrixA2 = new JTextPane();
		txtPMatrixA2.setEnabled(false);
		txtPMatrixA2.setBounds(771, 286, 261, 230);
		txtPMatrixA2.setDisabledTextColor(Color.BLACK);
		panel.add(txtPMatrixA2);
	}
}
