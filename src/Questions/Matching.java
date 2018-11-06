package Questions;

import java.util.ArrayList;

public class Matching extends MultipleChoice{
	
	ArrayList<QuestionOptions> rightHandChoice = new ArrayList();
	ArrayList<QuestionOptions> leftHandChoice = new ArrayList();

	
	public Matching() {
		this.rightHandChoice = rightHandChoice;
		this.leftHandChoice = leftHandChoice;
	}
	
	
	public void display() {
		getPrompt().display();;
		for(int i =0; i < this.rightHandChoice.size(); i++) {
			output.displayOneLine(this.leftHandChoice.get(i).getQuestionOptions() + "   |   " + this.rightHandChoice.get(i).getQuestionOptions());
			output.displayOneLine("\n");
		}
	}
	
	
	public void take() {
		
	}
	
	@Override
	public void getQuestionOptionsFromAdmin() {
		try {
			menu.promptQuestionOptionMenu(getQuestionType());
			Integer numOfChoices = Integer.parseInt(input.getInput());
			menu.promptMatchingQuestionRightMenu(getQuestionType());
			if(numOfChoices < 2) {
				throw new IllegalArgumentException();
			}
			
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterChoiceMenu(i);
				String userDefinedOption = input.getInput();
				QuestionOptions option = new QuestionOptions(userDefinedOption);
				setRightHandQuestionOption(option);
			}
			
			menu.promptMatchingQuestionLeftMenu(getQuestionType());
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterChoiceMenu(i);
				String userDefinedOption = input.getInput();
				QuestionOptions option = new QuestionOptions(userDefinedOption);
				setLeftHandQuestionOption(option);
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
	
	
	
}
