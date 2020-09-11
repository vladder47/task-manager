package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//    @Query("select p.id as id, p.title as title, p.leader.username as leaderUsername, " +
//            "p.createdAt as createdAt, p.deadline as deadline from Project p")
//    List<ProjectDto> findAllProjectsDto();

    @Query("select p.id as id, p.title as title, p.leader.id as leaderId," +
            " p.leader.username as leaderUsername, p.createdAt as createdAt," +
            " p.deadline as deadline from Project p")
    Page<ProjectDto> findAllProjectsDto(Pageable pageable);

    @Query("select p.id as id, p.title as title, p.leader.id as leaderId, p.leader.username as leaderUsername, " +
            "p.deadline as deadline from Project p where p.id = :id")
    Optional<ProjectDto> findProjectDtoById(Long id);
}
