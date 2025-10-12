package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.SituacaoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class BemResponseDTO {
    private Long id;
    private TipoBem tipoBem;
    private String imagemUrl;
    private String marca;
    private String modelo;
    private BigDecimal valorEstimado;
    private Long pessoaId;
    private Long delegaciaId;
    private Long instituicaoId;
    private SituacaoBem situacaoBem;
    private String origem;
    private String numeroLacre;
    private String localBem;
    private String observacao;
    private String descricao;
    private ArmaResponseDTO arma;
    private ObjetoResponseDTO objeto;
    private VeiculoResponseDTO veiculo;
    private DrogaResponseDTO droga;
}