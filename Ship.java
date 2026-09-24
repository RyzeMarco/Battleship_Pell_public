package Battleship;

import java.util.List;

public class Ship {
    private int id;
    private int lunghezza;
    private List<Cell> cells;

    public Ship(int id, int lunghezza, List<Cell> cells) {
        this.id = id;
        this.lunghezza = lunghezza;
        this.cells = cells;
    }

    public boolean isSunk() {
        for (Cell cell : cells) {
            if (cell.getStato() != 'C') {
                return false; // Se almeno una cella non è 'C', la nave non è affondata
            }
        }
        return true;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLunghezza() { return lunghezza; }
    public void setLunghezza(int lunghezza) { this.lunghezza = lunghezza; }

    public List<Cell> getCells() { return cells; }
    public void setCells(List<Cell> cells) { this.cells = cells; }
}