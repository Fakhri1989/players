package com.intuit.player.microservice.repository;

import com.intuit.player.microservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findByPlayerIDIn(List<String> playerIDs);
}
