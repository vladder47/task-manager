package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("select f.fileName from File f where f.id = :id")
    String getFileNameDtoById(Long id);

    @Query("select f.fileName from File f where f.projectId = :projectId and f.taskId = :taskId")
    List<String> getFileNameByProjectIdAndTaskIdDto(Long projectId, Long taskId);

    List<File> findAllByProjectIdAndTaskId(Long projectId, Long taskId);

    boolean existsByFileNameAndProjectIdAndTaskId(String fileName, Long projectId, Long taskId);
}