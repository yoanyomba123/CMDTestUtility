package Prompt;

import java.io.Serializable;

/*
 * Prompt class serves as the template class that stores various
 * prompts to be utilized in question objects.
 * Prompt objects have the ability to display themselves as well
 * as change themselves
 * <p>
 * @Author Yoan Yomba
 * @version 1.0.0
 */
public abstract class Prompt<T> implements Serializable{
	
	private T prompt;
	
	/*
	 * Allows a prompt to be displayed to users for consumption
	 */
	public abstract void display();
	
	/*
	 * Setter method useful to set data in prompt object
	 * 
	 * @param T prompt object
	 * @return 	none
	 */
	public void setPrompt(T prompt) {
		this.prompt = prompt;
	}
	
	/*
	 * Getter method useful to obtain prompt data from prompt object
	 * 
	 * @param None
	 * @return  Prompt data object
	 */
	public T getPrompt() {
		return this.prompt;
	}
}
