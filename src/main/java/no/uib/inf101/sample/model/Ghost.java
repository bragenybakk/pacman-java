package no.uib.inf101.sample.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Klasse som representerer et spøkelse i spillet
 * Tegner og flytter de
 */
public class Ghost {
    private Block pos;
    private Image image;

    /**
     * Konstruktør som tar inn en Block og et bilde
     * 
     * @param pos   Block/posisjon
     * @param image bilde til spøkelset
     */
    public Ghost(Block pos, Image image) {
        this.pos = pos;
        this.image = image;
    }

    /**
     * @return Ghost sin Block/posisjon
     */
    public Block getPos() {
        return pos;
    }

    /**
     * Metode som tegner spøkelset på brettet, bruker objektet sitt gitte bilde og
     * tegner det på brettet ut ifra posisjonen sin
     * 
     * @param g        Graphics
     * @param tileSize Satt størrelse på cellene
     */
    public void drawGhost(Graphics g, int tileSize) {
        g.drawImage(image, pos.getx() * tileSize, pos.gety() * tileSize, tileSize, tileSize, null);
    }

    /**
     * Flytter spøkelset i en tilfeldig retning, sjekker om det er en blokk i den
     * retningen og om den ikke er en vegg. Hvis det er en blokk i den retningen så
     * flytter den spøkelset til den blokken
     * @param hashMap
     */
    public void move(HashMap<Point, Block> hashMap) {
        List<Direction> directions = new ArrayList<>(
                java.util.Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));

        Collections.shuffle(directions);

        for (Direction dir : directions) {
            int nextX = pos.getx() + dir.getDx();
            int nextY = pos.gety() + dir.getDy();
            Block next = hashMap.get(new Point(nextX, nextY));

            if (next != null && !next.getType().equals("wall")) {
                pos = next;
                return;
            }
        }
    }

}