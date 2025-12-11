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
    
    private static Random random = new Random();
    private boolean activo;
    
    public ObjetoCaida(int x, int y, int ancho, int alto, TipoObjeto tipo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.tipo = tipo;
        this.velocidad = 2 + random.nextInt(3);
        this.activo = true;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
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