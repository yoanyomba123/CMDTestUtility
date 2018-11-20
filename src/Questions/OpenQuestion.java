package Questions;

import java.util.ArrayList;
import java.util.HashMap;

import Response.Response;

public class OpenQuestion extends Question{

	Integer wordLimit;
	Integer DEFAULTLIMIT = 100;
	HashMap<String,Integer> tabulateMap = new HashMap<>();

	public OpenQuestion() { 
		this.wordLimit = DEFAULTLIMIT;
	}
	
	public OpenQuestion(Integer limit) {
		this.wordLimit = limit;
	}
	
	public void setWordLimit(Integer limit) {
		this.wordLimit = limit;
	}
	
	public Integer getWordLimit() {
		return this.wordLimit;
	}
	
	public Boolean isValid() {
		Response userResponse = (Response) super.userResponse.get(0);
		String[] words = userResponse.toString().split(" ");
		Integer numWords = words.length;
		if(numWords > this.wordLimit) {
			return false;
		}
		return true;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		getPrompt().display();
		/*for(Object response : super.userResponse) {
			((Response) response).display();
		}*/
		//output.display(super.userResponse);
	}

	@Override
	public void take() {
		try {

            String userChoice = input.getInput();
            Response newResponse = new Response(userChoice);
            userResponse.add(newResponse);
            	
            if (!isValid()) {
            	int userResponselength= userResponse.size();
            	userResponse.remove(userResponselength-1);
                throw new IllegalStateException();
            }
            
            if(inHashMap(userChoice)) {
            	int value = tabulateMap.get(userChoice) + 1;
            	tabulateMap.put(userChoice, value);
            }
            else {
            	tabulateMap.put(userChoice, 1);
            }
            
        } catch (IllegalStateException e) {
            output.display("Not a Valid Answer");
            take();
        }
		
	}
	public boolean inHashMap(String value) {
		if(tabulateMap.keySet().contains(value)) {
			return true;
		}
		return false;
	}
	
	
	private void editPrompt() {
		output.display("Current Prompt: " + prompt.getPrompt());
		output.display("Please enter a new prompt to edit \n");
		String userInputPrompt = input.getInput();
		prompt.setPrompt(userInputPrompt);
		output.display("New Prompt: " + prompt.getPrompt() + " was sucessfully updated \n");
	}

	@Override
	public void edit() {
		menu.promptEditOne();
		String userInput = input.getInput();
		switch(userInput) {
			case "1":
				editPrompt();
				break;	
			default:
				break;
		}
	}
	
	@Override
	public int grade(Object correctAnswers) {
		return -1;
	}

	@Override
	public int grade(Response response) {
		// TODO Auto-generated method stub
		return -1;
	}


	
	
}
