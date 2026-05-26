package com.sistemassa.aluno.controller;

import com.sistemassa.aluno.dto.AlunoRequestDTO;
import com.sistemassa.aluno.dto.AlunoResponseDTO;
import com.sistemassa.aluno.service.AlunoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.status(201).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}