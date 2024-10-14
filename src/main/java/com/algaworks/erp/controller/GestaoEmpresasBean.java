package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.repository.RamoAtividades;
import com.algaworks.erp.util.FacesMessages;

@Named // Para que fique acessivel a partir das nossas páginas de html
@ViewScoped // A instancia é criada quando a requisicao é feita, e vice-versa. (Tempo de vida)
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Inject
	private FacesMessages messages;
	
	private Converter ramoAtividadeConverter;
	
	private List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	
	private String termoPesquisa;
	
	
	public void pesquisar() {
		listaEmpresas = this.empresas.pesquisar(termoPesquisa);
		
		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}
	
	public List<RamoAtividade> completarRamoAtividades(String termo) {
		List<RamoAtividade> listaRamoAtividades = this.ramoAtividades.pesquisar(termo);
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		return listaRamoAtividades;
	}
	
	public void todasEmpresas() {
		listaEmpresas = empresas.listar();
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	

	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}
}
