package com.vtb.java.spring.task.manager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "leader_id")
    private Long leaderId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @AllArgsConstructor
    @Getter
    public enum Priority {
        PLANNING("В планах"), VERYLOW("Очень низкий"), LOW("Низкий"),
        MEDIUM("Средний"), HIGH("Высокий"), VERYHIGH("Очень высокий");

        private String rus;
    }

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @AllArgsConstructor
    @Getter
    public enum Status {
        CREATED("Создана"), INPROGRESS("В работе"), CHECKING("Передана на проверку"),
        RETURNED("Возвращена на доработку"), COMPLETED("Завершена"), CANCELED("Отменена");

        private String rus;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "deadline")
    private LocalDate deadLine;

    @ManyToMany
    @JoinTable(name = "users_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(mappedBy = "task")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Commentary> commentaries;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
