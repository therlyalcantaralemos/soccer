package com.soccer.demo.services;

import com.soccer.demo.models.Player;
import com.soccer.demo.models.dto.PlayerCaptainDTO;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.repositories.PlayerRepository;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.exceptions.IdUsedException;
import com.soccer.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (playerRepository.findByIdPlayer(playerDto.getIdPlayer()).isPresent()) {
            throw new IdUsedException();
        }
        teamRepository.findByIdTeam(playerDto.getIdTeam()).orElseThrow(ObjectNotFoundException::new);

        playerRepository.save(new Player(playerDto));
        return playerDto;
    }

    public PlayerDTO setCaptain(PlayerCaptainDTO playerCaptainDto) throws ObjectNotFoundException {
        Optional<Player> player = playerRepository.findByIdTeamAndIdPlayer(playerCaptainDto.getIdTeam(), playerCaptainDto.getIdPlayer());

        if(player.isPresent()){
            playerRepository.findByIdTeamAndCaptainIsTrue(playerCaptainDto.getIdTeam())
                    .stream().forEach(existsCaptain ->{
                    existsCaptain.setCaptain(false);
                     playerRepository.save(existsCaptain);

            });

            player.stream().forEach(captain -> {
                captain.setCaptain(true);
                playerRepository.save(captain);

            });
        }else{
            throw new ObjectNotFoundException();
        }
        PlayerDTO playerDto = new PlayerDTO(player.get());
        return playerDto;
    }

    public PlayerDTO findCaptainByIdTeam(Long IdTeam) {
        Player player = playerRepository.findByIdTeamAndCaptainIsTrue(IdTeam).orElseThrow(ObjectNotFoundException::new);
        return new PlayerDTO(player);
    }

    public PlayerDTO findByIdPlayer(Long idPlayer){
        Optional<Player> player = playerRepository.findByIdPlayer(idPlayer);
        if(!player.isPresent()){
            throw new ObjectNotFoundException();
        }

        PlayerDTO playerDto = new PlayerDTO();
        playerDto.setName(player.get().getName());

        return playerDto;
    }

    public List<PlayerDTO> findPlayersByTeam(Long idTeam){
        List<Player> players = playerRepository.findByIdTeamOrderByIdPlayerAsc(idTeam);

        if(players.isEmpty()){
            throw new ObjectNotFoundException();
        }

        List<PlayerDTO> playerDto = new ArrayList<>();
        players.stream().forEach(player -> {
            playerDto.add(new PlayerDTO(player));
        });

        return playerDto;
    }

    public PlayerDTO findBestPlayerByTeam(Long idTeam){
        Player player = playerRepository.findFirstByIdTeamOrderBySkillLevelDesc(idTeam).orElseThrow(ObjectNotFoundException::new);
        return new PlayerDTO(player);
    }

    public PlayerDTO findOldPlayerByTeam(Long idTeam){
        Player player = playerRepository.findFirstByIdTeamOrderByBirthDateAscIdPlayerAsc(idTeam).orElseThrow(ObjectNotFoundException::new);
        return new PlayerDTO(player);
    }

    public PlayerDTO findBiggerSalaryPlayerByTeam(Long idTeam){
        Player player = playerRepository.findFirstByIdTeamOrderBySalaryDescIdPlayerAsc(idTeam).orElseThrow(ObjectNotFoundException::new);
        return new PlayerDTO(player);
    }

    public PlayerDTO findSalaryByPlayer(Long idPlayer){
        Player player = playerRepository.findFirstByIdPlayerOrderBySalaryDescIdPlayerAsc(idPlayer).orElseThrow(ObjectNotFoundException::new);
        PlayerDTO playerDto = new PlayerDTO();
        playerDto.setSalary(player.getSalary());
        return playerDto;
    }

    public List<PlayerDTO> findTopPlayers(Integer playersNumber) {
        Page<Player> players = playerRepository.findAllByOrderBySkillLevelDesc(PageRequest.of(0, playersNumber));

        List<PlayerDTO> playerDto = new ArrayList<>();
        if (!players.isEmpty()) {
            players.stream().forEach(player ->{
                playerDto.add(new PlayerDTO(player));
            });
        }
        return playerDto;
    }

}
