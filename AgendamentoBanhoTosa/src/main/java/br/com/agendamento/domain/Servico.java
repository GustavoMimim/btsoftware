package br.com.agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Servico extends GenericDomain {

	// Atributos
	@Column(length = 30, nullable = false)
	private String tipoServico;

	@Column(length = 30, nullable = true)
	private String descricao;

	@Column(nullable = false)
	private double preco;

	// Chave Estrangeira
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;

	// Getters e Setters :)
	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
