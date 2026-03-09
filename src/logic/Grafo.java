package logic;
import java.util.*;

public class Grafo {
    private List<Node> nodos = new ArrayList<>();

    public Node agregarNodo(String nombre, int x, int y) {
        Node nuevo = new Node(nombre, x, y);
        nodos.add(nuevo);
        return nuevo;
    }

    public void conectar(Node a, Node b, boolean esEscalera) {
        double dist = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
        a.agregarArista(new Arista(b, dist, esEscalera));
        b.agregarArista(new Arista(a, dist, esEscalera));
    }

    public Node buscarNodoCercano(int x, int y) {
        for (Node n : nodos) {
            double d = Math.sqrt(Math.pow(n.getX() - x, 2) + Math.pow(n.getY() - y, 2));
            if (d < 30) return n; // Si el clic está cerca del punto
        }
        return null;
    }

    public List<Node> getNodos() { return nodos; }
}