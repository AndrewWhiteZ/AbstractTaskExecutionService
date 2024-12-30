package ru.raperan.abstracttaskexecutorservice.masterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;

import java.util.UUID;

@Repository
public interface StepRepository extends JpaRepository<Step, UUID> {

    @Modifying
    @Query("update Step s set s.status = :status, s.result = :message where s.task.id = :taskId and s.id = :id")
    void updateStepStatus(
            @Param("status") Status status,
            @Param("message") String message,
            @Param("taskId") UUID taskId,
            @Param("id") UUID id
    );

}
