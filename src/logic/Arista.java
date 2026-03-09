package logic;

public class Arista {
    private Node destino;
    private double peso;
    private boolean esEscalera;

    public Arista(Node destino, double peso, boolean esEscalera) {
        this.destino = destino;
        this.peso = peso;
        this.esEscalera = esEscalera;
    }

    public Node getDestino() { return destino; }
    public double getPeso() { return peso; }
    
    public boolean isEsEscalera() { 
        return esEscalera; 
    }
}