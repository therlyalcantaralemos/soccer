package com.soccer.demo.services;

import com.soccer.demo.models.Team;
import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.exceptions.IdUsedException;
import com.soccer.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDTO insert(TeamDTO teamDto){
        if(teamRepository.findByIdTeam(teamDto.getIdTeam()).isPresent()){
           throw new IdUsedException();
        }
        teamRepository.save(new Team(teamDto));

        return teamDto;
    }

    public TeamDTO findTeamByName(Long idTeam){
        Optional<Team> team = teamRepository.findByIdTeam(idTeam);
        if(!team.isPresent()){
            throw new ObjectNotFoundException();
        }

        TeamDTO teamDto = new TeamDTO();
        teamDto.setName(team.get().getName());

        return teamDto;
    }

    public List<TeamDTO> findTeams(){
        List<TeamDTO> teamDto = new ArrayList<>();
        teamRepository.findAllByOrderByIdTeam().forEach(team -> teamDto.add(new TeamDTO(team)));
        return teamDto;
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
}
