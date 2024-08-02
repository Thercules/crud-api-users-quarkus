package org.acme.hercules.repository.servicosRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.hercules.entity.Servicos;

public interface ServicoRepositoryInterface extends PanacheRepository<Servicos> {
    void addServico(Servicos servico);
    Servicos updateServico(Servicos servico);
    void deleteServico(Long id); // Se o id para deletar continuar como Long, mantenha assim

    // Adicione o método findById se ele não existir
    Servicos findById(Integer id); // Mudança de Long para Integer
}
