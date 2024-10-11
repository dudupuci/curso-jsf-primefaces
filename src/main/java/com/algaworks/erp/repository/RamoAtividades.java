package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public RamoAtividades() {
		
	}

	public RamoAtividades(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		TypedQuery<RamoAtividade> query = entityManager
				.createQuery("from RamoAtividade where descricao like :descricao", RamoAtividade.class);
		
		query.setParameter("descricao", descricao + "%");
		return query.getResultList();
	}
	
	

}
