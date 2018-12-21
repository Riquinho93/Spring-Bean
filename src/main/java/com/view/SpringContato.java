package com.view;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.model.Contato;

public class SpringContato extends WebPage{
	
	private static final long serialVersionUID = 6430351346790614075L;
	
	@SpringBean(name="contato")
	private Contato contato;
	
	public SpringContato() {
		
		contato.setNome("Henrique");
		contato.setEmail("henrique.riquin@hotmail.com");
		contato.setTelefone("999999999999");
		add(new Label("nome", new PropertyModel<String>(contato, "nome")));
		add(new Label("email", new PropertyModel<String>(contato, "email")));
		add(new Label("telefone", new PropertyModel<String>(contato, "telefone")));
		
	}

}
