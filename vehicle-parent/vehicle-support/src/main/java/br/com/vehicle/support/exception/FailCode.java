package br.com.vehicle.support.exception;

import br.com.vehicle.model.I18nMessage;

public enum FailCode implements I18nMessage
{
	MSG_REQUIRED("validation.value.required", "%s cannot be null", 400),
	MSG_INVALID("validation.value.invalid", "%s value is invalid", 400),
	MSG_RANGE_DATE_INVALID("validation.date.invalid", "Start date and end date are invalid", 400),
	MSG_GT_INVALID("validation.gt.invalid", "Number %s is greater than %s", 400),
	MSG_GE_INVALID("validation.ge.invalid", "Number %s is greater than or equals %s", 400),
	MSG_LT_INVALID("validation.lt.invalid", "Number %s is less than %s", 400),
	MSG_LE_INVALID("validation.le.invalid", "Number %s is less than or equals %s", 400),
	SYS_ERROR("Exception.unexpected", "An unexpected error occurred while processing your request", 500);

	private String code;
	private String defaultMsg;
	private Integer statusCode;

	private FailCode(final String codeInput, final String defaultMsgInput, final Integer statusCodeInput)
	{
		code = codeInput;
		defaultMsg = defaultMsgInput;
		statusCode = statusCodeInput;
	}

	@Override
	public String getCode()
	{
		return code;
	}

	@Override
	public Integer getStatusCode()
	{
		return statusCode;
	}

	@Override
	public String getDefaultMessage()
	{
		return defaultMsg;
	}
}
