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
	 * @param relaciones
	 * @param relaciones2
	 * @return
	 *************************************************/
	public String interseccionRnS(ArrayList<Relacion> relaciones, ArrayList<Relacion> relaciones2) {
		String interseccionRnS = "";
		String eR1 = "";
		String eR2 = "";
		for (int i = 0; i < relaciones.size(); i++) {
				eR1 = relaciones.get(i).getElemento1().getNombre();
				eR2 = relaciones.get(i).getElemento2().getNombre();				
			for (int j = 0; j < relaciones2.size(); j++) {
				if(relaciones.get(i).equals(relaciones2.get(j))){
					interseccionRnS += "("+eR1+","+eR2+")"+",";
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
	 * @param relaciones
	 * @param relaciones2
	 * @return
	 ***********************************************/

	public String unionRuS(ArrayList<Relacion> relaciones, ArrayList<Relacion> relaciones2) {
		ArrayList<Relacion> interseccion = interseccioRnS(relaciones, relaciones2);
		String e1, e2;
		String union = "";
		for (int i = 0; i < relaciones.size(); i++) {
			e1 = relaciones.get(i).getElemento1().getNombre();
			e2 = relaciones.get(i).getElemento2().getNombre();
			if (!comp(relaciones.get(i), interseccion)) {
				union += "("+ e1 + ","+e2+")"+",";
			}
		}
		for (int j = 0; j < relaciones2.size(); j++) {
			e1 = relaciones2.get(j).getElemento1().getNombre();
			e2 = relaciones2.get(j).getElemento2().getNombre();
			if (!comp(relaciones2.get(j), interseccion)) {
				union += "("+ e1 + ","+e2+")"+",";
			}
		}
		if (!union.equals("")) {
			union = quitarUltimaComa(union);
		}

		return union;
	}

	/***************************************
	 * Comprueba si un elemento existe en 
	 * la interseccion
	 * 
	 * @param e
	 * @param interseccion
	 * @return
	 ***************************************/

	public boolean comp(Relacion e, ArrayList<Relacion> interseccion) {		
		for (int k = 0; k < interseccion.size(); k++) {
			if(e.equals(interseccion.get(k))){
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
	
	
	public ArrayList<Relacion> interseccioRnS(ArrayList<Relacion> relaciones, ArrayList<Relacion> relaciones2) {
		ArrayList<Relacion> interseccionRnS = new ArrayList<>();	
		for (int i = 0; i < relaciones.size(); i++) {				
			for (int j = 0; j < relaciones2.size(); j++) {
				if(relaciones.get(i).equals(relaciones2.get(j))){
					interseccionRnS.add(relaciones.get(i));
				}
			}
		}
		
		return interseccionRnS;
	}
	
	
	public String matrizToString(String[][] matriz,ArrayList<Elemento> p){
		String stringMatriz = "";
		String elements = "";
		for (int i = 0; i < p.size(); i++) {
			elements += p.get(i).getNombre()+"  ";
		}
		elements += "\n";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				stringMatriz += matriz[i][j]+"  ";
			}
			stringMatriz += p.get(i).getNombre();
			stringMatriz += "\n";
		}
		elements += stringMatriz;
		return elements;
	}

}
