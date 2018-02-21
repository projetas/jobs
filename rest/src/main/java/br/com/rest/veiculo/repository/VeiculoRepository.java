package br.com.rest.veiculo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.rest.veiculo.entity.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

}
