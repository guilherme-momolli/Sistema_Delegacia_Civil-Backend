package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrogaRequestDTO {
    private Long id;
    private Long bemId;
    private String tipoDroga;
    private String nomePopular;
    private String unidadeMedida;
    private String quantidadePorExtenso;
}
