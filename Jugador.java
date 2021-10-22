import java.util.ArrayList;

/**
 * Clase que simula a un jugador de domino
 */
public class Jugador {
    private String nombre;
    private ArrayList<Ficha> fichas;

    /**
     * Constructor que crea un jugador a partir de su nombre y las fichas que le
     * tocara jugar en el juego actual
     * 
     * @param nombre
     * @param fichas
     */
    public Jugador(String nombre, ArrayList<Ficha> fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
    }

    /**
     * Método que devuelve la representación en cadena dando el nombre del jugador y
     * sus fichas actuales
     * 
     * @return
     */
    public String toString() {
        String s = this.nombre + ": \nTus fichas son: \n";
        int n = 1;
        for (Ficha f : this.fichas) {
            s += n + ". " + f.toString() + "\n";
            n++;
        }
        return s;
    }

    public ArrayList<Ficha> getFichas() {
        return this.fichas;
    }

}
