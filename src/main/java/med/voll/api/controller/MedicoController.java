package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")

public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { // esse Pageable serve para paginar o que estamos buscando
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
        // sem a paginacao fica assim return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }

    @PutMapping
    @Transactional// precisa quando vai fazer algo (inserir) no bd
    public ResponseEntity atualizar(@RequestBody @Valid DadosatualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());

        medico.atualizarInfo(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }// nao precisa chamar o repository no final, quando a gente ja muda as coisas ele reflete automaticamente no banco de dados, pois tem a tag @Transctional

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

}
