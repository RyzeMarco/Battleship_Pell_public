package Battleship;

import Battleship.Board;
import Battleship.CsvReaderService;
import Battleship.GameService;

public class Main {
    public static void main(String[] args) {
        CsvReaderService readerService = new CsvReaderService();
        GameService gameService = new GameService();

        try {
            String csvPath = "src/Battleship/BattagliaNavale_Player1_Example.csv";

            Board board = readerService.caricaCampoDaCsv(csvPath);

            System.out.println("--- Griglia Iniziale ---");
            board.stampaGriglia();

            System.out.println("\nEsecuzione di un colpo di test in una posizione...");
            gameService.colpisciCella(board, 3, 6);

            System.out.println("\n--- Griglia Aggiornata ---");
            board.stampaGriglia();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}