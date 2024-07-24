package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com um médico inativo");
        }
    }

}
