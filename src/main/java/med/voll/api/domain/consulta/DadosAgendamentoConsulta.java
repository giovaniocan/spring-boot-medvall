package med.voll.api.domain.consulta;

// Trecho de código suprimido

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future // é uma data que tem que ser agendada no futuro
        LocalDateTime data,

        Especialidade especialidade) {
}