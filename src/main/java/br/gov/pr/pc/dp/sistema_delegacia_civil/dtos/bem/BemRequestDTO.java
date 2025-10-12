package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.SituacaoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BemRequestDTO {

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
    private ArmaRequestDTO arma;
    private ObjetoRequestDTO objeto;
    private DrogaRequestDTO droga;
    private VeiculoRequestDTO veiculo;
}