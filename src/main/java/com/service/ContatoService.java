package com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ContatoDao;
import com.model.Contato;

@Service
public class ContatoService implements IContatoService {

	private ContatoDao contatoDao;

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}
	
	@Override
	@Transactional
	public void SalvarOuAlterar(Contato contato) {
		contatoDao.SalvarOuAlterar(contato);
	}

	@Override
	@Transactional(readOnly = true)
	public Contato buscarPorId(Integer id) {
		return contatoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer idContato) {
		contatoDao.excluir(idContato);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contato> listar() {
		return contatoDao.listar();
	}
}
