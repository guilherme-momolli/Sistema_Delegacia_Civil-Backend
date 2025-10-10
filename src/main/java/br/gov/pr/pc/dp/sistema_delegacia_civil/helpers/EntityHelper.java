package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia.BoletimOcorrenciaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.delagacia.DelegaciaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.pessoa.PessoaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BoletimOcorrenciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.DelegaciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityHelper {

    private final DelegaciaRepository delegaciaRepository;
    private final PessoaRepository pessoaRepository;
    private final BoletimOcorrenciaRepository boletimRepository;
    private final InqueritoPolicialRepository inqueritoPolicialRepository;

    public void validarDelegaciaExistente(Long id) {
        if (!delegaciaRepository.existsById(id)) {
            throw new DelegaciaNotFoundException(id);
        }
    }

    public void validarInqueritoPolicalExistente(Long id){
        if (!inqueritoPolicialRepository.existsById(id)) {
            throw new DelegaciaNotFoundException(id);
        }
    }
    public void validarPessoaExistente(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new PessoaNotFoundException(id);
        }
    }

    public void validarBoletimExistente(Long id) {
        if (!boletimRepository.existsById(id)) {
            throw new BoletimOcorrenciaNotFoundException(id);
        }
    }
}
