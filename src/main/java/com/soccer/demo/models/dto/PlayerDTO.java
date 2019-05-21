package com.soccer.demo.models.dto;

import com.soccer.demo.models.Player;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlayerDTO {
    private Long idPlayer;
    private Long idTeam;
    private String name;
    private LocalDate birthDate;
    private Integer skillLevel;
    private BigDecimal salary;
    private Boolean captain;


    public PlayerDTO() {
    }

    public PlayerDTO(Player player) {
        this.idPlayer = player.getIdPlayer();
        this.idTeam = player.getIdTeam();
        this.name = player.getName();
        this.birthDate = player.getBirthDate();
        this.skillLevel = player.getSkillLevel();
        this.salary = player.getSalary();
        this.captain = player.getCaptain();

    }
}
