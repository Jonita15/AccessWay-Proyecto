package logic;
import java.util.*;

public class Node {
    private String nombre;
    private int x, y;
    private List<Arista> adyacentes = new ArrayList<>();

    public Node(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public void agregarArista(Arista a) { adyacentes.add(a); }
    public List<Arista> getAdyacentes() { return adyacentes; }
    public String getNombre() { return nombre; }
    public int getX() { return x; }
    public int getY() { return y; }
    @Override public String toString() { return nombre; }
}