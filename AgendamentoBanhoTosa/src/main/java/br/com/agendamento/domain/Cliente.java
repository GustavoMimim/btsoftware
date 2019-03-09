package br.com.agendamento.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericDomain {

	private String nome;

	// Chave Estrangeira para Usuario de Inclusao
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}
}
