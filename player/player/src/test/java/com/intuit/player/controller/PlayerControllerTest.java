package com.intuit.player.controller;

import com.intuit.player.microservice.controller.PlayerController;
import com.intuit.player.microservice.exception.PlayerNotFoundException;
import com.intuit.player.microservice.model.Player;
import com.intuit.player.microservice.model.PlayerUpdateRequest;
import com.intuit.player.microservice.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    void testGetAllPlayers() throws Exception {
        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> players = Arrays.asList(player1, player2);

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    void testGetPlayerById() throws Exception {
        Player player = new Player();
        player.setPlayerID("aaronha01");
        when(playerService.getPlayerById("aaronha01")).thenReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/aaronha01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerID").value("aaronha01"));
    }

    @Test
    void testGetPlayerByIdNotFound() throws Exception {
        when(playerService.getPlayerById(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/players/dasdasdasdasdasdasdasd"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPlayersById() throws Exception {
        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> players = Arrays.asList(player1, player2);

        when(playerService.getPlayersById(any())).thenReturn(players);

        mockMvc.perform(get("/api/players/getplayers")
                        .param("ids", "abbotku01", "achteaj01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    void testUpdatePlayers() throws Exception {
        PlayerUpdateRequest request = new PlayerUpdateRequest();
        Player player = new Player();
        List<Player> players = Arrays.asList(player);

        when(playerService.updatePlayers(any())).thenReturn(players);

        mockMvc.perform(patch("/api/players/update")
                        .contentType("application/json")
                        .content("[{}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testUpdatePlayer() throws Exception {
        PlayerUpdateRequest request = new PlayerUpdateRequest();
        Player player = new Player();
        player.setPlayerID("1");

        when(playerService.updatePlayer(any())).thenReturn(player);

        mockMvc.perform(patch("/api/players/update/single")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerID").value("1"));
    }

    @Test
    void testDeletePlayer() throws Exception {
        doNothing().when(playerService).deletePlayer("1");

        mockMvc.perform(delete("/api/players/1"))
                .andExpect(status().isNoContent());

        verify(playerService, times(1)).deletePlayer("1");
    }

    @Test
    void testDeletePlayerNotFound() throws Exception {
        doThrow(new PlayerNotFoundException("Player not found")).when(playerService).deletePlayer("1");

        mockMvc.perform(delete("/api/players/1"))
                .andExpect(status().isNotFound());

        verify(playerService, times(1)).deletePlayer("1");
    }

    @Test
    void testCreatePlayer() throws Exception {
        Player player = new Player();
        player.setPlayerID("1");

        when(playerService.createPlayer(any(Player.class))).thenReturn(player);

        mockMvc.perform(post("/api/players")
                        .contentType("application/json")
                        .content("{\"playerID\":\"1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.playerID").value("1"));
    }
}
