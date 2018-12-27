package com.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.model.Contato;

public abstract class GenericDao<Entidade, id extends Serializable> extends GenericDAOImpl<Entidade, id> {

	private Session session;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		sessionFactory = sessionFactory;
	}

	public Entidade buscarPorId(Integer id) {

		session = getSessionFactory().openSession();

		Entidade t = (Entidade) session.get(persistentClass, id);

		session.close();

		return t;
	}

	public void SalvarOuAlterar(Entidade entidade) {
		session = getSessionFactory().openSession();
		session.beginTransaction();

		session.saveOrUpdate(entidade);

		session.getTransaction().commit();
		session.close();
	}

	public void excluir(Integer id) {
		session = getSessionFactory().openSession();
		session.beginTransaction();

		Entidade funcionarioRemover = buscarPorId(id);
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
