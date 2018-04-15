package br.com.vehicle.support.exception;

import br.com.vehicle.model.I18nMessage;

public enum AppCode implements I18nMessage
{
	/* Domains */
	VEHICLE("domain.vehicle", "Vehicle"),
	VEHICLE_BRAND("domain.vehicle.brand", "Brand"),
	VEHICLE_COLOR("domain.vehicle.color", "Color"),

	/* General messages */
	MSG_INSERT_SUCCESS("app.insert.sucess", "%s was inserted"),
	MSG_UPDATE_SUCCESS("app.update.sucess", "%s was updated"),
	MSG_DELETE_SUCCESS("app.delete.sucess", "%s was deleted");

	private String code;
	private String defaultMsg;

	private AppCode(final String codeInput, final String defaultMsgInput)
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
