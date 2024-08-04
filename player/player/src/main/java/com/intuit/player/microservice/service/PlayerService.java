package com.intuit.player.microservice.service;

import com.intuit.player.microservice.exception.PlayerNotFoundException;
import com.intuit.player.microservice.model.Player;
import com.intuit.player.microservice.model.PlayerUpdateRequest;
import com.intuit.player.microservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(String id) {
        return playerRepository.findById(id);
    }


    public List<Player> updatePlayers(List<PlayerUpdateRequest> updates) throws PlayerNotFoundException{
        List<Player> updated = new ArrayList<>();
        for (PlayerUpdateRequest updateRequest : updates) {
            String playerID = updateRequest.getPlayerId();

            // Find the player by ID
            Optional<Player> optionalPlayer = playerRepository.findById(playerID);

            if (optionalPlayer.isPresent()) {
                Player player = optionalPlayer.get();
                player.updateFields(updateRequest.getUpdates());

                updated.add(playerRepository.save(player));
            } else {
                throw new PlayerNotFoundException("Player with ID " + playerID + " not found");
            }
        }
        return updated;
    }

    public List<Player> getPlayersById(List<String> ids) {
        return playerRepository.findByPlayerIDIn(ids);
    }

    public Player updatePlayer(PlayerUpdateRequest update) throws PlayerNotFoundException{
        Optional<Player> optionalPlayer = playerRepository.findById(update.getPlayerId());
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.updateFields(update.getUpdates());

            return playerRepository.save(player);
        } else {
            throw new PlayerNotFoundException("Player with ID " + update.getPlayerId() + " not found");
        }
    }

    public void deletePlayer(String playerID) throws PlayerNotFoundException{
        Optional<Player> optionalPlayer = playerRepository.findById(playerID);

        if (optionalPlayer.isPresent()) {
            playerRepository.delete(optionalPlayer.get());
        } else {
            throw new PlayerNotFoundException("Player with ID " + playerID + " not found");
        }
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }
}
