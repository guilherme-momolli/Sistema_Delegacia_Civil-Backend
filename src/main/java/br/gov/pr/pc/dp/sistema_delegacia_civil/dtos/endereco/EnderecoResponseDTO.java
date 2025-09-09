package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.Pais;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.UF;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {

    private Long id;
    private Long numero;
    private String logradouro;
    private String bairro;
    private String municipio;
    private UF uf;
    private Pais pais;
    private String cep;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static EnderecoResponseDTO fromEntity(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getMunicipio(),
                endereco.getUf(),
                endereco.getPais(),
                endereco.getCep(),
                endereco.getCreatedAt(),
                endereco.getUpdatedAt()
        );
    }
}
