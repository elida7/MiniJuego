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
    
    public void dibujarJugador(Graphics2D g2d) {
        Jugador jugador = juego.getJugador();
        
        // Dibujar canasta
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto());
    
        //borde de la canasta
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.drawRect(jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto());
    
        //asa de la canasta
        int[] xPoints = {jugador.getX() + 5, jugador.getX() + 15, jugador.getX() + 45, jugador.getX() + 55};
        int[] yPoints = {jugador.getY(), jugador.getY() - 10, jugador.getY() - 10, jugador.getY()};
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 4);
    
    }
    
    //dibujar objetos que caen
    public void dibujarObjetos(Graphics2D g2d) {
        List<ObjetoCaida> objetos = juego.getObjetos();
        
        for (ObjetoCaida objeto : objetos) {
            if (objeto.isActivo()) {
                if (objeto.getTipo() == ObjetoCaida.TipoObjeto.BUENO) {
                    g2d.setColor(Color.GREEN);g2d.fillOval(objeto.getX(), objeto.getY(), objeto.getAncho(), objeto.getAlto());
                    g2d.setColor(new Color(0, 100, 0)); 
                    g2d.setStroke(new java.awt.BasicStroke(2));
                    g2d.drawOval(objeto.getX(), objeto.getY(), objeto.getAncho(), objeto.getAlto());
                } else {
                    g2d.setColor(Color.RED);
                    g2d.fillOval(objeto.getX(), objeto.getY(), objeto.getAncho(), objeto.getAlto());
                    g2d.setColor(new Color(139, 0, 0)); 
                    g2d.setStroke(new java.awt.BasicStroke(2));
                    g2d.drawOval(objeto.getX(), objeto.getY(), objeto.getAncho(), objeto.getAlto());
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