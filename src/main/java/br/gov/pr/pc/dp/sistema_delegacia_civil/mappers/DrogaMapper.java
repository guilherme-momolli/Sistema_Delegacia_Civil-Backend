//package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
//
//public class DrogaMapper {
//
//    public static Droga toEntity(DrogaRequestDTO dto, Bem bem) {
//        if (dto == null) return null;
//
//        Droga droga = new Droga();
//        droga.setId(dto.getId());
//        droga.setBem(bem);
//        droga.setTipoDroga(dto.getTipoDroga());
//        droga.setNomePopular(dto.getNomePopular());
//        droga.setUnidadeMedida(dto.getUnidadeMedida());
//        droga.setQuantidadePorExtenso(dto.getQuantidadePorExtenso());
//
//        return droga;
//    }
//
//    public static DrogaResponseDTO toResponseDTO(Droga entity) {
//        if (entity == null) return null;
//
//        DrogaResponseDTO dto = new DrogaResponseDTO();
//        dto.setId(entity.getId());
//        dto.setBemId(entity.getBem() != null ? entity.getBem().getId() : null);
//        dto.setTipoDroga(entity.getTipoDroga());
//        dto.setNomePopular(entity.getNomePopular());
//        dto.setUnidadeMedida(entity.getUnidadeMedida());
//        dto.setQuantidadePorExtenso(entity.getQuantidadePorExtenso());
//        dto.setCreatedAt(entity.getCreatedAt());
//        dto.setUpdatedAt(entity.getUpdatedAt());
//
//        return dto;
//    }
//}