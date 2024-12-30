package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Embeddable
@Builder
public class Result {

    @Column(name = "result")
    String body;

}
