package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase Juego - Maneja la lógica principal
 */
public class Juego {
    private Jugador jugador;
    private List<ObjetoCaida> objetos;
    private int anchoPantalla;
    private int altoPantalla;
    private boolean enJuego;
    private boolean pausado;
    private int vidas;
    private int puntuacion;
    private Random random;
    private int contadorFrames;
    private int frecuenciaObjetos;
    
    public Juego(int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.random = new Random();
        this.objetos = new ArrayList<>();
        this.vidas = 3;
        this.puntuacion = 0;
        this.frecuenciaObjetos = 60;
        this.contadorFrames = 0;
        
        // Crear jugador en posición inicial
        int anchoJugador = 60;
        int altoJugador = 30;
        int xInicial = (anchoPantalla - anchoJugador) / 2;
        int yInicial = altoPantalla - altoJugador - 20;
        
        this.jugador = new Jugador(xInicial, yInicial, anchoJugador, altoJugador);
        this.enJuego = false;
        this.pausado = false;
    }
    
    public void iniciar() {
        enJuego = true;
        pausado = false;
        objetos.clear();
        vidas = 3;
        puntuacion = 0;
        contadorFrames = 0;
    }

    public void pausar() {
        if (enJuego) {
            pausado = !pausado;
        }
    }
    
    public void actualizar() {
        if (!enJuego || pausado) return;
        
        contadorFrames++;
        
        //Generar nuevos objetos
        if (contadorFrames % frecuenciaObjetos == 0) {
            generarObjetos();
        }
        
        // Mover y verificar objetos
        for (ObjetoCaida objeto : new ArrayList<>(objetos)) {
            objeto.caer();

            //verificar colisiones con jugador
            if (jugador.colisiona(objeto)) {
                objetos.remove(objeto);
                if (objeto.getTipo() == ObjetoCaida.TipoObjeto.BUENO) {
                    puntuacion += 10;
                } else {
                    vidas--;
                    if (vidas <= 0) {
                        enJuego = false;
                    }
                }
            }

            //eliminar si sale de la pantalla
            if (objeto.getY() > altoPantalla) {
                objetos.remove(objeto);
            }
        }
    }

    private void generarObjeto() {
        int x = random.nextInt(anchoPantalla - 30);
        ObjetoCaida.TipoObjeto tipo = random.nextBoolean() ? 
            ObjetoCaida.TipoObjeto.BUENO : ObjetoCaida.TipoObjeto.MALO;
        
        objetos.add(new ObjetoCaida(x, 0, 30, 30, tipo));
    }
    
    public void moverJugadorDerecha() {
        if (enJuego && !pausado) {
        jugador.moverDerecha(anchoPantalla);
    }
    
    public void moverJugadorIzquierda() {
        if (enJuego && !pausado) {
            jugador.moverIzquierda();
        }
    }
    
    // Getters
    public Jugador getJugador() { return jugador; }
    public List<ObjetoCaida> getObjetos() { return objetos; }
    public boolean isEnJuego() { return enJuego; }
    public boolean isPausado() { return pausado; }
    public int getVidas() { return vidas; }
    public int getPuntuacion() { return puntuacion; }
}