package org.acme.hercules.services.solicitacaoServicosService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.hercules.entity.Servicos;
import org.acme.hercules.entity.SolicitacaoServico;
import org.acme.hercules.entity.User;
import org.acme.hercules.repository.servicosRepository.ServicoRepository;
import org.acme.hercules.repository.solicitacaoServicoRepository.SolicitacaoServicoRepository;
import org.acme.hercules.repository.userRepository.UserRepository;

import java.util.List;

@ApplicationScoped
public class SolicitacaoServicoService implements SolicitacaoServicoServiceInterface {

    @Inject
    SolicitacaoServicoRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    ServicoRepository servicoRepository;

    @Transactional
    @Override
    public void createSolicitacaoServico(Long userId, Integer servicoId, String status) {
        if (userId == null || servicoId == null) {
            throw new IllegalArgumentException("User ID and Service ID must not be null");
        }

        User user = userRepository.findById(userId);
        Servicos servico = servicoRepository.findById(servicoId);

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

    @Override
    public List<SolicitacaoServico> listAllSolicitacoes() {
        return repository.listAll();
    }

    @Override
    public SolicitacaoServico findSolicitacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void updateSolicitacaoStatus(Long id, String status) {
        SolicitacaoServico solicitacao = repository.findById(id);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não encontrada");
        }
        solicitacao.setStatus(status);
        repository.persist(solicitacao);
    }

    @Transactional
    @Override
    public void deleteSolicitacao(Long id) {
        SolicitacaoServico solicitacao = repository.findById(id);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não encontrada");
        }
        repository.delete(solicitacao);
    }
}
