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

}
