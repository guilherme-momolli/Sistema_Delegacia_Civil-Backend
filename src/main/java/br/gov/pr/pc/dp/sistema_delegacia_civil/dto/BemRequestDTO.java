package br.gov.pr.pc.dp.sistema_delegacia_civil.dto;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Bem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BemRequestDTO {

    private String tipoDetalhe;
    private String detalhesJson;
    private Bem bem;
    private MultipartFile imagem;
}
