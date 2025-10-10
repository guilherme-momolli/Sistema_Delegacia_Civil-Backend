package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class BemMapper {

    public static Bem toEntity(BemRequestDTO dto) {
        if (dto == null) return null;

        Bem bem = new Bem();
        bem.setId(dto.getId());
        bem.setTipoBem(dto.getTipoBem());
        bem.setDescricao(dto.getDescricao());
        bem.setSituacaoBem(dto.getSituacaoBem());
        bem.setOrigem(dto.getOrigem());
        bem.setNumeroLacre(dto.getNumeroLacre());
        bem.setLocalBem(dto.getLocalBem());
        bem.setObservacao(dto.getObservacao());
        bem.setValorEstimado(dto.getValorEstimado());
        bem.setMarca(dto.getMarca());
        bem.setModelo(dto.getModelo());

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

        return bem;
    }

    public static BemResponseDTO toResponseDTO(Bem bem) {
        if (bem == null) return null;

        BemResponseDTO dto = new BemResponseDTO();
        dto.setId(bem.getId());
        dto.setTipoBem(bem.getTipoBem());
        dto.setDescricao(bem.getDescricao());
        dto.setSituacaoBem(bem.getSituacaoBem());
        dto.setOrigem(bem.getOrigem());
        dto.setNumeroLacre(bem.getNumeroLacre());
        dto.setLocalBem(bem.getLocalBem());
        dto.setObservacao(bem.getObservacao());
        dto.setValorEstimado(bem.getValorEstimado());
        dto.setMarca(bem.getMarca());
        dto.setModelo(bem.getModelo());
        dto.setImagemUrl(bem.getImagemUrl());

        dto.setPessoaId(bem.getPessoa() != null ? bem.getPessoa().getId() : null);
        dto.setDelegaciaId(bem.getDelegacia() != null ? bem.getDelegacia().getId() : null);
        dto.setInstituicaoId(bem.getInstituicao() != null ? bem.getInstituicao().getId() : null);

        dto.setCreatedAt(bem.getCreatedAt());
        dto.setUpdatedAt(bem.getUpdatedAt());

        return dto;
    }

    // 🔄 método para atualização parcial
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



