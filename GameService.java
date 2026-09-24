package Battleship;


import java.util.logging.Logger;

public class GameService {
    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());

    public void colpisciCella(Board board, int x, int y) {
        Cell[][] grid = board.getGrid();

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            LOGGER.warning("Coordinate fuori griglia: (" + x + ", " + y + ")");
            System.out.println("Coordinate non valide!");
            return;
        }

        Cell cella = grid[x][y];
        LOGGER.info("Tentativo di colpo alla cella: (" + x + ", " + y + ") con stato attuale: " + cella.getStato());

        if (cella.getStato() == 'A') {
            cella.setStato('M');
            LOGGER.info("Risultato: Mancato (M)");
            System.out.println("Acqua! Colpo Mancato (M).");
        } else if (cella.getStato() == 'M' || cella.getStato() == 'C') {
            LOGGER.info("Risultato: Cella già colpita in precedenza.");
            System.out.println("Cella già colpita in precedenza!");
        } else if (cella.getStato() == 'N') {
            cella.setStato('C');
            LOGGER.info("Risultato: Collezionato centro! Nave colpita (C)");
            System.out.println("Impatto! Nave Colpita (C).");

            // Verifica se la nave è affondata
            Ship nave = cella.getShip();
            if (nave != null && nave.isSunk()) {
                LOGGER.info("La nave ID " + nave.getId() + " di lunghezza " + nave.getLunghezza() + " è stata AFFONDATA!");
                System.out.println(">>> Affondata una nave di lunghezza " + nave.getLunghezza() + "!");
            }

            // Verifica se tutte le navi sono state affondate
            if (verificaFinePartita(board)) {
                LOGGER.info("Tutte le navi sono state affondate. PARTITA TERMINATA!");
                System.out.println(">>> Vittoria! Tutte le navi sono state affondate!");
            }
        }
    }

    public boolean verificaFinePartita(Board board) {
        for (Ship ship : board.getShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }
}