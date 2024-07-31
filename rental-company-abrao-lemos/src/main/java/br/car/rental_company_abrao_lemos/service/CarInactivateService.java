package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;

public interface CarInactivateService {

    public void inactivateCar(InactiveRequest inactiveRequest) throws ValidationRequestException;

}
