package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegaciaDTO {
    private Long id;
    private String nome;
    private String email;
    private String secretaria;
    private String telefoneFixo;
    private String telefoneCelular;
    private EnderecoDTO endereco;

}
