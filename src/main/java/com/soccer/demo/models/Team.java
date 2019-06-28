package com.soccer.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soccer.demo.models.dto.TeamDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private Long idTeam;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String colorFirstUniform;
    @Column(nullable=false)
    private String colorSecondUniform;
    @Column(nullable=false)
    private LocalDate dateCreatedTeam;
    @JsonIgnore
    private List<Player> players;

    public Team() {
    }

    public Team(TeamDTO teamDto) {
        this.idTeam = teamDto.getIdTeam();
        this.name = teamDto.getName();
        this.colorFirstUniform = teamDto.getColorFirstUniform();
        this.colorSecondUniform = teamDto.getColorSecondUniform();
        this.dateCreatedTeam = teamDto.getDateCreatedTeam();

    }


}


