package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.File;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;

    public List<File> findAllFiles(){
       return fileRepository.findAll();
    }

    public File findFileById(Long id){
        return fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Файл с такм " + id + " не найден"));
    }

    public String findFileNameById(Long id){
        return fileRepository.getFileNameDtoById(id);
    }
}
