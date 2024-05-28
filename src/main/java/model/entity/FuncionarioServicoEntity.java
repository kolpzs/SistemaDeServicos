package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "funcionario_servico")
public class FuncionarioServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "func_id_fk", nullable = false)
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "servico_id_fk", nullable = false)
    private ServicoEntity servico;

    public FuncionarioServicoEntity(FuncionarioEntity funcionario, ServicoEntity servico) {
        this.funcionario = funcionario;
        this.servico = servico;
    }

    public FuncionarioServicoEntity() {}

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuncionarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioEntity funcionario) {
        this.funcionario = funcionario;
    }

    public ServicoEntity getServico() {
        return servico;
    }

    public void setServico(ServicoEntity servico) {
        this.servico = servico;
    }
}

