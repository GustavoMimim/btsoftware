package br.com.agendamento.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class PlanoAgendamento extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Animal codAnimal;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horario;

	@Column(length = 3, nullable = false)
	private int frequenciaAtendimento;

	@Column(length = 3, nullable = false)
	private int frequenciaPagamento;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date dataCadastro;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date dataCancelamento;

	@Column(nullable = false)
	private double precoPlano;

	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getFrequenciaAtendimento() {
		return frequenciaAtendimento;
	}

	public void setFrequenciaAtendimento(int frequenciaAtendimento) {
		this.frequenciaAtendimento = frequenciaAtendimento;
	}

	public int getFrequenciaPagamento() {
		return frequenciaPagamento;
	}

	public void setFrequenciaPagamento(int frequenciaPagamento) {
		this.frequenciaPagamento = frequenciaPagamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public double getPrecoPlano() {
		return precoPlano;
	}

	public void setPrecoPlano(double precoPlano) {
		this.precoPlano = precoPlano;
	}

}
