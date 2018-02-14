package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class HibernateDAO<T> extends BaseDAO {

	private Class<T> classe;
	protected Session session;
	
	public HibernateDAO(Class<T> classe) {
		super();
		this.classe = classe;
		this.session = super.getSession();
	}
	
	public HibernateDAO(Class<T> classe, Session session) {
		super();
		this.classe = classe;
		this.session = session;
	}

	
	public void atualizar(T bean) {
		session.update(bean);
	}

	
	public void excluir(T bean) {
		session.delete(bean);
	}

	
	public T getBean(Serializable codigo) {
		@SuppressWarnings("unchecked")
		T bean = (T)session.get(classe, codigo);
		return bean;
	}

	
	public List<T> getBeans() {
		Criteria crit = session.createCriteria(classe);
		crit.setCacheable(true);

		@SuppressWarnings("unchecked")
		List<T> beans = (List<T>)crit.list();
		return beans;
	}

	
	public void salvar(T bean) {
		session.save(bean);
	}

	@SuppressWarnings("unchecked")
	public List<T> getBeansByExample(T bean) {
		Example example = getExample(bean);
		return session.createCriteria(classe).add(example).list();
	}
	
	protected Example getExample(T bean)
	{
		Example example = Example.create(bean);
		example.enableLike(MatchMode.START);
		example.ignoreCase();
		example.excludeZeroes();
		return example;
	}

}
