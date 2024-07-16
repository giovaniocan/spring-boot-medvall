package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // anotação para indicar que vai ser uma classe que trata erros
public class TratadorDeErros {

    // aqui falamos que se em qualquer controller for lancado um 'entity not found exception' vai chamar esse metodo aqui
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

}
