package Questions;

import java.io.Serializable;
import java.util.ArrayList;

import IO.ConsoleInput;
import IO.ConsoleOutput;
import Menu.Menu;
import Prompt.Prompt;
import Prompt.QuestionPrompt;
import Response.Response;

/*
 * Question is the abstract base class for all question contexts
 * which allow this specific application to create specialize question components
 * to use for testing an survey purposes
 * A Question object encapsulates the state information needed for various processing
 * operations especially in relation to test and survey taking
 * <p>
 * 
 * @author	Yoan Yomba
 * @version	1.0.0
 */
public abstract class Question implements Serializable{
	private static final long serialVersionUID = 13L;
	ConsoleInput input = new ConsoleInput();
	ConsoleOutput output = new ConsoleOutput();
	Menu menu = new Menu();
	QuestionPrompt prompt;
	String questionType;
	ArrayList<Object> userResponse;
	
	/*
	 * Defining Question Constructor
	 * @param none
	 * @return none
	 */
	public Question() {
		this.prompt = new QuestionPrompt();
		this.userResponse = new ArrayList();
	}
	
	public abstract void display();
	public abstract void take();
	public abstract void edit();

	

	
	public void getPromptFromAdmin(ConsoleInput input) {
		String userDefinedPrompt = input.getInput();
		setPrompt(userDefinedPrompt);
	}
	
	
	public void setPrompt(String prompt) {
		this.prompt.setPrompt(prompt);
	}
	
	public Prompt getPrompt() {
		return this.prompt;
	}
	
	public ArrayList<Object> getUserResponses(){
		return this.userResponse;
	}
	
	public void setUserResponse(Response response){
		this.userResponse.add(response);
	}
	
	public void setQuestionType(String type) {
		this.questionType = type;
	}
	
	public String getQuestionType() {
		return this.questionType;
	}
	
	
}
