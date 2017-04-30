package mundo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase encargada de realizar las operaciones entre conjuntos 
 */
public class Operaciones {

	/**
	 * Arreglo de las relaciones A.
	 */
	ArrayList<String> arrayA;
	
	/**
	 * Arreglo de las relaciones B.
	 */
	ArrayList<String> arrayB;

	/**
	 * Universal
	 */
	ArrayList<String> arrayUniversal = new ArrayList<>();
	
	ArrayList<String> interseccion;
	
	/**
	 * Constructor de la clase operaciones
	 * @param arrayA Conjunto de relaciones 1
	 * @param arrayB Conjunto de relaciones 2
	 */
	public Operaciones(ArrayList<Relacion> arrayA, ArrayList<Relacion> arrayB) {
		this.arrayA = toString(arrayA);
		this.arrayB = toString(arrayB);
		
		interseccion = new ArrayList<>(this.arrayA);
		
		generarUniversal(this.arrayA, this.arrayB);
		
	}

	
	/**
	 * Genera el conjunto universal basado en los dos conjuntos ingresados
	 * @param arrayA2 Conjunto de Strings 1
	 * @param arrayB2 Conjunto de Strings 2
	 */
	private void generarUniversal(ArrayList<String> arrayA2, ArrayList<String> arrayB2) {
		
		for (String relacion : arrayA2) {
			arrayUniversal.add(relacion);
		}
		
		for (String relacion : arrayB2) {
			arrayUniversal.add(relacion);
		}
		
		Set<String> hs = new HashSet<>();
		
		hs.addAll(arrayUniversal);
		arrayUniversal.clear();
		arrayUniversal.addAll(hs);
	}
	
	/**
	 * Convierte Arraylist de relaciones a tipo String
	 * @param relaciones Conjunto de relaciones a ser convertido
	 * @return Arraylist de Strings
	 */
	private ArrayList<String> toString(ArrayList<Relacion> relaciones){

		ArrayList<String> nuevo = new ArrayList<>();
		for (Relacion relacion : relaciones) {
			nuevo.add("("+relacion.getElemento1().getNombre() +"," + relacion.getElemento2().getNombre()+")");
		}
		
		return nuevo;		
	}
	
	/**
	 * Toma el conjunto universal y lo devuelve como una cadena de caracteres
	 * @return universal
	 */
	public String universal(){
		String todos = "";
		
		for (String relacion : arrayUniversal) {
			todos += relacion.toString();
		}
		
		return todos;
	}
	
	public String interseccion(){
		

		interseccion.retainAll(arrayB);
		String todos = "";
		for (String relacion : interseccion) {
			todos += relacion.toString();
		}
		
		return todos;
	}
	
	public String diferenciaAB(){
		
		String todos = "";
		for (int i = 0; i < arrayA.size(); i++) {
			boolean encontrar = false;
			for (int j = 0; j < interseccion.size(); j++) {
			
				if(arrayA.get(i).equals(interseccion.get(j))){
					encontrar = true;
				}			
			}
			if(!encontrar){
				
				todos += arrayA.get(i);
				
			}			
		}
		
		return todos;
	}
	
	public String diferenciaBA(){
		
		String todos = "";
		for (int i = 0; i < arrayB.size(); i++) {
			boolean encontrar = false;
			for (int j = 0; j < interseccion.size(); j++) {
			
				if(arrayB.get(i).equals(interseccion.get(j)) ){
					encontrar = true;
				}			
			}
			if(!encontrar){
				
				todos += arrayB.get(i);
				
			}			
		}
		
		return todos;
	}

	public String difSimetrica(){
	
		ArrayList<String> a = new ArrayList<>(arrayA);
		a.addAll(arrayB);
		String todos = "";
		for (int i = 0; i < a.size(); i++) {
			boolean encontrar = false;
			for (int j = 0; j < interseccion.size(); j++) {
				
				if(a.get(i).equals(interseccion.get(j))){
					encontrar = true;
				}			
			}	
			if(!encontrar){
				todos += a.get(i);
			}
		}
		
		return todos;
	}	
}