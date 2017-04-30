package mundo;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Propiedades {

	String nodo, nodo1, cadena;
	String[][] matrizRelacion;
	String[][] matriz_2;
	String[] vector;
	public ArrayList<String[]> elementos_permuta = new ArrayList<String[]>();
	boolean entro = false;

	public String[][] matrizRelacion(ArrayList<Elemento> elementos, ArrayList<Relacion> relaciones) {

		matrizRelacion = new String[elementos.size()][elementos.size()];
		for (int i = 0; i < elementos.size(); i++) {
			for (int j = 0; j < elementos.size(); j++) {

				nodo = elementos.get(i).getNombre();
				nodo1 = elementos.get(j).getNombre();
				for (int k = 0; k < relaciones.size(); k++) {
					if (nodo.equals(relaciones.get(k).getElemento1().toString())
							&& nodo1.equals(relaciones.get(k).getElemento2().toString())) {
						matrizRelacion[i][j] = "1";
						entro = true;
					}
				}

				if (entro == false) {
					matrizRelacion[i][j] = "0";
				}
				entro = false;
			}
		}
		return matrizRelacion;
	}

	public String[][] matrizVector(String[] elementos, ArrayList<Relacion> relaciones) {

		matrizRelacion = new String[elementos.length][elementos.length];
		for (int i = 0; i < elementos.length; i++) {
			for (int j = 0; j < elementos.length; j++) {

				nodo = elementos[i];
				nodo1 = elementos[j];
				for (int k = 0; k < relaciones.size(); k++) {
					if (nodo.equals(relaciones.get(k).getElemento1().toString())
							&& nodo1.equals(relaciones.get(k).getElemento2().toString())) {
						matrizRelacion[i][j] = "1";
						entro = true;
					}
				}

				if (entro == false) {
					matrizRelacion[i][j] = "0";
				}
				entro = false;
			}
		}
		return matrizRelacion;
	}

	public ArrayList<String[]> permutacion(ArrayList<Elemento> elementos, String act, int n, int r) {
		vector = new String[elementos.size()];
		if (n == 0) {
			cadena = "";
			for (int i = 0; i < elementos.size(); i++) {
				// System.out.println(act.charAt(i)+" "+ i);
				cadena = "" + act.charAt(i);
				vector[i] = cadena;
			}
			elementos_permuta.add(vector);
		} else {
			for (int i = 0; i < r; i++) {
				if (!act.contains(elementos.get(i).toString())) {

					permutacion(elementos, act + elementos.get(i).toString() + "", n - 1, r);
				}
			}
		}
		return elementos_permuta;

	}

	public ArrayList<String[][]> matricesPosibles(ArrayList<String[]> permuta, ArrayList<Elemento> elementos,
			ArrayList<Relacion> relaciones) {
		String[] vectorM = new String[elementos.size()];
		ArrayList<String[][]> matriz = new ArrayList<>();
		for (int i = 0; i < permuta.size(); i++) {
			for (int j = 0; j < permuta.get(i).length; j++) {
				vectorM[j] = permuta.get(i)[j];
				System.out.print(permuta.get(i)[j]);
			}

			matriz.add(matrizVector(vectorM, relaciones));
			System.out.println("");
		}
		return matriz;

	}

	public boolean isomorfos(ArrayList<String[][]> matricesPos, String[][] matrizRelacion1) {
		boolean matrizIgual = false;
		for (int j = 0; j < matricesPos.size(); j++) {

			for (int k = 0; k < matricesPos.get(j).length; k++) {
				for (int i = 0; i < matricesPos.get(j)[k].length; i++) {
					System.out.print(matricesPos.get(j)[k][i] + "\t");
				}
				System.out.println("");
			}

			if (Arrays.deepEquals(matricesPos.get(j), matrizRelacion1)) {
				matrizIgual = true;
				break;
			} else
				matrizIgual = false;

		}

		return matrizIgual;

	}

	public int[][] matrizPotencia(int[][] matriz, int[][] matrizB, ArrayList<Elemento> elementos) {
		int fil_m1 = matriz.length;
		int col_m1 = matriz[0].length;
		int[][] APotencia = new int[fil_m1][col_m1];

		for (int x = 0; x < APotencia.length; x++) {
			for (int y = 0; y < APotencia[x].length; y++) {
				for (int z = 0; z < col_m1; z++) {
					APotencia[x][y] += matriz[x][z] * matrizB[z][y];
				}

				System.out.print(APotencia[x][y]);

			}
			System.out.println("");
		}

		return APotencia;

	}

	public boolean conexo(String[][] caminos, ArrayList<Elemento> elementos, JTable table) {
		boolean filas = true;
		int pos1 = 0;
		int pos2 = 0;
		for (int i = 0; i < caminos.length; i++) {
			if (filas) {

				for (int j = 0; j < caminos[0].length; j++) {

					if (caminos[i][j].equals("0")) {

						filas = false;
						pos1 = i;
						pos2 = j;
					}
				}
			}
		}
		if (!filas) {
			table.setValueAt("No hay camino entre " + elementos.get(pos1) + " y " + elementos.get(pos2), 2, 3);
		}
		return filas;

	}

	public boolean buscaCeroFueraDiagonal(int[][] matrizPotencia1, ArrayList<Elemento> elementos, JTable table) {
		boolean filas = true;
		int pos1 = 0;
		int pos2 = 0;
		for (int i = 0; i < matrizPotencia1.length; i++) {
			if (filas) {

				for (int j = 0; j < matrizPotencia1[0].length; j++) {

					if (matrizPotencia1[i][j] == 0 && i != j) {
						pos1 = i;
						pos2 = j;
						filas = false;
					}
				}
			}
		}

		if (!filas) {
			table.setValueAt("No hay camino entre " + elementos.get(pos1) + " y " + elementos.get(pos2), 3, 3);
		}
		return filas;

	}

	public boolean buscaCeroFuera1(int[][] matrizPotencia1, ArrayList<Elemento> elementos, JTable table) {
		boolean filas = true;
		int pos1 = 0;
		int pos2 = 0;
		for (int i = 0; i < matrizPotencia1.length; i++) {
			if (filas) {

				for (int j = 0; j < matrizPotencia1[0].length; j++) {

					if (matrizPotencia1[i][j] == 0 && i != j) {
						pos1 = i;
						pos2 = j;
						filas = false;
					}
				}
			}
		}
		if (!filas) {
			table.setValueAt("No hay camino entre " + elementos.get(pos2) + " y " + elementos.get(pos1), 5, 3);
		}
		return filas;

	}

	public int[][] matrizSuma(ArrayList<int[][]> conexoArray) {

		int[][] matrizSuma = new int[conexoArray.get(0).length][conexoArray.get(0)[0].length];
		for (int k = 0; k < conexoArray.size(); k++) {
			for (int i = 0; i < conexoArray.get(k).length; i++) {
				for (int j = 0; j < conexoArray.get(k)[0].length; j++) {

					matrizSuma[i][j] += conexoArray.get(k)[i][j];
				}
			}
		}

		for (int i = 0; i < matrizSuma.length; i++) {
			for (int j = 0; j < matrizSuma.length; j++) {
				System.out.print(matrizSuma[i][j] + "\t");
			}
			System.out.println("");
		}

		return matrizSuma;

	}

	public String[][] matrizCaminos(int[][] matrizSuma) {
		String[][] matrizCaminos = new String[matrizSuma.length][matrizSuma[0].length];

		for (int i = 0; i < matrizSuma.length; i++) {
			for (int j = 0; j < matrizSuma[0].length; j++) {
				if (matrizSuma[i][j] != 0) {
					matrizCaminos[i][j] = "1";
				} else
					matrizCaminos[i][j] = "0";
			}

		}

		for (int i = 0; i < matrizCaminos.length; i++) {
			for (int j = 0; j < matrizCaminos.length; j++) {
				System.out.print(matrizCaminos[i][j] + "\t");
			}
			System.out.println("");
		}
		return matrizCaminos;

	}

	public boolean regular(int[][] matrizPotencia, ArrayList<Elemento> elementos, JTable table) {
		int[] vectorSuma = new int[matrizPotencia.length];
		boolean regular = true;
		for (int i = 0; i < matrizPotencia.length; i++) {
			for (int j = 0; j < matrizPotencia[0].length; j++) {

				vectorSuma[i] += matrizPotencia[i][j];

			}
			// System.out.println(vectorSuma[i]);
		}

		for (int i = 0; i < vectorSuma.length; i++) {
			if (vectorSuma[0] != vectorSuma[i]) {
				regular = false;
				table.setValueAt("El grado del nodo " + elementos.get(i) + " es diferente a los demas", 4, 3);
			}
		}

		return regular;

	}
}
