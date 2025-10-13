package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import org.springframework.stereotype.Component;

@Component
public class DelegaciaMapper {

    public static DelegaciaResponseDTO toResponseDTO(Delegacia delegacia) {
        if (delegacia == null) return null;

        return new DelegaciaResponseDTO(
                delegacia.getId(),
                delegacia.getImagemUrl(),
                delegacia.getNome(),
                delegacia.getEmail(),
                delegacia.getSecretaria(),
                delegacia.getTelefoneFixo(),
                delegacia.getTelefoneCelular(),
                EnderecoMapper.toResponseDTO(delegacia.getEndereco())
        );
    }

    public static Delegacia toEntity(DelegaciaRequestDTO dto) {
        if (dto == null) return null;

        Delegacia delegacia = new Delegacia();
        delegacia.setNome(dto.getNome());
        delegacia.setEmail(dto.getEmail());
        delegacia.setSecretaria(dto.getSecretaria());
        delegacia.setTelefoneFixo(dto.getTelefoneFixo());
        delegacia.setTelefoneCelular(dto.getTelefoneCelular());
        delegacia.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        return delegacia;
    }
}
