package br.car.rental_company_abrao_lemos.service;

import br.car.rental_company_abrao_lemos.TestRequest;
import br.car.rental_company_abrao_lemos.converter.CarConverter;
import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.exception.ValidationRequestException;
import br.car.rental_company_abrao_lemos.repository.CarRepository;
import br.car.rental_company_abrao_lemos.utils.Consts;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
public class CarAddServiceTest {

    private CarRepository carRepository;
    private TestRequest testRequest;
    private CarValidateService validateService;
    private DatabaseService databaseService;
    private CarAddService carAddService;
    private CarConverter carConverter;

    @BeforeEach
    void setup() {
        carRepository = Mockito.mock(CarRepository.class);
        carConverter = Mockito.mock(CarConverter.class);
        testRequest = new TestRequest();
        validateService = Mockito.mock(CarValidateService.class);
        databaseService = new DatabaseServiceImpl(carRepository, carConverter);
        carAddService = new CarAddServiceImpl(databaseService, validateService);
    }

    @Test
    void whenAddedSuccessfully() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithValidPlaca();
        CarEntity carEntity = testRequest.getCar(addRequest);

        doNothing().when(validateService).validateRequest(addRequest);
        doNothing().when(validateService).isValidAddCar(addRequest);
        when(validateService.isValidPlaca(addRequest.getPlaca())).thenReturn(true);
        when(validateService.isFindPlaca(addRequest.getPlaca())).thenReturn(true);
        when(carConverter.converAdd(addRequest)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(carEntity);

        carAddService.addCar(addRequest);

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, times(1)).isValidAddCar(addRequest);
        verify(carRepository, times(1)).save(carEntity);
    }

    @Test
    void whenAddedValidationPlacaException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidPlacaNull();

        doThrow(new ValidationRequestException("A placa deve ser preenchida", null))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("A placa deve ser preenchida", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationPlacaAtualException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidPlacaNull();

        doThrow(new IllegalArgumentException(Consts.PLACA_INVALID))
                .when(validateService).validateRequest(addRequest);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals(Consts.PLACA_INVALID, exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationMarcaException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidMarcaNull();

        doThrow(new ValidationRequestException("A marca deve ser preenchida", "GRD4789"))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("A marca deve ser preenchida", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationModeloException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidModeloNull();

        doThrow(new ValidationRequestException("O modelo deve ser preenchido", "GRD4789"))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("O modelo deve ser preenchido", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationCombustivelException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidCombustivelNull();

        doThrow(new ValidationRequestException("O combustivel deve ser preenchido", "GRD4789"))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("O combustivel deve ser preenchido", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationAnoFabricacaoException() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidAnoFabricacaoNull();

        doThrow(new ValidationRequestException("O ano de fabricação deve ser preenchido", "GRD4789"))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("O ano de fabricação deve ser preenchido", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

    @Test
    void whenAddedValidationAnoFabricacaoMinor2015Exception() throws IOException, ValidationRequestException {
        AddRequest addRequest = testRequest.getCarAddWithInvalidAnoFabricacao2015();

        doThrow(new ValidationRequestException("O ano de fabricação deve ser maior que 2015", "GRD4789"))
                .when(validateService).validateRequest(addRequest);

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            carAddService.addCar(addRequest);
        });

        assertEquals("O ano de fabricação deve ser maior que 2015", exception.getMessage());

        verify(validateService, times(1)).validateRequest(addRequest);
        verify(validateService, never()).isValidAddCar(any(AddRequest.class));
        verify(carRepository, never()).save(any(CarEntity.class));
    }

}
