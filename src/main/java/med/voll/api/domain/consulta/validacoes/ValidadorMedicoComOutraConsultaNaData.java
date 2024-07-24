package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaNaData {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsulta = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if(medicoPossuiOutraConsulta){
            throw new ValidacaoException("Médico j possui outra consulta agendada nessa mesmo horario");
        }
    }

}
