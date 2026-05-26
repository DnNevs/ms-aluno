package com.sistemassa.aluno.dto;

public record AlunoResponseDTO(
        Long id,
        String matricula,
        String nome,
        String email,
        String telefone,
        Integer periodo,
        Boolean status,
        Long cursoId,
        String cursoNome
) {}