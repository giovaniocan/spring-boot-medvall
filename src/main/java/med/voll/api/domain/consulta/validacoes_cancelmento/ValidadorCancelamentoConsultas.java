package med.voll.api.domain.consulta.validacoes_cancelmento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsultas {

    void validar(DadosCancelamentoConsulta dados);

}
