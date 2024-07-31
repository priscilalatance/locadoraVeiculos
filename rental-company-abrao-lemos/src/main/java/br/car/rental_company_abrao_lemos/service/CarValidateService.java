package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;



public interface CarValidateService {

    public void validateRequest(AddRequest addRequest) throws ValidationRequestException;

    public void validateConsultRequest(ConsultRequest consultRequest) throws ValidationRequestException;

    public void validateDeleteRequest(DeleteRequest deleteRequest) throws ValidationRequestException;

    public void validateInactiveRequest(InactiveRequest inactiveRequest) throws ValidationRequestException;

    public void validateUpdateRequest(UpdateRequest updateRequest) throws ValidationRequestException;

    public void isValidAddCar(AddRequest addRequest);

    public boolean isValidPlaca(String placa);

    public boolean isFindPlaca(String placa);

}
