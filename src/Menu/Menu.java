package Menu;
import java.io.Serializable;

import IO.ConsoleOutput;

/*
 * Menu Class
 * Purpose: Displays various forms of menus for the user
 * 
 * 
 */

public class Menu implements Serializable{
	private static final long serialVersionUID = 12L;
	ConsoleOutput output =  new ConsoleOutput();
	
	/*
	 * Define Constructor
	 */
	public Menu() {
		
	}
	
	public void surveyPrompt() {
		String surveyNamePrompt = "Please Enter Survey or Test Name\n";
		output.display(surveyNamePrompt);
	}
	
	
	public void mainMenu() {
		String[] mainMenuOptions = {"\nMenu 1:\n\n", "1) Survey", "2) Test"};
		output.display(mainMenuOptions);
	}
	
	public void surveyMenu() {
		String[] surveyMenuOptions = {"\nSurvey Menu 2:\n", "1) Create a new Survey", "2) Display a Survey", "3) Load a Survey", "4) Save a Survey", "5) Modify An Existing Survey", "6) Take A Survey", "7) Tabulate a Survey", "8) Quit"};
		output.display(surveyMenuOptions);
	}
	
	public void testMenu() {
		String[] testMenuOptions = {"\nTest Menu 2:\n", "1) Create a new Test", "2) Display a Test", "3) Load a Test", "4) Save a Test", "5) Modify An Existing Test", "6) Take A Test", "7) Tabulate a Test", "8) Grade a Test", "9) Quit"};
		output.display(testMenuOptions);
	}
	
	public void questionOptionsMenu() {
		String[] questionMenuOptions = {"\nMenu 3:\n", "1) Add a new T/F question", "2) Add a new multiple choice question", "3) Add a new short answer question", "4) Add a new essay question", "5) Add a new ranking question", "6) Add a new matching question", "7) Quit"};
		output.display(questionMenuOptions);
	}
	
	public void promptQuestionMenu(String questionType) {
		String[] promptMenuOptions = {"Enter the prompt or your " + questionType +  " question"};
		output.display(promptMenuOptions);
	}
	
	public void promptMatchingQuestionLeftMenu(String questionType) {
		String[] promptMenuOptions = {"Enter the Left Hand Options for  the " + questionType +  " question"};
		output.display(promptMenuOptions);
	}
	
	public void promptMatchingQuestionRightMenu(String questionType) {
		String[] promptMenuOptions = {"Enter the Right Hand Options for  the " + questionType +  " question"};
		output.display(promptMenuOptions);
	}
	
	public void promptQuestionOptionMenu(String questionType) {
		String[] promptMenuOptions = {"How many" + questionType +  " choices would you like?"};
		output.display(promptMenuOptions);
	}
	
	public void promptCorrectAnswerMenu(String questionType) {
		String[] promptCorrectAnswerMenu = {"Number of correct Answers for current" + questionType +  " question"};
		output.display(promptCorrectAnswerMenu);
	}
	
	public void promptEnterChoiceMenu(int choiceNum) {
		String[] promptEnterChoice = {"Enter choice #" + (choiceNum + 1) +  ":"};
		output.display(promptEnterChoice);
	}
	
	public void promptEnterCorrectAnswerMenu(int choiceNum) {
		String[] promptEnterCorrectAnswer = {"Enter Correct Answer #" + (choiceNum + 1) +  ":"};
		output.display(promptEnterCorrectAnswer);
	}
	
	public void promptEnterNameOfUser(String type) {
		String[] promptEnterNameOfUser = {"Name of User taking" + type +  ":"};
		output.display(promptEnterNameOfUser);

	}
	
	public void promptPleaseRankTheFollowing() {
		String[] promptRankTheFollowing = {"Please Rank the Following Items"};
		output.display(promptRankTheFollowing);
	}
	
	public void promptSpecifyItemsRank() {
		String[] promptRankTheFollowing = {"Specify The Rank Of The Following Items"};
		output.display(promptRankTheFollowing);
	}
	
	public void promptSpecifyItemRank() {
		String[] promptRankTheFollowing = {"Specify The Rank Of The Following Item"};
		output.display(promptRankTheFollowing);
	}
	
	/*
	 * Edit Test & Survey Prompts
	 */
	public void promptWhichQuestionToEdit() {
		String[] promptWhichQuestionToEdit = {"Specify The Question You Wish To Edit"};
		output.display(promptWhichQuestionToEdit);
	}
	public void promptEditOne() {
		String[] promptEditOne = {"Please Edit one of the Choices Present Below \n 1) Prompt\n 2) Choices"};
		output.display(promptEditOne);
	}
	
	//@Comeback here and edit the rest of this
	/*
	public void multipleChoiceQuestionMenu() {
		String[] multipleChoiceMenuOptions = {"Enter the prompt for you multiple-choice question"};
		output.display(trueFalseMenuOptions);
	}
	*/
	
}
