package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("select f.fileName from File f where f.id = :id")
    String getFileNameDtoById(Long id);
}