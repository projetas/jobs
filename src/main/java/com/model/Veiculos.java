package com.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "veiculos")
@SequenceGenerator(name="veiculo_seq", sequenceName="seq_veiculos", allocationSize = 1)
public class Veiculos {
	
	@Id
	@Column(name = "id_veiculos")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="veiculo_seq")
	private int id;
	
	@Column(name="ds_marca")
	private String marca;// , texto, não nulo, 40 caracteres;

	@Column(name="ds_modelo")
	private String modelo;// , texto, não nulo, 50 caracteres,

	@Column(name="ds_cor")
	private String cor;// , texto, não nulo, 30 caracteres;

	@Column(name="nu_ano")
	private int ano;// inteiro positivo, não nulo;

	@Column(name="nu_preco")
	private double preco; // decimal positivo, não nulo;

	@Column(name="ds_descricao")
	private String descricao; // texto;

	@Column(name="isnew")
	private boolean isNew;// É novo?, boleano, não nulo;

	@Column(name="dt_data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataDeCadastro;// data e hora, não nulo;

	@Column(name="dt_data_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataDeAtualizacao;// data e hora, nulo.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public Date getDataDeAtualizacao() {
		return dataDeAtualizacao;
	}
	public void setDataDeAtualizacao(Date dataDeAtualizacao) {
		this.dataDeAtualizacao = dataDeAtualizacao;
	}
	
	
}
