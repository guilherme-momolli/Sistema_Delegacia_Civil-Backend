package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.TipoEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEnvolvimentoResponseDTO {

    private Long id;
    private Long pessoaId;
    private Long boletimId;
    private Long inqueritoId;
    private TipoEnvolvimento tipoEnvolvimento;
    private String observacao;

    public static PessoaEnvolvimentoResponseDTO fromEntity(PessoaEnvolvimento entity) {
        return new PessoaEnvolvimentoResponseDTO(
                entity.getId(),
                entity.getPessoa() != null ? entity.getPessoa().getId() : null,
                entity.getBoletimOcorrencia() != null ? entity.getBoletimOcorrencia().getId() : null,
                entity.getInqueritoPolicial() != null ? entity.getInqueritoPolicial().getId() : null,
                entity.getTipoEnvolvimento(),
                entity.getObservacao()
        );
    }
}
