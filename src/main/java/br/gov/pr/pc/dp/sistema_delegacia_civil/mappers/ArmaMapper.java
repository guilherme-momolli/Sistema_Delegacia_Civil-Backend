package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.SituacaoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ArmaMapper {

    public static Arma toEntity(ArmaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Arma entity = new Arma();

        entity.setSituacaoArmaFogo(dto.getSituacaoArmaFogo());
        entity.setTipoArmaFogo(dto.getTipoArmaFogo());
        entity.setEspecieArma(dto.getEspecieArma());
        entity.setCalibre(dto.getCalibre());
        entity.setNumeroPorte(dto.getNumeroPorte());
        entity.setNumeroSerie(dto.getNumeroSerie());
        entity.setNumeroRegistro(dto.getNumeroRegistro());
        entity.setCapacidade(dto.getCapacidade());

        if (dto.getBemId() != null && dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }

        return entity;
    }

    public static ArmaResponseDTO toResponseDTO(Arma entity) {
        if (entity == null) {
            return null;
        }

        ArmaResponseDTO dto = new ArmaResponseDTO();

        dto.setId(entity.getId());
        dto.setTipoArmaFogo(entity.getTipoArmaFogo());
        dto.setEspecieArma(entity.getEspecieArma());
        dto.setCalibre(entity.getCalibre());
        dto.setSituacaoArmaFogo(entity.getSituacaoArmaFogo());
        dto.setNumeroPorte(entity.getNumeroPorte());
        dto.setNumeroSerie(entity.getNumeroSerie());
        dto.setNumeroRegistro(entity.getNumeroRegistro());
        dto.setCapacidade(entity.getCapacidade());

        if (entity.getBem() != null) {
            Bem bem = entity.getBem();
            BemResponseDTO bemDTO = new BemResponseDTO();
            bemDTO.setId(bem.getId());
            dto.setBemId(bem.getId());
        }

        return dto;
    }

    public static void toUpdate(ArmaRequestDTO dto, Arma entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getTipoArmaFogo() != null) {
            entity.setTipoArmaFogo(dto.getTipoArmaFogo());
        }

        if (dto.getEspecieArma() != null) {
            entity.setEspecieArma(dto.getEspecieArma());
        }

        if (dto.getSituacaoArmaFogo() != null){
            entity.setSituacaoArmaFogo(dto.getSituacaoArmaFogo());
        }

        if (dto.getCalibre() != null) {
            entity.setCalibre(dto.getCalibre());
        }

        if (dto.getNumeroPorte() != null) {
            entity.setNumeroPorte(dto.getNumeroPorte());
        }

        if (dto.getNumeroSerie() != null) {
            entity.setNumeroSerie(dto.getNumeroSerie());
        }

        if (dto.getNumeroRegistro() != null) {
            entity.setNumeroRegistro(dto.getNumeroRegistro());
        }

        if (dto.getCapacidade() != null) {
            entity.setCapacidade(dto.getCapacidade());
        }

        if (dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }
    }

    public static ArmaDashboardResponseDTO toArmaDashboard(List<Arma> armas){

        Map<Calibre, Long> totalPorCalibre = armas.stream()
                .collect(Collectors.groupingBy(Arma::getCalibre, Collectors.counting()));

        Map<EspecieArma, Long> totalPorEspecieArma = armas.stream()
                .collect(Collectors.groupingBy(Arma::getEspecieArma, Collectors.counting()));

        Map<SituacaoArmaFogo, Long> totalPorSituacaoArmaFogo = armas.stream()
                .collect(Collectors.groupingBy(Arma::getSituacaoArmaFogo, Collectors.counting()));

        Map<TipoArmaFogo, Long> totalPorTipoArmaFogo = armas.stream()
                .collect(Collectors.groupingBy(Arma::getTipoArmaFogo, Collectors.counting()));

        return new ArmaDashboardResponseDTO(totalPorCalibre, totalPorEspecieArma, totalPorSituacaoArmaFogo, totalPorTipoArmaFogo);
    }

}
