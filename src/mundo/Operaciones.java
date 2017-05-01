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
	
	/**
	 * Arraylist que contiene las intersecciones entre la lista A y la lista B
	 */
	ArrayList<String> interseccion;
	
	/**
	 * Arraylist que contiene los puntos individuales de cada arraylist
	 */
	ArrayList<String> puntos = new ArrayList<>();
	
	/**
	 * Constructor de la clase operaciones
	 * @param arrayA Conjunto de relaciones 1
	 * @param arrayB Conjunto de relaciones 2
	 */
	public Operaciones(ArrayList<Relacion> arrayA, ArrayList<Relacion> arrayB) {
		
		this.arrayA = toString(arrayA);
		this.arrayB = toString(arrayB);
		
		interseccion = new ArrayList<>(this.arrayA);
		generarPuntos(arrayA);
		generarPuntos(arrayB);
		
		generarUniversal(this.arrayA, this.arrayB);
		
	}
	
	private void generarPuntos(ArrayList<Relacion> relaciones){
		
		for (int i = 0; i < relaciones.size(); i++) {
			puntos.add(relaciones.get(i).getElemento1().getNombre());
			puntos.add(relaciones.get(i).getElemento2().getNombre());

		}
		
		Set<String> hs = new HashSet<>();
		
		hs.addAll(puntos);
		puntos.clear();
		puntos.addAll(hs);
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
	
	/**
	 * Genera el arraylist con las intersecciones de las listas
	 * @return Arraylist de intersecciones
	 */
	public String interseccion(){
		

		interseccion.retainAll(arrayB);
		String todos = "";
		for (String relacion : interseccion) {
			todos += relacion.toString();
		}
		
		return todos;
	}
	
	/**
	 * Genera la diferencia entre la lista A y la lista B
	 * @return String con las parejas de la diferencia
	 */
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
	
	/**
	 * Genera la diferencia entre la lista B y la lista A
	 * @return String con las parejas de la diferencia
	 */
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

	/**
	 * Genera la diferencia simétrica con respecto a el conjunto universal
	 * @return String con las parejas
	 */
	public String difSimetrica(){
		final int tamaño = puntos.size();
		String parejas = "";
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				String pareja = "("+puntos.get(i)+","+puntos.get(j)+")";
				boolean encontrar = false;
				for (int k = 0; k < arrayUniversal.size(); k++) {
					
					if(pareja.equals(arrayUniversal.get(k))){
						encontrar = true;
					}					
				}
				if(!encontrar){
					parejas += pareja;
				}
			}			
		}
		return parejas;
	}	
	
	public String difSimetrica2(){
		
		String parejas = "";
		
		for (int i = 0; i < arrayUniversal.size(); i++) {
			boolean encontrado = false;
			for (int j = 0; j < interseccion.size(); j++) {
				if(interseccion.get(j).equals(arrayUniversal.get(i))){
					encontrado = true;
				}
			}
			if(!encontrado){
				parejas += arrayUniversal.get(i);
			}			
		}
		
		return parejas;
	}
	
	
	/**
	 * Genera el complemento de A con respecto al conjunto universal
	 * @return String con las parejas
	 */
	public String complementoA(){
		final int tamaño = puntos.size();
		String parejas = "";
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				
				String pareja = "("+puntos.get(i)+","+puntos.get(j)+")";
				boolean encontrar = false;
				for (int k = 0; k < arrayA.size(); k++) {
					
					if(pareja.equals(arrayA.get(k))){
						encontrar = true;
					}					
				}
				if(!encontrar){
					parejas += pareja;
				}
				
			}
		}
		return parejas;
	}

	/**
	 * Genera el complemento de A con respecto al conjunto universal
	 * @return String con las parejas
	 */
	public String complementoB(){
		final int tamaño = puntos.size();
		String parejas = "";
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				
				String pareja = "("+puntos.get(i)+","+puntos.get(j)+")";
				boolean encontrar = false;
				for (int k = 0; k < arrayB.size(); k++) {
					
					if(pareja.equals(arrayB.get(k))){
						encontrar = true;
					}					
				}
				if(!encontrar){
					parejas += pareja;
				}
				
			}
		}
		return parejas;
	}

	
	
}