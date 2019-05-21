package com.soccer.demo.repositories;

import com.soccer.demo.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByIdPlayer(Long IdPlayer);

    Optional<Player> findByIdTeam(Long idTeam);

    Optional<Player> findByIdTeamAndIdPlayer(Long idTeam, Long IdPlayer);

    Optional<Player> findByIdTeamAndCaptainIsTrue(Long idTeam);

}