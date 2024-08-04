package com.intuit.player.microservice.model;

import com.intuit.player.microservice.enums.DominantHand;
import com.intuit.player.microservice.mappers.LocalDateAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "players")
public class Player implements Serializable {
    @Id
    private String playerID;
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
    @Column(name = "throws")
    private DominantHand dominantHand;
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate debut;

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

    public void updateFields(PlayerUpdate updateRequest) {
        if (updateRequest.getBirthYear() != null) this.setBirthYear(updateRequest.getBirthYear());
        if (updateRequest.getBirthMonth() != null) this.setBirthMonth(updateRequest.getBirthMonth());
        if (updateRequest.getBirthDay() != null) this.setBirthDay(updateRequest.getBirthDay());
        if (updateRequest.getBirthCountry() != null) this.setBirthCountry(updateRequest.getBirthCountry());
        if (updateRequest.getBirthState() != null) this.setBirthState(updateRequest.getBirthState());
        if (updateRequest.getBirthCity() != null) this.setBirthCity(updateRequest.getBirthCity());
        if (updateRequest.getDeathYear() != null) this.setDeathYear(updateRequest.getDeathYear());
        if (updateRequest.getDeathMonth() != null) this.setDeathMonth(updateRequest.getDeathMonth());
        if (updateRequest.getDeathDay() != null) this.setDeathDay(updateRequest.getDeathDay());
        if (updateRequest.getDeathCountry() != null) this.setDeathCountry(updateRequest.getDeathCountry());
        if (updateRequest.getDeathState() != null) this.setDeathState(updateRequest.getDeathState());
        if (updateRequest.getDeathCity() != null) this.setDeathCity(updateRequest.getDeathCity());
        if (updateRequest.getNameFirst() != null) this.setNameFirst(updateRequest.getNameFirst());
        if (updateRequest.getNameLast() != null) this.setNameLast(updateRequest.getNameLast());
        if (updateRequest.getNameGiven() != null) this.setNameGiven(updateRequest.getNameGiven());
        if (updateRequest.getWeight() != null) this.setWeight(updateRequest.getWeight());
        if (updateRequest.getHeight() != null) this.setHeight(updateRequest.getHeight());
        if (updateRequest.getBats() != null) this.setBats(updateRequest.getBats());
        if (updateRequest.getDominantHand() != null) this.setDominantHand(updateRequest.getDominantHand());
        if (updateRequest.getDebut() != null) this.setDebut(updateRequest.getDebut());
        if (updateRequest.getFinalGame() != null) this.setFinalGame(updateRequest.getFinalGame());
        if (updateRequest.getRetroID() != null) this.setRetroID(updateRequest.getRetroID());
        if (updateRequest.getBbrefID() != null) this.setBbrefID(updateRequest.getBbrefID());
    }
}
