package br.com.vehicle.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public abstract class AbstractModel
{
	@Id
	public UUID id;
}