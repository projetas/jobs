package br.com.projetas.carapp.carapp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "CAR")
public class Car {

//	Atributos do Veículo
//	Marca, texto, não nulo, 40 caracteres;
//	Modelo, texto, não nulo, 50 caracteres,
//	Cor, texto, não nulo, 30 caracteres;
//	Ano, inteiro positivo, não nulo;
//	Preço, decimal positivo, não nulo;
//	Descrição, texto;
//	É novo?, boleano, não nulo;
//	Data de cadastro, data e hora, não nulo;
//	Data de atualização, data e hora, nulo.
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Long id;
	
		private String marca;
		
		private String modelo;
		
		private String cor;
		
		private int ano;
		
		private BigDecimal preco;
		
		private String descricao;
		
		private boolean novo;
		
		@Column(nullable = false)
	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @NotNull(message = "Data Cadastro é uma informação obrigatória.")
		private Date dtCadastro;
		
		@Column(nullable = false)
	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @NotNull(message = "Data Atualizacao é uma informação obrigatória.")
		private Date dtAtualizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	
}
