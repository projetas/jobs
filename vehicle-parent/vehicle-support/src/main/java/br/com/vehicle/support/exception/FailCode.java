package br.com.vehicle.support.exception;

import br.com.vehicle.model.I18nMessage;

public enum FailCode implements I18nMessage
{
	/* Input validation data */
	MSG_REQUIRED("validation.value.required", "%s cannot be null"),
	MSG_INVALID("validation.value.invalid", "%s value is invalid"),
	MSG_RANGE_DATE_INVALID("validation.date.invalid", "Start date and end date are invalid"),
	MSG_GT_INVALID("validation.gt.invalid", "Number %s is greater than %s"),
	MSG_GE_INVALID("validation.ge.invalid", "Number %s is greater than or equals %s"),
	MSG_LT_INVALID("validation.lt.invalid", "Number %s is less than %s"),
	MSG_LE_INVALID("validation.le.invalid", "Number %s is less than or equals %s"),

	/* Rules errors */
	MSG_NOT_FOUND("domain.not.found", "%s not found"),
	MSG_ALREADY_EXIST("domain.already.exist", "Unable to create. A %s with %s already exist"),

	/* Unexpected error */
	SYS_ERROR("Exception.unexpected", "An unexpected error occurred while processing your request");

	private String code;
	private String defaultMsg;

	private FailCode(final String codeInput, final String defaultMsgInput)
	{
		code = codeInput;
		defaultMsg = defaultMsgInput;
	}

	@Override
	public String getCode()
	{
		return code;
	}

	@Override
	public String getDefaultMessage()
	{
		return defaultMsg;
	}
}
