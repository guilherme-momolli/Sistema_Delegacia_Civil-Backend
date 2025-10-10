package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.TipoBem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private String situacaoBem;
    private String origem;
    private String numeroLacre;
    private String localBem;
    private String observacao;
    private String descricao;
}