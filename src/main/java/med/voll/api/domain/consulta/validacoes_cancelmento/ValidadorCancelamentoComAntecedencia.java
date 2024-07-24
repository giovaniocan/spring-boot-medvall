package med.voll.api.domain.consulta.validacoes_cancelmento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoComAntecedencia implements ValidadorCancelamentoConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsulta dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, consulta.getData()).toHours();

        if(diferenca < 24){
            throw new ValidacaoException("Consulta deve ser cancelada com no mínimo 24 horas de anteceência");
        }
    }

}
