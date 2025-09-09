package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.Pais;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.UF;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {

    private Long numero;
    private String logradouro;
    private String bairro;
    private String municipio;
    private UF uf;
    private Pais pais;
    private String cep;

    public Endereco toEntity(){
        Endereco entity = new Endereco();
        entity.setCep(this.cep);
        entity.setNumero(this.numero);
        entity.setLogradouro(this.logradouro);
        entity.setBairro(this.bairro);
        entity.setMunicipio(this.municipio);
        entity.setUf(this.uf);
        entity.setPais(this.pais);
        return entity;
    }

}
