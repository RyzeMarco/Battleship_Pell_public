package Battleship;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderService {

    public Board caricaCampoDaCsv(String filePath) throws IOException {
        List<String[]> linee = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valori = linea.split(",");
                linee.add(valori);
            }
        }

        int righe = linee.size();
        int colonne = linee.get(0).length;
        Cell[][] grid = new Cell[righe][colonne];

        // 1. Creazione della matrice di celle
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                char stato = linee.get(i)[j].trim().charAt(0);
                // Normalizziamo eventuali lettere di test (es. S o B) a 'N' per coerenza
                if (stato != 'A' && stato != 'M' && stato != 'C') {
                    stato = 'N';
                }
                grid[i][j] = new Cell(i, j, stato);
            }
        }

        // 2. Rilevamento e associazione delle navi
        List<Ship> ships = rilevaNavi(grid);
        return new Board(grid, ships);
    }

    private List<Ship> rilevaNavi(Cell[][] grid) {
        List<Ship> ships = new ArrayList<>();
        int righe = grid.length;
        int colonne = grid[0].length;
        boolean[][] visitati = new boolean[righe][colonne];
        int shipId = 1;

        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                if (grid[i][j].getStato() == 'N' && !visitati[i][j]) {
                    List<Cell> naveCells = new ArrayList<>();
                    // Esplorazione semplice contigua (orizzontale o verticale)
                    esploraNave(grid, i, j, visitati, naveCells);

                    Ship ship = new Ship(shipId++, naveCells.size(), naveCells);
                    for (Cell c : naveCells) {
                        c.setShip(ship);
                    }
                    ships.add(ship);
                }
            }
        }
        return ships;
    }

    private void esploraNave(Cell[][] grid, int r, int c, boolean[][] visitati, List<Cell> naveCells) {
        int righe = grid.length;
        int colonne = grid[0].length;

        if (r < 0 || r >= righe || c < 0 || c >= colonne || visitati[r][c] || grid[r][c].getStato() != 'N') {
            return;
        }

        visitati[r][c] = true;
        naveCells.add(grid[r][c]);

        // Controlla adiacenze (destra, sinistra, basso, alto)
        esploraNave(grid, r + 1, c, visitati, naveCells);
        esploraNave(grid, r - 1, c, visitati, naveCells);
        esploraNave(grid, r, c + 1, visitati, naveCells);
        esploraNave(grid, r, c - 1, visitati, naveCells);
    }
}