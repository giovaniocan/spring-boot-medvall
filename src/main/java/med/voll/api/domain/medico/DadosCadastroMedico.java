package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(

        @NotBlank // validacao nao pode ser nulo nem vazio, presta atencao que o not blank so funciona para string
        String nome,
        @NotBlank
        @Email// para verificar o email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull // nao pode ser nulo
        Especialidade especialidade,
        @NotNull
        @Valid // e para validar os dados que tem em endereco, usamos isso para dtos
        DadosEndereco endereco
) {
}
