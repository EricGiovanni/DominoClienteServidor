import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que simula un juego de domino
 */
public class Domino {
    private ArrayList<Ficha> fichasRestantes;
    private ArrayList<Ficha> tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    /**
     * Constructor que crea las fichas de domino y reparte las fichas entre los
     * jugadores
     * 
     * @param nombre1
     * @param nombre2
     */
    public Domino(String nombre1, String nombre2) {
        this.fichasRestantes = new ArrayList<Ficha>();
        creaFichas();
        Collections.shuffle(this.fichasRestantes);
        reparteFichas(nombre1, nombre2);
        this.tablero = new ArrayList<Ficha>();
        this.tablero.add(new Ficha("6", "6"));
    }

    /**
     * Método privado que crea todas las fichas de domino
     */
    private void creaFichas() {
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                if (i != 6 || j != 6) {
                    Ficha f = new Ficha(i + "", j + "");
                    this.fichasRestantes.add(f);
                }
            }
        }
    }

    /**
     * Método que reparte fichas entre ambos jugadores con las que podrán jugar
     * 
     * @param nombre1
     * @param nombre2
     */
    private void reparteFichas(String nombre1, String nombre2) {
        ArrayList<Ficha> j1 = new ArrayList<Ficha>();
        ArrayList<Ficha> j2 = new ArrayList<Ficha>();
        for (int i = 0; i < 7; i++) {
            j1.add(this.fichasRestantes.remove(0));
            j2.add(this.fichasRestantes.remove(0));
        }
        this.jugador1 = new Jugador(nombre1, j1);
        this.jugador2 = new Jugador(nombre2, j2);
    }

    public ArrayList<Ficha> getTablero() {
        return this.tablero;
    }

    public ArrayList<Ficha> getFichasRestantes() {
        return this.fichasRestantes;
    }

    public Jugador getJugador1() {
        return this.jugador1;
    }

    public Jugador getJugador2() {
        return this.jugador2;
    }

    public void DarFichaJ1() {
        if (this.fichasRestantes.size() != 0) {
            this.jugador1.agregaFicha(this.fichasRestantes.remove(0));
        }
    }

    public void DarFichaJ2() {
        if (this.fichasRestantes.size() != 0) {
            this.jugador2.agregaFicha(this.fichasRestantes.remove(0));
        }
    }

    public boolean coincide(Ficha c) {
        boolean coincide = false;
        return this.fichasRestantes.get(0).coincide(c)
                || this.fichasRestantes.get(this.fichasRestantes.size() - 1).coincide(c);
    }

    /**
     * Método que devuelve la representación en cadena de todo el domino
     * 
     * @return
     */
    public String toString() {
        return this.tablero.toString();
    }

}
