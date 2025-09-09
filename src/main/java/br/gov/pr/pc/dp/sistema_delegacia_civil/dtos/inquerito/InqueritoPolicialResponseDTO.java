package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InqueritoPolicialResponseDTO {
    private Long id;
    private String numeroJustica;
    private Long ordemIp;
    private LocalDate data;
    private Peca peca;
    private SituacaoInquerito situacaoInquerito;
    private OrigemForcaPolicial origemForcaPolicial;
    private String naturezaDoDelito;
    private String observacao;
    private Long delegaciaId;
    private List<PessoaEnvolvimentoResponseDTO> pessoasEnvolvidas = new ArrayList<>();

    public static InqueritoPolicialResponseDTO fromEntity(InqueritoPolicial inquerito) {
        InqueritoPolicialResponseDTO dto = new InqueritoPolicialResponseDTO();
        dto.setId(inquerito.getId());
        dto.setNumeroJustica(inquerito.getNumeroJustica());
        dto.setOrdemIp(inquerito.getOrdemIp());
        dto.setData(inquerito.getData());
        dto.setPeca(inquerito.getPeca());
        dto.setSituacaoInquerito(inquerito.getSituacaoInquerito());
        dto.setOrigemForcaPolicial(inquerito.getOrigemForcaPolicial());
        dto.setNaturezaDoDelito(inquerito.getNaturezaDoDelito());
        dto.setObservacao(inquerito.getObservacao());

        if (inquerito.getDelegacia() != null) {
            dto.setDelegaciaId(inquerito.getDelegacia().getId());
        }

        if (inquerito.getPessoasEnvolvidas() != null) {
            List<PessoaEnvolvimentoResponseDTO> pessoas = inquerito.getPessoasEnvolvidas()
                    .stream()
                    .map(PessoaEnvolvimentoResponseDTO::fromEntity)
                    .collect(Collectors.toList());
            dto.setPessoasEnvolvidas(pessoas);
        }
        return dto;
    }
}
