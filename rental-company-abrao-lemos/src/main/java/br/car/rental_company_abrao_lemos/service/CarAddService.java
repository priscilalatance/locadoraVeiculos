package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;

public interface CarAddService {

    public void addCar(AddRequest addRequest)throws ValidationRequestException;

}
