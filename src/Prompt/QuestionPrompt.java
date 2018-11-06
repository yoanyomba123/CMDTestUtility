package Prompt;

import IO.ConsoleOutput;
import IO.Output;

public class QuestionPrompt extends Prompt {
	ConsoleOutput output = new ConsoleOutput();
	String questionPrompt;
	
	/*
	 * Question Constructor sets the super classes prompt to the current prompt state
	 * 
	 * @param prompt values passed in as param to class constructor 
	 * @return None
	 */
	public QuestionPrompt(String prompt) {
		super.setPrompt(prompt);;
	}
	
	/*
	 * Question Constructor sets the super classes prompt to null value if nothing
	 * is passed as input
	 * 
	 * @param None
	 * @return None
	 */
	public QuestionPrompt() {
		super.setPrompt(null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see Prompt.Prompt#display()
	 */
	@Override
	public void display() {
		output.display(super.getPrompt());
	}
	
	

}
