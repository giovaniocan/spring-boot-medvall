package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations{

    @Autowired
    private SecurityFilter securityFilter;

    // onde eu desabilitei a protecao contra crossside request ..., aquele textão enorme que aparece quando da erro
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> { // aqui estamos falando que para acessar o login não precisa estar logado, mas de resto precisa do token
                            req.requestMatchers("/login").permitAll(); // no login pode permitir
                            req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll(); // esse ** é apra facilitar os subenderecos
                            req.anyRequest().authenticated(); // em qualquer  outra tem que estar autenticado
                        })
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // to falando, quero adicionar o meu filtro primeiro que o do spring, porque se o o Spring vir primeiro ele vai verificar se eu to logado, mas eu so faco iss nom meu filtro, então nunca vai me deixar ser logado
                        .build();
    }


    // o bean vai meio que exportar a classe para ser injetada em outro lugares
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // aqui ensinamos o spring que é para usar a sneha encriptografada com o BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
