package IO;

import java.util.Scanner;

public class ConsoleInput extends Input{
	
	transient Scanner scan = new Scanner(System.in);
	
	/*
	 * Define console input constructor
	 */
	public ConsoleInput() {
		
	}

	/*
	 * Process user input
	 * @param: none
	 * @return: String
	 */
	public String getInput() {
		return scan.nextLine();
	}
	
	/*
	 * Processes integer input from user
	 * @return Integer
	 */
	public Integer getIntegerInput() {
		String input = getInput();
		Integer integer = Integer.parseInt(input);
		return integer;
	}
	
}
