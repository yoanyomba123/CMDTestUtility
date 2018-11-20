package Questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.ChoiceBoxSkin;

import IO.ConsoleInput;
import IO.ConsoleOutput;
import Prompt.Prompt;
import Response.Response;

public class ChoiceQuestion extends Question{
	boolean Multipleflag;
	Integer numOptions;
	ArrayList<QuestionOptions> questionOptions = new ArrayList();


	
	ArrayList<Response> correctAnswersForThis = new ArrayList();

	public ChoiceQuestion() {
		this.questionOptions = questionOptions;
		this.Multipleflag = false;
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
	
	public void setQuestionOptionMain(ArrayList<QuestionOptions> option) {
		this.questionOptions = option;
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
		String rankOrder = "";
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
				rankOrder = rankOrder + String.valueOf(rank) + "|";
				output.display(rankOrder + "THis is the order of ranking");
			}
			rankOrder = rankOrder.substring(0, rankOrder.length()-1);
			output.display("RANK ORDER IS" + rankOrder);
			Response newAnswer = new Response(rankOrder);
			correctAnswersForThis.add(newAnswer);
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
		this.Multipleflag = true;
		ArrayList<Response> correctAnswersFromAdmin = new ArrayList();
		Response answer;
		try {
			menu.promptCorrectAnswerMenu(getQuestionType());
			Integer numOfChoices = Integer.parseInt(input.getInput());
			if(numOfChoices < 1) {
				throw new IllegalArgumentException();
			}
			
			setNumberOfQuestionOptions(this.questionOptions.size());
			for(int i =0; i < numOfChoices; i++) {
				menu.promptEnterCorrectAnswerMenu(i, numOfChoices);
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
			if(this.getNumberOfQuestionOptions() == null) {
				menu.promptEnterCorrectAnswerMenu(1);
			}else {
				menu.promptEnterCorrectAnswerMenu(1,this.getNumberOfQuestionOptions());
			}
			
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
		output.display("Do you wish to edit the prompt (Please enter Yes or No)");
		try {
			String userInput = super.input.getInput();
			if(userInput.equals("Yes") || userInput.equals("yes") || userInput.equals("Y") || userInput.equals("y")) {
				editPrompt();
			}
			else if(userInput.equals("No") || userInput.equals("no") || userInput.equals("N") || userInput.equals("n")){
				output.display("Do you wish to edit the Choices? (Yes or No)");
				String userResponse = super.input.getInput();
				try {
					if(userResponse.equals("Yes") || userResponse.equals("yes") || userResponse.equals("Y") || userResponse.equals("y")) {
						editChoices();
					}
					else if(userResponse.equals("No") || userResponse.equals("no") || userResponse.equals("N") || userResponse.equals("n")){
						return;
					}
				}
				catch(NumberFormatException e){
					edit();
				}
				
			}
		}
		catch(NumberFormatException e){
			edit();
		
		}catch(IndexOutOfBoundsException e){
			output.display("Invalid Choice");
			edit();
		}
		
	}
	
	private void editPrompt() {
		output.display("Current Prompt: " + prompt.getPrompt());
		output.display("Please enter a new prompt to edit \n");
		String userInputPrompt = input.getInput();
		this.prompt.setPrompt(userInputPrompt);
		this.setPrompt(userInputPrompt);
		output.display("New Prompt: " + prompt.getPrompt() + " was sucessfully updated \n");
	}
	
	private void editChoices() {
		try {
			output.display("Which choice would you want to edit? ");
			for(int i =0; i < this.questionOptions.size(); i++) {
				output.displayOneLine(i+1 +  ")" );
				this.questionOptions.get(i).display();
			}
			int userChoice = Integer.valueOf(input.getInput());
			output.display("Please Enter The New Choice Option");
			Scanner entry = new Scanner(System.in);
			String newUserChoiceOption = entry.nextLine();
			
			QuestionOptions userSelectedOption = questionOptions.get(userChoice-1);
			
			userSelectedOption.setQuestionOptions(newUserChoiceOption);
			questionOptions.set(userChoice-1, userSelectedOption); 		
			setQuestionOptionMain(questionOptions);

			//int val = tabulateMap.get(userSelectedOption.getQuestionOptions().toString());
			//tabulateMap.remove(userSelectedOption.getQuestionOptions().toString());

			//tabulateMap.put(newUserChoiceOption, val);
			
		}catch(NumberFormatException e){
			output.display("A Number is Needed");
			edit();
		}catch(IndexOutOfBoundsException e){
			output.display("Invalid Choice");
			edit();
		}
		
	
	}

	public int grade(Object correctAnswers) {
		int numRight = 0;
		if(correctAnswers instanceof ArrayList) {
			for(int i =0; i < userResponse.size(); i++ ) {
				// output.display("Testing User Response | Correct Answer is instance of Arraylist");
				for(int j =0; j < ((ArrayList<QuestionOptions>) correctAnswers).size(); j++) {
					if(((Response) userResponse.get(i)).getData().equals(((ArrayList<Response>) correctAnswers).get(j).getData())) {
						numRight += 1;
						break;
					}
				}
			}
		}
		else if(correctAnswers instanceof Response) {
			// ((Response) userResponse.get(0)).display();
			if(((Response) correctAnswers).getData().equals(((Response) userResponse.get(0)).getData())) {
				 // output.printTwoColumns(((Response) correctAnswers).getData(), ((Response) userResponse.get(0)).getData());
				numRight +=1;
			}
		}
		else {
			output.display("WHAT IS GOING ON");
		}
		
		if(numRight != numRight) {
			return 0;
		}
		else {
			if(numRight > 1) {
				numRight = (numRight/numRight);
			}
		}
		return numRight;
		
	}

	/*
	@Override
	public int grade(Response response) {
		int numRight = 0;
		for(int i =0; i < super.getUserResponses().size(); i++ ) {
			output.display("Choice Question User Answer is ");
			((Response) super.getUserResponses().get(i)).display();
			if(super.getUserResponses().get(i).equals(response)) {
				output.display("WE GUCCI");
				numRight += 1;
			}
		}
		if(numRight != numRight) {
			return 0;
		}
		else {
			if(numRight > 1) {
				numRight = (numRight/numRight);
			}
		}
		return numRight;
	}
	*/
	
	public void tabulate() {
		for(String key: tabulateMap.keySet()) {
			output.display(key + ":" + tabulateMap.get(key));
		}
	}

	@Override
	public int grade(Response response) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<Response> getCorrectAnswersForThis() {
		return correctAnswersForThis;
	}

	public void setCorrectAnswersForThis(ArrayList<Response> correctAnswersForThis) {
		this.correctAnswersForThis = correctAnswersForThis;
	}



}

