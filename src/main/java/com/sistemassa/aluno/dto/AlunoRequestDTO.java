package com.sistemassa.aluno.dto;

public record AlunoRequestDTO(
        String matricula,
        String nome,
        String email,
        String telefone,
        Integer periodo,
        Boolean status,
        Long cursoId,
        String cursoNome
) {}