package br.car.rental_company_abrao_lemos.repository;

import br.car.rental_company_abrao_lemos.entities.CarEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String>{
    Optional<CarEntity> findByPlaca(String placa);
}
