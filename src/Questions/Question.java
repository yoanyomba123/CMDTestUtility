package Questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	private static final long serialVersionUID = 55L;
	static ConsoleInput input = new ConsoleInput();
	static ConsoleOutput output = new ConsoleOutput();
	ArrayList<Object> correctAnswer = new ArrayList<>();


	private HashMap<Question,ArrayList<Object>> tabulate = new HashMap<>();
	Menu menu = new Menu();
	QuestionPrompt prompt;
	String questionType;
	ArrayList<Object> userResponse;
	HashMap<String,Integer>  tabulateMap;

	/*
	 * Defining Question Constructor
	 * @param none
	 * @return none
	 */
	public Question() {
		this.prompt = new QuestionPrompt();
		this.userResponse = new ArrayList();
		this.tabulateMap = new HashMap<>();
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
	
	public Boolean isInTabulate(Question question) {
		if(tabulate.containsKey(question)){
			return true;
		}
		return false;
	}
	
	public void addToTabulate(Question question, Object response) {
		if(isInTabulate(question)) {
			ArrayList<Object> value = tabulate.get(question);
			value.add(response);
			tabulate.put(question, value);
		}
		else{
			ArrayList<Object> value = new ArrayList();
			value.add(response);
			tabulate.put(question, value);
		}
	}
	public void displayTabulationResults() {
		for(Object item: getTabulate()) {
			output.display(item);
		}
	}
	
	public ArrayList<Object> getTabulate() {
		return tabulate.get(this);
	}

	public void setTabulate(HashMap<Question, ArrayList<Object>> tabulate) {
		this.tabulate = tabulate;
	}

	public abstract int grade(Object object);

	public abstract int grade(Response response);
	
	
}
