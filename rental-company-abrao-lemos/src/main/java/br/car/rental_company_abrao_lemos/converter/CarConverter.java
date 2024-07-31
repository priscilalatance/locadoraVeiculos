package br.car.rental_company_abrao_lemos.converter;

import br.car.rental_company_abrao_lemos.dto.AddRequest;
import br.car.rental_company_abrao_lemos.dto.DeleteRequest;
import br.car.rental_company_abrao_lemos.dto.UpdateRequest;
import br.car.rental_company_abrao_lemos.entities.CarEntity;
import br.car.rental_company_abrao_lemos.utils.Consts;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class CarConverter {

    public CarEntity converAdd(AddRequest addRequest) {

        return CarEntity.builder()
                .placa(addRequest.getPlaca())
                .marca(addRequest.getMarca())
                .modelo(addRequest.getModelo())
                .combustivel(addRequest.getCombustivel())
                .anoFabricacao(addRequest.getAnoFabricacao())
                .anoModelo(addRequest.getAnoModelo())
                .activeCar(Consts.ACTIVE)
                .build();

    }

    public CarEntity converUpdate(UpdateRequest updateRequest) {
         return CarEntity.builder()
                .placa(updateRequest.getPlaca())
                .marca(updateRequest.getModelo())
                .combustivel(updateRequest.getCombustivel())
                .anoFabricacao(updateRequest.getAnoFabricacao())
                .anoModelo(updateRequest.getAnoModelo())
                .build();
        
        
    }

    public CarEntity convertDelete(DeleteRequest deleteRequest) {
        final CarEntity carEntity = CarEntity.builder()
                .placa(deleteRequest.getPlaca())
                .build();
        return carEntity;        
    }
}
