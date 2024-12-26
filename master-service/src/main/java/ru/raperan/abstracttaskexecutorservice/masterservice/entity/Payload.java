package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Payload {

    @Column(name = "payload")
    String body;

}
