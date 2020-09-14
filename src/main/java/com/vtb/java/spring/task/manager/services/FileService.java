package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.File;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;

    private static final String FILE_DIRECTORY = "C://FILES";

//    public List<File> findAllFiles(){
//       return fileRepository.findAll();
//    }

    public File findFileById(Long id){
        return fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Файл с такм " + id + " не найден"));
    }

    public String findFileNameById(Long id){
        return fileRepository.getFileNameDtoById(id);
    }

    public void storeFile(Long projectId, Long taskId, MultipartFile file) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + "/" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        if(!fileRepository.existsByFileNameAndProjectIdAndTaskId(file.getOriginalFilename(), projectId, taskId)){
            fileRepository.save(new File(projectId, taskId, file.getOriginalFilename()));
        }
    }

    public List<String> findFilesByProjectIdAndTaskId(Long projectId, Long taskId){
        return fileRepository.getFileNameByProjectIdAndTaskIdDto(projectId, taskId);
    }


}
