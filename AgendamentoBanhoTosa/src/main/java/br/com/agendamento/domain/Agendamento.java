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
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Servico codServico;

	public Animal getCodAnimal() {
		return codAnimal;
	}

	public void setCodAnimal(Animal codAnimal) {
		this.codAnimal = codAnimal;
	}

	public Servico getCodServico() {
		return codServico;
	}

	public void setCodServico(Servico codServico) {
		this.codServico = codServico;
	}

	@Column(nullable = false)
	private String horario;

	@Column(length = 3, nullable = false)
	private String entregarAnimal;

	@Column(length = 3, nullable = false)
	private String buscarAnimal;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAtendimento;

	@Column(length = 3, nullable = false)
	private String pacote;

	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getEntregarAnimal() {
		return entregarAnimal;
	}

	public void setEntregarAnimal(String entregarAnimal) {
		this.entregarAnimal = entregarAnimal;
	}

	public String getBuscarAnimal() {
		return buscarAnimal;
	}

	public void setBuscarAnimal(String buscarAnimal) {
		this.buscarAnimal = buscarAnimal;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getPacote() {
		return pacote;
	}

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

}
