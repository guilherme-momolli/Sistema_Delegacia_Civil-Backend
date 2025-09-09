package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegaciaRequestDTO {

    private String nome;
    private String email;
    private String secretaria;
    private String telefoneFixo;
    private String telefoneCelular;
    private String imagemUrl;
    private EnderecoRequestDTO endereco;
    private String senha;

    public Delegacia toEntity() {
        Delegacia delegacia = new Delegacia();
        delegacia.setNome(this.nome);
        delegacia.setEmail(this.email);
        delegacia.setSecretaria(this.secretaria);
        delegacia.setTelefoneFixo(this.telefoneFixo);
        delegacia.setTelefoneCelular(this.telefoneCelular);
        delegacia.setImagemUrl(this.imagemUrl);

        if (this.endereco != null) {
            Endereco enderecoEntity = this.endereco.toEntity(); // Converte EnderecoRequestDTO em Endereco
            delegacia.setEndereco(enderecoEntity);
        }

        return delegacia;
    }
}

