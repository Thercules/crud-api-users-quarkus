package org.acme.hercules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "Servicos")
public class Servicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Nome cannot be blank")
    public String nome;

    public String descricao;
    public BigDecimal preco;
    public int duracao;
    public boolean ativo;
}
