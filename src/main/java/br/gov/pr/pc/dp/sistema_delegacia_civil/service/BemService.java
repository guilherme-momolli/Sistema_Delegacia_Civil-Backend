package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.TipoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.BemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemService {

    private final BemRepository bemRepository;
    private final ArmaService armaService;
    private final DrogaService drogaService;
    private final FileStorageService fileStorageService;
    private final ObjetoService objetoService;
    private final VeiculoService veiculoService;

    public List<Bem> listarBens() {
        return bemRepository.findAll();
    }

    public Optional<Bem> buscarPorId(Long id) {
        return bemRepository.findById(id);
    }

    @Transactional
    public Bem salvarBemComDetalhes(Bem bem, Object detalhesBem, MultipartFile imagem) {
        if (imagem != null && !imagem.isEmpty()) {
            String imagemUrl = fileStorageService.storeFile(imagem);
            bem.setImagemUrl(imagemUrl);
        }

        Bem bemSalvo = bemRepository.save(bem);

        switch (bemSalvo.getTipoBem()) {
            case ARMA -> {
                if (detalhesBem instanceof Arma arma) {
                    arma.setBem(bemSalvo);
                    armaService.salvar(arma);
                }
            }
            case DROGA -> {
                if (detalhesBem instanceof Droga droga) {
                    droga.setBem(bemSalvo);
                    drogaService.salvar(droga);
                }
            }
            case OBJETO -> {
                if (detalhesBem instanceof Objeto objeto) {
                    objeto.setBem(bemSalvo);
                    objetoService.salvar(objeto);
                }
            }
            case VEICULO -> {
                if (detalhesBem instanceof Veiculo veiculo) {
                    veiculo.setBem(bemSalvo);
                    veiculoService.salvar(veiculo);
                }
            }
            default -> throw new IllegalArgumentException("Tipo de bem desconhecido: " + bemSalvo.getTipoBem());
        }

        return bemSalvo;
    }


    @Transactional
    public Bem atualizar(Bem bemAtualizado, MultipartFile novaImagem) {
        Optional<Bem> bemExistenteOpt = bemRepository.findById(bemAtualizado.getId());
        if (bemExistenteOpt.isEmpty()) {
            throw new IllegalArgumentException("Bem não encontrado com ID: " + bemAtualizado.getId());
        }

        Bem bemExistente = bemExistenteOpt.get();

        bemExistente.setTipoBem(bemAtualizado.getTipoBem());
        bemExistente.setMarca(bemAtualizado.getMarca());
        bemExistente.setModelo(bemAtualizado.getModelo());
        bemExistente.setValorEstimado(bemAtualizado.getValorEstimado());
        bemExistente.setPessoa(bemAtualizado.getPessoa());
        bemExistente.setDelegacia(bemAtualizado.getDelegacia());
        bemExistente.setInstituicao(bemAtualizado.getInstituicao());
        bemExistente.setSituacaoBem(bemAtualizado.getSituacaoBem());
        bemExistente.setOrigem(bemAtualizado.getOrigem());
        bemExistente.setNumeroLacre(bemAtualizado.getNumeroLacre());
        bemExistente.setLocalBem(bemAtualizado.getLocalBem());
        bemExistente.setObservacao(bemAtualizado.getObservacao());
        bemExistente.setDescricao(bemAtualizado.getDescricao());
        bemExistente.setAtivo(bemAtualizado.getAtivo());

        if (novaImagem != null && !novaImagem.isEmpty()) {
            String novaImagemUrl = fileStorageService.storeFile(novaImagem);
            bemExistente.setImagemUrl(novaImagemUrl);
        }

        return bemRepository.save(bemExistente);
    }

    public void deletar(Long id) {
        bemRepository.deleteById(id);
    }

}

