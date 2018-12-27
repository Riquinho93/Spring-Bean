package com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

public abstract class GenericDao<Entidade, id extends Serializable> extends GenericDAOImpl<Entidade, id> {
	
	private Session session;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public Entidade buscarPorId(Integer id) {

		session = getSessionFactory().openSession();

		Entidade t = (Entidade) session.get(persistentClass, id);

		session.close();

		return t;
	}

//	@Transactional
	public void SalvarOuAlterar(Entidade entidade) {
		session = getSessionFactory().openSession();
		session.beginTransaction();

		session.saveOrUpdate(entidade);

		session.getTransaction().commit();
		session.close();
	}

//	@Transactional
	public void excluir(Integer id) {
		session = getSessionFactory().openSession();
		session.beginTransaction();

		Entidade funcionarioRemover = buscarPorId(id);
		session.delete(funcionarioRemover);

		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Entidade> listar() {
		Query query = getSessionFactory().getCurrentSession().createQuery("from Contato ");
		List<Entidade> userList = query.list();
		return userList;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
