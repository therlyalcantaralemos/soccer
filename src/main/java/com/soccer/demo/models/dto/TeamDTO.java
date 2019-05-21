package com.soccer.demo.models.dto;

import com.soccer.demo.models.Team;
import lombok.Data;

@Data
public class TeamDTO {
    private Long idTeam;
    private String name;
    private String colorFirstUniform;
    private String colorSecondUniform;

    public TeamDTO() {
    }

    public TeamDTO(Team team) {
        this.idTeam = team.getIdTeam();
        this.name = team.getName();
        this.colorFirstUniform = team.getColorFirstUniform();
        this.colorSecondUniform = team.getColorSecondUniform();
    }
}
