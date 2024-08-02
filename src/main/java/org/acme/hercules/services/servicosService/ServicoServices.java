package org.acme.hercules.services.servicosService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.Servicos;
import org.acme.hercules.repository.servicosRepository.ServicoRepository;

import java.util.List;

@ApplicationScoped
public class ServicoServices implements ServicoServiceInterface {

    @Inject
    ServicoRepository servicoRepository;

    @Transactional
    public List<Servicos> listAllServicos() {
        return servicoRepository.listAll();
    }

    @Transactional
    public Servicos findServicoById(Long id) {
        return servicoRepository.findById(id);
    }

    @Transactional
    public void createServico(Servicos servico) {
        servicoRepository.addServico(servico);
    }

    @Transactional
    public void updateServico(Servicos servico) {
        servicoRepository.updateServico(servico);
    }

    @Transactional
    public void deleteServico(Long id) {
        servicoRepository.deleteServico(id);
    }
}
