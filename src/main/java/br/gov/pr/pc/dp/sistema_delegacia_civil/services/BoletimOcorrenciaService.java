package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia.BoletimOcorrenciaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.delagacia.DelegaciaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.pessoa.PessoaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.PessoaEnvolvimentoMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BoletimOcorrenciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.DelegaciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.EnderecoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoletimOcorrenciaService {

    private final BoletimOcorrenciaRepository boletimOcorrenciaRepository;
    private final DelegaciaRepository delegaciaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public List<BoletimOcorrencia> findAll() {
        return boletimOcorrenciaRepository.findAll();
    }

    @Transactional
    public BoletimOcorrencia findById(Long id) {
        return boletimOcorrenciaRepository.findById(id)
                .orElseThrow(() -> new BoletimOcorrenciaNotFoundException(id));
    }

    @Transactional
    public List<BoletimOcorrencia> getBoletinsByDelegacia(Long delegaciaId) {
        return boletimOcorrenciaRepository.findByDelegaciaId(delegaciaId);
    }

    @Transactional
    public BoletimOcorrencia createBoletimOcorrencia(
            BoletimOcorrencia boletim,
            List<PessoaEnvolvimentoRequestDTO> pessoasDTO
    ) {
        // ✅ Valida a delegacia
        delegaciaRepository.findById(boletim.getDelegacia().getId())
                .orElseThrow(() -> new DelegaciaNotFoundException(boletim.getDelegacia().getId()));

        // ✅ Valida ou cria o endereço via service
        if (boletim.getEndereco() != null) {
            Endereco endereco = boletim.getEndereco();
            Endereco enderecoValido;

            if (endereco.getId() == null) {
                enderecoValido = enderecoService.createEndereco(endereco);
            } else {
                enderecoValido = enderecoService.getById(endereco.getId());
            }

            boletim.setEndereco(enderecoValido);
        }

        BoletimOcorrencia salvo = boletimOcorrenciaRepository.save(boletim);

        if (pessoasDTO != null && !pessoasDTO.isEmpty()) {
            List<PessoaEnvolvimento> envolvimentos = pessoasDTO.stream()
                    .map(dto -> {
                        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                                .orElseThrow(() -> new PessoaNotFoundException(dto.getPessoaId()));
                        return PessoaEnvolvimentoMapper.toEntity(dto, pessoa, salvo);
                    })
                    .toList();

            salvo.getPessoasEnvolvidas().addAll(envolvimentos);
        }

        return boletimOcorrenciaRepository.save(salvo);
    }


    @Transactional
    public BoletimOcorrencia updateBoletim(
            Long id,
            BoletimOcorrencia boletim,
            List<PessoaEnvolvimentoRequestDTO> pessoasDTO
    ) {
        BoletimOcorrencia existing = boletimOcorrenciaRepository.findById(id)
                .orElseThrow(() -> new BoletimOcorrenciaNotFoundException(id));

        if (boletim.getDelegacia() != null && boletim.getDelegacia().getId() != null) {
            delegaciaRepository.findById(boletim.getDelegacia().getId())
                    .orElseThrow(() -> new DelegaciaNotFoundException(boletim.getDelegacia().getId()));
            existing.setDelegacia(boletim.getDelegacia());
        }

        existing.setOrigemForcaPolicial(boletim.getOrigemForcaPolicial());
        existing.setDataOcorrencia(boletim.getDataOcorrencia());
        existing.setBoletim(boletim.getBoletim());
        existing.setNatureza(boletim.getNatureza());
        existing.setRepresentacao(boletim.getRepresentacao());

        if (boletim.getEndereco() != null) {
            Endereco endereco = boletim.getEndereco();
            Endereco enderecoValido;
            if (endereco.getId() == null) {
                enderecoValido = enderecoService.createEndereco(endereco);
            } else {
                enderecoValido = enderecoService.getById(endereco.getId());
            }
            existing.setEndereco(enderecoValido);
        }

        existing.getPessoasEnvolvidas().clear();
        if (pessoasDTO != null && !pessoasDTO.isEmpty()) {
            List<PessoaEnvolvimento> envolvimentos = pessoasDTO.stream()
                    .map(dto -> {
                        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                                .orElseThrow(() -> new PessoaNotFoundException(dto.getPessoaId()));
                        return PessoaEnvolvimentoMapper.toEntity(dto, pessoa, existing);
                    })
                    .toList();
            existing.getPessoasEnvolvidas().addAll(envolvimentos);
        }

        return boletimOcorrenciaRepository.save(existing);
    }



    @Transactional
    public void delete(Long id) {
        if (!boletimOcorrenciaRepository.existsById(id)) {
            throw new BoletimOcorrenciaNotFoundException(id);
        }
        boletimOcorrenciaRepository.deleteById(id);
    }

    private List<PessoaEnvolvimento> mapearPessoas(
            List<PessoaEnvolvimentoRequestDTO> pessoasDTO,
            BoletimOcorrencia boletim
    ) {
        return pessoasDTO.stream()
                .map(dto -> {
                    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new PessoaNotFoundException(dto.getPessoaId()));
                    return PessoaEnvolvimentoMapper.toEntity(dto, pessoa, boletim);
                })
                .toList();
    }
}
