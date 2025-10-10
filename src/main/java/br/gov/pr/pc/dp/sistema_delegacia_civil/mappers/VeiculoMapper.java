//package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Veiculo;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VeiculoMapper {
//
//    public static Veiculo toEntity(VeiculoRequestDTO dto, Bem bem) {
//        if (dto == null) return null;
//
//        Veiculo veiculo = new Veiculo();
//        veiculo.setId(dto.getId());
//        veiculo.setBem(bem);
//        veiculo.setRenavam(dto.getRenavam());
//        veiculo.setPlaca(dto.getPlaca());
//        veiculo.setChassi(dto.getChassi());
//        veiculo.setNumeroMotor(dto.getNumeroMotor());
//        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
//        veiculo.setCategoria(dto.getCategoria());
//        veiculo.setEspecie(dto.getEspecie());
//        veiculo.setAnoModelo(dto.getAnoModelo());
//        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
//        veiculo.setCombustivel(dto.getCombustivel());
//        veiculo.setCambio(dto.getCambio());
//        veiculo.setTipoTracao(dto.getTipoTracao());
//        veiculo.setCorPredominante(dto.getCorPredominante());
//        veiculo.setCarroceria(dto.getCarroceria());
//        veiculo.setNumeroEixos(dto.getNumeroEixos());
//        veiculo.setCapacidadeCarga(dto.getCapacidadeCarga());
//        veiculo.setPotenciaMotor(dto.getPotenciaMotor());
//        veiculo.setCilindrada(dto.getCilindrada());
//        veiculo.setPesoBruto(dto.getPesoBruto());
//        veiculo.setUfRegistro(dto.getUfRegistro());
//        veiculo.setMunicipioRegistro(dto.getMunicipioRegistro());
//        veiculo.setSituacaoVeiculo(dto.getSituacaoVeiculo());
//        veiculo.setSituacaoLicenciamento(dto.getSituacaoLicenciamento());
//        veiculo.setRestricaoJudicial(dto.getRestricaoJudicial());
//        veiculo.setDataPrimeiroLicenciamento(dto.getDataPrimeiroLicenciamento());
//        veiculo.setNumeroCrv(dto.getNumeroCrv());
//        veiculo.setNumeroCrlv(dto.getNumeroCrlv());
//        veiculo.setTabelaFipe(dto.getTabelaFipe());
//
//        return veiculo;
//    }
//
//    public static VeiculoResponseDTO toResponseDTO(Veiculo entity) {
//        if (entity == null) return null;
//
//        VeiculoResponseDTO dto = new VeiculoResponseDTO();
//        dto.setId(entity.getId());
//        dto.setBemId(entity.getBem() != null ? entity.getBem().getId() : null);
//        dto.setRenavam(entity.getRenavam());
//        dto.setPlaca(entity.getPlaca());
//        dto.setChassi(entity.getChassi());
//        dto.setNumeroMotor(entity.getNumeroMotor());
//        dto.setTipoVeiculo(entity.getTipoVeiculo());
//        dto.setCategoria(entity.getCategoria());
//        dto.setEspecie(entity.getEspecie());
//        dto.setAnoModelo(entity.getAnoModelo());
//        dto.setAnoFabricacao(entity.getAnoFabricacao());
//        dto.setCombustivel(entity.getCombustivel());
//        dto.setCambio(entity.getCambio());
//        dto.setTipoTracao(entity.getTipoTracao());
//        dto.setCorPredominante(entity.getCorPredominante());
//        dto.setCarroceria(entity.getCarroceria());
//        dto.setNumeroEixos(entity.getNumeroEixos());
//        dto.setCapacidadeCarga(entity.getCapacidadeCarga());
//        dto.setPotenciaMotor(entity.getPotenciaMotor());
//        dto.setCilindrada(entity.getCilindrada());
//        dto.setPesoBruto(entity.getPesoBruto());
//        dto.setUfRegistro(entity.getUfRegistro());
//        dto.setMunicipioRegistro(entity.getMunicipioRegistro());
//        dto.setSituacaoVeiculo(entity.getSituacaoVeiculo());
//        dto.setSituacaoLicenciamento(entity.getSituacaoLicenciamento());
//        dto.setRestricaoJudicial(entity.getRestricaoJudicial());
//        dto.setDataPrimeiroLicenciamento(entity.getDataPrimeiroLicenciamento());
//        dto.setNumeroCrv(entity.getNumeroCrv());
//        dto.setNumeroCrlv(entity.getNumeroCrlv());
//        dto.setTabelaFipe(entity.getTabelaFipe());
//        dto.setCreatedAt(entity.getCreatedAt());
//        dto.setUpdatedAt(entity.getUpdatedAt());
//
//        return dto;
//    }
//}