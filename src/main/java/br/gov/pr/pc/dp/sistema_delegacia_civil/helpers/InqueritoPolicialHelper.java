package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial.InqueritoPolicialException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.InqueritoPolicialMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InqueritoPolicialHelper {

    private final EntityHelper entityHelper;

    public InqueritoPolicial montarInqueritoParaCriacao(InqueritoPolicialRequestDTO requestDTO) {
        if (requestDTO.getDelegaciaId() == null)
            throw new InqueritoPolicialException(ErrorType.VALIDACAO, "Delegacia é obrigatória para criar o inquérito.");

        entityHelper.validarDelegaciaExistente(requestDTO.getDelegaciaId());
        Delegacia delegacia = new Delegacia();
        delegacia.setId(requestDTO.getDelegaciaId());

        return InqueritoPolicialMapper.toEntity(requestDTO, delegacia);
    }

    public void atualizarDelegacia(InqueritoPolicial inquerito, Long delegaciaId) {
        if (delegaciaId != null) {
            entityHelper.validarDelegaciaExistente(delegaciaId);
            Delegacia delegacia = new Delegacia();
            delegacia.setId(delegaciaId);
            inquerito.setDelegacia(delegacia);
        }
    }
}
