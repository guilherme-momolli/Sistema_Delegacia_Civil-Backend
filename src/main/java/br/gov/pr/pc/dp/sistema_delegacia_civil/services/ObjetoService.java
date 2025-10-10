//package br.gov.pr.pc.dp.sistema_delegacia_civil.services;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto.ObjetoResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.ObjetoMapper;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Objeto;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.ObjetoRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class ObjetoService {
//
//    private final BemRepository bemRepository;
//    private final ObjetoRepository objetoRepository;
//    private final FileStorageService fileStorageService;
//    private final String subFolder = "Imagens/Objetos";
//
//    public List<ObjetoResponseDTO> listObjetos() {
//        return objetoRepository.findAll()
//                .stream()
//                .map(ObjetoMapper::toResponseDTO)
//                .toList();
//    }
//
//    @Transactional
//    public ObjetoResponseDTO createObjeto(ObjetoRequestDTO dto, MultipartFile imagem) {
//
//        Bem bem = new Bem();
//        bem.setTipoBem("OBJETO");
//        if (imagem != null && !imagem.isEmpty()) {
//            bem.setImagemUrl(fileStorageService.storeFile(imagem, subFolder));
//        }
//        Bem bemSalvo = bemRepository.save(bem);
//
//        Objeto objeto = ObjetoMapper.toEntity(dto, bemSalvo);
//        objetoRepository.save(objeto);
//
//        return ObjetoMapper.toResponseDTO(objeto);
//    }
//
//    @Transactional
//    public ObjetoResponseDTO updateObjeto(Long id, ObjetoRequestDTO dto, MultipartFile imagem) {
//        Objeto objetoExistente = objetoRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
//
//        Bem bem = objetoExistente.getBem();
//        if (imagem != null && !imagem.isEmpty()) {
//            bem.setImagemUrl(fileStorageService.storeFile(imagem, subFolder));
//        }
//        bemRepository.save(bem);
//
//        Objeto atualizado = ObjetoMapper.toEntity(dto, bem);
//        atualizado.setId(objetoExistente.getId());
//        objetoRepository.save(atualizado);
//
//        return ObjetoMapper.toResponseDTO(atualizado);
//    }
//
//    @Transactional
//    public void deleteObjeto(Long id) {
//        Objeto objeto = objetoRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
//
//        Bem bem = objeto.getBem();
//        if (bem.getImagemUrl() != null) {
//            fileStorageService.deleteFile(bem.getImagemUrl());
//        }
//
//        objetoRepository.delete(objeto);
//        bemRepository.delete(bem);
//    }
//}
