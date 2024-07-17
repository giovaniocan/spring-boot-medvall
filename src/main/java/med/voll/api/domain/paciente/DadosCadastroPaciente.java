package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        @NotBlank(message = "{nome.obrigatorio}") // Nome é obrigatório
        String nome,

        @NotBlank(message = "{email.obrigatorio}") // Email é obrigatório
        @Email(message = "{email.invalido}") // Formato do email é inválido
        String email,

        @NotBlank(message = "{telefone.obrigatorio}") // Telefone é obrigatório
        String telefone,

        @NotBlank(message = "{cpf.obrigatorio}") // CPF é obrigatório
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "{cpf.invalido}") // Formato do CPF é inválido
        String cpf,

        @NotNull(message = "{endereco.obrigatorio}") // Dados do endereço são obrigatórios
        @Valid // Para validar os dados que tem em endereco, usamos isso para DTOs
        DadosEndereco endereco
) {}
