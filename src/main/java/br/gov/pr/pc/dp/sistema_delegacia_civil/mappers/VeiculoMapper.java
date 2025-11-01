package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.TipoDroga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.veiculo.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Veiculo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    public static Veiculo toEntity(VeiculoRequestDTO dto) {
        if (dto == null) return null;

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setRenavam(dto.getRenavam());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setChassi(dto.getChassi());
        veiculo.setNumeroMotor(dto.getNumeroMotor());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setCategoria(dto.getCategoria());
        veiculo.setEspecieVeiculo(dto.getEspecieVeiculo());
        veiculo.setAnoModelo(dto.getAnoModelo());
        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        veiculo.setCombustivel(dto.getCombustivel());
        veiculo.setCambio(dto.getCambio());
        veiculo.setTipoTracao(dto.getTipoTracao());
        veiculo.setCorPredominante(dto.getCorPredominante());
        veiculo.setCarroceria(dto.getCarroceria());
        veiculo.setNumeroEixos(dto.getNumeroEixos());
        veiculo.setCapacidadeCarga(dto.getCapacidadeCarga());
        veiculo.setPotenciaMotor(dto.getPotenciaMotor());
        veiculo.setCilindrada(dto.getCilindrada());
        veiculo.setPesoBruto(dto.getPesoBruto());
        veiculo.setUfRegistro(dto.getUfRegistro());
        veiculo.setMunicipioRegistro(dto.getMunicipioRegistro());
        veiculo.setSituacaoVeiculo(dto.getSituacaoVeiculo());
        veiculo.setSituacaoLicenciamento(dto.getSituacaoLicenciamento());
        veiculo.setRestricaoJudicial(dto.getRestricaoJudicial());
        veiculo.setDataPrimeiroLicenciamento(dto.getDataPrimeiroLicenciamento());
        veiculo.setNumeroCrv(dto.getNumeroCrv());
        veiculo.setNumeroCrlv(dto.getNumeroCrlv());
        veiculo.setTabelaFipe(dto.getTabelaFipe());

        if (dto.getBemId() != null && dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            veiculo.setBem(bem);
        }

        return veiculo;
    }

    public static VeiculoResponseDTO toResponseDTO(Veiculo entity) {
        if (entity == null) return null;

        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        dto.setId(entity.getId());
        if (entity.getBem() != null) {
            Bem bem = entity.getBem();
            BemResponseDTO bemDTO = new BemResponseDTO();
            bemDTO.setId(bem.getId());
            dto.setBemId(bem.getId());
        }
        dto.setRenavam(entity.getRenavam());
        dto.setPlaca(entity.getPlaca());
        dto.setChassi(entity.getChassi());
        dto.setNumeroMotor(entity.getNumeroMotor());
        dto.setTipoVeiculo(entity.getTipoVeiculo());
        dto.setCategoria(entity.getCategoria());
        dto.setEspecieVeiculo(entity.getEspecieVeiculo());
        dto.setAnoModelo(entity.getAnoModelo());
        dto.setAnoFabricacao(entity.getAnoFabricacao());
        dto.setCombustivel(entity.getCombustivel());
        dto.setCambio(entity.getCambio());
        dto.setTipoTracao(entity.getTipoTracao());
        dto.setCorPredominante(entity.getCorPredominante());
        dto.setCarroceria(entity.getCarroceria());
        dto.setNumeroEixos(entity.getNumeroEixos());
        dto.setCapacidadeCarga(entity.getCapacidadeCarga());
        dto.setPotenciaMotor(entity.getPotenciaMotor());
        dto.setCilindrada(entity.getCilindrada());
        dto.setPesoBruto(entity.getPesoBruto());
        dto.setUfRegistro(entity.getUfRegistro());
        dto.setMunicipioRegistro(entity.getMunicipioRegistro());
        dto.setSituacaoVeiculo(entity.getSituacaoVeiculo());
        dto.setSituacaoLicenciamento(entity.getSituacaoLicenciamento());
        dto.setRestricaoJudicial(entity.getRestricaoJudicial());
        dto.setDataPrimeiroLicenciamento(entity.getDataPrimeiroLicenciamento());
        dto.setNumeroCrv(entity.getNumeroCrv());
        dto.setNumeroCrlv(entity.getNumeroCrlv());
        dto.setTabelaFipe(entity.getTabelaFipe());

        return dto;
    }

    public static void toUpdate(VeiculoRequestDTO dto, Veiculo entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getRenavam() != null) entity.setRenavam(dto.getRenavam());
        if (dto.getPlaca() != null) entity.setPlaca(dto.getPlaca());
        if (dto.getChassi() != null) entity.setChassi(dto.getChassi());
        if (dto.getNumeroMotor() != null) entity.setNumeroMotor(dto.getNumeroMotor());
        if (dto.getTipoVeiculo() != null) entity.setTipoVeiculo(dto.getTipoVeiculo());
        if (dto.getCategoria() != null) entity.setCategoria(dto.getCategoria());
        if (dto.getEspecieVeiculo() != null) entity.setEspecieVeiculo(dto.getEspecieVeiculo());
        if (dto.getAnoModelo() != null) entity.setAnoModelo(dto.getAnoModelo());
        if (dto.getAnoFabricacao() != null) entity.setAnoFabricacao(dto.getAnoFabricacao());
        if (dto.getCombustivel() != null) entity.setCombustivel(dto.getCombustivel());
        if (dto.getCambio() != null) entity.setCambio(dto.getCambio());
        if (dto.getTipoTracao() != null) entity.setTipoTracao(dto.getTipoTracao());
        if (dto.getCorPredominante() != null) entity.setCorPredominante(dto.getCorPredominante());
        if (dto.getCarroceria() != null) entity.setCarroceria(dto.getCarroceria());
        if (dto.getNumeroEixos() != null) entity.setNumeroEixos(dto.getNumeroEixos());
        if (dto.getCapacidadeCarga() != null) entity.setCapacidadeCarga(dto.getCapacidadeCarga());
        if (dto.getPotenciaMotor() != null) entity.setPotenciaMotor(dto.getPotenciaMotor());
        if (dto.getCilindrada() != null) entity.setCilindrada(dto.getCilindrada());
        if (dto.getPesoBruto() != null) entity.setPesoBruto(dto.getPesoBruto());
        if (dto.getUfRegistro() != null) entity.setUfRegistro(dto.getUfRegistro());
        if (dto.getMunicipioRegistro() != null) entity.setMunicipioRegistro(dto.getMunicipioRegistro());
        if (dto.getSituacaoVeiculo() != null) entity.setSituacaoVeiculo(dto.getSituacaoVeiculo());
        if (dto.getSituacaoLicenciamento() != null) entity.setSituacaoLicenciamento(dto.getSituacaoLicenciamento());
        if (dto.getRestricaoJudicial() != null) entity.setRestricaoJudicial(dto.getRestricaoJudicial());
        if (dto.getDataPrimeiroLicenciamento() != null) entity.setDataPrimeiroLicenciamento(dto.getDataPrimeiroLicenciamento());
        if (dto.getNumeroCrv() != null) entity.setNumeroCrv(dto.getNumeroCrv());
        if (dto.getNumeroCrlv() != null) entity.setNumeroCrlv(dto.getNumeroCrlv());
        if (dto.getTabelaFipe() != null) entity.setTabelaFipe(dto.getTabelaFipe());

        if (dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }
    }

    public static VeiculoDashboardResponseDTO toVeiculoDashboard(List<Veiculo> veiculos){

        Map<Cambio, Long> totalPorCambio = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getCambio, Collectors.counting()));

        Map<Carroceria, Long> totalPorCarroceria = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getCarroceria, Collectors.counting()));

        Map<CategoriaVeiculo,Long> totalPorCategoriaVeiculo = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getCategoria, Collectors.counting()));

        Map<Combustivel, Long> totalPorCombustivel = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getCombustivel, Collectors.counting()));

        Map<EspecieVeiculo, Long> totalPorEspecieCombustivel = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getEspecieVeiculo, Collectors.counting()));

        Map<SituacaoLicenciamento, Long> totalPorLicenciamento = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getSituacaoLicenciamento, Collectors.counting()));

        Map<SituacaoVeiculo, Long> totalPorSituacaoVeiculo = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getSituacaoVeiculo, Collectors.counting()));

        Map<TipoTracao, Long> totalPorTracao = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getTipoTracao, Collectors.counting()));

        Map<TipoVeiculo, Long> totalPorTipoVeiculo = veiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getTipoVeiculo, Collectors.counting()));

        return new VeiculoDashboardResponseDTO(
                totalPorCambio,
                totalPorCarroceria,
                totalPorCategoriaVeiculo,
                totalPorCombustivel,
                totalPorEspecieCombustivel,
                totalPorLicenciamento,
                totalPorSituacaoVeiculo,
                totalPorTracao,
                totalPorTipoVeiculo
        );
    }
}