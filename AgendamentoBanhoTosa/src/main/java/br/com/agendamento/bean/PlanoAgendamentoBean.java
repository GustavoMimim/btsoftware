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

import br.com.agendamento.dao.AnimalDAO;
import br.com.agendamento.dao.PlanoAgendamentoDAO;
import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Animal;
import br.com.agendamento.domain.PlanoAgendamento;
import br.com.agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PlanoAgendamentoBean implements Serializable {

	private PlanoAgendamento plano;
	private PlanoAgendamento planoSelecionado;
	private List<PlanoAgendamento> planos;// atributo para listar os agendamentos
	private List<Animal> animais;// atributo para listar animais

	// Getters e Setters
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	// Getters e Setters
	public PlanoAgendamento getPlanoSelecionado() {
		return planoSelecionado;
	}

	public void setPlanoSelecionado(PlanoAgendamento planoSelecionado) {
		this.planoSelecionado = planoSelecionado;
	}

	public PlanoAgendamento getPlano() {
		return plano;
	}

	public void setPlano(PlanoAgendamento plano) {
		this.plano = plano;
	}

	public List<PlanoAgendamento> getPlanos() {
		return planos;
	}

	public void setPlanos(List<PlanoAgendamento> planos) {
		this.planos = planos;
	}

	// zera todos os campos de Animal
	public void novo() {
		try {
			plano = new PlanoAgendamento();

			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um plano!");
			erro.printStackTrace();
		}
	}

	public void index() {
		try {
			Faces.redirect("./pages/planos.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
		}

	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planos = planoagendamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os planos de agendamento!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscar(1L);
			plano.setCodUsuarioInclusao(usuario);

			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planoagendamentoDAO.merge(plano);

			novo();
			planos = planoagendamentoDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o Plano!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		planoSelecionado = (PlanoAgendamento) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		plano = (PlanoAgendamento) evento.getComponent().getAttributes().get("planoSelecionado");

		try {
			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planoagendamentoDAO.excluir(plano);
			Messages.addGlobalInfo("Plano de Agendamento do animal " + plano.getCodAnimal().getNome() + " Excluído");

			planos = planoagendamentoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent evento) {

		try {
			plano = (PlanoAgendamento) evento.getComponent().getAttributes().get("planoSelecionado");
			if (plano == null) {
				Messages.addGlobalError("Não foi possivel alterar!(Valor(es) Nulo(s))");
			} else {
				// populando lista de clientes
				AnimalDAO animalDAO = new AnimalDAO();
				animais = animalDAO.listar();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um plano!");
			erro.printStackTrace();
		}
	}

}
