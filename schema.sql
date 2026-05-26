-- Banco de dados: ssa_aluno
CREATE DATABASE ssa_aluno;

-- Tabela principal
CREATE TABLE aluno (
                       id          BIGSERIAL PRIMARY KEY,
                       matricula   VARCHAR(50)  NOT NULL UNIQUE,
                       nome        VARCHAR(150) NOT NULL,
                       email       VARCHAR(100) UNIQUE,
                       telefone    VARCHAR(20),
                       periodo     INTEGER,
                       status      BOOLEAN DEFAULT TRUE,
                       curso_id    BIGINT,
                       curso_nome  VARCHAR(255)
);