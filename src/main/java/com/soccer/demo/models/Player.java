package com.soccer.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.models.dto.TeamDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private Long idPlayer;
    @Column(nullable=false)
    private Long idTeam;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private LocalDate birthDate;
    @Column(nullable=false)
    private Integer skillLevel;
    @Column(nullable=false)
    private BigDecimal salary;
    private Boolean captain;

    public Player() {
    }

    public Player(PlayerDTO playerDto) {
        this.idPlayer = playerDto.getIdPlayer();
        this.idTeam = playerDto.getIdTeam();
        this.name = playerDto.getName();
        this.birthDate = playerDto.getBirthDate();
        this.skillLevel = playerDto.getSkillLevel();
        this.salary = playerDto.getSalary();
    }
}
