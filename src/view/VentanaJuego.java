package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import controller.JuegoController;

/**
 * Ventana principal del juego 
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
        controlador = new JuegoController(this);
        
        configurarVentana();
        crearComponentes();
        organizarLayout();
        
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);

        timer = new Timer(16, this); // 60fps
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        addKeyListener(this);
        setFocusable(true);
        setMinimumSize(new Dimension(800, 600));
    }
    
    private void crearComponentes() {
        //panel del juego personalizado
        panelJuego = new PanelJuego(controlador);
        panelJuego.setPreferredSize(new Dimension(800, 500));
        panelJuego.setMinimumSize(new Dimension(800, 500));
        panelJuego.setBackground(new Color(135, 206, 250));

        // Panel de información
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información"));
        panelInfo.setBackground(new Color(240, 240, 240));

        labelPuntuacion = new JLabel("Puntuación: 0");
        labelPuntuacion.setFont(new Font("Arial", Font.BOLD, 16));
        
        labelVidas = new JLabel("Vidas: 3");
        labelVidas.setFont(new Font("Arial", Font.BOLD, 16));
        labelVidas.setForeground(Color.RED);
        
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.addActionListener(this);
        botonIniciar.setPreferredSize(new Dimension(120, 35));
        
        botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(this);
        botonPausar.setPreferredSize(new Dimension(100, 35));
        botonPausar.setEnabled(false);
        
        panelInfo.add(labelPuntuacion);
        panelInfo.add(labelVidas);
        panelInfo.add(botonIniciar);
        panelInfo.add(botonPausar);
        
        add(panelJuego, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
        
        // Panel de instrucciones
        JPanel panelInstrucciones = new JPanel();
        panelInstrucciones.setBorder(BorderFactory.createTitledBorder("Instrucciones"));
        panelInstrucciones.setBackground(new Color(240, 240, 240));
        JLabel instrucciones = new JLabel(
            "<html><center>" +
            "Usa las flechas ← → para mover la canasta<br/>" +
            "Atrapa los objetos verdes (puntos) y evita los rojos (pierdes vida)<br/>" +
            "¡Sobrevive el mayor tiempo posible!</center></html>"
        );
        instrucciones.setFont(new Font("Arial", Font.PLAIN, 12));
        panelInstrucciones.add(instrucciones);
        add(panelInstrucciones, BorderLayout.NORTH);
    }
    
    private void organizarLayout() {
        // Layout ya configurado en crear componentes
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            controlador.actualizarJuego();
        } else if (e.getSource() == botonIniciar) {
            iniciarJuego();
        } else if (e.getSource() == botonPausar) {
            pausarJuego();
        }
    }

    private void iniciarJuego() {
        controlador.iniciarJuego();
        botonIniciar.setEnabled(false);
        botonPausar.setEnabled(true);
        botonPausar.setText("Pausar");
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

    public void actualizarVista() {
        labelPuntuacion.setText("Puntuación: " + controlador.getPuntuacion());
        labelVidas.setText("Vidas: " + controlador.getVidas());
        
        if (!controlador.isEnJuego() && controlador.getVidas() <= 0) {
            timer.stop();
            botonIniciar.setEnabled(true);
            botonPausar.setEnabled(false);
            mostrarGameOver();
        }
        
        panelJuego.repaint();
    }

    private void mostrarGameOver() {
        String mensaje = "¡Juego Terminado!\n\n" +
                        "Puntuación Final: " + controlador.getPuntuacion() + "\n\n" +
                        "¿Deseas jugar de nuevo?";
        
        int opcion = JOptionPane.showConfirmDialog(
            this,
            mensaje,
            "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            iniciarJuego();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            controlador.moverJugadorIzquierda();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            controlador.moverJugadorDerecha();
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
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int anchoPanel = getWidth();
            int altoPanel = getHeight();
            
            controller.dibujarJugador(g2d, anchoPanel, altoPanel);
            controller.dibujarObjetos(g2d, anchoPanel, altoPanel);

            // Dibujar mensaje de pausa
            if (controller.isPausado()) {
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 48));
                String texto = "PAUSA";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(texto)) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2d.drawString(texto, x, y);
            }
        }
    }
}