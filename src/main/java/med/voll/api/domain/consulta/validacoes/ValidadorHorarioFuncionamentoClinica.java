package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); // pega se é domingo
        var antesAbertura = dataConsulta.getHour() < 7; // pega se é antes que 7 horas
        var depoisFechamento = dataConsulta.getHour() > 18; //pega se já fechou, e é as 18 pois nemhuma consulta começa as 7, e sim a ultima começa as 6

        if(domingo || antesAbertura || depoisFechamento){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
        }
    }
}
