package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial.InqueritoPolicialException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial.InqueritoPolicialNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.BemEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.InqueritoPolicialHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.InqueritoPolicialMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EntityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InqueritoPolicialService {

    private final InqueritoPolicialRepository inqueritoRepository;
    private final EntityHelper entityHelper;
    private final PessoaEnvolvimentoHelper pessoaEnvolvimentoHelper;
    private final BemEnvolvimentoHelper bemEnvolvimentoHelper;
    private final InqueritoPolicialHelper inqueritoPolicialHelper;

    @Transactional
    public List<InqueritoPolicialResponseDTO> findAll() {
        List<InqueritoPolicial> inqueritos = inqueritoRepository.findAll();

        if (inqueritos.isEmpty()) {
            throw new InqueritoPolicialException(
                    ErrorType.NAO_ENCONTRADO,
                    "Nenhum inquérito policial encontrado.",
                    "O repositório retornou lista vazia em findAll()."
            );
        }

        return inqueritos.stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InqueritoPolicialResponseDTO findById(Long id) {
        entityHelper.validarInqueritoPolicalExistente(id);

        InqueritoPolicial inquerito = inqueritoRepository.findById(id)
                .orElseThrow(() -> new InqueritoPolicialNotFoundException(id));

        return InqueritoPolicialMapper.toResponseDTO(inquerito);
    }

    @Transactional
    public List<InqueritoPolicialResponseDTO> getInqueritosByDelegacia(Long delegaciaId) {
        entityHelper.validarDelegaciaExistente(delegaciaId);

        List<InqueritoPolicial> inqueritos = inqueritoRepository.findByDelegaciaId(delegaciaId);

        if (inqueritos.isEmpty()) {
            throw new InqueritoPolicialException(
                    ErrorType.NAO_ENCONTRADO,
                    "Nenhum inquérito encontrado para esta delegacia.",
                    "Delegacia ID " + delegaciaId + " não possui registros vinculados."
            );
        }

        return inqueritos.stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InqueritoPolicialDashboardResponseDTO getInqueritoResumo() {
        List<InqueritoPolicial> inqueritos = inqueritoRepository.findAll();
        return InqueritoPolicialMapper.toInqueritoDashboard(inqueritos);
    }

    @Transactional
    public InqueritoPolicialResponseDTO createInqueritoPolicial(InqueritoPolicialRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new InqueritoPolicialException(
                    ErrorType.VALIDACAO,
                    "Os dados do inquérito são obrigatórios.",
                    "DTO de requisição veio nulo em createInqueritoPolicial()."
            );
        }
        try {
            InqueritoPolicial inquerito = inqueritoPolicialHelper.montarInqueritoParaCriacao(requestDTO);

            List<PessoaEnvolvimento> pessoas = pessoaEnvolvimentoHelper.mapearPessoas(
                    requestDTO.getPessoasEnvolvidas(), inquerito
            );

            List<BemEnvolvimento> bens = bemEnvolvimentoHelper.mapearBensPorInqueritoPolicial(
                    requestDTO.getBensEnvolvidos(), inquerito
            );

            inquerito.setPessoasEnvolvidas(pessoas);
            inquerito.setBensEnvolvidos(bens);

            InqueritoPolicial salvo = inqueritoRepository.save(inquerito);

            return InqueritoPolicialMapper.toResponseDTO(salvo);

        } catch (InqueritoPolicialException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao criar inquérito policial: {}", e.getMessage(), e);
            throw new InqueritoPolicialException(
                    ErrorType.INTERNO,
                    "Erro ao criar o inquérito policial.",
                    e
            );
        }
    }

    @Transactional
    public InqueritoPolicialResponseDTO updateInqueritoPolicial(Long id, InqueritoPolicialRequestDTO requestDTO) {
        entityHelper.validarInqueritoPolicalExistente(id);

        try {
            InqueritoPolicial existente = inqueritoRepository.findById(id)
                    .orElseThrow(() -> new InqueritoPolicialNotFoundException(id));

            InqueritoPolicialMapper.updateEntityFromDTO(requestDTO, existente);
            inqueritoPolicialHelper.atualizarDelegacia(existente, requestDTO.getDelegaciaId());

            existente.getPessoasEnvolvidas().clear();
            existente.getPessoasEnvolvidas().addAll(
                    pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), existente)
            );

            existente.getBensEnvolvidos().clear();
            existente.getBensEnvolvidos().addAll(
                    bemEnvolvimentoHelper.mapearBensPorInqueritoPolicial(requestDTO.getBensEnvolvidos(), existente)
            );

            InqueritoPolicial atualizado = inqueritoRepository.save(existente);
            return InqueritoPolicialMapper.toResponseDTO(atualizado);

        } catch (InqueritoPolicialException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao atualizar inquérito policial ID {}: {}", id, e.getMessage(), e);
            throw new InqueritoPolicialException(
                    ErrorType.INTERNO,
                    "Erro ao atualizar o inquérito policial.",
                    e
            );
        }
    }


    @Transactional
    public void delete(Long id) {
        entityHelper.validarInqueritoPolicalExistente(id);

        try {
            inqueritoRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Erro ao deletar inquérito ID {}: {}", id, e.getMessage(), e);
            throw new InqueritoPolicialException(
                    ErrorType.INTERNO,
                    "Erro ao excluir o inquérito policial.",
                    e
            );
        }
    }
}