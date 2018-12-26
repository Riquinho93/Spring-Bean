package com.service;

import org.springframework.stereotype.Service;

import com.dao.ContatoDao;
import com.model.Contato;

@Service
public class ContatoService implements IContatoService {

	private ContatoDao contatoDao;

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}
	
	@Override
	public void SalvarOuAlterar(Contato contato) {
		contatoDao.SalvarOuAlterar(contato);
	}

	@Override
	public Contato buscarPorId(Integer id) {
		return contatoDao.buscarPorId(id);
	}

	@Override
	public void excluir(Integer idContato) {
		contatoDao.excluir(idContato);
	}
}
