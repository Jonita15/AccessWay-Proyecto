package logic;

import java.awt.*;
import javax.swing.*;

public class WelcomeFrame extends JFrame {

    private Image backgroundImage;

    public WelcomeFrame() {
        // Configuraciones básicas de la ventana
        setTitle("AccessWay - Sistema de Navegación");
        setSize(1100, 850); // Ajustado al plano
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // carga de la imagen
        try {
            // Usamos el nombre exacto 
            java.net.URL imgURL = getClass().getResource("/logic/mapa.jpg.jpeg");
            if (imgURL != null) {
                backgroundImage = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Error: No se encontró la imagen en /logic/mapa.jpg.jpeg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //FILTRO DE BELLEZA
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                
                // Filtros para que el plano se vea nítido
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                if (backgroundImage != null) {
                    g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                
                // Oscurecemos un poco el fondo para que el botón resalte
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        // 3. BOTÓN DE INICIO 
        JButton btnStart = new JButton("Iniciar Navegación");
        btnStart.setFont(new Font("Arial", Font.BOLD, 24));
        btnStart.setForeground(Color.WHITE);
        btnStart.setBackground(new Color(34, 139, 34)); // Verde bosque
        btnStart.setFocusPainted(false);
        btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnStart.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(15, 30, 15, 30)
        ));

        // Acción para pasar al mapa principal
        btnStart.addActionListener(e -> {
            this.dispose(); // Cierra la bienvenida
            EventQueue.invokeLater(() -> {
                new MainFrame().setVisible(true); // Abre el mapa
            });
        });

        // Agregar botón al centro
        mainPanel.add(btnStart);
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        // Ejecución segura para evitar que "no salga nada"
        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame().setVisible(true);
        });
    }
}