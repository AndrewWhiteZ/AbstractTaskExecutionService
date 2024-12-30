package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
@Builder
public class Payload {

    @Column(name = "payload")
    String body;

}
