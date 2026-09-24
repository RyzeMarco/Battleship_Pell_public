package Battleship;

import java.util.List;

public class Board {
    private Cell[][] grid;
    private List<Ship> ships;

    public Board(Cell[][] grid, List<Ship> ships) {
        this.grid = grid;
        this.ships = ships;
    }

    public Cell[][] getGrid() { return grid; }
    public void setGrid(Cell[][] grid) { this.grid = grid; }

    public List<Ship> getShips() { return ships; }
    public void setShips(List<Ship> ships) { this.ships = ships; }

    public void stampaGriglia() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getStato() + " ");
            }
            System.out.println();
        }
    }
}