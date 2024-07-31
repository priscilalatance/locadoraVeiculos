package br.car.rental_company_abrao_lemos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Builder
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
@Table(name = "CAR")
public class CarEntity {

    @Id
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "COMBUSTIVEL")
    private String combustivel;
    @Column(name = "ANOFABRIC")
    private Integer anoFabricacao;
    @Column(name = "ANOMODEL")
    private Integer anoModelo;
    @Column(name = "IN_ATIVO")
    private Boolean activeCar;

}
