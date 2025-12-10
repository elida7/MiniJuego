package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Juego - Maneja la lógica principal
 */
public class Juego {
    private Jugador jugador;
    private List<ObjetoCaida> objetos;
    private int anchoPantalla;
    private int altoPantalla;
    
    public Juego(int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.objetos = new ArrayList<>();
        
        // Crear jugador en posición inicial
        int anchoJugador = 60;
        int altoJugador = 30;
        int xInicial = (anchoPantalla - anchoJugador) / 2;
        int yInicial = altoPantalla - altoJugador - 20;
        
        this.jugador = new Jugador(xInicial, yInicial, anchoJugador, altoJugador);
    }
    
    public void iniciar() {
        objetos.clear();
    }
    
    public void actualizar() {
        // Mover objetos
        for (ObjetoCaida objeto : objetos) {
            objeto.caer();
        }
    }
    
    public void moverJugadorDerecha() {
        jugador.moverDerecha(anchoPantalla);
    }
    
    public void moverJugadorIzquierda() {
        jugador.moverIzquierda();
    }
    
    // Getters
    public Jugador getJugador() { return jugador; }
    public List<ObjetoCaida> getObjetos() { return objetos; }
}