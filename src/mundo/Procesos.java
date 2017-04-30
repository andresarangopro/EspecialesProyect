package mundo;

import java.util.ArrayList;

public class Procesos {
	
	///////////////////////////////
	//Metodos
	///////////////////////////////
	
	/****************************************************
	 * Metodo que genera la matriz de adyacencia
	 * generando una matriz de nxn donde n es el 
	 * n�mero de elementos, compara cada par de 
	 * elementos con las relaciones existentes
	 * y si esta existe pone un 1 en la pos i,j de 
	 * la matriz que se esta generando en caso contrario
	 * pone un 0
	 * @param matrizR matriz de relaciones
	 * @param matrizE matriz de elementos
	 * @return matriz de adyacencias
	 ******************************************************/
	
	public String [][] matrizAd(ArrayList<Relacion> matrizR, ArrayList<Elemento> matrizE){
		int tam = matrizE.size();
		String[][] relaciones = new String[tam][tam];		
		String e1 ;
		String e2 ;
		for(int i = 0; i < tam; i++){
			for (int j = 0; j < tam; j++) {
				e1 = matrizE.get(i).getNombre();
				e2 = matrizE.get(j).getNombre();
				if(existRelacion(e1, e2, matrizR)){
					relaciones[i][j] = "1";
				}else
					relaciones[i][j] = "0";
			}
		
		}
		return relaciones;
	}
	
	/****************************************************
	 * Metodo que compara un par de nodos con la
	 * lista de relaciones si existe retorna true en
	 * caso contrario retorna false 
	 * @param elemento1 
	 * @param elemento2
	 * @param matrizR matriz de relaciones
	 * @return si existe o no la relacion
	 ******************************************************/
	
	public boolean existRelacion(String elemento1, String elemento2, ArrayList<Relacion> matrizR){
		String eR1 ;
		String eR2 ;
		for (int k = 0; k < matrizR.size(); k++) {
			eR1 = matrizR.get(k).getElemento1().getNombre();
			eR2 = matrizR.get(k).getElemento2().getNombre();
			if(elemento1.equals(eR1) && elemento2.equals(eR2)){
				return true;
			}
		}
		return false;
	}
	
	/**************************************************
	 * Metodo que halla la interseccion de dos listas
	 * 
	 * @param matrizE1
	 * @param matrizE2
	 * @return
	 *************************************************/
	public String interseccionRnS(ArrayList<Elemento> matrizE1, ArrayList<Elemento> matrizE2) {
		String interseccionRnS = "";
		for (int i = 0; i < matrizE1.size(); i++) {
			for (int j = 0; j < matrizE2.size(); j++) {
				if (matrizE1.get(i).equals(matrizE2.get(j))) {
					interseccionRnS += matrizE1.get(i).getNombre() + ",";

				}
			}

		}
		if (!interseccionRnS.equals("")) {
			interseccionRnS = quitarUltimaComa(interseccionRnS);
		}
		return interseccionRnS;
	}

	/**********************************************
	 * Metodo que halla la union de dos listas
	 * 
	 * @param matrizE1
	 * @param matrizE2
	 * @return
	 ***********************************************/

	public String unionRuS(ArrayList<Elemento> matrizE1, ArrayList<Elemento> matrizE2) {
		String interseccion = interseccionRnS(matrizE1, matrizE2);
		String e1, e2;
		String union = "";
		for (int i = 0; i < matrizE1.size(); i++) {
			e1 = matrizE1.get(i).getNombre();
			if (!comp(e1, interseccion)) {
				union += e1 + ",";
			}
		}
		for (int j = 0; j < matrizE2.size(); j++) {
			e2 = matrizE2.get(j).getNombre();
			if (!comp(e2, interseccion)) {
				union += e2 + ",";
			}
		}
		if (!union.equals("")) {
			union = quitarUltimaComa(union);
		}

		return union;
	}

	/***************************************
	 * Comprueba si un elemento existe en la interseccion
	 * 
	 * @param e
	 * @param interseccion
	 * @return
	 ***************************************/

	public boolean comp(String e, String interseccion) {

		String charAtPos = "";
		char[] inter = interseccion.toCharArray();
		for (int k = 0; k < inter.length; k++) {
			charAtPos = inter[k] + "";
			if (e.equals(charAtPos)) {
				return true;
			}
		}
		return false;
	}

	/***********************************************
	 * Metodo que elimina el ultimo caracter de un string
	 * 
	 * @param interseccionRnS
	 * @return
	 ***********************************************/

	public String quitarUltimaComa(String interseccionRnS) {
		char[] p = interseccionRnS.toCharArray();
		p[p.length - 1] = ' ';
		String t = "";
		for (int i = 0; i < p.length; i++) {
			t += p[i];
		}
		return t;
	}

}
