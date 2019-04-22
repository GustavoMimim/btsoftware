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

import br.com.agendamento.dao.PagamentoDAO;
import br.com.agendamento.domain.Pagamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PagamentoBean implements Serializable {

	// atributo para guardar os dados da view
	private Pagamento pagamento;

	private Pagamento pagamentoSelecionado;

	// atributo para listar pagamentos
	private List<Pagamento> pagamentos;

	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	//funções
	public void novo() {
		pagamento = new Pagamento();
	}
	
	public void index() {
		try {
			Faces.redirect("./pages/pagamento.xhtml");
		}
		catch (IOException erro) {
			erro.printStackTrace();
		}
		
	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			PagamentoDAO pagamentoDAO = new PagamentoDAO();
			pagamentos = pagamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar serviços!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			//Teste
			/*UsuarioDAO usuarioDao = new UsuarioDAO();
			Usuario usuario = new Usuario();
			usuario = usuarioDao.buscar(1L);
			*/
			PagamentoDAO pagamentoDAO = new PagamentoDAO();
			//pagamento.setCodUsuarioInclusao(usuario);
			pagamentoDAO.merge(pagamento);

			novo();
			pagamentos = pagamentoDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar Serviço!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		// aqui voce ja pode trabalhar com o event enviado e converter conforme
		// necessidade

		pagamentoSelecionado = (Pagamento) event.getObject();

		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		pagamento = (Pagamento) evento.getComponent().getAttributes().get("pagamentoSelecionado");

		try{
			PagamentoDAO pagamentoDAO = new PagamentoDAO();
			pagamentoDAO.excluir(pagamento);
			Messages.addGlobalInfo("Pagamento " + pagamento.getCodigo() + "do tipo " + pagamento.getTipoPagamento() + " excluído");
			
			pagamentos = pagamentoDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		pagamento = (Pagamento) evento.getComponent().getAttributes().get("pagamentoSelecionado");
		
		if(pagamento == null) {
			Messages.addGlobalError("Não foi possivel alterar!");
		}
	}

}
