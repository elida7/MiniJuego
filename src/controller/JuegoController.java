package controller;

import model.Juego;
import model.Jugador;
import model.ObjetoCaida;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

/**
 * Controlador del juego
 */
public class JuegoController {
    private Juego juego;
    
    public JuegoController() {
        this.juego = new Juego(800, 500);
    }
    
    public void iniciarJuego() {
        juego.iniciar();
    }
    
    public void actualizarJuego() {
        juego.actualizar();
    }
    
    public void moverJugadorIzquierda() {
        juego.moverJugadorIzquierda();
    }
    
    public void moverJugadorDerecha() {
        juego.moverJugadorDerecha();
    }
    
    public void dibujarJugador(Graphics2D g2d) {
        Jugador jugador = juego.getJugador();
        
        // Dibujar canasta simple
        g2d.setColor(Color.BLUE);
        g2d.fillRect(jugador.getX(), jugador.getY(), 
                    jugador.getAncho(), jugador.getAlto());
    }
    
    public void dibujarObjetos(Graphics2D g2d) {
        List<ObjetoCaida> objetos = juego.getObjetos();
        
        for (ObjetoCaida objeto : objetos) {
            if (objeto.getTipo() == ObjetoCaida.TipoObjeto.BUENO) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.fillOval(objeto.getX(), objeto.getY(), 
                        objeto.getAncho(), objeto.getAlto());
        }
    }
}