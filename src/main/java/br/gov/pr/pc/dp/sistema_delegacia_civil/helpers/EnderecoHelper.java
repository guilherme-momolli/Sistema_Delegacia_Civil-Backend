package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoHelper {

    private final EnderecoService enderecoService;

    public Endereco resolveEndereco(Endereco endereco) {
        if (endereco == null) return null;
        return endereco.getId() == null
                ? enderecoService.createEndereco(endereco)
                : enderecoService.getById(endereco.getId());
    }
}