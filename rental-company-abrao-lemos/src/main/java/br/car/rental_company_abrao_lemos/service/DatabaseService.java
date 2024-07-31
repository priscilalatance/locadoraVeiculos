package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import org.springframework.stereotype.Component;

@Component
public interface DatabaseService {

    public void saveAdd(AddRequest addrequest);

    public CarEntity saveUpdate(UpdateRequest updateRequest);

    public CarEntity saveInactiveCar(InactiveRequest inactiveRequest);

    public void saveDelete(DeleteRequest deleteRequest);
    
    public CarEntity findByPlaca(ConsultRequest consultRequest);

}
