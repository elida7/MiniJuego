package controller;

import model.Juego;
import model.Jugador;
import model.ObjetoCaida;
import view.VentanaJuego;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

/**
 * Controlador del juego
 */
public class JuegoController {
    private Juego juego;
    private VentanaJuego vista;
    
    public JuegoController(VentanaJuego vista) {
        this.vista = vista;
        this.juego = new Juego(800, 500);
    }
    
    public void iniciarJuego() {
        juego.iniciar();
    }

    public void pausarJuego() {
        juego.pausar();
    }
    
    public void actualizarJuego() {
        juego.actualizar();
        vista.actualizarVista();
    }
    
    public void moverJugadorIzquierda() {
        juego.moverJugadorIzquierda();
    }
    
    public void moverJugadorDerecha() {
        juego.moverJugadorDerecha();
    }
    
    public void dibujarJugador(Graphics2D g2d, int anchoPanel, int altoPanel) {
        Jugador jugador = juego.getJugador();
        
        // Calcular factores de escala
        double escalaX = (double) anchoPanel / juego.getAnchoPantalla();
        double escalaY = (double) altoPanel / juego.getAltoPantalla();
        
        // Escalar coordenadas y tamaños
        int x = (int)(jugador.getX() * escalaX);
        int y = (int)(jugador.getY() * escalaY);
        int ancho = (int)(jugador.getAncho() * escalaX);
        int alto = (int)(jugador.getAlto() * escalaY);
        
        // Dibujar canasta
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(x, y, ancho, alto);
    
        //borde de la canasta
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new java.awt.BasicStroke((float)(2 * Math.min(escalaX, escalaY))));
        g2d.drawRect(x, y, ancho, alto);
    
        //asa de la canasta
        int[] xPoints = {
            (int)((jugador.getX() + 5) * escalaX), 
            (int)((jugador.getX() + 15) * escalaX), 
            (int)((jugador.getX() + 45) * escalaX), 
            (int)((jugador.getX() + 55) * escalaX)
        };
        int[] yPoints = {
            (int)(jugador.getY() * escalaY), 
            (int)((jugador.getY() - 10) * escalaY), 
            (int)((jugador.getY() - 10) * escalaY), 
            (int)(jugador.getY() * escalaY)
        };
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 4);
    
    }
    
    //dibujar objetos que caen
    public void dibujarObjetos(Graphics2D g2d, int anchoPanel, int altoPanel) {
        List<ObjetoCaida> objetos = juego.getObjetos();
        
        // Calcular factores de escala
        double escalaX = (double) anchoPanel / juego.getAnchoPantalla();
        double escalaY = (double) altoPanel / juego.getAltoPantalla();
        
        for (ObjetoCaida objeto : objetos) {
            if (objeto.isActivo()) {
                // Escalar coordenadas y tamaños
                int x = (int)(objeto.getX() * escalaX);
                int y = (int)(objeto.getY() * escalaY);
                int ancho = (int)(objeto.getAncho() * escalaX);
                int alto = (int)(objeto.getAlto() * escalaY);
                
                if (objeto.getTipo() == ObjetoCaida.TipoObjeto.BUENO) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(x, y, ancho, alto);
                    g2d.setColor(new Color(0, 100, 0)); 
                    g2d.setStroke(new java.awt.BasicStroke((float)(2 * Math.min(escalaX, escalaY))));
                    g2d.drawOval(x, y, ancho, alto);
                } else {
                    g2d.setColor(Color.RED);
                    g2d.fillOval(x, y, ancho, alto);
                    g2d.setColor(new Color(139, 0, 0)); 
                    g2d.setStroke(new java.awt.BasicStroke((float)(2 * Math.min(escalaX, escalaY))));
                    g2d.drawOval(x, y, ancho, alto);
                }
            }   
        }
    }

    // Getters para la vista
    public boolean isEnJuego() {
        return juego.isEnJuego();
    }
    
    public boolean isPausado() {
        return juego.isPausado();
    }
    
    public int getVidas() {
        return juego.getVidas();
    }
    
    public int getPuntuacion() {
        return juego.getPuntuacion();
    }
}