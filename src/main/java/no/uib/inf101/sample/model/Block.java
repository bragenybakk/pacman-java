package no.uib.inf101.sample.model;

/**
 * Block klasse som representerer en enkelt posisjon på brettet
 * Håndterer posisjon og type blokk
 */
public class Block {
    private int x;
    private int y;
    private String type;

    /**
     * Konstruktør som tar inn x og y posisjon og type blokk
     * 
     * @param x    x posisjon blokk
     * @param y    y posisjon blokk
     * @param type type blokk
     */
    public Block(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * @return x posisjon blokk
     */
    public int getx() {
        return x;
    }

    /**
     * @return y posisjon blokk
     */
    public int gety() {
        return y;
    }

    /**
     * @return type blokk
     */
    public String getType() {
        return type;
    }

    /**
     * Setter ny posisjon
     * 
     * @param newX ny x posisjon
     * @param newY ny y posisjon
     */
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Setter ny type blokk
     * 
     * @param newType ny type blokk
     */
    public void setType(String newType) {
        this.type = newType;
    }
}
