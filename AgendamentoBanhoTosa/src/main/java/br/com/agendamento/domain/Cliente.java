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
public class Cliente extends GenericDomain {


	// Chave Estrangeira para Usuario de Inclusao
	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario codUsuarioInclusao;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario codUsuarioAlteracao;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 12, nullable = false)
	private String rg;

	@Column(length = 14, nullable = false)
	private String telefone;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;
	
	@Column(length = 10, nullable = false)
	private String status;

	@Column(length = 50, nullable = false)
	private String logradouro;

	@Column(length = 14, nullable = false)
	private String cep;

	@Column(nullable = false)
	private int numero;

	@Column(length = 30, nullable = false)
	private String bairro;

	@Column(length = 30, nullable = false)
	private String cidade;

	@Column(length = 20, nullable = false)
	private String estado;

	@Column(length = 14)
	private String complemento;
	
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getCodUsuarioAlteracao() {
		return codUsuarioAlteracao;
	}

	public void setCodUsuarioAlteracao(Usuario codUsuarioAlteracao) {
		this.codUsuarioAlteracao = codUsuarioAlteracao;
	}

	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}

}
