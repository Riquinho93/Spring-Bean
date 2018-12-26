package com.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.hibernateUtil.HibernateUtil;
import com.model.Contato;

@Repository
public class ContatoDao implements IContatoDao {

//	private Class classe;
	private Session session;

	public void SalvarOuAlterar(Contato contato) {
		session = HibernateUtil.getFactory().openSession();
		session.beginTransaction();

		session.saveOrUpdate(contato);

		session.getTransaction().commit();
		session.close();
	}

	public Contato buscarPorId(Integer id) {

		Session session = HibernateUtil.getFactory().openSession();

		Contato contato = (Contato) session.get(Contato.class, id);

		session.close();

		return contato;
	}

	public void excluir(Integer idFuncionario) {
		Session session = HibernateUtil.getFactory().openSession();
		session.beginTransaction();

		Contato funcionarioRemover = buscarPorId(idFuncionario);
		session.delete(funcionarioRemover);

		session.getTransaction().commit();
		session.close();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
