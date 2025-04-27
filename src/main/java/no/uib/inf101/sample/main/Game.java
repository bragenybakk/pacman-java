package no.uib.inf101.sample.main;

import javax.swing.*;

import no.uib.inf101.sample.GameBoard;
import no.uib.inf101.sample.controller.Controller;

/**
 * Main klasse som starter spillet
 * Setter opp vindu og brett
 */
public class Game {
    public static void main(String[] args) throws Exception {
        int rows = 21;
        int cols = 19;
        int tileSize = 32;
        int boardHeight = tileSize * rows;
        int boardWidth = tileSize * cols;

        JFrame frame = new JFrame("PacMan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);

        GameBoard gameBoard = new GameBoard();
        frame.add(gameBoard);
        frame.pack();
        frame.setVisible(true);

        Controller controller = new Controller(gameBoard.getPlayer(), gameBoard);
        frame.addKeyListener(controller);
        frame.setFocusable(true);
        frame.requestFocusInWindow();


    }
}
