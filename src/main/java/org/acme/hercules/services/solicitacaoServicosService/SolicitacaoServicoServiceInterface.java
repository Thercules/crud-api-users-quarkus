package org.acme.hercules.services.solicitacaoServicosService;

import org.acme.hercules.entity.SolicitacaoServico;

import java.util.List;

public interface SolicitacaoServicoServiceInterface {
    void createSolicitacaoServico(Long userId, Integer servicoId, String status);
    List<SolicitacaoServico> listAllSolicitacoes();
    SolicitacaoServico findSolicitacaoById(Long id);
    void updateSolicitacaoStatus(Long id, String status);
    void deleteSolicitacao(Long id);
}
