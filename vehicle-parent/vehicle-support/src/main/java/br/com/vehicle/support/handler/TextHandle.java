package br.com.vehicle.support.handler;

public final class TextHandle
{
	private TextHandle()
	{}

	/**
	 * Remove extra spaces, characters
	 * 
	 * @param input
	 * @return input without spaces, just letters and numbers
	 */
	public static String cleanText(String input)
	{
		return cleanText(input, false);
	}
	
	/**
	 * Remove extra spaces, characters
	 * 
	 * @param input
	 * @return input without spaces, just letters, numbers and it convert to upper case
	 */
	public static String cleanText(String input, boolean upperCase)
	{
		if (input == null || input.isEmpty())
		{
			return input;
		}
		
		input = input.replaceAll("[^a-zA-Z1-9 ]", "");
		input = input.replaceAll("\\s{2}", " ");
		return input.trim().toUpperCase();
	}
}
