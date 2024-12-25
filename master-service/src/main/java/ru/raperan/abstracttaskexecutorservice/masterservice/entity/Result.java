package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;

@Embeddable
public class Result {

    @Column(name = "result")
    String body;

}
