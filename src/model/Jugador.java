package model;

/**
 * Clase Jugador - Representa al jugador en el juego
 */
public class Jugador {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    
    public Jugador(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void moverDerecha(int limite) {
        if (x + ancho < limite) {
            x += 10;
        }
    }
    
    public void moverIzquierda() {
        if (x > 0) {
            x -= 10;
        }
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
}