package com.service;

import java.util.List;

import com.model.Contato;

public interface IContatoService {
	
	public void SalvarOuAlterar(Contato contato);
	public Contato buscarPorId(Integer id);
	public void excluir(Integer idContato);
	public List<Contato> listar();
}
