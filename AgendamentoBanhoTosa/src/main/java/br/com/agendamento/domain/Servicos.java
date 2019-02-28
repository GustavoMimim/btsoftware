package br.com.agendamento.domain;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity

public class Servicos extends GenericDomain {
 
	private String tipoServico;
	private String descricao;
	private float preco;
	
	//Getters e Setters :)
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	

	
	

}
