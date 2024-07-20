package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// esse arroba é como se fosse uma coisa generica par ao spring ler, mas não tem nada especifico
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    // quando cjegar uma requisicao vai bater aqui primeiro, e só valida apenas uma vez por requisição
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request); // recupero o token do cabecalho

        if(tokenJWT != null){ // se tiver cabecalho eu faco a validacao e recupero o subjetc ( que eu queria como o login da pessoa), se nao a pessoa nao entra no login
            var subject = tokenService.getSubject(tokenJWT);
            // se vier para a linha de baixo quer dizer que o token esta valido, pq se nao estivesse iria chamar umerro no tokenSerice
            var usuario = repository.findByLogin(subject); // peguei a pessoa do banco de dados com o login

            // criando um objeto especifica para poder autenticar ele
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // essa linha serve para chamar o proximo filtro
        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        // recuperando o token
        var authorizationHeader = request.getHeader("Authorization");

       if (authorizationHeader != null){
           return authorizationHeader.replace("Bearer ", "");
       }
        return null;
    }
}
