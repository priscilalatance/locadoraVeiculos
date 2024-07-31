package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.TestRequest;
import br.car.rental_company_abrao_lemos.converter.CarConverter;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarUpdateServiceTest {

    private CarRepository carRepository;
    private TestRequest testRequest;
    private CarValidateService validateService;
    private DatabaseService databaseService;
    private CarUpdateService carUpdateService;
    private CarConverter carConverter;

    @BeforeEach
    void setup() {
        carRepository = Mockito.mock(CarRepository.class);
        carConverter = Mockito.mock(CarConverter.class);
        testRequest = new TestRequest();
        validateService = Mockito.mock(CarValidateService.class);
        databaseService = new DatabaseServiceImpl(carRepository, carConverter);
        carUpdateService = new CarUpdateServiceImpl(databaseService, validateService);
    }

    @Test
    void whenUpdateSuccessfully() throws IOException, ValidationRequestException {
        UpdateRequest updateRequest = testRequest.getCarUpdateWithValidPlaca();

        CarEntity car = testRequest.getCar();
        when(carRepository.findById("GRD4789")).thenReturn(Optional.of(car));
        when(carRepository.save(any())).thenReturn(car);
        carUpdateService.updateCar(updateRequest);
        verify(carRepository, times(1)).findById("GRD4789");
        verify(carRepository, times(1)).save(any(CarEntity.class));
    }

    @Test
    void whenUpdateValidationPlacaException() throws IOException, ValidationRequestException {
        UpdateRequest updateRequest = testRequest.getCarUpdateWithInvalidPlacaNull();

        doThrow(new ValidationRequestException("A placa deve ser preenchida", null))
                .when(validateService).validateUpdateRequest(updateRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carUpdateService.updateCar(updateRequest);
        });

        assertEquals("A placa deve ser preenchida", exception.getMessage());

        verify(validateService, times(1)).validateUpdateRequest(updateRequest);
        verify(carRepository, never()).save(any(CarEntity.class));
    }
}
