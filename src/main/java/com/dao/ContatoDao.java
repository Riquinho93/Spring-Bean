package com.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Contato;

@Repository
public class ContatoDao extends GenericDao<Contato, Serializable> {

}
