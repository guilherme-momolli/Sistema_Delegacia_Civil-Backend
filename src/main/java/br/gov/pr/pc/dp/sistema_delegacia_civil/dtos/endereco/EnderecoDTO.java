package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.Pais;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.UF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;
    private Long numero;
    private String logradouro;
    private String bairro;
    private String municipio;
    private UF uf;
    private Pais pais;
    private String cep;

}
