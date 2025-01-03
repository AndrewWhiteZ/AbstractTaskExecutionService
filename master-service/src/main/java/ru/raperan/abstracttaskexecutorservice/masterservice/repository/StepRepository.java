package ru.raperan.abstracttaskexecutorservice.masterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;

import java.util.UUID;

@Repository
public interface StepRepository extends JpaRepository<Step, UUID> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE step s SET status=:status, result=:message WHERE id=:id;")
    @Transactional
    void updateStepStatus(
            @Param("status") String status,
            @Param("message") String message,
            @Param("taskId") UUID taskId,
            @Param("id") UUID id
    );

}
