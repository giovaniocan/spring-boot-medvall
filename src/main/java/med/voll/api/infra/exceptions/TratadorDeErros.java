package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // anotação para indicar que vai ser uma classe que trata erros
public class TratadorDeErros {

    // aqui falamos que se em qualquer controller for lancado um 'entity not found exception' vai chamar esse metodo aqui
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    // anotacao para quando os argumentos por algum motivo nao esta de acordo com o esperado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex){ // estamos recebendo a excecao
        var erros = ex.getFieldErrors();


        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }

    // aqui é um record de como ficaria os dados do resposta desse erro, apenas qual o campo que ta com erro e qual a mensagem de erro por si so
    private record DadosErrosValidacao(String campo, String mensagem){
        public DadosErrosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
