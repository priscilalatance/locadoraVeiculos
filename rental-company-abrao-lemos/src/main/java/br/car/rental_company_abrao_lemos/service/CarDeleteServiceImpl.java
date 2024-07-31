package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CarDeleteServiceImpl implements CarDeleteService {

    private final DatabaseService databaseService;

    @Autowired
    private CarValidateService carValidate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void deleteCar(DeleteRequest deleteRequest) throws ValidationRequestException {

            carValidate.validateDeleteRequest(deleteRequest);
            databaseService.saveDelete(deleteRequest);

    }

}
