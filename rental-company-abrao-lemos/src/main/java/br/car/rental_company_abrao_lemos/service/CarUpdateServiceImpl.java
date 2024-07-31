package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CarUpdateServiceImpl implements CarUpdateService {

    private final DatabaseService databaseService;

    @Autowired
    private CarValidateService carValidate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void updateCar(UpdateRequest updateRequest) throws ValidationRequestException {
        carValidate.validateUpdateRequest(updateRequest);
        databaseService.saveUpdate(updateRequest);

    }

}
