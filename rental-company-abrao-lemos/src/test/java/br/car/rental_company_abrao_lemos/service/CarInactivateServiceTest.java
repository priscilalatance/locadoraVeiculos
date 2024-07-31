package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.TestRequest;
import br.car.rental_company_abrao_lemos.converter.CarConverter;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import br.car.rental_company_abrao_lemos.repository.CarRepository;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarInactivateServiceTest {

    private CarRepository carRepository;
    private TestRequest testRequest;
    private CarValidateService validateService;
    private DatabaseService databaseService;
    private CarInactivateService carInactivateService;
    private CarConverter carConverter;

    @BeforeEach
    void setup() {
        carRepository = Mockito.mock(CarRepository.class);
        carConverter = Mockito.mock(CarConverter.class);
        testRequest = new TestRequest();
        validateService = Mockito.mock(CarValidateService.class);
        databaseService = new DatabaseServiceImpl(carRepository, carConverter);
        carInactivateService = new CarInactivateServiceImpl(databaseService, validateService);
    }

    @Test
    void whenInactivateSuccessfully() throws IOException, ValidationRequestException {
        InactiveRequest inactiveRequest = testRequest.getCarInactiveWithValidPlaca();
        CarEntity car = testRequest.getCar();
        CarEntity carAtualizado = testRequest.getCarAtual();

        doNothing().when(validateService).validateInactiveRequest(inactiveRequest);
        when(carRepository.findById("GRD4789")).thenReturn(Optional.of(car));
        when(carRepository.save(any())).thenReturn(carAtualizado);

        carInactivateService.inactivateCar(inactiveRequest);

        verify(carRepository, times(1)).findById("GRD4789");
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void whenInactivateValidationPlacaException() throws IOException, ValidationRequestException {
        InactiveRequest inactiveRequest = testRequest.getCarInactiveWithInvalidPlacaNull();

        doThrow(new ValidationRequestException("A placa deve ser preenchida", null))
                .when(validateService).validateInactiveRequest(inactiveRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carInactivateService.inactivateCar(inactiveRequest);
        });

        assertEquals("A placa deve ser preenchida", exception.getMessage());

        verify(validateService, times(1)).validateInactiveRequest(inactiveRequest);
        verify(carRepository, never()).save(any(CarEntity.class));
    }
}
