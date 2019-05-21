package com.soccer.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soccer.demo.models.dto.TeamDTO;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
    @CreatedDate
    private LocalDate dateCreated;
    @Version
    @JsonIgnore
    private Long version;

    public Team() {
    }

    public Team(TeamDTO teamDto) {
        this.idTeam = teamDto.getIdTeam();
        this.name = teamDto.getName();
        this.colorFirstUniform = teamDto.getColorFirstUniform();
        this.colorSecondUniform = teamDto.getColorSecondUniform();
    }

    @PrePersist
    public void prePersist(){
        this.dateCreated = LocalDate.now();

    }
}


