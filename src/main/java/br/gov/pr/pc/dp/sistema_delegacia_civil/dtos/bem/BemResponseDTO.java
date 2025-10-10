package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.TipoBem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String situacaoBem;
    private String origem;
    private String numeroLacre;
    private String localBem;
    private String observacao;
    private String descricao;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}