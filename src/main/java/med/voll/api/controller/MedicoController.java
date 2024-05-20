package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")

public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { // esse Pageable serve para paginar o que estamos buscando
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        // sem a paginacao fica assim return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }

    @PutMapping
    @Transactional// precisa quando vai fazer algo (inserir) no bd
    public void atualizar(@RequestBody @Valid DadosatualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());

        medico.atualizarInfo(dados);
    }// nao precisa chamar o repository no final, quando a gente ja muda as coisas ele reflete automaticamente no banco de dados, pois tem a tag @Transctional

    @Transactional
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
