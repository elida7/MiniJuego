package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del juego
 */
public class VentanaJuego extends JFrame implements ActionListener {
    private JButton botonIniciar;
    private JButton botonPausar;
    private JLabel labelPuntuacion;
    private JLabel labelVidas;
    
    public VentanaJuego() {
        super("MiniJuego - Atrapa los Objetos");
        
        configurarVentana();
        crearComponentes();
        organizarLayout();
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    
    private void crearComponentes() {
        // Panel superior con información
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
        
        // Panel central de juego
        JPanel panelJuego = new JPanel();
        panelJuego.setPreferredSize(new Dimension(800, 500));
        panelJuego.setBackground(new Color(135, 206, 250)); // Azul cielo
        panelJuego.setBorder(BorderFactory.createTitledBorder("Área de Juego"));
        
        add(panelInfo, BorderLayout.SOUTH);
        add(panelJuego, BorderLayout.CENTER);
        
        // Panel de instrucciones
        JPanel panelInstrucciones = new JPanel();
        JLabel instrucciones = new JLabel(
            "Usa las flechas ← → para mover. Atrapa objetos verdes, evita rojos."
        );
        panelInstrucciones.add(instrucciones);
        add(panelInstrucciones, BorderLayout.NORTH);
    }
    
    private void organizarLayout() {
        // Layout ya configurado
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            botonIniciar.setEnabled(false);
            botonPausar.setEnabled(true);
        } else if (e.getSource() == botonPausar) {
            // Lógica de pausa pendiente
        }
    }
}