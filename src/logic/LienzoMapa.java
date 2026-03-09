package logic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LienzoMapa extends JPanel {
    private List<Node> ruta;
    private Image imagenMapa;
    private Node nodoSeleccionadoInicio;
    private Node nodoSeleccionadoFin;

    public LienzoMapa() {
        try {
            java.net.URL imgURL = getClass().getResource("/logic/mapa.jpg.jpeg");
            if (imgURL != null) {
                imagenMapa = new ImageIcon(imgURL).getImage();
                // Forzamos al panel a tomar el tamaño de la imagen original
                this.setPreferredSize(new Dimension(imagenMapa.getWidth(null), imagenMapa.getHeight(null)));
            }
        } catch (Exception e) {
            System.err.println("Error: No se encontró la imagen en /logic/");
        }
    }

    public void setRuta(List<Node> ruta, Node inicio, Node fin) {
        this.ruta = ruta;
        this.nodoSeleccionadoInicio = inicio;
        this.nodoSeleccionadoFin = fin;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenMapa != null) g.drawImage(imagenMapa, 0, 0, this);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (nodoSeleccionadoInicio != null) {
            g2.setColor(Color.BLUE);
            g2.fillOval(nodoSeleccionadoInicio.getX() - 8, nodoSeleccionadoInicio.getY() - 8, 16, 16);
        }

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.RED);
        if (ruta != null && ruta.size() > 1) {
            for (int i = 0; i < ruta.size() - 1; i++) {
                Node n1 = ruta.get(i);
                Node n2 = ruta.get(i + 1);
                // Si la distancia es muy grande (salto de piso), dibujamos un círculo en vez de línea
                if (Math.abs(n1.getX() - n2.getX()) < 400) {
                    g2.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
                } else {
                    g2.drawOval(n1.getX() - 10, n1.getY() - 10, 20, 20);
                }
            }
        }

        if (nodoSeleccionadoFin != null) {
            g2.setColor(new Color(0, 150, 0));
            g2.fillOval(nodoSeleccionadoFin.getX() - 8, nodoSeleccionadoFin.getY() - 8, 16, 16);
        }
    }
}