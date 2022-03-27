package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game = new Game();
    private Player player1 = new Player(1, "Tom", 27);
    private Player player2 = new Player(2, "Wade", 45);
    private Player player3 = new Player(3, "James", 50);
    private Player player4 = new Player(4, "Jax", 45);

    @BeforeEach
    void shouldRegisterAllPlayers() {
        game.registerAll(List.of(player1, player2, player3, player4));
    }

    @Test
    void shouldFindAllRegisteredPlayers() {
        assertEquals(List.of(player1, player2, player3, player4), game.findAll());
    }

    @Test
    void shouldFindByNameWhenRegister() {
        assertEquals(player3, game.findByName("James"));
    }

    @Test
    void shouldReturnNullWhenNotRegister() {
        assertNull(game.findByName("Stef"));
    }

    @Test
    void shouldShowResultIfPlayer1Wins() {
        assertEquals(1, game.round("Wade", "Tom"));
    }

    @Test
    void shouldShowResultIfPlayer2Wins() {
        assertEquals(2, game.round("Wade", "James"));
    }

    @Test
    void shouldShowResultWhenDraw() {
        assertEquals(0, game.round("Wade", "Jax"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer1Unregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("Alexandeer", "Jax"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2Unregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("Tom", "Victoria"));
    }

    @Test
    void shouldThrowExceptionWhenPlayersBothUnregistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("Terminator", "Batman"));
    }
}