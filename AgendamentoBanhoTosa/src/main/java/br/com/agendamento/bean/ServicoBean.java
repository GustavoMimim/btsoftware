package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.ServicoDAO;
import br.com.agendamento.domain.Servico;
import br.com.agendamento.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable {

	// atributo para guardar os dados da view
	private Servico servico;

	private Servico servicoSelecionado;

	// atributo para listar servicos
	private List<Servico> servicos;

	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	//funções
	public void novo() {
		servico = new Servico();
	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar serviços!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.merge(servico);

			novo();
			servicos = servicoDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar Serviço!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		// aqui voce ja pode trabalhar com o event enviado e converter conforme
		// necessidade

		servicoSelecionado = (Servico) event.getObject();

		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");

		try{
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.excluir(servico);
			Messages.addGlobalInfo("Serviço " + servico.getTipoServico() + " Excluído");
			
			servicos = servicoDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");
		
		if(servico == null) {
			Messages.addGlobalError("Não foi possivel alterar!");
		}
	}

}
