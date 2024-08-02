package org.acme.hercules.services.servicosService;
import org.acme.hercules.entity.Servicos;
import java.util.List;

public interface ServicoServiceInterface {
    List<Servicos> listAllServicos();
    Servicos findServicoById(Long id);
    void createServico(Servicos servico);
    void updateServico(Servicos servico);
    void deleteServico(Long id);
}


