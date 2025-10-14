package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.BemEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.InqueritoPolicialMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EntityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InqueritoPolicialService {

    private final InqueritoPolicialRepository inqueritoRepository;
    private final EntityHelper entityHelper;
    private final PessoaEnvolvimentoHelper pessoaEnvolvimentoHelper;
    private final BemEnvolvimentoHelper bemEnvolvimentoHelper;

    @Transactional
    public List<InqueritoPolicialResponseDTO> findAll() {
        return inqueritoRepository.findAll()
                .stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🔹 BUSCAR POR ID
    @Transactional
    public InqueritoPolicialResponseDTO findById(Long id) {
        entityHelper.validarInqueritoPolicalExistente(id);
        InqueritoPolicial inquerito = inqueritoRepository.findById(id).orElseThrow();
        return InqueritoPolicialMapper.toResponseDTO(inquerito);
    }

    @Transactional
    public List<InqueritoPolicialResponseDTO> getInqueritosByDelegacia(Long delegaciaId) {
        entityHelper.validarDelegaciaExistente(delegaciaId);
        return inqueritoRepository.findByDelegaciaId(delegaciaId)
                .stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InqueritoPolicialResponseDTO createInqueritoPolicial(InqueritoPolicialRequestDTO requestDTO) {

        entityHelper.validarDelegaciaExistente(requestDTO.getDelegaciaId());
        Delegacia delegacia = new Delegacia();
        delegacia.setId(requestDTO.getDelegaciaId());

        InqueritoPolicial inquerito = InqueritoPolicialMapper.toEntity(requestDTO, delegacia);

        InqueritoPolicial salvo = inqueritoRepository.save(inquerito);

        List<PessoaEnvolvimento> pessoasEnvolvidas =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), salvo);
        List<BemEnvolvimento> bensEnvolvidos =
                bemEnvolvimentoHelper.mapearBensPorInqueritoPolicial(requestDTO.getBensEnvolvidos(), salvo);

        salvo.setPessoasEnvolvidas(new ArrayList<>(pessoasEnvolvidas));
        salvo.setBensEnvolvidos(new ArrayList<>(bensEnvolvidos));

        InqueritoPolicial atualizado = inqueritoRepository.save(salvo);

        return InqueritoPolicialMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public InqueritoPolicialResponseDTO updateInqueritoPolicial(Long id, InqueritoPolicialRequestDTO requestDTO) {

        InqueritoPolicial existente = inqueritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inquérito não encontrado"));

        InqueritoPolicialMapper.updateEntityFromDTO(requestDTO, existente);

        if (requestDTO.getDelegaciaId() != null) {
            entityHelper.validarDelegaciaExistente(requestDTO.getDelegaciaId());
            Delegacia delegacia = new Delegacia();
            delegacia.setId(requestDTO.getDelegaciaId());
            existente.setDelegacia(delegacia);
        }

        existente.getPessoasEnvolvidas().clear();
        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), existente);
        existente.setPessoasEnvolvidas(new ArrayList<>(envolvimentos));

        List<BemEnvolvimento> bensEnvolvidos =
                bemEnvolvimentoHelper.mapearBensPorInqueritoPolicial(requestDTO.getBensEnvolvidos(), existente);
        existente.setBensEnvolvidos(new ArrayList<>(bensEnvolvidos));


        InqueritoPolicial atualizado = inqueritoRepository.save(existente);
        return InqueritoPolicialMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void delete(Long id) {
        entityHelper.validarInqueritoPolicalExistente(id);
        inqueritoRepository.deleteById(id);
    }
}

