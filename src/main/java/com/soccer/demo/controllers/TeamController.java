package com.soccer.demo.controllers;

import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.services.PlayerService;
import com.soccer.demo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<TeamDTO> getTeams(){
        return teamService.findTeams();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TeamDTO create(@RequestBody TeamDTO teamDto){
        return teamService.create(teamDto);
    }

    @GetMapping("/{idTeam}")
    @ResponseStatus(value = HttpStatus.OK)
    public TeamDTO getNameTeam(@PathVariable Long idTeam){
        return teamService.getNameTeam(idTeam);
    }

    @GetMapping("/teamOutShirt")
    public TeamDTO getColorShirtTeamOut(@RequestParam(value="idTeam", defaultValue = "") Long idTeam,
                                                  @RequestParam(value="idTeamOut", defaultValue = "") Long idTeamOut){
        return teamService.findColorShirtTeamOut(idTeam, idTeamOut);
    }

    @GetMapping("/{idTeam}/players")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PlayerDTO> getPlayers(@PathVariable Long idTeam){
        return teamService.findPlayers(idTeam);
    }

    @GetMapping("/{idTeam}/bestPlayer")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getBestPlayer(@PathVariable Long idTeam){
        return teamService.findBestPlayer(idTeam);
    }

//    @GetMapping("/{idTeam}/oldPlayer")
//    @ResponseStatus(value = HttpStatus.OK)
//    public PlayerDTO getOldPlayer(@PathVariable Long idTeam){
//        return teamService.findOldPlayer(idTeam);
//    }


}
