package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mapper.PessoaEnvolvimentoMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InqueritoPolicialService {

    @Autowired
    private final InqueritoPolicialRepository inqueritoRepository;
    @Autowired
    private final PessoaRepository pessoaRepository;
    @Autowired
    private PessoaEnvolvimentoService pessoaEnvolvimentoService;

    public List<InqueritoPolicial> findAll() {
        return inqueritoRepository.findAll();
    }

    public Optional<InqueritoPolicial> findById(Long id) {
        return inqueritoRepository.findById(id);
    }

    public List<InqueritoPolicial> getInqueritosByDelegacia(Long delegaciaId) {
        return inqueritoRepository.findByDelegaciaId(delegaciaId);
    }

    @Transactional
    public InqueritoPolicial createInqueritoPolicial(InqueritoPolicial inquerito, List<PessoaEnvolvimentoDTO> pessoasDTO) {
        if (pessoasDTO != null) {
            for (PessoaEnvolvimentoDTO dto : pessoasDTO) {
                Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                        .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

                PessoaEnvolvimento envolvimento = PessoaEnvolvimentoMapper.toEntity(dto, pessoa, inquerito);

                inquerito.getPessoasEnvolvidas().add(envolvimento);
            }
        }

        return inqueritoRepository.save(inquerito);
    }


    @Transactional
    public InqueritoPolicial updateInqueritoPolicial(Long id, InqueritoPolicial inquerito, List<PessoaEnvolvimentoDTO> pessoasDTO) {
        InqueritoPolicial existente = inqueritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquérito não encontrado"));

        existente.setNumeroJustica(inquerito.getNumeroJustica());
        existente.setOrdemIp(inquerito.getOrdemIp());
        existente.setData(inquerito.getData());
        existente.setPeca(inquerito.getPeca());
        existente.setSituacaoInquerito(inquerito.getSituacaoInquerito());
        existente.setOrigemForcaPolicial(inquerito.getOrigemForcaPolicial());
        existente.setNaturezaDoDelito(inquerito.getNaturezaDoDelito());
        existente.setObservacao(inquerito.getObservacao());
        existente.setDelegacia(inquerito.getDelegacia());

        existente.getPessoasEnvolvidas().clear();

        InqueritoPolicial atualizado = inqueritoRepository.save(existente);

        if (pessoasDTO != null) {
            for (PessoaEnvolvimentoDTO dto : pessoasDTO) {
                Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                        .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

                PessoaEnvolvimento envolvimento = PessoaEnvolvimentoMapper.toEntity(dto, pessoa, atualizado);

                atualizado.getPessoasEnvolvidas().add(envolvimento);
            }
        }

        return inqueritoRepository.save(atualizado);
    }



    public void delete(Long id) {
        if (!inqueritoRepository.existsById(id)) {
            throw new RuntimeException("Inquérito não encontrado para exclusão");
        }
        inqueritoRepository.deleteById(id);
    }
}

