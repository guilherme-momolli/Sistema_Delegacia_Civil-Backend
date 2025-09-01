package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Genero;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.SituacaoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFiltroDTO {
    private String nome;
    private String cpf;
    private String rg;
    private Genero sexo;
    private SituacaoPessoa situacaoPessoa;
}