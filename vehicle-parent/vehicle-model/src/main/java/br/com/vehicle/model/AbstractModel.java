package br.com.vehicle.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class AbstractModel
{
	@Id
	public UUID id;

}