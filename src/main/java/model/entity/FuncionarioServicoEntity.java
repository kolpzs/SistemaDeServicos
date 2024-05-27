package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "funcionario_servico")
public class FuncionarioServicoEntity {

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
