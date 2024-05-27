package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "func_id")
    private Long id;

    @Column(name = "func_nome")
    private String nome;

    @Column(name = "func_cpf")
    private String cpf;

    @Column(name = "func_telefone")
    private String telefone;

    @Column(name = "func_ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_fk")
    private UsuarioEntity usuario;

    public FuncionarioEntity(Long id, String nome, String cpf, String telefone, Boolean ativo, UsuarioEntity usuario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.ativo = ativo;
        this.usuario = usuario;
    }

    public FuncionarioEntity(){}

    // Getterse e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
