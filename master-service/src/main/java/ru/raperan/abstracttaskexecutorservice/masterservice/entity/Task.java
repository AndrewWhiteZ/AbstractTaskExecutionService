package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @ManyToOne
    @JoinColumn(name = "parent_task")
    private Task parentTask;

    @Column(name = "ttl")
    private Long ttl;

    @OneToMany(mappedBy = "task", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Step> steps;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public void addSteps(Collection<Step> stepsList) {
        stepsList.stream().distinct().forEach(step -> step.setTask(this));
    }

}
