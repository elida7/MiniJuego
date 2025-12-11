import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import view.VentanaJuego;

public class Main {
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            System.err.println("Error al configurar Look and Feel: " + e.getMessage());
        }
        
        
        java.awt.EventQueue.invokeLater(() -> {
            VentanaJuego ventana = new VentanaJuego();
            ventana.setVisible(true);
        });
    }
}