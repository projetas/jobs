package com.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dao.VeiculosDAO;
import com.model.Veiculos;


@Path("/service")
public class VeiculoRest {
	VeiculosDAO dao = new VeiculosDAO();
	int cont = 0;
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Veiculos[] getVeiculos(){
		
		List<Veiculos> list = dao.getBeans();
		
		Veiculos[] retorno = new Veiculos[list.size()];
		
		
		for(Veiculos t : list ){
			retorno[cont] = t;
			cont++;
		}
		
		
		return retorno;
		
	}
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Veiculos veiculos){
		
		Date now = new Date();
		
		if(validate(veiculos) == true){
			
			veiculos.setDataDeCadastro(now);
			veiculos.setDataDeAtualizacao(null);
						
			dao.salvar(veiculos);
			dao.commit();
			
			return Response.ok().entity(veiculos).build();
			
		}else{
			return Response.status(401).entity("Campos não preenchidos completamento ou corretamente!").build();
		}
	}
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Veiculos veiculos){
		
		if(validate(veiculos) == true){
		veiculos = fill(veiculos);
		
		dao.atualizar(veiculos);
		dao.commit();
		
		return Response.ok().entity(veiculos).build();
		
		}else{
			return Response.status(401).entity("Campos não preenchidos completamento ou corretamente!").build();
		}
		
	}
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Veiculos veiculos){
		if(veiculos.getId() > 0 ){
			dao.excluir(veiculos);
			dao.commit();
			
			return Response.ok().build();
		}else{
			return Response.status(401).entity("Id não informado!").build();
		}
		
	}
	
	
	public Veiculos fill(Veiculos t){
		Date now = new Date();
		
		Veiculos bean = dao.getBean(t.getId());
		
		bean.setAno(t.getAno());
		bean.setCor(t.getCor());
		bean.setDataDeAtualizacao(now);
		bean.setDescricao(t.getDescricao());
		bean.setIsNew(t.isNew());
		bean.setMarca(t.getMarca());
		bean.setModelo(t.getModelo());
		bean.setPreco(t.getPreco());
		
		return bean;
	}
	public boolean validate(Veiculos veiculo){
		boolean test = true;
		
		if(veiculo.getAno() <= 0)
			test = false; 
		if(veiculo.getPreco() <= 0)
			test = false;
		if(veiculo.getMarca()==null || veiculo.getMarca().equals(""))
			test = false;
		if(	veiculo.getModelo()==null || veiculo.getModelo().equals(""))
			test = false;
		if(veiculo.getCor()==null|| veiculo.getCor().equals(""))
			test = false;
				
		return test;
	}
}
