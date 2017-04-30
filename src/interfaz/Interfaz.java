package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mundo.Elemento;
import mundo.Propiedades;
import mundo.Relacion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private Dibujos dibujos = null;
	private Dibujos dibujos_1 = null;
	private Propiedades propiedades = new Propiedades();
	private String[][] matrizRelacion1;
	private String[][] matrizConexo;
	private int[][] productoConex;
	private ArrayList<String[]> permuta = new ArrayList<String[]>();
	private ArrayList<String[][]> matricesPos = new ArrayList<>();
	private ArrayList<int[][]> conexoArray = new ArrayList<>();
	private int[][] matrizSuma;
	private String[][] matrizCaminos;
	private JTable table;

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
		setBounds(100, 100, 783, 627);
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
		btnClear.setBounds(289, 544, 199, 23);
		panel.add(btnClear);

		dibujos_1 = new Dibujos();
		dibujos_1.setBounds(395, 11, 345, 334);
		panel.add(dibujos_1);

		JButton btnConexo = new JButton("Generar Tabla");
		btnConexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				/**
				if (dibujos.elementos.size() != 0) {
					matrizConexo = propiedades.matrizRelacion(dibujos.elementos, dibujos.relaciones);
					productoConex = new int[matrizConexo.length][matrizConexo[0].length];
					for (int i = 0; i < matrizConexo.length; i++) {
						for (int j = 0; j < matrizConexo.length; j++) {
							productoConex[i][j] = Integer.parseInt(matrizConexo[i][j]);

						}
					}
					conexoArray.add(productoConex);
					for (int i = 0; i < dibujos.elementos.size() - 1; i++) {
						conexoArray
								.add(propiedades.matrizPotencia(conexoArray.get(i), productoConex, dibujos.elementos));
					}

					matrizSuma = propiedades.matrizSuma(conexoArray);
					matrizCaminos = propiedades.matrizCaminos(matrizSuma);
					// pone el texto en la tabla de conexo
					if (propiedades.conexo(matrizCaminos,dibujos.elementos,table)) {
						table.setValueAt("X", 2, 1);
					} else {
						
						table.setValueAt("X", 2, 2);
					}
					// pone el texto en la tabla de fuertemente conexo
					if (propiedades.buscaCeroFueraDiagonal(conexoArray.get(0),dibujos.elementos,table)) {
						table.setValueAt("X", 3, 1);
					} else {
						table.setValueAt("X", 3, 2);
					}
					// pone el texto en la tabla de regular
					if (propiedades.regular(conexoArray.get(0),dibujos.elementos,table)) {
						table.setValueAt("X", 4, 1);
					} else {
						table.setValueAt("X", 4, 2);
					}
					
					// pone el texto en la tabla de completo
					if(conexoArray.size() > 1){
						if (propiedades.buscaCeroFuera1(conexoArray.get(1),dibujos.elementos,table)) {
							table.setValueAt("X", 5, 1);
						} else {
							table.setValueAt("X", 5, 2);
						}

					}else{
						table.setValueAt("X", 5, 2);
						table.setValueAt("No hay camino entre "+dibujos.elementos.get(0)+" y "+ dibujos.elementos.get(0), 5, 3);
					}
				}*/

			}
		});
		btnConexo.setBounds(289, 510, 199, 23);
		panel.add(btnConexo);

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
		table.setBounds(10, 390, 730, 96);
		panel.add(table);
	}
}
