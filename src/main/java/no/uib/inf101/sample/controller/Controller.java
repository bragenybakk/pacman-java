package no.uib.inf101.sample.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.sample.GameBoard;
import no.uib.inf101.sample.model.Direction;
import no.uib.inf101.sample.model.GameState;
import no.uib.inf101.sample.model.Player;

public class Controller implements KeyListener {
    private Player player;
    private GameBoard gameBoard;

    public Controller(Player player, GameBoard gameBoard) {
        this.player = player;
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> player.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> player.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> player.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> player.setDirection(Direction.RIGHT);
        }
        if (gameBoard.getGameState() == GameState.START_SCREEN && e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameBoard.setGameState(GameState.PLAY);
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
