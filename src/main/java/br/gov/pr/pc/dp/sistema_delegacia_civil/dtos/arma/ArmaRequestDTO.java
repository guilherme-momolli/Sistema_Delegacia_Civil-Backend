package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma;


import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmaRequestDTO {
    private BemRequestDTO bemId;
    private String tipoArmaFogo;
    private String especie;
    private String calibre;
    private String numeroPorte;
    private String numeroSerie;
    private String numeroRegistro;
    private String capacidade;
}
