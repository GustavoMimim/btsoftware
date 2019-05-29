package br.com.agendamento.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.AgendamentoDAO;
import br.com.agendamento.dao.AnimalDAO;
import br.com.agendamento.dao.ServicoDAO;
import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Agendamento;
import br.com.agendamento.domain.Animal;
import br.com.agendamento.domain.Servico;
import br.com.agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	private Agendamento agendamento;
	private Agendamento agendamentoSelecionado;
	private List<Agendamento> agendamentos;// atributo para listar os agendamentos
	private List<Animal> animais;
	private List<Servico> servicos;

	// Getters e Setters
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
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

	// zera todos os campos de Animal	
	public void novo() {
		try {
			agendamento = new Agendamento();

			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listar();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um agendamento!");
			erro.printStackTrace();
		}
	}

	public void index() {
		try {
			Faces.redirect("./pages/agendamentos.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
		}

	}

	@PostConstruct // � chamado logo apos o construtor da classe
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
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscar(1L);
			agendamento.setCodUsuarioInclusao(usuario);
			
			AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
			
			if (!agendamentoDAO.verificaAnimal(agendamento.getCodAnimal().getCodigo(),agendamento.getDataAtendimento()).isEmpty()) {
				Messages.addGlobalError("Animal ja agendado para o dia " + agendamento.getDataAtendimento());
			} else {//Cadastra um novo agendamento

				agendamentoDAO.merge(agendamento);

				novo();
				agendamentos = agendamentoDAO.listar();

				Messages.addGlobalInfo("Opera��o Realizada com Sucesso!");
			}
			

			//Messages.addGlobalInfo("Opera��o Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o agendamento!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		agendamentoSelecionado = (Agendamento) event.getObject();

		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");

		try {
			AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
			agendamentoDAO.excluir(agendamento);
			Messages.addGlobalInfo("Agendamento hor�rio " + agendamento.getHorario() + " e dia "
					+ agendamento.getDataAtendimento() + " Exclu�do");

			agendamentos = agendamentoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("N�o foi possivel excluir!");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent evento) {
		try {
			agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");
			if (agendamento == null) {
				Messages.addGlobalError("N�o foi possivel alterar!(Valor(es) Nulo(s))");
			} else {
				// populando lista de clientes
				AnimalDAO animalDAO = new AnimalDAO();
				animais = animalDAO.listar();
				
				ServicoDAO servicoDAO = new ServicoDAO();
				servicos = servicoDAO.listar();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um agendamento!");
			erro.printStackTrace();
		}
	}

}
