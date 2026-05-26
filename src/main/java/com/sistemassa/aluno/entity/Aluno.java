package com.sistemassa.aluno.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String matricula;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    private Integer periodo;

    private Boolean status;

    @Column(name = "curso_id")
    private Long cursoId;

    private String cursoNome;

    public void inativar() {
        this.status = false;
    }
}