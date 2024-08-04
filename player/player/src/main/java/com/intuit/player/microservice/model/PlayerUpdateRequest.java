package com.intuit.player.microservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class PlayerUpdateRequest {
    private String playerId;

    private PlayerUpdate updates;
}
