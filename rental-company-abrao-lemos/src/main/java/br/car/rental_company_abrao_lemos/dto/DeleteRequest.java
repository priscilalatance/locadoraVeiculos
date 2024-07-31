package br.car.rental_company_abrao_lemos.dto;

import jakarta.validation.constraints.NotBlank;
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
public class DeleteRequest {
    @NotBlank(message = "A placa deve ser preenchida")
    private String placa;
}
