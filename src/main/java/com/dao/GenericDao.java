package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.model.Contato;
import com.mysql.jdbc.PreparedStatement;

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
		Query query = getSessionFactory().getCurrentSession().createQuery("select c from Contato c order by c.nome");
		List<Entidade> userList = query.list();
		return userList;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<Entidade> searchDao(Search search) {
		session = getSessionFactory().openSession();
		session.beginTransaction();
		List<Entidade> userList = search(search);
		session.close();
		return userList;
	
	}
	
	public void pesquisar(String nome) {
		try {
			Connection conexao = null;
			String sql= "select * from Contato where nome like ?";
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, nome + "%");
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
