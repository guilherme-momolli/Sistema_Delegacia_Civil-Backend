package br.gov.pr.pc.dp.sistema_delegacia_civil.dto;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Privilegio;
import lombok.Data;

@Data
public class CadastroRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private Long instituicaoId;
    private Privilegio privilegio;

}