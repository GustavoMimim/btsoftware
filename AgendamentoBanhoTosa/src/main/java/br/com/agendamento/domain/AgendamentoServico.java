package br.com.agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class AgendamentoServico extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Agendamento codAgendamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Servico codServico;

	@Column(nullable = true)
	private double preco;

	public Agendamento getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(Agendamento codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

	public Servico getCodServico() {
		return codServico;
	}

	public void setCodServico(Servico codServico) {
		this.codServico = codServico;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
