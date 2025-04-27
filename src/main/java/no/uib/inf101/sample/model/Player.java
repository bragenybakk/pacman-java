package no.uib.inf101.sample.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.ImageIcon;

import no.uib.inf101.sample.GameBoard;

/**
 * Klasse som representerer spilleren (PacMan) i spillet
 * Håndterer retning, bevegelse og øker poengsum
 */
public class Player {
    private Block pos;
    private Image image;
    private Direction dir;
    private int x, y;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;
    private GameBoard gameBoard;

    public Player(Block pos, Image image, Direction dir, GameBoard gameBoard) {
        this.dir = dir;
        this.pos = pos;
        this.image = image;
        this.gameBoard = gameBoard;

        pacmanUpImage = new ImageIcon(getClass().getResource("/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/pacmanRight.png")).getImage();
    }

    /**
     * Endrer player sin Block/posisjon
     * 
     * @param pos ny posisjon
     */
    public void setPos(Block pos) {
        this.pos = pos;
    }

    /**
     * 
     * @return Player klassen sin Block/posisjon
     */
    public Block getPos() {
        return pos;
    }

    /**
     * Tegner Player
     * 
     * @param g        Graphics
     * @param tileSize satt størrelse på cellene
     */
    public void drawPlayer(Graphics g, int tileSize) {
        g.drawImage(image, pos.getx() * tileSize, pos.gety() * tileSize, tileSize, tileSize, null);
    }

    /**
     * Endrer Player sin retning og samtidig endrer Player sitt bilde ut ifra
     * retningen
     * 
     * @param newDir Ny retning
     */
    public void setDirection(Direction newDir) {
        this.dir = newDir;
        if (newDir == Direction.RIGHT)
            image = pacmanRightImage;
        if (newDir == Direction.LEFT)
            image = pacmanLeftImage;
        if (newDir == Direction.DOWN)
            image = pacmanDownImage;
        if (newDir == Direction.UP)
            image = pacmanUpImage;
    }

    /**
     * Flytter på Player sin posisjon i kartet, og samtidig øker poengsummen om
     * player treffer "mat"
     * 
     * @param hashMap   hashmap som kart
     * @param direction Nåværende retning
     */
    public void move(HashMap<Point, Block> hashMap, Direction direction) {

        x = pos.getx() + direction.getDx();
        y = pos.gety() + direction.getDy();

        Block next = hashMap.get(new Point(x, y));
        if (next != null && !next.getType().equals("wall")) {
            pos = next;
        }

        if (isFood(next)) {
            next.setType("empty");
            gameBoard.addScore(100);
        }
    }

    /**
     * 
     * @return Player sin retning
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * Hjelpemetode som sjekker om Player treffer "mat"
     * 
     * @param block in posisjon i kartet
     * @return boolsk verdi
     */
    public boolean isFood(Block block) {
        return block.getType().equals("food");
    }

    /**
     * Sjekker om Player er på samme Block som et spøkelse
     * 
     * @param ghost Et spøkelse objekt
     * @return Boolsk verdi som sier om det er sant eller ikke
     */
    public boolean isOnSameBlockAsGhost(Ghost ghost) {
        return pos.getx() == ghost.getPos().getx() && pos.gety() == ghost.getPos().gety();
    }

}
