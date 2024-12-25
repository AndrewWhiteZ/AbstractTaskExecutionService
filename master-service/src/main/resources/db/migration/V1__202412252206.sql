CREATE TABLE step
(
    id         UUID NOT NULL,
    name       TEXT,
    status     TEXT,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    task_id    UUID,
    result     TEXT,
    payload    TEXT,
    CONSTRAINT pk_step PRIMARY KEY (id)
);

CREATE TABLE task
(
    id          UUID NOT NULL,
    parent_task UUID,
    ttl         BIGINT,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

ALTER TABLE step
    ADD CONSTRAINT FK_STEP_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_PARENT_TASK FOREIGN KEY (parent_task) REFERENCES task (id);