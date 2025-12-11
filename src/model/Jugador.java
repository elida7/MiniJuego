package model;

/**
 * Clase Jugador - Representa al jugador en el juego
 */
public class Jugador {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int puntuacion;
    
    public Jugador(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.puntuacion = 0;
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

    public boolean colisionaCon(ObjetoCaida objeto) {
        return x < objeto.getX() + objeto.getAncho() &&
               x + ancho > objeto.getX() &&
               y < objeto.getY() + objeto.getAlto() &&
               y + alto > objeto.getY();
    }
    
    public void incrementarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    // Getters y Setters
    public int getX() { 
        return x; 
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() { 
        return y; 
    }

    public int getAncho() {
        return ancho; 
    }

    public int getAlto() { 
        return alto; 
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void resetPuntuacion() {
        this.puntuacion = 0;
    }
}