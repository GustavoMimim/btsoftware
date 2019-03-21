package br.com.agendamento.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class PagamentoServico extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Pagamento codPagamento;

	@OneToOne
	@JoinColumn(nullable = true)
	private PlanoAgendamento codPlano;

	@OneToOne
	@JoinColumn(nullable = true)
	private Agendamento codAgendamento;

	public Pagamento getCodPagamento() {
		return codPagamento;
	}

	public void setCodPagamento(Pagamento codPagamento) {
		this.codPagamento = codPagamento;
	}

	public PlanoAgendamento getCodPlano() {
		return codPlano;
	}

	public void setCodPlano(PlanoAgendamento codPlano) {
		this.codPlano = codPlano;
	}

	public Agendamento getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(Agendamento codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

}
