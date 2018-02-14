package com.dao;

import com.model.Veiculos;

public class VeiculosDAO extends HibernateDAO<Veiculos> {

	public VeiculosDAO() {
		super(Veiculos.class);
	}
}
