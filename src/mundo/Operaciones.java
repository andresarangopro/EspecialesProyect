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
	ArrayList<Relacion> arrayA;
	
	/**
	 * Arreglo de las relaciones B.
	 */
	ArrayList<Relacion> arrayB;

	
	ArrayList<Relacion> arrayUniversal;
	
	public Operaciones(ArrayList<Relacion> arrayA, ArrayList<Relacion> arrayB) {
		this.arrayA = arrayA;
		this.arrayB = arrayB;
		
		generarUniversal(arrayA, arrayB);
		
	}

	private void generarUniversal(ArrayList<Relacion> arrayA2, ArrayList<Relacion> arrayB2) {
		
		for (Relacion relacion : arrayA2) {
			arrayUniversal.add(relacion);
		}
		
		for (Relacion relacion : arrayB2) {
			arrayUniversal.add(relacion);
		}
		
		Set<Relacion> hs = new HashSet<>();
		
		hs.addAll(arrayUniversal);
		arrayUniversal.clear();
		arrayUniversal.addAll(hs);
	}
	
	private void interseccion(){
		
		ArrayList<Relacion> interseccion = new ArrayList<>(arrayA);
		
		System.out.println(interseccion.retainAll(arrayB));		
	}

	
	//////////////////////////////////////
	////Getters y Setters
	//////////////////////////////////////
	
	
	
}