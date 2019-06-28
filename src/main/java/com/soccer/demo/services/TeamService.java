package com.soccer.demo.services;

import com.soccer.demo.models.Team;
import com.soccer.demo.models.dto.PlayerDTO;
import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerService playerService;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
    }

    public TeamDTO create(TeamDTO teamDto){
        if(teamRepository.findByIdTeam(teamDto.getIdTeam()).isPresent()){
            teamRepository.save(new Team(teamDto));
            return teamDto;
        }else{
            throw new ObjectNotFoundException();
        }
    }

    public TeamDTO getNameTeam(Long idTeam){
        Team team = teamRepository.findByIdTeam(idTeam).orElseThrow(ObjectNotFoundException::new);
        TeamDTO teamDto = new TeamDTO();
        teamDto.setName(team.getName());
        return teamDto;
    }

    public List<TeamDTO> findTeams(){
        return getTeamDTO(teamRepository.findAllByOrderByIdTeam());
    }

    public TeamDTO findColorShirtTeamOut(Long idTeam, Long idTeamOut) {
        Team team = teamRepository.findByIdTeam(idTeam).orElseThrow(ObjectNotFoundException::new);
        Team teamOut = teamRepository.findByIdTeam(idTeamOut).orElseThrow(ObjectNotFoundException::new);

        TeamDTO teamOutShirt = new TeamDTO();
        if(team.getColorFirstUniform() == teamOut.getColorFirstUniform()){
            teamOutShirt.setColorSecondUniform(teamOut.getColorSecondUniform());
        }else{
            teamOutShirt.setColorFirstUniform(teamOut.getColorFirstUniform());
        }
        return teamOutShirt;
    }

    public List<TeamDTO> getTeamDTO(List<Team> team){
        return team.stream().map(TeamDTO::new).collect(Collectors.toList());
    }

    public List<PlayerDTO> findPlayers(Long idTeam){
        return playerService.getPlayerDTO(teamRepository.findByIdTeam(idTeam).orElseThrow(ObjectNotFoundException::new).getPlayers());
    }

    public PlayerDTO findBestPlayer(Long idTeam){
        return findPlayers(idTeam).stream().max(Comparator.comparing(PlayerDTO::getSkillLevel)).orElseThrow(ObjectNotFoundException::new);
    }
}
