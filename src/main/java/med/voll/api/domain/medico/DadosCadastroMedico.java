package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(

        @NotBlank(message = "{nome.obrigatorio}") // Nome é obrigatório
        String nome,

        @NotBlank(message = "{email.obrigatorio}") // Email é obrigatório
        @Email(message = "{email.invalido}") // Formato do email é inválido
        String email,

        @NotBlank(message = "{telefone.obrigatorio}") // Telefone é obrigatório
        String telefone,

        @NotBlank(message = "{crm.obrigatorio}") // CRM é obrigatório
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}") // Formato do CRM é inválido
        String crm,

        @NotNull(message = "{especialidade.obrigatoria}") // Especialidade é obrigatória
        Especialidade especialidade,

        @NotNull(message = "{endereco.obrigatorio}") // Dados do endereço são obrigatórios
        @Valid // Para validar os dados que tem em endereco, usamos isso para DTOs
        DadosEndereco endereco
) {}
