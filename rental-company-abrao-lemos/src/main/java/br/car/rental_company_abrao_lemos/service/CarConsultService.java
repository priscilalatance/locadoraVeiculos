package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;

public interface CarConsultService {

    public CarEntity  consultCar(ConsultRequest consultRequest) throws ValidationRequestException;

}
