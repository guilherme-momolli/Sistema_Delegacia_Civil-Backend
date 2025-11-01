package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Objeto;
import org.springframework.stereotype.Component;

@Component
public class ObjetoMapper {

    public static Objeto toEntity(ObjetoRequestDTO dto) {
        if (dto == null) return null;

        Objeto objeto = new Objeto();
        objeto.setId(dto.getId());
        objeto.setTipoObjeto(dto.getTipoObjeto());
        objeto.setNumeroSerie(dto.getNumeroSerie());
        objeto.setCor(dto.getCor());
        objeto.setMaterial(dto.getMaterial());
        objeto.setDimensoes(dto.getDimensoes());
        objeto.setEstadoConservacao(dto.getEstadoConservacao());
        objeto.setSituacaoObjeto(dto.getSituacaoObjeto());

        if (dto.getBemId() != null && dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            objeto.setBem(bem);
        }

        return objeto;
    }

    public static ObjetoResponseDTO toResponseDTO(Objeto entity) {
        if (entity == null) return null;

        ObjetoResponseDTO dto = new ObjetoResponseDTO();
        dto.setId(entity.getId());
        if (entity.getBem() != null) {
            Bem bem = entity.getBem();
            BemResponseDTO bemDTO = new BemResponseDTO();
            bemDTO.setId(bem.getId());
            dto.setBemId(bem.getId());
        }
        dto.setTipoObjeto(entity.getTipoObjeto());
        dto.setNumeroSerie(entity.getNumeroSerie());
        dto.setCor(entity.getCor());
        dto.setMaterial(entity.getMaterial());
        dto.setDimensoes(entity.getDimensoes());
        dto.setEstadoConservacao(entity.getEstadoConservacao());
        dto.setSituacaoObjeto(entity.getSituacaoObjeto());

        return dto;
    }

    public static void toUpdate(ObjetoRequestDTO dto, Objeto entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getTipoObjeto() != null) {
            entity.setTipoObjeto(dto.getTipoObjeto());
        }

        if (dto.getNumeroSerie() != null) {
            entity.setNumeroSerie(dto.getNumeroSerie());
        }

        if (dto.getCor() != null) {
            entity.setCor(dto.getCor());
        }

        if (dto.getMaterial() != null) {
            entity.setMaterial(dto.getMaterial());
        }

        if (dto.getDimensoes() != null) {
            entity.setDimensoes(dto.getDimensoes());
        }

        if (dto.getEstadoConservacao() != null) {
            entity.setEstadoConservacao(dto.getEstadoConservacao());
        }

        if (dto.getSituacaoObjeto() != null) {
            entity.setSituacaoObjeto(dto.getSituacaoObjeto());
        }

        if (dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }
    }

}