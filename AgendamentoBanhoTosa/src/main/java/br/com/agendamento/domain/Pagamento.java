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
public class Pagamento extends GenericDomain {

	// Atributos
	@Column(length = 45, nullable = true)
	private String tipoPagamento;
	
	@Column(nullable = true)
	private String cliente;

	@Column(nullable = false)
	private double valor;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPagamento;

	// Chave Estrangeira
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario codUsuarioInclusao;
	
	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Usuario getCodUsuarioInclusao() {
		return codUsuarioInclusao;
	}

	public void setCodUsuarioInclusao(Usuario codUsuarioInclusao) {
		this.codUsuarioInclusao = codUsuarioInclusao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	

	
}
