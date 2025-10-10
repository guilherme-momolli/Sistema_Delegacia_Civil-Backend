package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.ArmaMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.ArmaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArmaService {

    private final ArmaRepository armaRepository;
    private final BemRepository bemRepository;

    @Transactional
    public ArmaResponseDTO create(ArmaRequestDTO dto) {
        Arma arma = ArmaMapper.toEntity(dto);

        if (arma.getBem() != null && arma.getBem().getId() != null) {
            Bem bem = bemRepository.findById(arma.getBem().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bem não encontrado com ID: " + arma.getBem().getId()));
            arma.setBem(bem);
        }

        Arma saved = armaRepository.save(arma);
        return ArmaMapper.toResponseDTO(saved);
    }

    public List<ArmaResponseDTO> listAll() {
        return armaRepository.findAll()
                .stream()
                .map(ArmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ArmaResponseDTO getById(Long id) {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arma não encontrada com ID: " + id));
        return ArmaMapper.toResponseDTO(arma);
    }

    @Transactional
    public ArmaResponseDTO update(Long id, ArmaRequestDTO dto) {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arma não encontrada com ID: " + id));

        Arma updatedData = ArmaMapper.toEntity(dto);

        arma.setTipoArmaFogo(updatedData.getTipoArmaFogo());
        arma.setEspecie(updatedData.getEspecie());
        arma.setCalibre(updatedData.getCalibre());
        arma.setNumeroPorte(updatedData.getNumeroPorte());
        arma.setNumeroSerie(updatedData.getNumeroSerie());
        arma.setNumeroRegistro(updatedData.getNumeroRegistro());
        arma.setCapacidade(updatedData.getCapacidade());

        if (updatedData.getBem() != null && updatedData.getBem().getId() != null) {
            Bem bem = bemRepository.findById(updatedData.getBem().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bem não encontrado com ID: " + updatedData.getBem().getId()));
            arma.setBem(bem);
        }

        Arma saved = armaRepository.save(arma);
        return ArmaMapper.toResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arma não encontrada com ID: " + id));
        armaRepository.delete(arma);
    }
}

