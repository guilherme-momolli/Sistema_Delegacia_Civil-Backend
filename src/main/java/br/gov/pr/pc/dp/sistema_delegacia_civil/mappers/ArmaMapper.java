package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import org.springframework.stereotype.Component;

@Component
public class ArmaMapper {

    public static Arma toEntity(ArmaRequestDTO dto) {
        if (dto == null) return null;

        Arma arma = new Arma();

        if (dto.getBemId() != null) {
            Bem bem = BemMapper.toEntity(dto.getBemId());
            arma.setBem(bem);
        }

        arma.setTipoArmaFogo(dto.getTipoArmaFogo());
        arma.setEspecie(dto.getEspecie());
        arma.setCalibre(dto.getCalibre());
        arma.setNumeroPorte(dto.getNumeroPorte());
        arma.setNumeroSerie(dto.getNumeroSerie());
        arma.setNumeroRegistro(dto.getNumeroRegistro());
        arma.setCapacidade(dto.getCapacidade());

        return arma;
    }

    public static ArmaResponseDTO toResponseDTO(Arma arma) {
        if (arma == null) return null;

        ArmaResponseDTO dto = new ArmaResponseDTO();

        dto.setId(arma.getId());
        dto.setBemId(BemMapper.toResponseDTO(arma.getBem()));
        dto.setTipoArmaFogo(arma.getTipoArmaFogo());
        dto.setEspecie(arma.getEspecie());
        dto.setCalibre(arma.getCalibre());
        dto.setNumeroPorte(arma.getNumeroPorte());
        dto.setNumeroSerie(arma.getNumeroSerie());
        dto.setNumeroRegistro(arma.getNumeroRegistro());
        dto.setCapacidade(arma.getCapacidade());
        dto.setCreatedAt(arma.getCreatedAt());
        dto.setUpdatedAt(arma.getUpdatedAt());

        return dto;
    }
}

