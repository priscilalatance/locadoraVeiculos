package br.car.rental_company_abrao_lemos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@With
public class AddRequest {

    @NotBlank(message = "A placa deve ser preenchida")
    private String placa;
    @NotBlank(message = "A marca deve ser preenchida")
    private String marca;
    @NotBlank(message = "O modelo deve ser preenchido")
    private String modelo;
    @NotBlank(message = "O combustivel deve ser preenchido")
    private String combustivel;
    @NotNull(message = "O ano de fabricação deve ser preenchido")
    @Min(value = 2015, message = "O ano de fabricação deve ser maior que 2014")
    private Integer anoFabricacao;
    @NotNull(message = "O ano modelo deve ser preenchido")
    @Min(value = 2015, message = "O ano de fabricação deve ser maior que 2014")
    private Integer anoModelo;

}
