package br.car.rental_company_abrao_lemos.service;


import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import br.car.rental_company_abrao_lemos.repository.CarRepository;
import br.car.rental_company_abrao_lemos.utils.Consts;
import br.car.rental_company_abrao_lemos.utils.LogsUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class CarValidateServiceImpl implements CarValidateService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void validateRequest(AddRequest addRequest) throws ValidationRequestException {
        log.info(LogsUtil.information(), "Validação da requisição.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));

        ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
        Validator validatorRequest = validator.getValidator();
        Set<ConstraintViolation<AddRequest>> violations = validatorRequest.validate(addRequest);

        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", errors, LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            throw new ValidationRequestException(errors, addRequest.getPlaca());
        }

    }

    @Override
    public void validateConsultRequest(ConsultRequest consultRequest) throws ValidationRequestException {
        log.info(LogsUtil.information(), "Validação da requisição.", LogsUtil.propertiesPlaca(consultRequest.getPlaca()));

        ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
        Validator validatorRequest = validator.getValidator();
        Set<ConstraintViolation<ConsultRequest>> violations = validatorRequest.validate(consultRequest);

        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", errors, LogsUtil.propertiesPlaca(consultRequest.getPlaca()));
            throw new ValidationRequestException(errors, consultRequest.getPlaca());
        }
    }

    @Override
    public void validateDeleteRequest(DeleteRequest deleteRequest) throws ValidationRequestException {
        log.info(LogsUtil.information(), "Validação da requisição.", LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));

        ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
        Validator validatorRequest = validator.getValidator();
        Set<ConstraintViolation<DeleteRequest>> violations = validatorRequest.validate(deleteRequest);

        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", errors, LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));
            throw new ValidationRequestException(errors, deleteRequest.getPlaca());
        }
    }

    @Override
    public void validateInactiveRequest(InactiveRequest inactiveRequest) throws ValidationRequestException {
        log.info(LogsUtil.information(), "Validação da requisição.", LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));

        ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
        Validator validatorRequest = validator.getValidator();
        Set<ConstraintViolation<InactiveRequest>> violations = validatorRequest.validate(inactiveRequest);

        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", errors, LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
            throw new ValidationRequestException(errors, inactiveRequest.getPlaca());
        }
    }

    @Override
    public void validateUpdateRequest(UpdateRequest updateRequest) throws ValidationRequestException {
        log.info(LogsUtil.information(), "Validação da requisição.", LogsUtil.propertiesPlaca(updateRequest.getPlaca()));

        ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
        Validator validatorRequest = validator.getValidator();
        Set<ConstraintViolation<UpdateRequest>> violations = validatorRequest.validate(updateRequest);

        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", errors, LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
            throw new ValidationRequestException(errors, updateRequest.getPlaca());
        }
    }

    @Override
    public void isValidAddCar(AddRequest addRequest) {
        log.info(LogsUtil.information(), "Iniciando validação de placa.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));
        if (!isValidPlaca(addRequest.getPlaca())) {
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", Consts.PLACA_INVALID, LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            throw new IllegalArgumentException(Consts.PLACA_INVALID);
        }

        if (isFindPlaca(addRequest.getPlaca())) {
            log.info(LogsUtil.information(), "Encontrado erro de validação: {}", Consts.FIND_PLACA, LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            throw new IllegalArgumentException(Consts.FIND_PLACA);
        }
        log.info(LogsUtil.information(), "Finalizado a validação de placa.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));
    }

    @Override
    public boolean isValidPlaca(String placa) {
        String padraoAtual = "^[A-Z]{3}\\d{4}$";
        String padraoMercosul = "^[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}$";

        return placa.matches(padraoAtual) || placa.matches(padraoMercosul);
    }

    @Override
    public boolean isFindPlaca(String placa) {
        return carRepository.findByPlaca(placa).isPresent();
    }

}
