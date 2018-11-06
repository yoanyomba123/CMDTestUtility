package Questions;

import java.util.ArrayList;

import Response.Response;

public class Ranking extends MultipleChoice{
	ArrayList<Response> rankedResponses;
	
	public Ranking() {
		this.rankedResponses = new ArrayList();
	}
	public void display() {
		getPrompt().display();
		Integer counter = 0;
        for(QuestionOptions option: questionOptions) {
        	output.displayOneLine(alphabeticOptions[counter] + ") " + option.getQuestionOptions().toString());
        	output.displayOneLine("\n");
        	counter += 1;
        }
        output.displayOneLine("\n");
        //menu.promptPleaseRankTheFollowing();
	}
	
	
	
	public void take() {
		display();
		int numOfOptions = questionOptions.size();
		int count = 0;
		Response userResponse;
		while(count != numOfOptions) {
			output.display("Please enter your response for rank #" + String.valueOf(count+1));
			String userChoice = input.getInput();
			userResponse = new Response(userChoice);
			this.rankedResponses.add(userResponse);
			count++;
		}
		super.userResponse.add(this.rankedResponses);
	}
	
	
	
	
}
