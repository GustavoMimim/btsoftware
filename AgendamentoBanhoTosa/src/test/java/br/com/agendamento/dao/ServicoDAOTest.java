package br.com.agendamento.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agendamento.domain.Servico;

public class ServicoDAOTest {

	//@Ignore
	@Test
	public void salvar() {
		// Instanciando objeto
		Servico servico = new Servico();

		// Atribuindo as coisas ao objeto
		servico.setDescricao("Tosa Geral");
		servico.setPreco(40);
		servico.setTipoServico("Tosa");

		// Instanciando para salvar
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.salvar(servico);
	}
}
