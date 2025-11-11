package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.SituacaoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BemMapper {

    private final ArmaMapper armaMapper;
    private final ObjetoMapper objetoMapper;
    private final VeiculoMapper veiculoMapper;
    private final DrogaMapper drogaMapper;

    public static Bem toEntity(BemRequestDTO dto) {
        if (dto == null) return null;

        Bem bem = new Bem();
        bem.setId(dto.getId());
        bem.setTipoBem(dto.getTipoBem());
        bem.setImagemUrl(dto.getImagemUrl());
        bem.setMarca(dto.getMarca());
        bem.setModelo(dto.getModelo());
        bem.setValorEstimado(dto.getValorEstimado());
        bem.setSituacaoBem(dto.getSituacaoBem());
        bem.setOrigem(dto.getOrigem());
        bem.setNumeroLacre(dto.getNumeroLacre());
        bem.setLocalBem(dto.getLocalBem());
        bem.setObservacao(dto.getObservacao());
        bem.setDescricao(dto.getDescricao());
        if (dto.getPessoaId() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.getPessoaId());
            bem.setPessoa(pessoa);
        }
        if (dto.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(dto.getDelegaciaId());
            bem.setDelegacia(delegacia);
        }

        if (dto.getTipoBem() != null) {
            switch (dto.getTipoBem()) {
                case ARMA -> {
                    if (dto.getArma() != null) {
                        Arma arma = ArmaMapper.toEntity(dto.getArma());
                        arma.setBem(bem);
                        bem.setArma(arma);
                    }
                }
                case OBJETO -> {
                    if (dto.getObjeto() != null) {
                        Objeto objeto = ObjetoMapper.toEntity(dto.getObjeto());
                        objeto.setBem(bem);
                        bem.setObjeto(objeto);
                    }
                }
                case VEICULO -> {
                    if (dto.getVeiculo() != null) {
                        Veiculo veiculo = VeiculoMapper.toEntity(dto.getVeiculo());
                        veiculo.setBem(bem);
                        bem.setVeiculo(veiculo);
                    }
                }
                case DROGA -> {
                    if (dto.getDroga() != null) {
                        Droga droga = DrogaMapper.toEntity(dto.getDroga());
                        droga.setBem(bem);
                        bem.setDroga(droga);
                    }
                }
            }
        }

        return bem;
    }

    public static BemResponseDTO toResponseDTO(Bem bem) {
        if (bem == null) return null;

        BemResponseDTO dto = new BemResponseDTO();
        dto.setId(bem.getId());
        dto.setTipoBem(bem.getTipoBem());
        dto.setImagemUrl(bem.getImagemUrl());
        dto.setMarca(bem.getMarca());
        dto.setModelo(bem.getModelo());
        dto.setValorEstimado(bem.getValorEstimado());
        dto.setSituacaoBem(bem.getSituacaoBem());
        dto.setOrigem(bem.getOrigem());
        dto.setNumeroLacre(bem.getNumeroLacre());
        dto.setLocalBem(bem.getLocalBem());
        dto.setObservacao(bem.getObservacao());
        dto.setDescricao(bem.getDescricao());
        dto.setCreatedAt(bem.getCreatedAt());
        dto.setUpdatedAt(bem.getUpdatedAt());

        if (bem.getPessoa() != null) dto.setPessoaId(bem.getPessoa().getId());
        if (bem.getDelegacia() != null) dto.setDelegaciaId(bem.getDelegacia().getId());

        if (bem.getArma() != null) dto.setArma(ArmaMapper.toResponseDTO(bem.getArma()));
        if (bem.getObjeto() != null) dto.setObjeto(ObjetoMapper.toResponseDTO(bem.getObjeto()));
        if (bem.getVeiculo() != null) dto.setVeiculo(VeiculoMapper.toResponseDTO(bem.getVeiculo()));
        if (bem.getDroga() != null) dto.setDroga(DrogaMapper.toResponseDTO(bem.getDroga()));

        return dto;
    }


    public static void updateEntityFromDTO(BemRequestDTO dto, Bem bem) {
        if (dto.getTipoBem() != null) bem.setTipoBem(dto.getTipoBem());
        if (dto.getDescricao() != null) bem.setDescricao(dto.getDescricao());
        if (dto.getSituacaoBem() != null) bem.setSituacaoBem(dto.getSituacaoBem());
        if (dto.getOrigem() != null) bem.setOrigem(dto.getOrigem());
        if (dto.getNumeroLacre() != null) bem.setNumeroLacre(dto.getNumeroLacre());
        if (dto.getLocalBem() != null) bem.setLocalBem(dto.getLocalBem());
        if (dto.getObservacao() != null) bem.setObservacao(dto.getObservacao());
        if (dto.getValorEstimado() != null) bem.setValorEstimado(dto.getValorEstimado());
        if (dto.getMarca() != null) bem.setMarca(dto.getMarca());
        if (dto.getModelo() != null) bem.setModelo(dto.getModelo());

        if (dto.getPessoaId() != null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(dto.getPessoaId());
            bem.setPessoa(pessoa);
        }

        if (dto.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(dto.getDelegaciaId());
            bem.setDelegacia(delegacia);
        }

        // 🔹 Atualização de campos específicos conforme o tipo do bem
        if (dto.getTipoBem() != null) {
            switch (dto.getTipoBem()) {
                case ARMA -> {
                    if (dto.getArma() != null) {
                        if (bem.getArma() == null) bem.setArma(new Arma());
                        ArmaMapper.toUpdate(dto.getArma(), bem.getArma());
                    }
                }
                case OBJETO -> {
                    if (dto.getObjeto() != null) {
                        if (bem.getObjeto() == null) bem.setObjeto(new Objeto());
                        ObjetoMapper.toUpdate(dto.getObjeto(), bem.getObjeto());
                    }
                }
                case VEICULO -> {
                    if (dto.getVeiculo() != null) {
                        if (bem.getVeiculo() == null) bem.setVeiculo(new Veiculo());
                        VeiculoMapper.toUpdate(dto.getVeiculo(), bem.getVeiculo());
                    }
                }
                case DROGA -> {
                    if (dto.getDroga() != null) {
                        if (bem.getDroga() == null) bem.setDroga(new Droga());
                        DrogaMapper.toUpdate(dto.getDroga(), bem.getDroga());
                    }
                }
            }
        }
    }

    public static BemDashboardResponseDTO toBemDashboard(List<Bem> bens) {
        long totalBens = bens.size();

        Map<SituacaoBem, Long> totalPorSituacaoBem = bens.stream()
                .collect(Collectors.groupingBy(Bem::getSituacaoBem, Collectors.counting()));

        Map<TipoBem, Long> totalPorTipoBem = bens.stream()
                .collect(Collectors.groupingBy(Bem::getTipoBem, Collectors.counting()));

        // ======= Filtra por tipo de bem =======
        List<Arma> armas = bens.stream()
                .filter(bem -> bem.getTipoBem() == TipoBem.ARMA && bem.getArma() != null)
                .map(Bem::getArma)
                .collect(Collectors.toList());

        List<Droga> drogas = bens.stream()
                .filter(bem -> bem.getTipoBem() == TipoBem.DROGA && bem.getDroga() != null)
                .map(Bem::getDroga)
                .collect(Collectors.toList());

        List<Veiculo> veiculos = bens.stream()
                .filter(bem -> bem.getTipoBem() == TipoBem.VEICULO && bem.getVeiculo() != null)
                .map(Bem::getVeiculo)
                .collect(Collectors.toList());

        ArmaDashboardResponseDTO armaDashboardResponseDTO = ArmaMapper.toArmaDashboard(armas);
        DrogaDashboardResponseDTO drogaDashboardResponseDTO = DrogaMapper.toDrogaDashboard(drogas);
        VeiculoDashboardResponseDTO veiculoDashboardResponseDTO = VeiculoMapper.toVeiculoDashboard(veiculos);

        return new BemDashboardResponseDTO(
                totalBens,
                totalPorTipoBem,
                totalPorSituacaoBem,
                armaDashboardResponseDTO,
                drogaDashboardResponseDTO,
                veiculoDashboardResponseDTO
        );
    }

}



