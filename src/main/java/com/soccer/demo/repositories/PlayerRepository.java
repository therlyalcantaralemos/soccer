package com.soccer.demo.repositories;

import com.soccer.demo.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByIdPlayer(Long IdPlayer);

    List<Player> findByIdTeamOrderByIdPlayerAsc(Long idTeam);

    Optional<Player> findByIdTeamAndIdPlayer(Long idTeam, Long IdPlayer);

    Optional<Player> findByIdTeamAndCaptainIsTrue(Long idTeam);

    Optional<Player> findFirstByIdTeamOrderBySkillLevelDesc(Long idTeam);

    Optional<Player> findFirstByIdTeamOrderByBirthDateAscIdPlayerAsc(Long idTeam);

    Optional<Player> findFirstByIdTeamOrderBySalaryDescIdPlayerAsc(Long idTeam);

    Page<Player> findAllByOrderBySkillLevelDesc(Pageable pageable);

}
