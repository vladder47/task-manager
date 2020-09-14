package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.File;
import com.vtb.java.spring.task.manager.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private FileService fileService;


    @GetMapping("/{id}")
    public File getFileById(@PathVariable Long id){
//        System.out.println("FILEEEEEEEe");
        String path = "src/main/resources/files/";


        String fileName = fileService.findFileNameById(id);
        try (FileReader file = new FileReader(path + fileName)){

            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                String line = input.nextLine();
                System.out.println("line = " + line);
            }
            input.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return fileService.findFileById(id);
    }

}
