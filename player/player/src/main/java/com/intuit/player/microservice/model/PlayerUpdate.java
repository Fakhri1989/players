package com.intuit.player.microservice.model;

import com.intuit.player.microservice.enums.DominantHand;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerUpdate {
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private String bats;
    private DominantHand dominantHand;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

    // Getters and setters
}

