package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dto.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CepService {
    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public ViaCepResponseDTO consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = UriComponentsBuilder
                .fromUriString(VIACEP_URL)
                .buildAndExpand(cep)
                .toUriString();

        return restTemplate.getForObject(uri, ViaCepResponseDTO.class);
    }
}
