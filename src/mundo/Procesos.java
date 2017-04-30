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

}
