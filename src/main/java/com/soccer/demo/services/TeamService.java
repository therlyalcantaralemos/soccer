package com.soccer.demo.services;

import com.soccer.demo.models.Team;
import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.repositories.TeamRepository;
import com.soccer.demo.services.exceptions.IdUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team insert(TeamDTO teamDto){
        if(teamRepository.findByIdTeam(teamDto.getIdTeam()).isPresent()){
           throw new IdUsedException();
        }
        return teamRepository.save(new Team(teamDto));
    }

}
