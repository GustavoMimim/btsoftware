package br.com.agendamento.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class PlanoServico extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private PlanoAgendamento codPlano;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Servico codServico;

	public PlanoAgendamento getCodPlano() {
		return codPlano;
	}

	public void setCodPlano(PlanoAgendamento codPlano) {
		this.codPlano = codPlano;
	}

	public Servico getCodServico() {
		return codServico;
	}

	public void setCodServico(Servico codServico) {
		this.codServico = codServico;
	}
}
