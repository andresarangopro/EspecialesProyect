package mundo;

public class Relacion {
   
	//////////////////////////////
	//Variables
	//////////////////////////////
    private Elemento elemento1, elemento2;
    
 	//////////////////////////////
 	//Metodos
 	//////////////////////////////
    
    public boolean equals(Relacion relacion) {
        if (this == relacion)
            return true;
        
        if (elemento1.equals(relacion.getElemento1()) && elemento2.equals(relacion.getElemento2()))
            return true;
        
        return false;
    }
    
	//////////////////////////////
	//Getter && Setter
	//////////////////////////////
    
    public void setElemento1(Elemento elemento1) {
        this.elemento1 = elemento1;
    }
    public Elemento getElemento1() {
        return elemento1;
    }
    public void setElemento2(Elemento elemento2) {
        this.elemento2 = elemento2;
    }
    public Elemento getElemento2() {
        return elemento2;
    }
    public String toString() {
        return "(" + elemento1 + ", " + elemento2 + ")";
    }


}
