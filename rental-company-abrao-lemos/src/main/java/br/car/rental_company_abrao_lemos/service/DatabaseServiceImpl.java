package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.converter.CarConverter;
import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.repository.CarRepository;
import br.car.rental_company_abrao_lemos.utils.Consts;
import br.car.rental_company_abrao_lemos.utils.LogsUtil;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarConverter carConverter;

    @Override
    public void saveAdd(AddRequest addrequest) {
        log.info(LogsUtil.information(), "Salvando no banco o cadastro.", LogsUtil.propertiesPlaca(addrequest.getPlaca()));
        CarEntity car = carConverter.converAdd(addrequest);
        carRepository.save(car);

    }

    @Override
    public CarEntity saveUpdate(UpdateRequest updateRequest) {
        log.info(LogsUtil.information(), "Salvando atualização do cadastro.", LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
        return carRepository.findById(updateRequest.getPlaca()).map(car -> {
            if (updateRequest.getMarca() != null) {
                car.setMarca(updateRequest.getMarca());
            }
            if (updateRequest.getModelo() != null) {
                car.setModelo(updateRequest.getModelo());
            }
            if (updateRequest.getCombustivel() != null) {
                car.setCombustivel(updateRequest.getCombustivel());
            }
            if (updateRequest.getAnoFabricacao() != null) {
                if (updateRequest.getAnoFabricacao() < 2015) {
                    log.info(LogsUtil.information(), Consts.ANO_FABRICACAO, LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
                    throw new IllegalArgumentException(Consts.ANO_FABRICACAO);
                }
                car.setAnoFabricacao(updateRequest.getAnoFabricacao());
            }
            if (updateRequest.getAnoModelo() != null) {

                if (updateRequest.getAnoModelo() < 2015) {
                    log.info(LogsUtil.information(), Consts.ANO_MODELO, LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
                    throw new IllegalArgumentException(Consts.ANO_FABRICACAO);
                }
                car.setAnoModelo(updateRequest.getAnoModelo());
            }
            return carRepository.save(car);
        }).orElseThrow(() -> {
            log.info(LogsUtil.information(), Consts.NOT_FIND_PLACA, LogsUtil.propertiesPlaca(updateRequest.getPlaca()));
            return new NoSuchElementException(Consts.NOT_FIND_PLACA);
        });
    }

    @Override
    public void saveDelete(DeleteRequest deleteRequest) {
        log.info(LogsUtil.information(), "Excluindo do banco o cadastro.", LogsUtil.propertiesPlaca(deleteRequest.getPlaca()));
        carRepository.deleteById(deleteRequest.getPlaca());
    }

    @Override
    public CarEntity saveInactiveCar(InactiveRequest inactiveRequest) {
        log.info(LogsUtil.information(), "Ivativando veículo no banco.", LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
        return carRepository.findById(inactiveRequest.getPlaca()).map(car -> {
            car.setActiveCar(false);

            return carRepository.save(car);
        }).orElseThrow(() -> {
            log.info(LogsUtil.information(), Consts.NOT_FIND_PLACA, LogsUtil.propertiesPlaca(inactiveRequest.getPlaca()));
            return new NoSuchElementException(Consts.NOT_FIND_PLACA);
        });
    }

    @Override
    public CarEntity findByPlaca(ConsultRequest consultRequest) {
        log.info(LogsUtil.information(), "Consultando veículo no banco.", LogsUtil.propertiesPlaca(consultRequest.getPlaca()));
        return carRepository.findById(consultRequest.getPlaca())
                .orElseThrow(() -> {
                    log.info(LogsUtil.information(), Consts.NOT_FIND_PLACA, LogsUtil.propertiesPlaca(consultRequest.getPlaca()));
                    return new NoSuchElementException(Consts.NOT_FIND_PLACA);
                });
    }

}
