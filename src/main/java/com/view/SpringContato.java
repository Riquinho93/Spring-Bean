package com.view;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernateUtil.HibernateUtil;
import com.model.Contato;




public class SpringContato extends WebPage{
	
	private static final long serialVersionUID = 6430351346790614075L;
	
//	@SpringBean(name="contato")
	private Contato contato = new Contato();
	
	public SpringContato() {
		
		Session session = HibernateUtil.getFactory().openSession();

		session.beginTransaction();
		Contato contato2 = new Contato();
		
		contato.setNome("Maria");
		contato.setEmail("maria@gmail.com");
		contato.setTelefone("81235466");
		
		for (int i = 0; i < 2; i++) {
			System.out.println("EEE: " + contato.getNome());
			System.out.println("----------");
		}
		
		
		contato  = buscarPorId(2);
		System.out.println("HHH: " + contato.getNome());
//		contato.setNome("Riquinho");
//		contato.setEmail("henrique.riquin@hotmail.com");
//		contato.setTelefone("91821312");
		add(new Label("nome", new PropertyModel<String>(contato, "nome")));
		add(new Label("email", new PropertyModel<String>(contato, "email")));
		add(new Label("telefone", new PropertyModel<String>(contato, "telefone")));
		
		//session.save(contato2);

		session.getTransaction().commit();
		session.close();
	}
	
	public static Contato buscarPorId(long l) {

		Session session =  HibernateUtil.getFactory().openSession();

		Contato contato = (Contato) session.get(Contato.class, l);

		session.close();

		return contato;
	}
	

}
