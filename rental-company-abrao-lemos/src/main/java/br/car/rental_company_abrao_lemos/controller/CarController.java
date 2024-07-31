package br.car.rental_company_abrao_lemos.controller;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.service.CarAddService;
import br.car.rental_company_abrao_lemos.service.CarConsultService;
import br.car.rental_company_abrao_lemos.service.CarDeleteService;
import br.car.rental_company_abrao_lemos.service.CarInactivateService;
import br.car.rental_company_abrao_lemos.service.CarUpdateService;
import br.car.rental_company_abrao_lemos.utils.Consts;
import br.car.rental_company_abrao_lemos.utils.LogsUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    private CarAddService addService;
    @Autowired
    private CarUpdateService updateService;
    @Autowired
    private CarDeleteService deleteService;
    @Autowired
    private CarInactivateService inactivateService;
    @Autowired
    private CarConsultService consultService;

    @PostMapping
    public ResponseEntity<AddRequest> addCar(@RequestBody AddRequest addRequest) {

        try {
            log.info(LogsUtil.information(), "Cadastro de veiculo via controller.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            log.info(LogsUtil.information(), Consts.REQUEST, LogsUtil.jsonRequest(addRequest));
            addService.addCar(addRequest);
            log.info(LogsUtil.information(), "Cadastro de veiculo finalizado.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error(LogsUtil.errorIntern(), "Erro na criação de cadastro via controller.", LogsUtil.propertiesPlaca(addRequest.getPlaca()));
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<CarEntity> getCarByPlaca(@RequestBody ConsultRequest consultRequest) {
        try {
            log.info(LogsUtil.information(), "Consulta de veiculo via controller.", LogsUtil.propertiesPlaca(consultRequest.getPlaca()));
            log.info(LogsUtil.information(), Consts.REQUEST, LogsUtil.jsonRequest(consultRequest));
            CarEntity car = consultService.consultCar(consultRequest);
            log.info(LogsUtil.information(), "Consulta via controller finalizada.", LogsUtil.propertiesPlaca(consultRequest.getPlaca()));
            return ResponseEntity.ok(car);

        } catch (Exception e) {
            log.error(LogsUtil.errorIntern(), "Erro na consulta via controller.", LogsUtil.propertiesPlaca(consultRequest.getPlaca()));

            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<UpdateRequest> updateCar(@RequestBody UpdateRequest updateRequest) {

        try {
            log.info(LogsUtil.information(), "Atualização de veiculo via controller", LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
            log.info(LogsUtil.information(), Consts.REQUEST, LogsUtil.jsonRequest(updateRequest));
            updateService.updateCar(updateRequest);
            log.info(LogsUtil.information(), "Atualização de veiculo finalizado.", LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(LogsUtil.errorIntern(), "Erro na atualização via controller.", LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/inactive")
    public ResponseEntity<InactiveRequest> inactiveCar(@RequestBody InactiveRequest inactiveRequest) {

        try {
            log.info(LogsUtil.information(), "Inativação de veiculo via controller", LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
            log.info(LogsUtil.information(), Consts.REQUEST, LogsUtil.jsonRequest(inactiveRequest));

            inactivateService.inactivateCar(inactiveRequest);
            log.info(LogsUtil.information(), "Inativação de veiculo finalizada.", LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(LogsUtil.errorIntern(), "Erro na atualização via controller.", LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCar(@RequestBody DeleteRequest deleteRequest) {
        try {
            log.info(LogsUtil.information(), "Operação de deletar o veiculo via controller", LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));
            log.info(LogsUtil.information(), Consts.REQUEST, LogsUtil.jsonRequest(deleteRequest));
            deleteService.deleteCar(deleteRequest);
            log.info(LogsUtil.information(), "Operação de deletar o veiculo finalizada.", LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(LogsUtil.errorIntern(), "Erro na atualização via controller.", LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));
            return ResponseEntity.badRequest().build();
        }
    }
}
