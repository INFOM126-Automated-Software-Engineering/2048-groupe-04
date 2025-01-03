package be.unamur.game2048.models;

public enum GameState {
    /**
     * The game has just started.
     */
    start,
    /**
     * The player has won the game.
     */
    won,
    /**
     * The player is currently running.
     */
    running,
    /**
     * The game is over.
     */
    over
}
