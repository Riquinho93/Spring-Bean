package com.dao;

import com.model.Contato;

public interface IContatoDao {
	
	public void SalvarOuAlterar(Contato contato);
	public Contato buscarPorId(Integer id);
	public void excluir(Integer idContato);
}
