package br.com.vehicle.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class FetchResponse<T> implements Serializable
{
	private static final long serialVersionUID = -9138839058205702581L;
	
	private List<T> results;
	private Integer page;
	private Integer totalElements;
	private Integer totalPages;
}
