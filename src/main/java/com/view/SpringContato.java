package com.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.model.Contato;
import com.service.ContatoService;

public class SpringContato extends WebPage {

	private static final long serialVersionUID = 6430351346790614075L;

	private ModalWindow modalWindow;
	private List<Contato> contatoModels = new LinkedList<Contato>();

	private PageableListView<Contato> listView = null;
	// Criando um container
	private WebMarkupContainer listContainer = null;
	private LoadableDetachableModel<List<Contato>> loadList;
	private Contato contato;
	Form<Contato> form;

	@SpringBean(name = "contatoService")
	private ContatoService contatoService;

	public SpringContato() {

		Contato contato2 = new Contato();
		contatoModels = contatoService.listar();

		// Metodo do container
//		add(new PagingNavigation("nave", listView));

		add(divConteiner());
		add(filtrar());
//		contato.buscarPorId((1);

		/*
		 * contato2.setNome("Lucas"); contato2.setEmail("lucas@gmail.com");
		 * contato2.setTelefone("99989888");
		 */

		// contatoService.SalvarOuAlterar(contato2);
		/*
		 * Contato buscar = contatoService.buscarPorId(4); System.out.println("O nome: "
		 * + buscar.getNome()); System.out.println("Email: " + buscar.getEmail());
		 * System.out.println("telefone: " + buscar.getTelefone());
		 */

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
		listView = new PageableListView<Contato>("listview", loadList, 5) {

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
		add(new PagingNavigator("nave", listView));

	/*	PagingNavigation navigation = new PagingNavigation("nave", listView);
		navigation.setOutputMarkupId(true);
		add(navigation);
		listContainer.add(navigation);*/
		return listContainer;
	}

	public Form<Contato> filtrar() {
		contato = new Contato();
		form = new Form<Contato>("form", new CompoundPropertyModel<Contato>(contato));
		TextField<String> nome = new TextField<String>("nome");
		TextField<String> email = new TextField<String>("email");
		TextField<String> telefone = new TextField<String>("telefone");
		form.add(nome);
		form.add(email);
		form.add(telefone);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", form) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Contato.class);

				if (contato.getNome() != null && !contato.getNome().equals("")) {
					search.addFilterLike("nome", "%" + contato.getNome() + "%");
				}
				if (contato.getEmail() != null && !contato.getEmail().equals("")) {
					search.addFilterILike("email", "%" + contato.getEmail() + "%");
				}
				if (contato.getTelefone() != null && !contato.getTelefone().equals("")) {

					search.addFilterILike("telefone", "%" + contato.getTelefone() + "%");
				}
				System.out.println("nome: " + nome);
				contatoModels = contatoService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		form.setOutputMarkupId(true);
		form.add(ajaxSubmitLink).setOutputMarkupId(true);
		return form;

	}

}
