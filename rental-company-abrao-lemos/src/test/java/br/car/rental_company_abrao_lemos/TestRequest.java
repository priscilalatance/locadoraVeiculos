package br.car.rental_company_abrao_lemos;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.ConsultRequest;
import br.car.rental_company_abrao_lemos.dto.InactiveRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestRequest {

    public AddRequest getCarAddWithValidPlaca() throws IOException {
        String resourceName = "car-add-valid-placa.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public CarEntity getCar(AddRequest addRequest) {
        return CarEntity.builder()
                .placa(addRequest.getPlaca())
                .marca(addRequest.getMarca())
                .modelo(addRequest.getModelo())
                .combustivel(addRequest.getCombustivel())
                .anoFabricacao(addRequest.getAnoFabricacao())
                .anoModelo(addRequest.getAnoModelo())
                .activeCar(true)
                .build();
    }

    public CarEntity getCarAtual() {
        return CarEntity.builder()
                .placa("GRD4789")
                .marca("Hyundai")
                .modelo("HB20 Comfort")
                .combustivel("Flex")
                .anoFabricacao(2016)
                .anoModelo(2017)
                .activeCar(false)
                .build();
    }

    public CarEntity getCar() throws IOException {
        String resourceName = "get-car.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, CarEntity.class);
    }

    public AddRequest getCarAddWithInvalidPlacaNull() throws IOException {
        String resourceName = "car-add-invalid-placa-null.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public AddRequest getCarAddWithInvalidPlacaAtual() throws IOException {
        String resourceName = "car-add-invalid-placa-atual.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);

    }

    public AddRequest getCarAddWithInvalidMarcaNull() throws IOException {
        String resourceName = "car-add-invalid-marca.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public AddRequest getCarAddWithInvalidModeloNull() throws IOException {
        String resourceName = "car-add-invalid-modelo.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public AddRequest getCarAddWithInvalidCombustivelNull() throws IOException {
        String resourceName = "car-add-invalid-combustivel.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public AddRequest getCarAddWithInvalidAnoFabricacaoNull() throws IOException {
        String resourceName = "car-add-invalid-anofabricacao.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public AddRequest getCarAddWithInvalidAnoFabricacao2015() throws IOException {
        String resourceName = "car-add-invalid-anofabricacao-2015.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, AddRequest.class);
    }

    public ConsultRequest getCarConsultWithValidPlaca() throws IOException {
        String resourceName = "car-consult-valid-placa.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, ConsultRequest.class);
    }

    public ConsultRequest getCarConsultWithInvalidPlacaNull() throws IOException {
        String resourceName = "car-consult-invalid-placa-null.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, ConsultRequest.class);
    }

    public InactiveRequest getCarInactiveWithValidPlaca() throws IOException {
        String resourceName = "car-inactive-valid-placa.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, InactiveRequest.class);
    }

    public InactiveRequest getCarInactiveWithInvalidPlacaNull() throws IOException {
        String resourceName = "car-inactive-invalid-placa-null.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, InactiveRequest.class);
    }

    public UpdateRequest getCarUpdateWithValidPlaca() throws IOException {
        String resourceName = "car-update-valid-placa.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, UpdateRequest.class);
    }

    public UpdateRequest getCarUpdateWithInvalidPlacaNull() throws IOException {
        String resourceName = "car-update-invalid-placa-null.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return new ObjectMapper().readValue(file, UpdateRequest.class);
    }
}
