package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;


public interface CarUpdateService {

    public void updateCar(UpdateRequest updateRequest) throws ValidationRequestException;

}
