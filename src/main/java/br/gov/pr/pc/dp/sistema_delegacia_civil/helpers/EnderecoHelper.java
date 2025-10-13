package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoHelper {

    public static Endereco resolverEnderecoRequestDTO(EnderecoRequestDTO dto, EnderecoRepository repository) {
        if (dto.getId() != null) {
            return repository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + dto.getId()));
        }
        Endereco novo = new Endereco();
        novo.setLogradouro(dto.getLogradouro());
        novo.setNumero(dto.getNumero());
        novo.setBairro(dto.getBairro());
        novo.setMunicipio(dto.getMunicipio());
        novo.setUf(dto.getUf());
        novo.setPais(dto.getPais());
        novo.setCep(dto.getCep());
        return repository.save(novo);
    }

    public static Endereco resolverEntidadeEndereco(Endereco endereco, EnderecoRepository repository) {
        if (endereco.getId() != null) {
            return repository.findById(endereco.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + endereco.getId()));
        }
        return repository.save(endereco);
    }
}

