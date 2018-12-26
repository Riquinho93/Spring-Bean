package com.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;

import com.hibernateUtil.HibernateUtil;
import com.model.Contato;
import com.service.ContatoService;

public class SpringContato extends WebPage {

	private static final long serialVersionUID = 6430351346790614075L;

	@SpringBean(name = "contatoService")
	private ContatoService contato;

	public SpringContato() {

		Session session = HibernateUtil.getFactory().openSession();

		session.beginTransaction();
		Contato contato2 = new Contato();

//		contato.buscarPorId((1);
		
		contato2.setNome("Geovana");
		contato2.setEmail("geovana@gmail.com");
		contato2.setTelefone("99989888");

//		contato.SalvarOuAlterar(contato2);
	Contato buscar = contato.buscarPorId(4);
		System.out.println("O nome: " + buscar.getNome());
		System.out.println("Email: " + buscar.getEmail());
		System.out.println("telefone: " + buscar.getTelefone());
		

//		for (int i = 0; i < 2; i++) {
//			System.out.println("EEE: " + contato.getNome());
//			System.out.println("----------");
//		}
//		
//		
//		contato  = buscarPorId(2);
//		System.out.println("HHH: " + contato.getNome());
//		contato.setNome("Riquinho");
//		contato.setEmail("henrique.riquin@hotmail.com");
//		contato.setTelefone("91821312");
		add(new Label("nome", new PropertyModel<String>(contato2, "nome")));
		add(new Label("email", new PropertyModel<String>(contato2, "email")));
		add(new Label("telefone", new PropertyModel<String>(contato2, "telefone")));

		// session.save(contato2);

		session.getTransaction().commit();
		session.close();
	}

}
