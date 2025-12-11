package model;

import java.util.Random;

/**
 * Clase ObjetoCaida - Representa objetos que caen
 */
public class ObjetoCaida {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private TipoObjeto tipo;
    private boolean activo;
    
    public enum TipoObjeto {
        BUENO, MALO
    }
    
    private static Random random = new Random();
    
    public ObjetoCaida(int x, int y, int ancho, int alto, TipoObjeto tipo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.tipo = tipo;
        this.velocidad = 2 + random.nextInt(3);
        this.activo = true;
    }

    public void caer() {
        if (activo){
            y += velocidad;
        }
    }
    
    public boolean haSalidoDePantalla(int alturaPantalla) {
        return y > alturaPantalla;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public TipoObjeto getTipo() { return tipo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public int getVelocidad() { return velocidad; }
}