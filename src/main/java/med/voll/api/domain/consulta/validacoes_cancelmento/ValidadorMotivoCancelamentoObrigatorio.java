package med.voll.api.domain.consulta.validacoes_cancelmento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamentoObrigatorio implements ValidadorCancelamentoConsultas {

    public void validar(DadosCancelamentoConsulta dados){
        if (dados.motivo() == null){
            throw new ValidacaoException("O motivo do cancelamento deve ser informado");
        }
    }
}
