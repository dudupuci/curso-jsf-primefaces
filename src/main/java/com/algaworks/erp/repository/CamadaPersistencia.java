package com.algaworks.erp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;

public class CamadaPersistencia {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		RamoAtividades ramoAtividades = new RamoAtividades(entityManager);
		Empresas empresas = new Empresas(entityManager);
		
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar("");
		List<Empresa> listaEmpresas = empresas.pesquisar("");
		System.out.println(listaEmpresas);
		
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("João da Silva");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("João da Silva 41952519000157");
		empresa.setTipo(TipoEmpresa.MEI);
		empresa.setDataFundacao(new Date());
		empresa.setRamoAtividade(listaRamoAtividades.get(0));
		
		empresas.guardar(empresa);
		entityManager.getTransaction().commit();
		
		List<Empresa> listaEmpresaAtualizada = empresas.pesquisar("");
		System.out.println(listaEmpresaAtualizada);
		
		entityManager.close();
		emf.close();
		
	}
}
