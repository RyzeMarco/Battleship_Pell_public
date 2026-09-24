package Battleship;

public class Cell {
    private int x;
    private int y;
    private char stato; // 'A' (Acqua), 'M' (Mancato), 'N' (Nave), 'C' (Colpita)
    private Ship ship;  // Riferimento alla nave associata

    public Cell(int x, int y, char stato) {
        this.x = x;
        this.y = y;
        this.stato = stato;
    }

    // Getter e Setter
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public char getStato() { return stato; }
    public void setStato(char stato) { this.stato = stato; }

    public Ship getShip() { return ship; }
    public void setShip(Ship ship) { this.ship = ship; }
}