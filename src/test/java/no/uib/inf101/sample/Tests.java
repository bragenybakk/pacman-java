package no.uib.inf101.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sample.model.Block;
import no.uib.inf101.sample.model.Direction;
import no.uib.inf101.sample.model.GameState;
import no.uib.inf101.sample.model.Ghost;
import no.uib.inf101.sample.model.Player;

public class Tests {

    @Test
    public void testPlayerStartPosition() {
        Block start = new Block(5, 7, "P");
        Player p = new Player(start, null, Direction.NONE, null);
        assertEquals(5, p.getPos().getx());
        assertEquals(7, p.getPos().gety());
    }

    @Test
    public void testBlockType() {
        Block b = new Block(1, 1, "X");
        assertEquals("X", b.getType());
    }

    @Test
    void testBlockPositionAndType() {
        Block block = new Block(5, 7, "wall");
        assertEquals(5, block.getx());
        assertEquals(7, block.gety());
        assertEquals("wall", block.getType());
        block.setPosition(3, 4);
        assertEquals(3, block.getx());
        assertEquals(4, block.gety());
        block.setType("food");
        assertEquals("food", block.getType());
    }

    @Test
    void testDirectionValues() {
        assertEquals(0, Direction.UP.getDx());
        assertEquals(-1, Direction.UP.getDy());
        assertEquals(1, Direction.RIGHT.getDx());
        assertEquals(0, Direction.RIGHT.getDy());
    }

    @Test
    void testPlayerMovement() {
        Block start = new Block(1, 1, "player");
        Player player = new Player(start, null, Direction.NONE, null);
        HashMap<Point, Block> map = new HashMap<>();
        map.put(new Point(2, 1), new Block(2, 1, "empty"));
        player.setDirection(Direction.RIGHT);
        player.move(map, player.getDir());
        assertEquals(2, player.getPos().getx());
        assertEquals(1, player.getPos().gety());
    }

    @Test
    void testPlayerBlockedByWall() {
        Block start = new Block(1, 1, "player");
        Player player = new Player(start, null, Direction.NONE, null);
        HashMap<Point, Block> map = new HashMap<>();
        map.put(new Point(2, 1), new Block(2, 1, "wall"));
        player.setDirection(Direction.RIGHT);
        player.move(map, player.getDir());
        assertEquals(1, player.getPos().getx());
        assertEquals(1, player.getPos().gety());
    }

    @Test
    void testGameStateEnum() {
        assertEquals(GameState.START_SCREEN, GameState.valueOf("START_SCREEN"));
        assertEquals(GameState.PLAY, GameState.valueOf("PLAY"));
        assertEquals(GameState.GAME_OVER, GameState.valueOf("GAME_OVER"));
    }

    @Test
    void testGhostMovement() {
        Block start = new Block(5, 5, "ghost");
        Ghost ghost = new Ghost(start, null);
        HashMap<Point, Block> map = new HashMap<>();
        map.put(new Point(5, 4), new Block(5, 4, "empty"));
        map.put(new Point(4, 5), new Block(4, 5, "empty"));
        ghost.move(map);
        assertNotEquals(5, ghost.getPos().getx() + ghost.getPos().gety());
    }

    @Test
    void testPlayerStartsAtCorrectPosition() {
        Block start = new Block(3, 3, "player");
        Player player = new Player(start, null, Direction.NONE, null);
        assertEquals(3, player.getPos().getx());
        assertEquals(3, player.getPos().gety());
    }

    @Test
    void testGhostStartsAtCorrectPosition() {
        Block start = new Block(7, 7, "ghost");
        Ghost ghost = new Ghost(start, null);
        assertEquals(7, ghost.getPos().getx());
        assertEquals(7, ghost.getPos().gety());
    }

    @Test
    void testDirectionNone() {
        assertEquals(0, Direction.NONE.getDx());
        assertEquals(0, Direction.NONE.getDy());
    }

}
