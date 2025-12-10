package model;

/**
 * Clase ObjetoCaida - Representa objetos que caen
 */
public class ObjetoCaida {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    
    public enum TipoObjeto {
        BUENO, MALO
    }
    
    private TipoObjeto tipo;
    
    public ObjetoCaida(int x, int y, int ancho, int alto, TipoObjeto tipo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.tipo = tipo;
        this.velocidad = 3;
    }
    
    public void caer() {
        y += velocidad;
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public TipoObjeto getTipo() { return tipo; }
}