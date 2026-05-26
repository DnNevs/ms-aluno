package com.sistemassa.aluno.service;

import com.sistemassa.aluno.dto.AlunoRequestDTO;
import com.sistemassa.aluno.dto.AlunoResponseDTO;
import com.sistemassa.aluno.entity.Aluno;
import com.sistemassa.aluno.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Page<AlunoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDTO);
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        return toDTO(buscar(id));
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno(
                null,
                dto.matricula(),
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.periodo(),
                true,
                dto.cursoId(),
                dto.cursoNome()
        );
        return toDTO(repository.save(aluno));
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        buscar(id);
        Aluno aluno = new Aluno(
                id,
                dto.matricula(),
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.periodo(),
                dto.status(),
                dto.cursoId(),
                dto.cursoNome()
        );
        return toDTO(repository.save(aluno));
    }

    public void inativar(Long id) {
        Aluno aluno = buscar(id);
        aluno.inativar();
        repository.save(aluno);
    }

    private Aluno buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
    }

    private AlunoResponseDTO toDTO(Aluno a) {
        return new AlunoResponseDTO(
                a.getId(),
                a.getMatricula(),
                a.getNome(),
                a.getEmail(),
                a.getTelefone(),
                a.getPeriodo(),
                a.getStatus(),
                a.getCursoId(),
                a.getCursoNome()
        );
    }
}