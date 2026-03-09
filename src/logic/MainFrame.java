package logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {

    private Grafo grafo;
    private LienzoMapa lienzo;
    private JCheckBox chkSilla;
    private Node nodoOrigen = null;
    private Node nodoDestino = null;

    public MainFrame() {

        setTitle("AccessWay UNEG - Red Total - 30.897.634");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1350, 950);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        prepararDatos();

        JPanel pnlControl = new JPanel();

        chkSilla = new JCheckBox("♿ Evitar Escaleras");
        JButton btnLimpiar = new JButton("Limpiar Ruta");

        //Acción de botón Limpiar
        btnLimpiar.addActionListener(e -> {
            nodoOrigen = null;
            nodoDestino = null;
            lienzo.setRuta(null, null, null);
        });

        pnlControl.add(chkSilla);
        pnlControl.add(btnLimpiar);

        lienzo = new LienzoMapa();

        lienzo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                Node cercano = null;
                double minDist = 90.0;

                for (Node n : grafo.getNodos()) {

                    double d = Math.sqrt(
                            Math.pow(e.getX() - n.getX(), 2)
                            + Math.pow(e.getY() - n.getY(), 2)
                    );

                    if (d < minDist) {
                        minDist = d;
                        cercano = n;
                    }
                }

                if (cercano != null) {

                    if (nodoOrigen == null) {

                        nodoOrigen = cercano;
                        lienzo.setRuta(null, nodoOrigen, null);

                    } else if (nodoDestino == null) {

                        nodoDestino = cercano;

                        DijkstraEngine engine = new DijkstraEngine(grafo);

                        List<Node> camino = engine.calcularRuta(
                                nodoOrigen,
                                nodoDestino,
                                chkSilla.isSelected()
                        );

                        lienzo.setRuta(camino, nodoOrigen, nodoDestino);

                    } else {

                        nodoOrigen = cercano;
                        nodoDestino = null;

                        lienzo.setRuta(null, nodoOrigen, null);
                    }
                }
            }
        });

        add(new JScrollPane(lienzo), BorderLayout.CENTER);
        add(pnlControl, BorderLayout.NORTH);
    }

    private void prepararDatos() {

        grafo = new Grafo();

        // ==========================================
        // PLANTA BAJA (PB)
        // ==========================================
        Node p1 = grafo.agregarNodo("P1", 205, 200);
        conectarLocal("T22", 229, 198, p1);
        conectarLocal("TA", 208, 196, p1);
        conectarLocal("T01", 182, 213, p1);
        conectarLocal("T02", 184, 255, p1);
        conectarLocal("T03", 184, 278, p1);
        conectarLocal("T04", 185, 316, p1);
        conectarLocal("BP-P1", 242, 134, p1);

        Node p1m = grafo.agregarNodo("P1-Medio", 306, 222);
        conectarLocal("T21", 273, 198, p1m);
        conectarLocal("T20", 292, 198, p1m);
        conectarLocal("T19", 324, 198, p1m);
        conectarLocal("BP-P1m-A", 275, 134, p1m);
        conectarLocal("BP-P1m-B", 292, 134, p1m);
        conectarLocal("BP-P1m-C", 348, 108, p1m);
        conectarLocal("BP-P1m-D", 350, 132, p1m);

        Node p1d = grafo.agregarNodo("P1-Der", 425, 218);
        conectarLocal("T18", 376, 198, p1d);
        conectarLocal("TA2", 401, 198, p1d);
        conectarLocal("T17", 448, 214, p1d);
        conectarLocal("T16", 444, 254, p1d);
        conectarLocal("T15", 445, 275, p1d);

        Node p2 = grafo.agregarNodo("P2", 428, 332);
        conectarLocal("BP-P2-A", 445, 311, p2);
        conectarLocal("BP-P2-B", 480, 332, p2);
        conectarLocal("BP-P2-C", 504, 332, p2);
        conectarLocal("BP-P2-D", 447, 347, p2);

        Node p3 = grafo.agregarNodo("P3", 359, 429);

        Node asc1_pb = grafo.agregarNodo("Ascensor1-PB", 407, 400);
        Node asc2_pb = grafo.agregarNodo("Ascensor2-PB", 408, 455);

        grafo.conectar(asc1_pb, p3, false);
        grafo.conectar(asc2_pb, p3, false);

        Node p5 = grafo.agregarNodo("P5", 430, 619);

        conectarLocal("BP-P5-A", 445, 574, p5);
        conectarLocal("BP-P5-B", 478, 541, p5);
        conectarLocal("BP-P5-C", 480, 521, p5);
        conectarLocal("TA4", 445, 607, p5);

        Node p6 = grafo.agregarNodo("P6", 334, 624);

        conectarLocal("T14", 373, 653, p6);
        conectarLocal("T13", 304, 654, p6);
        conectarLocal("T12", 275, 653, p6);
        conectarLocal("PE3-Medio", 337, 731, p6);

        Node p7 = grafo.agregarNodo("P7", 199, 621);

        conectarLocal("T11", 226, 652, p7);
        conectarLocal("TA3-A", 197, 637, p7);
        conectarLocal("TA3-B", 181, 606, p7);
        conectarLocal("T10", 181, 578, p7);
        conectarLocal("T09", 183, 537, p7);

        Node p8 = grafo.agregarNodo("P8", 200, 424);

        conectarLocal("T08", 182, 514, p8);
        conectarLocal("T07", 180, 471, p8);
        conectarLocal("T06", 182, 380, p8);
        conectarLocal("T05", 185, 337, p8);

        Node esc1_pb = grafo.agregarNodo("Escalera-1", 134, 403);
        Node esc2_pb = grafo.agregarNodo("Escalera-2", 135, 445);

        grafo.conectar(esc1_pb, p8, false);
        grafo.conectar(esc2_pb, p8, false);

        grafo.conectar(p1, p1m, false);
        grafo.conectar(p1m, p1d, false);
        grafo.conectar(p1d, p2, false);
        grafo.conectar(p2, p3, false);
        grafo.conectar(p3, p5, false);
        grafo.conectar(p5, p6, false);
        grafo.conectar(p6, p7, false);
        grafo.conectar(p7, p8, false);
        grafo.conectar(p8, p1, false);

        // ==========================================
        // PISO 1
        // ==========================================
        Node p1p1 = grafo.agregarNodo("P1p1", 647, 217);
        Node p2p1 = grafo.agregarNodo("P2p1", 908, 218);
        Node p3p1 = grafo.agregarNodo("P3p1", 900, 425);
        Node p4p1 = grafo.agregarNodo("P4p1", 868, 639);
        Node p5p1 = grafo.agregarNodo("P5p1", 639, 629);
        Node p6p1 = grafo.agregarNodo("P6p1", 642, 424);

        grafo.conectar(p1p1, p2p1, false);
        grafo.conectar(p2p1, p3p1, false);
        grafo.conectar(p3p1, p4p1, false);
        grafo.conectar(p4p1, p5p1, false);
        grafo.conectar(p5p1, p6p1, false);
        grafo.conectar(p6p1, p1p1, false);

        // CORRECCIÓN 2: Se volvieron a añadir los locales del Piso 1
        conectarLocal("T23", 627, 213, p1p1);
        conectarLocal("T42", 642, 198, p1p1);
        conectarLocal("T41", 662, 197, p1p1);
        conectarLocal("T40", 693, 200, p1p1);
        conectarLocal("T39", 747, 200, p1p1);

        conectarLocal("Cineplaza1", 890, 196, p2p1);
        conectarLocal("T36", 842, 197, p2p1);
        conectarLocal("T37", 810, 198, p2p1);
        conectarLocal("T38", 768, 199, p2p1);

        Node asc1_p1 = grafo.agregarNodo("Ascensor1-P1", 868, 399);
        Node asc2_p1 = grafo.agregarNodo("Ascensor2-P1", 869, 457);

        grafo.conectar(asc1_p1, p3p1, false);
        grafo.conectar(asc2_p1, p3p1, false);
        conectarLocal("Cineplaza2", 893, 606, p3p1);

        conectarLocal("T34", 809, 654, p4p1);
        conectarLocal("T33", 749, 652, p4p1);
        conectarLocal("T32-A", 695, 654, p5p1);
        conectarLocal("T31-A", 674, 655, p5p1);
        conectarLocal("T32-B", 641, 654, p5p1);
        conectarLocal("T31-B", 624, 600, p6p1);
        conectarLocal("T30", 628, 539, p6p1);
        conectarLocal("T29", 627, 516, p6p1);
        conectarLocal("T28", 628, 470, p6p1);
        conectarLocal("T27", 625, 378, p6p1);
        conectarLocal("T26", 627, 316, p6p1);

        Node esc2_p1 = grafo.agregarNodo("Escalera2P1", 583, 446);
        grafo.conectar(esc2_p1, p6p1, false);

        // ==========================================
        // CONEXIONES ENTRE PISOS
        // ==========================================
        grafo.conectar(asc1_pb, asc1_p1, false);
        grafo.conectar(asc2_pb, asc2_p1, false);

        // Escaleras (SÍ son escaleras, las sillas de ruedas NO las usan)
        grafo.conectar(esc1_pb, esc2_p1, true);
        grafo.conectar(esc2_pb, esc2_p1, true);
    }

    private void conectarLocal(String nombre, int x, int y, Node pasillo) {
        Node local = grafo.agregarNodo(nombre, x, y);
        grafo.conectar(local, pasillo, false); // Los locales no son escaleras
    }

    // MÉTODO MAIN (INICIO DEL PROGRAMA)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

}