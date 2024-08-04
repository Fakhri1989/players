package com.intuit.player.microservice.controller;


import com.intuit.player.microservice.exception.PlayerNotFoundException;
import com.intuit.player.microservice.model.Player;
import com.intuit.player.microservice.model.PlayerUpdateRequest;
import com.intuit.player.microservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        Optional<Player> player = playerService.getPlayerById(id);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getplayers")
    public List<Player> getPlayerById(@RequestParam List<String> ids) {
        return playerService.getPlayersById(ids);
    }

    @PatchMapping("/update")
    public List<Player> retirePlayersWithId(@RequestBody List<PlayerUpdateRequest> updates){
        return playerService.updatePlayers(updates);
    }

    @PatchMapping("/update/single")
    public Player retirePlayersWithId(@RequestBody PlayerUpdateRequest update){
        return playerService.updatePlayer(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.noContent().build();
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }
}
