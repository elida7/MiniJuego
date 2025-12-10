import view.VentanaJuego;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            VentanaJuego ventana = new VentanaJuego();
            ventana.setVisible(true);
        });
    }
}