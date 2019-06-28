package com.soccer.demo.services;

import com.soccer.demo.models.Player;
import com.soccer.demo.models.Team;
import com.soccer.demo.models.dto.PlayerCaptainDTO;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.repositories.PlayerRepository;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.exceptions.IdUsedException;
import com.soccer.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public PlayerDTO created(PlayerDTO playerDto) {
        Team team = teamRepository.findByIdTeam(playerDto.getIdTeam()).orElseThrow(ObjectNotFoundException::new);
        playerRepository.findByIdPlayer(playerDto.getIdPlayer()).orElseThrow(IdUsedException::new);
        Player player = playerRepository.save(new Player(playerDto));

        updatePlayerToTeam(team, player);

        return playerDto;
    }

    @Transactional
    public PlayerDTO setCaptain(PlayerCaptainDTO playerCaptainDto){
        Player player = playerRepository.findByIdTeamAndIdPlayer(playerCaptainDto.getIdTeam(), playerCaptainDto.getIdPlayer())
                .orElseThrow(ObjectNotFoundException::new);

        playerRepository.findByIdTeamAndCaptainIsTrue(playerCaptainDto.getIdTeam()).ifPresent(captain -> {
            captain.setCaptain(false);
            playerRepository.save(captain);
        });

        player.setCaptain(true);
        return new PlayerDTO(playerRepository.save(player));
    }

    public PlayerDTO findCaptainByIdTeam(Long IdTeam) {
        return new PlayerDTO(playerRepository.findByIdTeamAndCaptainIsTrue(IdTeam).orElseThrow(ObjectNotFoundException::new));
    }

    public PlayerDTO findByIdPlayer(Long idPlayer){
        return new PlayerDTO(playerRepository.findByIdPlayer(idPlayer).orElseThrow(ObjectNotFoundException::new));
    }

    public PlayerDTO findBiggerSalaryPlayerByTeam(Long idTeam){
        return new PlayerDTO(playerRepository.findFirstByIdTeamOrderBySalaryDescIdPlayerAsc(idTeam).orElseThrow(ObjectNotFoundException::new));
    }

    public PlayerDTO findSalaryByPlayer(Long idPlayer){
        Player player = playerRepository.findFirstByIdPlayerOrderBySalaryDescIdPlayerAsc(idPlayer).orElseThrow(ObjectNotFoundException::new);
        PlayerDTO playerDto = new PlayerDTO();
        playerDto.setSalary(player.getSalary());
        return playerDto;
    }

    public List<PlayerDTO> findTopPlayers(Integer playersNumber) {
        List<Player> players = playerRepository.findAllByOrderBySkillLevelDesc();
        return getPlayerDTO(players.subList(0, playersNumber));
    }

    public List<PlayerDTO> getPlayerDTO(List<Player> player){
        return player.stream().map(PlayerDTO::new).collect(Collectors.toList());
    }

    public void updatePlayerToTeam(Team team, Player player){
        team.getPlayers().add(player);
        teamRepository.save(team);
    }
}
