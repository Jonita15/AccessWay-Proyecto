package logic;

import java.util.*;

public class DijkstraEngine {
    private Grafo grafo;

    public DijkstraEngine(Grafo g) {
        this.grafo = g;
    }

    //3 parámetros: origen, destino y el check de accesibilidad
    public List<Node> calcularRuta(Node origen, Node destino, boolean sillaRuedas) {
        Map<Node, Double> distancias = new HashMap<>();
        Map<Node, Node> padres = new HashMap<>();
        PriorityQueue<Node> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

        for (Node n : grafo.getNodos()) {
            distancias.put(n, Double.MAX_VALUE);
        }
        
        distancias.put(origen, 0.0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Node actual = cola.poll();
            if (actual == destino) break;

            for (Arista a : actual.getAdyacentes()) {
                // Si el usuario marcó "Evitar Escaleras" y la arista ES escalera, la ignoramos
                if (sillaRuedas && a.isEsEscalera()) {
                    continue;
                }

                double nuevaDist = distancias.get(actual) + a.getPeso();
                if (nuevaDist < distancias.get(a.getDestino())) {
                    distancias.put(a.getDestino(), nuevaDist);
                    padres.put(a.getDestino(), actual);
                    cola.add(a.getDestino());
                }
            }
        }

        List<Node> ruta = new ArrayList<>();
        Node paso = destino;
        while (paso != null) {
            ruta.add(0, paso);
            paso = padres.get(paso);
        }
        
        // Si el origen no es el primer elemento, no hay ruta posible
        if (ruta.isEmpty() || ruta.get(0) != origen) return new ArrayList<>();
        
        return ruta;
    }
}