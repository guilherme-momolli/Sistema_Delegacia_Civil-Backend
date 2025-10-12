package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.BemMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BemService {

    private final BemRepository bemRepository;
    private final FileStorageService fileStorageService;

    private static final String SUBFOLDER_ARMA = "Imagens/Bens/Armas";
    private static final String SUBFOLDER_DROGA = "Imagens/Bens/Drogas";
    private static final String SUBFOLDER_VEICULO = "Imagens/Bens/Veiculos";
    private static final String SUBFOLDER_OBJETO = "Imagens/Bens/Objetos";

    public List<BemResponseDTO> listBens() {
        return bemRepository.findAll()
                .stream()
                .map(BemMapper::toResponseDTO)
                .toList();
    }

    public BemResponseDTO getById(Long id) {
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bem não encontrado com id: " + id));
        return BemMapper.toResponseDTO(bem);
    }

    @Transactional
    public BemResponseDTO createBem(BemRequestDTO dto, MultipartFile imagem) {
        Bem bem = BemMapper.toEntity(dto);

        if (imagem != null && !imagem.isEmpty()) {
            String subFolder = getSubFolderByTipo(bem.getTipoBem());
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            bem.setImagemUrl(nomeArquivo);
        }

        return BemMapper.toResponseDTO(bemRepository.save(bem));

    }

    @Transactional
    public BemResponseDTO updateBem(Long id, BemRequestDTO dto, MultipartFile imagem) {
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bem não encontrado com id: " + id));

        BemMapper.updateEntityFromDTO(dto, bem);

        if (imagem != null && !imagem.isEmpty()) {
            String subFolder = getSubFolderByTipo(bem.getTipoBem());
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            bem.setImagemUrl(nomeArquivo);
        }

        Bem updated = bemRepository.save(bem);
        return BemMapper.toResponseDTO(updated);
    }

    @Transactional
    public void deleteBem(Long id) {
        if (!bemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bem não encontrado com id: " + id);
        }
        bemRepository.deleteById(id);
    }

    private String getSubFolderByTipo(TipoBem tipoBem) {
        if (tipoBem == null) return "Imagens/Bens/Outros";
        return switch (tipoBem) {
            case ARMA -> SUBFOLDER_ARMA;
            case DROGA -> SUBFOLDER_DROGA;
            case VEICULO -> SUBFOLDER_VEICULO;
            case OBJETO -> SUBFOLDER_OBJETO;
        };
    }
}