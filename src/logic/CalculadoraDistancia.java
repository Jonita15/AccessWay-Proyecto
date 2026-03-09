package logic;

/**
 * Esta clase es la que se encarga de medir la distancia real entre dos puntos
 * usando sus coordenadas X y Y del mapa.
 */
public class CalculadoraDistancia {

    
     //Calcula la distancia en línea recta entre el Nodo A y el Nodo B
    public static double calcular(Node a, Node b) {
        // Usamos la fórmula: raíz cuadrada de ( (x2-x1)² + (y2-y1)² )
        double diferenciaX = b.getX() - a.getX();
        double diferenciaY = b.getY() - a.getY();
        
        return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
    }
}