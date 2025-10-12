package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma;


import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmaRequestDTO {
    private Long bemId;
    private TipoArmaFogo tipoArmaFogo;
    private EspecieArma especieArma;
    private Calibre calibre;
    private String numeroPorte;
    private String numeroSerie;
    private String numeroRegistro;
    private String capacidade;
}
