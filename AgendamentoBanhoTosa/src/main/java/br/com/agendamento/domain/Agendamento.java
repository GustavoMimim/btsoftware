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
public class Agendamento extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Animal codAnimal;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horario;

	@Column(length = 1, nullable = false)
	private int entregarAnimal;

	@Column(length = 1, nullable = false)
	private int buscarAnimal;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAtendimento;

	@Column(length = 1, nullable = false)
	private int pacote;

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

	public int getEntregarAnimal() {
		return entregarAnimal;
	}

	public void setEntregarAnimal(int entregarAnimal) {
		this.entregarAnimal = entregarAnimal;
	}

	public int getBuscarAnimal() {
		return buscarAnimal;
	}

	public void setBuscarAnimal(int buscarAnimal) {
		this.buscarAnimal = buscarAnimal;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public int getPacote() {
		return pacote;
	}

	public void setPacote(int pacote) {
		this.pacote = pacote;
	}

}
