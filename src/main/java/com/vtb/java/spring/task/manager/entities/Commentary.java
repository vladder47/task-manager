package com.vtb.java.spring.task.manager.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "commentary")
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    @Size(min = 3, max = 255)
    private String text;

    @Column(name = "parent")
    @NotNull
    private Long parent;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @NotNull
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
