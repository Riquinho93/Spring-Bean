package com.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.model.Contato;
import com.service.ContatoService;

public class SpringContato extends WebPage {

	private static final long serialVersionUID = 6430351346790614075L;

	Form<?> form = new Form<Object>("form");
	private ModalWindow modalWindow;
	private List<Contato> contatoModels = new LinkedList<Contato>();

	private ListView<Contato> listView = null;
	// Criando um container
	private WebMarkupContainer listContainer = null;
	private LoadableDetachableModel<List<Contato>> loadList;

	@SpringBean(name = "contatoService")
	private ContatoService contatoService;

	public SpringContato() {

		Contato contato2 = new Contato();
		contatoModels = contatoService.listar();

		// Metodo do container
		add(divConteiner());
		
//		contato.buscarPorId((1);

		contato2.setNome("Lucas");
		contato2.setEmail("lucas@gmail.com");
		contato2.setTelefone("99989888");

	//	contatoService.SalvarOuAlterar(contato2);
		Contato buscar = contatoService.buscarPorId(4);
		System.out.println("O nome: " + buscar.getNome());
		System.out.println("Email: " + buscar.getEmail());
		System.out.println("telefone: " + buscar.getTelefone());

	}

	// ListView
	private WebMarkupContainer divConteiner() {
		listContainer = new WebMarkupContainer("theContainer");
		listContainer.setOutputMarkupId(true);
		loadList = new LoadableDetachableModel<List<Contato>>() {

			private static final long serialVersionUID = 1L;

			protected List<Contato> load() {
				return contatoModels;
			}
		};
		// Criando a lista View
		listView = new ListView<Contato>("listview", loadList) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Contato> item) {

				Contato user = item.getModelObject();

				// item.add(new Label("ID", user.getId()));
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("email", user.getEmail()));
				item.add(new Label("telefone", user.getTelefone()));
			}

		};
		add(listView);
		listView.setOutputMarkupId(true);
		listContainer.add(listView);

		return listContainer;
	}

}
