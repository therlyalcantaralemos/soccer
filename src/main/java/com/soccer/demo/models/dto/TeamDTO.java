package com.soccer.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.soccer.demo.models.Team;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
    private Long idTeam;
    private String name;
    private String colorFirstUniform;
    private String colorSecondUniform;
    private LocalDate dateCreatedTeam;


    public TeamDTO() {
    }

    public TeamDTO(Team team) {
        this.idTeam = team.getIdTeam();
        this.name = team.getName();
        this.colorFirstUniform = team.getColorFirstUniform();
        this.colorSecondUniform = team.getColorSecondUniform();
        this.dateCreatedTeam = team.getDateCreatedTeam();
    }
}
