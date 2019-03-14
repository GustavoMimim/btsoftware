package br.com.agendamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Animal extends GenericDomain {
	//----------------------------------------Atributos de chaves estrangeiras
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente codCliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;
	
	//----------------------------------------Atributos simples
	
	@Column(length = 20, nullable = false)
	private String nome;
	
	@Column(length = 60, nullable = true)
	private String observacoes;
	
	@Column(length = 10, nullable = false)
	private String porte;
	
	@Column(length = 30, nullable = false)
	private String cor;
	
	@Column(length = 5, nullable = false)
	private String sexo;
	
	@Column(length = 30, nullable = false)
	private String raca;
	
	@Column(length = 15, nullable = false)
	private String pelagem;
	//----------------------------------------Metodos
	public Cliente getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}
	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}
	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getPorte() {
		return porte;
	}
	public void setPorte(String porte) {
		this.porte = porte;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getPelagem() {
		return pelagem;
	}
	public void setPelagem(String pelagem) {
		this.pelagem = pelagem;
	}
	
}
