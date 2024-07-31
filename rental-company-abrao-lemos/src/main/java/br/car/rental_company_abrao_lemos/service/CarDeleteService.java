package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;

public interface CarDeleteService {

    public void deleteCar(DeleteRequest deleteRequest) throws ValidationRequestException;

}
