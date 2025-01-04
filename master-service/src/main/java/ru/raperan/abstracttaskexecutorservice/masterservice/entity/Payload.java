package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    @Column(name = "payload")
    String body;

}
