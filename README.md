# Pac-Man in Java

My own take on the classic Pac-Man, written from scratch in Java with Swing. Eat all the dots on the board without getting caught by the ghosts.

Built as the individual semester project in INF101 (Object-Oriented Programming) at the University of Bergen, spring 2025.

**▶ [Watch the demo video](https://youtu.be/VB6BoNtcpCA)**

## Features

- Start screen, game over screen and score counter
- Four ghosts that move independently around the maze
- Wall collision and movement on a tile-based board
- Keyboard controls with the arrow keys

## How it's built

I deliberately skipped the grid library provided by the course and built the board logic myself, to get a better understanding of how the pieces fit together.

- **Board:** the maze is stored as a map from coordinates to blocks (wall, food, empty), which gives quick lookups for collision and movement
- **Game loop:** two Swing timers drive the player and the ghosts separately
- **Structure:** split into model (`Player`, `Ghost`, `Block`, `GameState`), view (`GameBoard`) and controller
- **Tests:** JUnit tests for player and ghost movement, wall collision and start positions

## Tech stack

Java 17, Swing, Maven, JUnit 5

## Getting started

```bash
git clone https://github.com/bragenybakk/pacman-java.git
```

Open the project in your IDE (IntelliJ, VS Code or Eclipse) and run `src/main/java/no/uib/inf101/sample/main/Game.java`. Run the tests with `mvn test`.

## Controls

| Key | Action |
|---|---|
| `↑` `↓` `←` `→` | Move Pac-Man |

## Credits

- Pac-Man and ghost sprites from [ImKennyYip/pacman-java](https://github.com/ImKennyYip/pacman-java)
- Start and game over screens by Ella Solberg (INF101)
