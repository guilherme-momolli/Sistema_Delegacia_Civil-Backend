package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.TipoEnvolvimento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEnvolvimentoDTO {

    Long id;
    @NotNull(message = "O ID da pessoa é obrigatório")
    Long pessoaId;
    Long boletimId;
    Long inqueritoId;
    @NotNull(message = "O tipo de envolvimento é obrigatório")
    TipoEnvolvimento tipoEnvolvimento;
    @Size(max = 5000, message = "A observação pode ter no máximo 5000 caracteres")
    String observacao;

}
