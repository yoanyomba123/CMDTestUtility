package Questions;

import java.util.ArrayList;

import IO.ConsoleInput;
import Response.Response;

public class MultipleChoice extends ChoiceQuestion{

	Boolean takeMultipleResponses = false;
    String[] alphabeticOptions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	ConsoleInput input = new ConsoleInput();

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
        else {
            ArrayList<String> multipleChoices = new ArrayList<>();
            display();

            try {
                // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
                for (int i = 0; i < questionOptions.size(); i++) {
                    multipleChoices.add(alphabeticOptions[i]);
                }

                String userChoice = input.getInput().toUpperCase();
                Response newResponse = new Response(userChoice);

                if (!multipleChoices.contains(input))
                    throw new IllegalStateException();
                userResponse.add(newResponse);
            } catch (IllegalStateException e) {
                output.display("Not a Valid Answer");
                take();
            }
        }
    }
	
	protected void takeWithMultipleAnswers() {
        ArrayList<String> multipleChoices = new ArrayList<>();
        display();
        Integer numOfOptions = super.getNumberOfQuestionOptions();
        String mcOrder = "";
        output.display("Please give " + numOfOptions  + " choices: ");
        try {

        	 for (int i = 0; i < questionOptions.size(); i++) {
                 multipleChoices.add(alphabeticOptions[i]);
             }

            for (int i = 0; i < numOfOptions; i++) {
                output.display("Enter Answer #" + (i + 1) + ":");
                String userChoiceInput = input.getInput().toUpperCase();
                Response newResponse = new Response(userChoiceInput);
                
                if (!multipleChoices.contains(userChoiceInput))
                    throw new IllegalStateException();
                if (wasResponseChosenTwice(userChoiceInput))
                    throw new SetSameAnswerTwiceException();
                userResponse.add(newResponse);
            }
        } catch (SetSameAnswerTwiceException e) {
            output.display("Multiple choice can only be used once");
            takeWithMultipleAnswers();
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
}
