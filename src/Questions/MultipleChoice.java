package Questions;

import java.util.ArrayList;
import java.util.HashMap;

import IO.ConsoleInput;
import Response.Response;

public class MultipleChoice extends ChoiceQuestion{

	Boolean takeMultipleResponses = false;
    String[] alphabeticOptions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    static ConsoleInput input = new ConsoleInput();
    
	public MultipleChoice() {
		super();
		this.takeMultipleResponses = takeMultipleResponses;
	}
	
	public void setUserResponse(String response) {
		Response userResponse = new Response(response);
		Integer numCurrentResponses = super.getUserResponses().size();
		
		if(this.takeMultipleResponses) { 
			super.setUserResponse(userResponse);
		}
		else {
			if(numCurrentResponses <= 1) {
				super.setUserResponse(userResponse);
			}
		}
	}
	
	public void takeMultipleResponses() {
		this.takeMultipleResponses = true;
	}
	
	public void doNotTakeMultipleResponse() {
		this.takeMultipleResponses = false;
	}
	
	public void take() {
        if (this.takeMultipleResponses == true ) {
        	takeWithMultipleAnswers();
        }
        else if (Multipleflag == true) {
        	takeWithMultipleAnswers();
        }
        else {
            ArrayList<String> multipleChoices = new ArrayList<>();

            try {
                for (int i = 0; i < questionOptions.size(); i++) {
                    multipleChoices.add(alphabeticOptions[i]);
                    if(!(tabulateMap.containsKey(alphabeticOptions[i]))) {
                    	tabulateMap.put(alphabeticOptions[i], 0);
                    	
                    }
                }
                
                String userChoice = super.input.getInput().toUpperCase();
                Response newResponse = new Response(userChoice);
                
                
                if (!multipleChoices.contains(userChoice))
                    throw new IllegalStateException();
                userResponse.add(newResponse);
                tabulateMap.put(userChoice, tabulateMap.get(userChoice)+1);
                output.display(String.valueOf(tabulateMap.get(userChoice)));
            } catch (IllegalStateException e) {
            	
                output.display("Not a Valid Answer");
                take();
            }
        }
    }
	
	protected void takeWithMultipleAnswers() {
        ArrayList<String> multipleChoices = new ArrayList<>();
        Integer numOfOptions = super.getNumberOfQuestionOptions();
        String mcOrder = "";
        output.display("Please give " + numOfOptions  + " choices: ");
        try {

        	 for (int i = 0; i < questionOptions.size(); i++) {
                 multipleChoices.add(alphabeticOptions[i]);
                 if(!(tabulateMap.containsKey(alphabeticOptions[i]))) {
                 	tabulateMap.put(alphabeticOptions[i], 0);
                 }
             }

            for (int i = 0; i < numOfOptions; i++) {
                output.display("Enter Answer #" + (i + 1) + ":");
                String userChoiceInput = input.getInput().toUpperCase();
                Response newResponse = new Response(userChoiceInput);
                

                if (!multipleChoices.contains(userChoiceInput))
                    throw new IllegalStateException();
                userResponse.add(newResponse);
                tabulateMap.put(userChoiceInput, tabulateMap.get(userChoiceInput)+1);
            }
        } catch (IllegalStateException e) {
        	output.display("Not a Valid Answer");
        	takeWithMultipleAnswers();
        }
    }
	
	
	@Override
    public void display() {

        getPrompt().display();
     
        Integer counter = 0;
        for(QuestionOptions option: questionOptions) {
        	output.displayOneLine(alphabeticOptions[counter] + ") " + option.getQuestionOptions().toString());
        	output.displayOneLine(" ");
        	counter += 1;
        }
        output.displayOneLine("\n");
    }
	
	
	public Boolean wasResponseChosenTwice(String userChoice) {
		for(Object response: userResponse) {
			if(((Response) response).getResponseData().equals(userChoice)) {
				return true;
			}
		}
		return false;
	}
	
	public void edit() {
		super.edit();
	}
	

	
}