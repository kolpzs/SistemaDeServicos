package model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "servico")
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Long id;

    @Column(name = "servico_dia")
    @Temporal(TemporalType.DATE)
    private Date dia;

    @Column(name = "servico_ativo")
    private boolean ativo;

    @Column(name = "servico_descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk")
    private ClienteEntity cliente;

    public ServicoEntity(Long id, Date dia, boolean ativo, String descricao, ClienteEntity cliente) {
        this.id = id;
        this.dia = dia;
        this.ativo = ativo;
        this.descricao = descricao;
        this.cliente = cliente;
    }

    public ServicoEntity(){}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
