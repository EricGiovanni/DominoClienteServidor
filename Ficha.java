/**
 * Clase que simula una ficha de Domino
 */
public class Ficha {
    private String valor1;
    private String valor2;

    /**
     * Constructor que recibe los valores de la ficha Si el valor es 0, significa
     * que la ficha es blanca, por lo que sustituimos el 0, por un _
     * 
     * @param valor1
     * @param valor2
     */
    public Ficha(String valor1, String valor2) {
        if (valor1.equals("0")) {
            this.valor1 = "_";
        } else {
            this.valor1 = valor1;
        }
        if (valor2.equals("0")) {
            this.valor2 = "_";
        } else {
            this.valor2 = valor2;
        }
    }

    /**
     * Método que devuelve la representación en cadena con el valor 1 a la derecha y
     * el valor 2 a la izquierda
     * 
     * @return
     */
    public String toStringD() {
        return "[" + this.valor1 + ", " + this.valor2 + "]";
    }

    /**
     * Método que devuelve la representación en cadena con el valor 2 a la derecha y
     * el valor 1 a la izquierda
     * 
     * @return
     */
    public String toStringI() {
        return "[" + this.valor2 + ", " + this.valor1 + "]";
    }

    /**
     * Método que voltea los valores para representar la cadena de una u otra forma
     */
    public void voltearValores() {
        String aux;
        aux = this.valor1;
        this.valor1 = this.valor2;
        this.valor2 = aux;
    }

    public boolean coincide(Ficha f) {
        return true;
    }

    /**
     * Método que devuelve la representación en cadena con el valor 1 a la derecha y
     * el valor 2 a la izquierda
     * 
     * @return
     */
    public String toString() {
        return toStringD();
    }
}
