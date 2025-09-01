package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InqueritoPolicialRequestDTO {

    private InqueritoPolicialDTO inqueritoPolicial;

    private List<PessoaEnvolvimentoDTO> pessoasEnvolvidas;

    public InqueritoPolicial toEntity() {
        InqueritoPolicial inquerito = new InqueritoPolicial();
        inquerito.setId(inqueritoPolicial.getId());
        inquerito.setNumeroJustica(inqueritoPolicial.getNumeroJustica());
        inquerito.setOrdemIp(inqueritoPolicial.getOrdemIp());
        inquerito.setData(inqueritoPolicial.getData());
        inquerito.setPeca(inqueritoPolicial.getPeca());
        inquerito.setSituacaoInquerito(inqueritoPolicial.getSituacaoInquerito());
        inquerito.setOrigemForcaPolicial(inqueritoPolicial.getOrigemForcaPolicial());
        inquerito.setNaturezaDoDelito(inqueritoPolicial.getNaturezaDoDelito());
        inquerito.setObservacao(inqueritoPolicial.getObservacao());
        if (inqueritoPolicial.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(inqueritoPolicial.getDelegaciaId());
            inquerito.setDelegacia(delegacia);
        }
        return inquerito;
    }
}
