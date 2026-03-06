ALTER TABLE usuario
    ADD COLUMN user_sequence BIGINT;

ALTER TABLE lembrete
    ADD COLUMN user_number BIGINT;