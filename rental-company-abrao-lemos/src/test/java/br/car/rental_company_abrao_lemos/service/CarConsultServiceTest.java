package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.TestRequest;
import br.car.rental_company_abrao_lemos.converter.CarConverter;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import br.car.rental_company_abrao_lemos.repository.CarRepository;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarConsultServiceTest {

    private CarValidateService validateService;
    private DatabaseService databaseService;
    private CarConsultService consultService;
    private TestRequest testRequest;

    @BeforeEach
    void setup() {
        validateService = Mockito.mock(CarValidateService.class);
        databaseService = Mockito.mock(DatabaseService.class);
        consultService = new CarConsultServiceImpl(databaseService, validateService);
        testRequest = new TestRequest();
    }

    @Test
    void whenConsultSuccessfully() throws IOException, ValidationRequestException {

        ConsultRequest consultRequest = testRequest.getCarConsultWithValidPlaca();
        CarEntity car = testRequest.getCar();

        doNothing().when(validateService).validateConsultRequest(consultRequest);
        when(databaseService.findByPlaca(consultRequest)).thenReturn(car);


        CarEntity carEntity = consultService.consultCar(consultRequest);


        assertEquals(car, carEntity);
        verify(validateService, times(1)).validateConsultRequest(consultRequest);
        verify(databaseService, times(1)).findByPlaca(consultRequest);
    }

    @Test
    void whenConsultValidationPlacaException() throws IOException, ValidationRequestException {

        ConsultRequest consultRequest = testRequest.getCarConsultWithInvalidPlacaNull();

        doThrow(new ValidationRequestException("A placa deve ser preenchida", null))
                .when(validateService).validateConsultRequest(consultRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            consultService.consultCar(consultRequest);
        });

        assertEquals("A placa deve ser preenchida", exception.getMessage());

        verify(validateService, times(1)).validateConsultRequest(consultRequest);

    }
}
