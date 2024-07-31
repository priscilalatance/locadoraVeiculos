package br.car.rental_company_abrao_lemos.exception;

import br.car.rental_company_abrao_lemos.utils.Consts;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ValidationRequestException extends Exception {

    private final Integer code;
    private final String message;

    public ValidationRequestException(String message, String placa) {
        super("Erro de validação");
        this.code = Consts.VALIDATE;
        this.message = message;
    }
}
