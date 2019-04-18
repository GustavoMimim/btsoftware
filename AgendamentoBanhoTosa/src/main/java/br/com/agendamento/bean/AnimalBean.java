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
import br.com.agendamento.dao.ClienteDAO;
import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Animal;
import br.com.agendamento.domain.Cliente;
import br.com.agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AnimalBean implements Serializable {

	private Animal animal;
	private Animal animalSelecionado;
	private List<Animal> animais;// atributo para listar animais
	private List<Cliente> clientes;

	// Getters e Setters
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Animal getAnimalSelecionado() {
		return animalSelecionado;
	}

	public void setAnimalSelecionado(Animal animalSelecionado) {
		this.animalSelecionado = animalSelecionado;
	}

	// zera todos os campos de Animal
	public void novo() {
		try {
			animal = new Animal();

			// populando lista de clientes
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um animal!");
			erro.printStackTrace();
		}
	}

	public void index() {
		try {
			Faces.redirect("./pages/animais.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
		}

	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar animais!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscar(1L);
			animal.setCodUsuarioInclusao(usuario);

			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.merge(animal);

			novo();
			animais = animalDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o animal!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		animalSelecionado = (Animal) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");

		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.excluir(animal);
			Messages.addGlobalInfo("Animal " + animal.getNome() + " Excluído");

			animais = animalDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent evento) {
		try {
			animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");
			if (animal == null) {
				Messages.addGlobalError("Não foi possivel alterar!");
			}
			else {
				// populando lista de clientes
				ClienteDAO clienteDAO = new ClienteDAO();
				clientes = clienteDAO.listar();
			}


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um animal!");
			erro.printStackTrace();
		}

		
	}
}
