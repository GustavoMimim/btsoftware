package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.AgendamentoDAO;
import br.com.agendamento.domain.Agendamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable{

	private Agendamento agendamento;
	private Agendamento agendamentoSelecionado;
	private List<Agendamento> agendamentos;// atributo para listar os agendamentos
	
	//Getters e Setters
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Agendamento getAgendamentoSelecionado() {
		return agendamentoSelecionado;
	}

	public void setAgendamentoSelecionado(Agendamento agendamentoSelecionado) {
		this.agendamentoSelecionado = agendamentoSelecionado;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	//zera todos os campos de Animal
	public void novo() {
		agendamento = new Agendamento();
	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
			agendamentos = agendamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os agendamentos!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
			agendamentoDAO.merge(agendamento);

			novo();
			agendamentos = agendamentoDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o animal!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		agendamentoSelecionado = (Agendamento) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");

		try{
			AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
			agendamentoDAO.excluir(agendamento);
			Messages.addGlobalInfo("Agendamento horário " + agendamento.getHorario() + " e dia "+ agendamento.getDataAtendimento() + " Excluído");
			
			agendamentos = agendamentoDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");
		if(agendamento == null) {
			Messages.addGlobalError("Não foi possivel alterar!");
		}
	}
	
}
