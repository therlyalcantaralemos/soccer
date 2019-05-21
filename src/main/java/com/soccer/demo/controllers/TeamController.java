package com.soccer.demo.controllers;

import com.soccer.demo.models.Team;
import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Team insert(@RequestBody TeamDTO teamDto){
        return teamService.insert(teamDto);
    }

}
