package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BoletimOcorrenciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaEnvolvimentoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaEnvolvimentoService {

    private final PessoaRepository pessoaRepository;
    private final InqueritoPolicialRepository inqueritoRepository;
    private final BoletimOcorrenciaRepository boletimRepository;
    private final PessoaEnvolvimentoRepository pessoaEnvolvimentoRepository;


    public List<PessoaEnvolvimentoRequestDTO> listarPorInquerito(Long inqueritoId) {
        return pessoaEnvolvimentoRepository.findByInqueritoPolicialId(inqueritoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PessoaEnvolvimentoRequestDTO> listarPorBoletim(Long boletimId) {
        return pessoaEnvolvimentoRepository.findByBoletimOcorrenciaId(boletimId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PessoaEnvolvimentoRequestDTO toDTO(PessoaEnvolvimento envolvimento) {
        return new PessoaEnvolvimentoRequestDTO(
                envolvimento.getId(),
                envolvimento.getPessoa().getId(),
                envolvimento.getBoletimOcorrencia() != null ? envolvimento.getBoletimOcorrencia().getId() : null,
                envolvimento.getInqueritoPolicial() != null ? envolvimento.getInqueritoPolicial().getId() : null,
                envolvimento.getTipoEnvolvimento(),
                envolvimento.getObservacao()
        );
    }

//    public List<PessoaEnvolvimentoDTO> listarPessoasEnvolvidas(Long inqueritoId) {
//        List<PessoaEnvolvimento> envolvimentos = pessoaEnvolvimentoRepository.findByInqueritoPolicialId(inqueritoId);
//
//        return envolvimentos.stream()
//                .map(env -> PessoaEnvolvimentoDTO.builder()
//                        .id(env.getId())
//                        .tipoEnvolvimento(env.getTipoEnvolvimento().name())
//                        .observacao(env.getObservacao())
//                        .pessoa(PessoaDTO.builder()
//                                .id(env.getPessoa().getId())
//                                .nome(env.getPessoa().getNome())
//                                .cpf(env.getPessoa().getCpf())
//                                .sexo(env.getPessoa().getSexo().name())
//                                .build())
//                        .build())
//                .toList();
//    }

//    public PessoaEnvolvimentoDTO cadastrar(PessoaEnvolvimentoDTO dto) {
//        PessoaEnvolvimento envolvimento = toEntity(dto);
//        pessoaEnvolvimentoRepository.save(envolvimento);
//        return toDTO(envolvimento);
//    }
//
//    public PessoaEnvolvimentoDTO atualizar(Long id, PessoaEnvolvimentoDTO dto) {
//        PessoaEnvolvimento envolvimento = pessoaEnvolvimentoRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Envolvimento não encontrado"));
//
//        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
//                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
//        envolvimento.setPessoa(pessoa);
//
//        if (dto.getInqueritoId() != null) {
//            InqueritoPolicial inquerito = inqueritoRepository.findById(dto.getInqueritoId())
//                    .orElseThrow(() -> new RuntimeException("Inquérito não encontrado"));
//            envolvimento.setInqueritoPolicial(inquerito);
//        } else {
//            envolvimento.setInqueritoPolicial(null);
//        }
//
//        if (dto.getBoletimId() != null) {
//            BoletimOcorrencia boletim = boletimRepository.findById(dto.getBoletimId())
//                    .orElseThrow(() -> new RuntimeException("Boletim não encontrado"));
//            envolvimento.setBoletimOcorrencia(boletim);
//        } else {
//            envolvimento.setBoletimOcorrencia(null);
//        }
//
//        envolvimento.setTipoEnvolvimento(dto.getTipoEnvolvimento());
//        envolvimento.setObservacao(dto.getObservacao());
//
//        pessoaEnvolvimentoRepository.save(envolvimento);
//        return toDTO(envolvimento);
//    }
//
//    public void deletar(Long id) {
//        if (!pessoaEnvolvimentoRepository.existsById(id)) {
//            throw new EntityNotFoundException("Envolvimento não encontrado");
//        }
//        pessoaEnvolvimentoRepository.deleteById(id);
//    }
//
//    private PessoaEnvolvimentoDTO toDTO(PessoaEnvolvimento envolvimento) {
//        return new PessoaEnvolvimentoDTO(
//                envolvimento.getId(),
//                envolvimento.getPessoa() != null ? envolvimento.getPessoa().getId() : null,
//                envolvimento.getInqueritoPolicial() != null ? envolvimento.getInqueritoPolicial().getId() : null,
//                envolvimento.getBoletimOcorrencia() != null ? envolvimento.getBoletimOcorrencia().getId() : null,
//                envolvimento.getPessoa() != null ? envolvimento.getPessoa().getNome() : null,
//                envolvimento.getTipoEnvolvimento(),
//                envolvimento.getObservacao()
//        );
//    }
//
//    private PessoaEnvolvimento toEntity(PessoaEnvolvimentoDTO dto) {
//        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
//                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
//
//        InqueritoPolicial inquerito = null;
//        if (dto.getInqueritoId() != null) {
//            inquerito = inqueritoRepository.findById(dto.getInqueritoId())
//                    .orElseThrow(() -> new RuntimeException("Inquérito não encontrado"));
//        }
//
//        BoletimOcorrencia boletim = null;
//        if (dto.getBoletimId() != null) {
//            boletim = boletimRepository.findById(dto.getBoletimId())
//                    .orElseThrow(() -> new RuntimeException("Boletim não encontrado"));
//        }
//
//        PessoaEnvolvimento envolvimento = new PessoaEnvolvimento();
//        envolvimento.setId(dto.getId());
//        envolvimento.setPessoa(pessoa);
//        envolvimento.setInqueritoPolicial(inquerito);
//        envolvimento.setBoletimOcorrencia(boletim);
//        envolvimento.setTipoEnvolvimento(dto.getTipoEnvolvimento());
//        envolvimento.setObservacao(dto.getObservacao());
//        return envolvimento;
//    }
}
