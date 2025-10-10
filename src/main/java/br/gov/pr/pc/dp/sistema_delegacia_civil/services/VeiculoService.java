//package br.gov.pr.pc.dp.sistema_delegacia_civil.services;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.VeiculoMapper;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Veiculo;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.VeiculoRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class VeiculoService extends BemService<VeiculoRequestDTO, VeiculoResponseDTO> {
//
//    private final VeiculoRepository veiculoRepository;
//
//    public VeiculoService(BemRepository bemRepository, FileStorageService fileStorageService, VeiculoRepository veiculoRepository) {
//        super(bemRepository, fileStorageService);
//        this.veiculoRepository = veiculoRepository;
//    }
//
//    @Override
//    @Transactional
//    public VeiculoResponseDTO create(VeiculoRequestDTO dto, MultipartFile imagem) {
//        // Cria o bem genérico
//        Bem bem = new Bem();
//        bem.setTipoBem("VEICULO");
//        bem.setImagemUrl(salvarImagem(imagem));
//        bemRepository.save(bem);
//
//        // Cria o veículo específico
//        Veiculo veiculo = VeiculoMapper.toEntity(dto, bem);
//        veiculoRepository.save(veiculo);
//
//        return VeiculoMapper.toResponseDTO(veiculo);
//    }
//
//    @Override
//    @Transactional
//    public VeiculoResponseDTO update(Long id, VeiculoRequestDTO dto, MultipartFile imagem) {
//        Veiculo veiculoExistente = veiculoRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
//
//        // Atualiza a imagem do bem
//        if (imagem != null && !imagem.isEmpty()) {
//            veiculoExistente.getBem().setImagemUrl(salvarImagem(imagem));
//        }
//
//        // Atualiza os dados do veículo
//        Veiculo veiculoAtualizado = VeiculoMapper.toEntity(dto, veiculoExistente.getBem());
//        veiculoAtualizado.setId(id);
//        veiculoRepository.save(veiculoAtualizado);
//
//        return VeiculoMapper.toResponseDTO(veiculoAtualizado);
//    }
//}
