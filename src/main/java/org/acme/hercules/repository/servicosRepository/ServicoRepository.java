package org.acme.hercules.repository.servicosRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.Servicos;
import org.acme.hercules.repository.servicosRepository.ServicoRepositoryInterface;

@ApplicationScoped
public class ServicoRepository implements ServicoRepositoryInterface {

    @Transactional
    public void addServico(Servicos servico) {
        persist(servico);
    }

    @Transactional
    public Servicos updateServico(Servicos servico) {
        return getEntityManager().merge(servico);
    }

    @Transactional
    public void deleteServico(Long id) {
        deleteById(id);
    }

    @Override
    public Servicos findById(Integer id) {
        return find("id", id).firstResult();
    }
}
