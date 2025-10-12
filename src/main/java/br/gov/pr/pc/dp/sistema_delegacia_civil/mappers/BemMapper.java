package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import lombok.RequiredArgsConstructor;

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

        if (bem.getPessoa() != null) dto.setPessoaId(bem.getPessoa().getId());
        if (bem.getDelegacia() != null) dto.setDelegaciaId(bem.getDelegacia().getId());
        if (bem.getInstituicao() != null) dto.setInstituicaoId(bem.getInstituicao().getId());

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

        if (dto.getInstituicaoId() != null) {
            Instituicao instituicao = new Instituicao();
            instituicao.setId(dto.getInstituicaoId());
            bem.setInstituicao(instituicao);
        }
    }
}



