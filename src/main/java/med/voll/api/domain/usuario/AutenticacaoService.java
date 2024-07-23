package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// falando que ela executa algunm servico ( autenticacao )
@Service
public class AutenticacaoService implements UserDetailsService {
// essa é a classe service que o spring vai chamar quanod a pessoa se autenticar, por causa do userDetailsSErvice
    @Autowired
    private UsuarioRepository repository;

    // isso spring vai gerar automaticamente
    // toda vez que comecar a aplicacao vai cair nessa classe para se autenticar, entao temos que colocar toda a logica possivel
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
