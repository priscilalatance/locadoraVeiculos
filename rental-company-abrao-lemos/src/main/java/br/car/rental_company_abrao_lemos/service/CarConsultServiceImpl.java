package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CarConsultServiceImpl implements CarConsultService {

    private final DatabaseService databaseService;

    @Autowired
    private CarValidateService carValidate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public CarEntity consultCar(ConsultRequest consultRequest) throws ValidationRequestException {

        carValidate.validateConsultRequest(consultRequest);
        return databaseService.findByPlaca(consultRequest);

    }

}
