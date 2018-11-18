package Questions;

import java.util.ArrayList;

import com.sun.javafx.scene.control.skin.ChoiceBoxSkin;

import IO.ConsoleOutput;
import Response.Response;

public class ChoiceQuestion extends Question{

	Integer numOptions;
	ArrayList<QuestionOptions> questionOptions = new ArrayList();
	ConsoleOutput output = new ConsoleOutput();

	
	
	public ChoiceQuestion() {
		this.questionOptions = questionOptions;
	}
	
	public ChoiceQuestion(int numOptions) {
		this.questionOptions = questionOptions;
		this.numOptions = numOptions;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		output.display(super.getPrompt());
		output.display(super.userResponse);
	}
	

	
	public void setNumberOfQuestionOptions(int numOptions) {
		this.numOptions = numOptions;
	}
	
	
	public Integer getNumberOfQuestionOptions() {
		return this.numOptions;
	}
	
	public ArrayList<QuestionOptions> getQuestionOptions() {
		return this.questionOptions;
	}
	
	public void setQuestionOption(QuestionOptions option) {
		questionOptions.add(option);
	}
	
	public void getQuestionOptionsFromAdmin() {
		try {
			menu.promptQuestionOptionMenu(getQuestionType());
			Integer numOfChoices = Integer.parseInt(input.getInput());
			if(numOfChoices < 2) {
				throw new IllegalArgumentException();
			}
			
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterChoiceMenu(i);
				String userDefinedOption = input.getInput();
				QuestionOptions option = new QuestionOptions(userDefinedOption);
				setQuestionOption(option);
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
	
	public void getQuestionRankFromAdmin() {
		clearAllQuestionOptionsRanks();
		int sizeOfQuestionOptions = questionOptions.size();
		try {
			menu.promptSpecifyItemsRank();
			output.display("Please Make Sure To Enter A Rank Between The Ranges Of 1 and " + String.valueOf(sizeOfQuestionOptions));
			for(QuestionOptions option: questionOptions) {
				output.displayOneLine("Specify the Rank of the Following Option  :");
				option.display();
				int rank = Integer.parseInt(input.getInput());
				if(rank > sizeOfQuestionOptions) {
					throw new IllegalArgumentException();
				}
				else {
					option.setOptionRank(rank);
				}
			}
		}
		catch(NumberFormatException e) {
			output.display("Enter a Number");
			getQuestionRankFromAdmin();
		}
		catch(IllegalArgumentException e) {
			output.display("Please Enter A Rank Between The Ranges Of 1 and " + String.valueOf(sizeOfQuestionOptions));
			getQuestionRankFromAdmin();
		}
		catch(Exception e) {
			e.printStackTrace();
			getQuestionRankFromAdmin();
		}
	}
	
	public void clearAllQuestionOptionsRanks() {
		for(QuestionOptions option: questionOptions) {
			option.setOptionRank(null);
		}
	}
	
	public ArrayList<Response> getMultipleCorrectAnswerFromAdmin() {
		
		ArrayList<Response> correctAnswersFromAdmin = new ArrayList();
		Response answer;
		try {
			menu.promptCorrectAnswerMenu(getQuestionType());
			Integer numOfChoices = Integer.parseInt(input.getInput());
			if(numOfChoices < 1) {
				throw new IllegalArgumentException();
			}
			
			
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterCorrectAnswerMenu(i);
				String userDefinedOption = input.getInput();
				answer =  new Response(userDefinedOption);
				correctAnswersFromAdmin.add(answer);
			}
			return correctAnswersFromAdmin;
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
		return null;
	}
	
	
	public Response getSingleCorrectAnswerFromAdmin() {
		try {
			
			menu.promptEnterCorrectAnswerMenu(1);
			String userDefinedOption = input.getInput();
			return new Response(userDefinedOption);
			
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
		return null;
	}

	@Override
	public void take() {
		// TODO Auto-generated method stub
		
	}
	
	public void edit() {
		menu.promptEditOne();
		String userInput = input.getInput();
		switch(userInput) {
			case "1":
				editPrompt();
				break;
			case "2":
				editChoices();
				break;	
			default:
				break;
		}
	}
	
	private void editPrompt() {
		output.display("Current Prompt: " + prompt.getPrompt());
		output.display("Please enter a new prompt to edit \n");
		String userInputPrompt = input.getInput();
		prompt.setPrompt(userInputPrompt);
		output.display("New Prompt: " + prompt.getPrompt() + " was sucessfully updated \n");
	}
	
	private void editChoices() {
		output.display("Which choice would you want to edit? ");
		for(int i =0; i < this.questionOptions.size(); i++) {
			output.displayOneLine(i+1 +  ")" );
			this.questionOptions.get(i).display();
		}
		
		int userChoice =input.getIntegerInput();
		QuestionOptions userSelectedOption = this.questionOptions.get(userChoice-1);
		output.displayOneLine("Current Choice Optiont: ");
		userSelectedOption.display();
		
		output.display("Please Enter The New Choice Option");
		String newUserChoiceOption = input.getInput();
		QuestionOptions option = new QuestionOptions(newUserChoiceOption);
		this.questionOptions.set(userChoice-1, option);
	
	}
}
