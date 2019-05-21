package com.soccer.demo.controllers;

import com.soccer.demo.models.Player;
import com.soccer.demo.models.dto.PlayerCaptainDTO;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Player created(@RequestBody PlayerDTO playerDto){
        return playerService.created(playerDto);
    }

    @PutMapping
    public Optional<Player> setCaptain(@RequestBody PlayerCaptainDTO playerCaptainDto){
        return playerService.setCaptain(playerCaptainDto);

    }

    @GetMapping("/{idTeam}/captain")
    public Player getCaptain(@PathVariable String idTeam){
        return playerService.findCaptain(Long.parseLong(idTeam));
    }
}
