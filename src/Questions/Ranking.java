package Questions;

import java.util.ArrayList;

import IO.ConsoleInput;
import Response.Response;

public class Ranking extends MultipleChoice{
	ArrayList<Response> rankedResponses;
	static ConsoleInput input = new ConsoleInput();
	
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
		String orderRank = "";
		int numOfOptions = questionOptions.size();
		int count = 0;
		Response userResponse;
		while(count != numOfOptions) {
			output.display("Please enter your response for rank " +alphabeticOptions[count] + ")" + "  Note. Rank must be between 1 and " + String.valueOf(numOfOptions));
			String userChoice = input.getInput();
			orderRank = orderRank + userChoice + "|";
			userResponse = new Response(userChoice);
			this.rankedResponses.add(userResponse);
			count++;
		}
		orderRank = orderRank.substring(0, orderRank.length()-1);
		Response newAnswer = new Response(orderRank);
		super.userResponse.add(newAnswer);
		if(tabulateMap.containsKey(orderRank)) {
			tabulateMap.put(orderRank, tabulateMap.get(orderRank)+1);
		}
		else {
			tabulateMap.put(orderRank, 1);
		}
	}
	public void edit() {
		super.edit();
	}
	
	
	public void tabulate() {
		for(String key: tabulateMap.keySet()) {
			output.display(key + ":" + tabulateMap.get(key));
		}
	}
	
	
	
}
