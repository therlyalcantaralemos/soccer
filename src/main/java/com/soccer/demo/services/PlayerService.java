package com.soccer.demo.services;

import com.soccer.demo.models.Player;
import com.soccer.demo.models.dto.PlayerCaptainDTO;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.repositories.PlayerRepository;
import com.soccer.demo.services.exceptions.IdUsedException;
import com.soccer.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player created(PlayerDTO playerDto) {
        if (playerRepository.findByIdPlayer(playerDto.getIdPlayer()).isPresent()) {
            throw new IdUsedException();
        }
        playerRepository.findByIdTeam(playerDto.getIdTeam()).orElseThrow(ObjectNotFoundException::new);

        return playerRepository.save(new Player(playerDto));
    }

    public Optional<Player> setCaptain(PlayerCaptainDTO playerCaptainDto) throws ObjectNotFoundException {
        Optional<Player> player = playerRepository.findByIdTeamAndIdPlayer(playerCaptainDto.getIdTeam(), playerCaptainDto.getIdPlayer());

        player.ifPresentOrElse(findPlayer -> {
            playerRepository.findByIdTeamAndCaptainIsTrue(playerCaptainDto.getIdTeam()).ifPresent(cap -> {
                cap.setCaptain(false);
                playerRepository.save(cap);

            });

            findPlayer.setCaptain(true);
            playerRepository.save(findPlayer);
        }, ObjectNotFoundException::new);

        return player;
    }

    public Player findCaptain(Long IdTeam) {
        playerRepository.findByIdTeam(IdTeam).orElseThrow(ObjectNotFoundException::new);
        return playerRepository.findByIdTeamAndCaptainIsTrue(IdTeam).orElseThrow(ObjectNotFoundException::new);
    }

}
