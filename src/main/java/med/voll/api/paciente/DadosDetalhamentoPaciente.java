package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String telefone, String cpf, Boolean ativo, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getCpf(), paciente.getAtivo(), paciente.getEndereco());
    }
}
