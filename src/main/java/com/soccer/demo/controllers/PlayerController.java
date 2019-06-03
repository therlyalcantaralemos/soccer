package com.soccer.demo.controllers;

import com.soccer.demo.models.dto.PlayerCaptainDTO;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PlayerDTO created(@RequestBody PlayerDTO playerDto){
        return playerService.created(playerDto);
    }

    @PutMapping("/captain")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public PlayerDTO setCaptain(@RequestBody PlayerCaptainDTO playerCaptainDto){
        return playerService.setCaptain(playerCaptainDto);
    }

    @GetMapping("/{idPlayer}")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getPlayerByName(@PathVariable Long idPlayer){
        return playerService.findByIdPlayer(idPlayer);
    }

    @GetMapping("/{idTeam}/captain")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getCaptainByIdTeam(@PathVariable Long idTeam){
        return playerService.findCaptainByIdTeam(idTeam);
    }

    @GetMapping("/team/{idTeam}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PlayerDTO> getPlayersByTeam(@PathVariable Long idTeam){
        return playerService.findPlayersByTeam(idTeam);
    }

    @GetMapping("/team/{idTeam}/bestPlayer")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getBestPlayerByTeam(@PathVariable Long idTeam){
        return playerService.findBestPlayerByTeam(idTeam);
    }

    @GetMapping("/team/{idTeam}/oldPlayer")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getOldPlayerByTeam(@PathVariable Long idTeam){
        return playerService.findOldPlayerByTeam(idTeam);
    }

    @GetMapping("/team/{idTeam}/biggerSalary")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getBiggerSalaryPlayerByTeam(@PathVariable Long idTeam){
        return playerService.findBiggerSalaryPlayerByTeam(idTeam);
    }

    @GetMapping("/{idPlayer}/salary")
    @ResponseStatus(value = HttpStatus.OK)
    public PlayerDTO getSalaryByPlayer(@PathVariable Long idPlayer){
        return playerService.findSalaryByPlayer(idPlayer);
    }

    @GetMapping("/{playersNumber}/topPlayers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PlayerDTO> getSalaryByPlayer(@PathVariable Integer playersNumber){
        return playerService.findTopPlayers(playersNumber);
    }

}
