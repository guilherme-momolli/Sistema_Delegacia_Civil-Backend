//package br.gov.pr.pc.dp.sistema_delegacia_civil.services;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.DrogaMapper;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.DrogaRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DrogaService {
//
//    private final BemRepository bemRepository;
//    private final DrogaRepository drogaRepository;
//    private final FileStorageService fileStorageService;
//    private final String subFolder = "Imagens/Drogas";
//
//    public List<DrogaResponseDTO> listDrogas() {
//        return drogaRepository.findAll()
//                .stream()
//                .map(DrogaMapper::toResponseDTO)
//                .toList();
//    }
//
//    @Transactional
//    public DrogaResponseDTO createDroga(DrogaRequestDTO dto, MultipartFile imagem) {
//        Bem bem = new Bem();
//        bem.setTipoBem("DROGA");
//        if (imagem != null && !imagem.isEmpty()) {
//            bem.setImagemUrl(fileStorageService.storeFile(imagem, subFolder));
//        }
//        Bem bemSalvo = bemRepository.save(bem);
//
//        Droga droga = DrogaMapper.toEntity(dto, bemSalvo);
//        drogaRepository.save(droga);
//
//        return DrogaMapper.toResponseDTO(droga);
//    }
//
//    @Transactional
//    public DrogaResponseDTO updateDroga(Long id, DrogaRequestDTO dto, MultipartFile imagem) {
//        Droga drogaExistente = drogaRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Droga não encontrada"));
//
//        Bem bem = drogaExistente.getBem();
//        if (imagem != null && !imagem.isEmpty()) {
//            bem.setImagemUrl(fileStorageService.storeFile(imagem, subFolder));
//        }
//        bemRepository.save(bem);
//
//        Droga atualizado = DrogaMapper.toEntity(dto, bem);
//        atualizado.setId(drogaExistente.getId());
//        drogaRepository.save(atualizado);
//
//        return DrogaMapper.toResponseDTO(atualizado);
//    }
//
//    @Transactional
//    public void deleteDroga(Long id) {
//        Droga droga = drogaRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Droga não encontrada"));
//
//        Bem bem = droga.getBem();
//        if (bem.getImagemUrl() != null) {
//            fileStorageService.deleteFile(bem.getImagemUrl());
//        }
//
//        drogaRepository.delete(droga);
//        bemRepository.delete(bem);
//    }
//}