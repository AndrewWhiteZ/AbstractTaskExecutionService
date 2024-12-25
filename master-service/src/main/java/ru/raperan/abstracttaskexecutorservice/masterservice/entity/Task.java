package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @ManyToOne
    @JoinColumn(name = "parent_task")
    private Task parentTask;

    @Column(name = "ttl")
    private Long ttl;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private Set<Step> steps = new LinkedHashSet<>();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
