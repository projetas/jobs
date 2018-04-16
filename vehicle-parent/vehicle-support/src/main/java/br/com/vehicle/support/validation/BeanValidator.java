package br.com.vehicle.support.validation;

import static br.com.vehicle.support.exception.Message.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import br.com.vehicle.support.exception.Message;
import br.com.vehicle.support.exception.VehicleException;

@Component
public class BeanValidator
{
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public <T> boolean validate(T bean)
	{
		Set<ConstraintViolation<T>> violations = validator.validate(bean);

		if (violations == null || violations.isEmpty())
		{
			return true;
		}

		List<Message> messages = new ArrayList<>();
		violations.stream().forEach(v -> {
			String beanName = v.getRootBeanClass().getSimpleName();
			String constraint = v.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
			String field = v.getPropertyPath().toString();
			messages.add(builder(constraint + "." + beanName  + "." + field));
		});

		throw new VehicleException(messages.toArray(new Message[messages.size()]));
	}
}
