package no.uib.inf101.sample;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;
import javax.swing.Timer;

import no.uib.inf101.sample.model.Block;
import no.uib.inf101.sample.model.Direction;
import no.uib.inf101.sample.model.GameState;
import no.uib.inf101.sample.model.Ghost;
import no.uib.inf101.sample.model.Player;

/**
 * Klasse som representerer spillbrettet i spillet
 * Håndterer bevegelse av spiller og spøkelser, samt tegning av brettet
 */
public class GameBoard extends JPanel {
    private GameState gameState = GameState.START_SCREEN;

    private int rows = 21;
    private int cols = 19;
    private int tileSize = 32;
    private int boardHeight = rows * tileSize;
    private int boardWidth = cols * tileSize;
    private int score = 0;

    private Block startposplayer;
    private Block startRedGhost;
    private Block startBlueGhost;
    private Block startOrangeGhost;
    private Block startPinkGhost;

    private Player player;

    private Ghost redGhost;
    private Ghost blueGhost;
    private Ghost pinkGhost;
    private Ghost orangeGhost;

    private Image wallImage;
    private Image foodImage;
    private Image pacmanRightImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;
    private Image startScreenImage;
    private Image gameOverImage;

    private Timer ghostTimer;
    private Timer playerTimer;

    HashMap<Point, Block> hashMap = new HashMap<>();

    /**
     * Konstruktør som setter opp brettet og initialiserer spiller og spøkelser
     */
    public GameBoard() {

        wallImage = new ImageIcon(getClass().getResource("/wall.png")).getImage();
        foodImage = new ImageIcon(getClass().getResource("/powerFood.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/pacmanRight.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("/redGhost.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/pinkGhost.png")).getImage();
        startScreenImage = new ImageIcon(getClass().getResource("/startscreen.png")).getImage();
        gameOverImage = new ImageIcon(getClass().getResource("/gameOver.png")).getImage();

        this.startposplayer = new Block(9, 15, "P");
        this.startRedGhost = new Block(9, 8, "r");
        this.startBlueGhost = new Block(9, 9, "b");
        this.startOrangeGhost = new Block(10, 9, "o");
        this.startPinkGhost = new Block(8, 9, "p");
        this.redGhost = new Ghost(startRedGhost, redGhostImage);
        this.blueGhost = new Ghost(startBlueGhost, blueGhostImage);
        this.orangeGhost = new Ghost(startOrangeGhost, orangeGhostImage);
        this.pinkGhost = new Ghost(startPinkGhost, pinkGhostImage);
        this.player = new Player(startposplayer, pacmanRightImage, Direction.NONE, this);

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);

        ghostTimer = new Timer(200, e -> {
            redGhost.move(hashMap);
            pinkGhost.move(hashMap);
            orangeGhost.move(hashMap);
            blueGhost.move(hashMap);

            if (player.isOnSameBlockAsGhost(blueGhost) ||
                    player.isOnSameBlockAsGhost(redGhost) ||
                    player.isOnSameBlockAsGhost(orangeGhost) ||
                    player.isOnSameBlockAsGhost(pinkGhost)) {
                gameState = GameState.GAME_OVER;
                playerTimer.stop();
                ghostTimer.stop();
            }
            repaint();
        });

        playerTimer = new Timer(200, e -> {
            player.move(hashMap, player.getDir());

            if (player.isOnSameBlockAsGhost(blueGhost) ||
                    player.isOnSameBlockAsGhost(redGhost) ||
                    player.isOnSameBlockAsGhost(orangeGhost) ||
                    player.isOnSameBlockAsGhost(pinkGhost)) {
                gameState = GameState.GAME_OVER;
                playerTimer.stop();
                ghostTimer.stop();
            }
            repaint();
        });

        String[] map = {
                "XXXXXXXXXXXXXXXXXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X                 X",
                "X XX X XXXXX X XX X",
                "X    X       X    X",
                "XXXX XXXX XXXX XXXX",
                "XXXX X       X XXXX",
                "XXXX X XX XX X XXXX",
                "X                 X",
                "XXXX X XXXXX X XXXX",
                "XXXX X       X XXXX",
                "XXXX X XXXXX X XXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X  X           X  X",
                "XX X X XXXXX X X XX",
                "X    X   X   X    X",
                "X XXXXXX X XXXXXX X",
                "X                 X",
                "XXXXXXXXXXXXXXXXXXX"
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                String symbol = String.valueOf(map[row].charAt(col));

                switch (symbol) {
                    case "X" -> symbol = "wall";
                    case " " -> symbol = "food";
                    case "P" -> symbol = "player";
                    default -> symbol = "empty";
                }

                Block block = new Block(col, row, symbol);
                hashMap.put(new Point(col, row), block); // merk: col = x, row = y

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState == GameState.START_SCREEN) {
            g.drawImage(startScreenImage, 65, 0, 500, 500, null);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("PRESS ENTER TO START!", 170, 550);
            return;
        }
        if (gameState == GameState.GAME_OVER) {
            g.drawImage(gameOverImage, 65, 0, 500, 500, null);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("YOU'RE SCORE WAS: " + score, 170, 550);
            return;
        }

        for (Point point : hashMap.keySet()) {
            Block block = hashMap.get(point);
            String type = block.getType();
            int x = block.getx() * tileSize;
            int y = block.gety() * tileSize;
            int foodSize = tileSize / 2;
            int foodX = x + (tileSize - foodSize) / 2;
            int foodY = y + (tileSize - foodSize) / 2;
            switch (type) {
                case "wall" -> g.drawImage(wallImage, x, y, tileSize, tileSize, null);
                case "food" -> g.drawImage(foodImage, foodX, foodY, foodSize, foodSize, null);
            }
        }
        redGhost.drawGhost(g, tileSize);
        blueGhost.drawGhost(g, tileSize);
        pinkGhost.drawGhost(g, tileSize);
        orangeGhost.drawGhost(g, tileSize);
        player.drawPlayer(g, tileSize);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);

    }

    /**
     * Getter for spiller
     * 
     * @return spiller
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Legger til poeng til score
     * 
     * @param score poeng som skal legges til
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Getter for brettets GameState
     * 
     * @return GameState
     */

    public GameState getGameState() {
        return gameState;
    }

    /**
     * Setter ny GameState og starter timerne for spiller og spøkelser
     * 
     * @param gameState ny GameState
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        repaint();
        if (gameState == GameState.PLAY) {
            ghostTimer.start();
            playerTimer.start();
        }
    }
}