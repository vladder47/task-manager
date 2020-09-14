package com.vtb.java.spring.task.manager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    @NotNull
    private Long projectId;

    @Column(name = "task_id")
    @NotNull
    private Long taskId;

    @Column(name = "filename")
    @NotNull
    private String fileName;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public File(@NotNull Long projectId, @NotNull Long taskId, @NotNull String fileName) {
        this.projectId = projectId;
        this.taskId = taskId;
        this.fileName = fileName;
    }
}
