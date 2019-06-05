package com.soccer.demo;

import com.soccer.demo.models.dto.TeamDTO;
import com.soccer.demo.services.TeamService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeamTest extends SoccerApplicationTests{

    @Autowired
    private TeamService teamService;

    @Test
    public void testCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TeamDTO team = new TeamDTO();
        team.setIdTeam(123L);
        team.setName("vasco");
        team.setColorFirstUniform("white");
        team.setColorSecondUniform("black");
        team.setDateCreatedTeam(LocalDate.parse("2019-06-04", formatter));

        TeamDTO teamCreate = teamService.create(team);

        Assertions.assertNotNull(teamCreate);
    }

    @Test
    public void testFindTeams(){
        List<TeamDTO> team = teamService.findTeams();
        Assertions.assertNotNull(team);
    }

    @Test
    public void testFindTeamByName(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TeamDTO team = new TeamDTO();
        team.setIdTeam(133L);
        team.setName("nautico");
        team.setColorFirstUniform("white");
        team.setColorSecondUniform("red");
        team.setDateCreatedTeam(LocalDate.parse("2019-06-04", formatter));

        TeamDTO teamCreate = teamService.create(team);

        TeamDTO teamName = teamService.getNameTeam(team.getIdTeam());
        Assertions.assertNotNull(teamName);

    }

    @Test
    public void testeGetColorShirtTeamOut(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TeamDTO team = new TeamDTO();
        team.setIdTeam(144L);
        team.setName("juventos");
        team.setColorFirstUniform("white");
        team.setColorSecondUniform("red");
        team.setDateCreatedTeam(LocalDate.parse("2019-06-04", formatter));

        teamService.create(team);

        TeamDTO teamOut = new TeamDTO();
        teamOut.setIdTeam(155L);
        teamOut.setName("real");
        teamOut.setColorFirstUniform("white");
        teamOut.setColorSecondUniform("red");
        teamOut.setDateCreatedTeam(LocalDate.parse("2019-06-04", formatter));

        teamService.create(teamOut);

        TeamDTO teamShirt = teamService.findColorShirtTeamOut(team.getIdTeam(), teamOut.getIdTeam());
        Assertions.assertNotNull(teamShirt);
    }

}
