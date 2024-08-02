package org.acme.hercules.repository.solicitacaoServicoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.hercules.entity.SolicitacaoServico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SolicitacaoServicoRepository implements PanacheRepository<SolicitacaoServico> {
    // Métodos personalizados podem ser adicionados aqui
}
