package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegaciaResponseDTO {

    private Long id;
    private String imagemUrl;
    private String nome;
    private String email;
    private String secretaria;
    private String telefoneFixo;
    private String telefoneCelular;
    private EnderecoResponseDTO endereco;

    public static DelegaciaResponseDTO fromEntity(Delegacia delegacia) {
        if (delegacia == null) return null;

        DelegaciaResponseDTO dto = new DelegaciaResponseDTO();
        dto.setId(delegacia.getId());
        dto.setImagemUrl(delegacia.getImagemUrl());
        dto.setNome(delegacia.getNome());
        dto.setEmail(delegacia.getEmail());
        dto.setSecretaria(delegacia.getSecretaria());
        dto.setTelefoneFixo(delegacia.getTelefoneFixo());
        dto.setTelefoneCelular(delegacia.getTelefoneCelular());

        if (delegacia.getEndereco() != null) {
            dto.setEndereco(EnderecoResponseDTO.fromEntity(delegacia.getEndereco()));
        }

        return dto;
    }
}
