package org.acme.hercules.services.solicitacaoServicosService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.Servicos;
import org.acme.hercules.entity.SolicitacaoServico;
import org.acme.hercules.entity.User;
import org.acme.hercules.repository.servicosRepository.ServicoRepository;
import org.acme.hercules.repository.solicitacaoServico.SolicitacaoServicoRepository;
import org.acme.hercules.repository.UserRepository;

@ApplicationScoped
public class SolicitacaoServicoService {

    @Inject
    SolicitacaoServicoRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    ServicoRepository servicoRepository;

    @Transactional
    public void createSolicitacaoServico(Long userId, Integer servicoId, String status) {
        if (userId == null || servicoId == null) {
            throw new IllegalArgumentException("User ID and Service ID must not be null");
        }

        User user = userRepository.findById(userId);
        Servicos servico = servicoRepository.findById(servicoId); // Removendo a conversão para int

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (servico == null) {
            throw new IllegalArgumentException("Service not found");
        }

        SolicitacaoServico solicitacao = new SolicitacaoServico();
        solicitacao.setUser(user);
        solicitacao.setServico(servico);
        solicitacao.setStatus(status);

        repository.persist(solicitacao);
    }
}
