package view;

import controller.JuegoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Ventana principal del juego con controles
 */
public class VentanaJuego extends JFrame implements ActionListener, KeyListener {
    private JuegoController controlador;
    private PanelJuego panelJuego;
    private JButton botonIniciar;
    private JButton botonPausar;
    private JLabel labelPuntuacion;
    private JLabel labelVidas;
    private Timer timer;
    
    public VentanaJuego() {
        super("MiniJuego - Atrapa los Objetos");
        controlador = new JuegoController();
        
        configurarVentana();
        crearComponentes();
        organizarLayout();

        timer = new Timer(16, this); // 60fps
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        addKeyListener(this);
        setFocusable(true);
    }
    
    private void crearComponentes() {
        //panel del juego personalizado
        panelJuego = new PanelJuego(controlador);
        panelJuego.setPreferredSize(new Dimension(800, 500));

        // Panel de información
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información"));
        
        labelPuntuacion = new JLabel("Puntuación: 0");
        labelPuntuacion.setFont(new Font("Arial", Font.BOLD, 16));
        
        labelVidas = new JLabel("Vidas: 3");
        labelVidas.setFont(new Font("Arial", Font.BOLD, 16));
        labelVidas.setForeground(Color.RED);
        
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.addActionListener(this);
        
        botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(this);
        botonPausar.setEnabled(false);
        
        panelInfo.add(labelPuntuacion);
        panelInfo.add(labelVidas);
        panelInfo.add(botonIniciar);
        panelInfo.add(botonPausar);
        
        add(panelJuego, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
        
        // Panel de instrucciones
        JPanel panelInstrucciones = new JPanel();
        JLabel instrucciones = new JLabel(
            "Usa las flechas ← → para mover. Atrapa objetos verdes, evita rojos."
        );
        panelInstrucciones.add(instrucciones);
        add(panelInstrucciones, BorderLayout.NORTH);
    }
    
    private void organizarLayout() {
        // Layout ya configurado en crear componentes
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            controller.actualizarJuego();
            panelJuego.repaint();
        } else if (e.getSource() == botonPausar) {
            iniciarJuego();
        } else if (e.getSource() == botonPausar) {
            pausarJuego();
        }
    }

    private void iniciarJuego() {
        controller.iniciarJuego();
        botonIniciar.setEnabled(false);
        botonPausar.setEnabled(true);
        timer.start();
        requestFocus();
    }

    private void pausarJuego() {
        if (timer.isRunning()) {
            timer.stop();
            botonPausar.setText("Reanudar");
        } else {
            timer.start();
            botonPausar.setText("Pausar");
        }
        requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            controller.moverJugadorIzquierda();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            controller.moverJugadorDerecha();
        }
        panelJuego.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    // Clase interna para el panel de juego
    private class PanelJuego extends JPanel {
        private JuegoController controller;
        
        public PanelJuego(JuegoController controller) {
            this.controller = controller;
            setBackground(new Color(135, 206, 250));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            controller.dibujarJugador(g2d);
            controller.dibujarObjetos(g2d);
        }
    }
}