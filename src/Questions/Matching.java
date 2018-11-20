package Questions;

import java.util.ArrayList;

import Response.Response;

public class Matching extends MultipleChoice{
	
	ArrayList<QuestionOptions> rightHandChoice;
	ArrayList<QuestionOptions> leftHandChoice;
	ArrayList<String> rightHandChoiceString;
	ArrayList<String> leftHandChoiceString;
	ArrayList<Response> matchingResponses;
    String[] alphabeticOptions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

	
	public Matching() {
		this.rightHandChoice = new ArrayList();
		this.leftHandChoice = new ArrayList();
		this.rightHandChoiceString = new ArrayList();
		this.leftHandChoiceString = new ArrayList();
		this.matchingResponses = new ArrayList();
	}
	
	
	public void display() {
		getPrompt().display();;
		for(int i =0; i < this.rightHandChoice.size(); i++) {
			super.output.displayOneLine(i+1 +")");
			super.output.printTwoColumns(this.leftHandChoice.get(i).getQuestionOptions(),alphabeticOptions[i] + ")" + this.rightHandChoice.get(i).getQuestionOptions());
			super.output.displayOneLine("\n");
		}
	}
	
	
	public void take() {
		String orderMatch = "";
		output.display("Select the choices that match ");
		int numOfOptions = this.rightHandChoice.size();
		int count = numOfOptions-1;
		Response userResponse;
		try {
			while(!(count < 0)) {
				QuestionOptions choice = this.rightHandChoice.get(count);
				output.displayOneLine("What matches " + alphabeticOptions[count] + ")");
				choice.display();
				output.displayOneLine("      Note: Acceptable values must range from 1 to " + String.valueOf(numOfOptions));
				output.display("");
				int userInput = input.getIntegerInput();
				
				
				if(userInput > numOfOptions) {
					throw new IllegalArgumentException();
				}
				
				if(this.userResponse.contains(input)) {
					throw new SetSameAnswerTwiceException();
				}
				
				userResponse = new Response(String.valueOf(userInput));
				orderMatch = orderMatch + String.valueOf(userInput) + alphabeticOptions[count] +  "|";
				this.matchingResponses.add(userResponse);
				count--;
			}
			orderMatch = orderMatch.substring(0, orderMatch.length()-1);
			Response userAnswer = new Response(orderMatch);
			super.userResponse.add(userAnswer);
			if(tabulateMap.containsKey(orderMatch)) {
				tabulateMap.put(orderMatch, tabulateMap.get(orderMatch)+1);
			}
			else {
				tabulateMap.put(orderMatch, 1);
			}
		}
		catch(IllegalArgumentException e){
            output.display("Not a valid option");
            take();
        }
        catch(SetSameAnswerTwiceException e){
            output.display("Cant Match Same option twice");
            take();
        }
		
		
	}
	
	@Override
	public void getQuestionOptionsFromAdmin() {
		try {
			menu.promptQuestionOptionMenu(getQuestionType());
			Integer numOfChoices = Integer.parseInt(super.input.getInput());
			menu.promptMatchingQuestionRightMenu(getQuestionType());
			if(numOfChoices < 2) {
				throw new IllegalArgumentException();
			}
			
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterChoiceMenu(i);
				String userDefinedOption = super.input.getInput();
				QuestionOptions option = new QuestionOptions(userDefinedOption);
				setRightHandQuestionOption(option);
				setRightHandQuestionStringOption(userDefinedOption);

				
			}
			
			menu.promptMatchingQuestionLeftMenu(getQuestionType());
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterChoiceMenuOrig(i+1);
				String userDefinedOption = super.input.getInput();
				QuestionOptions option = new QuestionOptions(userDefinedOption);
				setLeftHandQuestionOption(option);
				setLeftHandQuestionStringOption(userDefinedOption);
			}
		}
		catch(NumberFormatException e) {
			output.display("Enter a Number");
			getQuestionOptionsFromAdmin();
		}
		catch(IllegalArgumentException e) {
			output.display("Must Enter More Than One Choice");
			getQuestionOptionsFromAdmin();
		}
		catch(Exception e) {
			e.printStackTrace();
			getQuestionOptionsFromAdmin();
		}
			
	}


	private void setRightHandQuestionOption(QuestionOptions option) {
		// TODO Auto-generated method stub
		this.rightHandChoice.add(option);
	}
	
	private void setLeftHandQuestionOption(QuestionOptions option) {
		// TODO Auto-generated method stub
		this.leftHandChoice.add(option);
	}
	
	private void setRightHandQuestionStringOption(String option) {
		// TODO Auto-generated method stub
		this.rightHandChoiceString.add(option);
	}
	
	private void setLeftHandQuestionStringOption(String option) {
		// TODO Auto-generated method stub
		this.leftHandChoiceString.add(option);
	}
	
	public void edit() {
		super.edit();
	}

	
}
