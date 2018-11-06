package TestSurvey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Questions.ChoiceQuestion;
import Questions.Essay;
import Questions.Matching;
import Questions.MultipleChoice;
import Questions.OpenQuestion;
import Questions.Question;
import Questions.QuestionOptions;
import Questions.Ranking;
import Questions.ShortAnswer;
import Questions.TrueFalse;
import Response.Response;
import Utilities.DirectoryFileList;

public class Test extends Survey{
	static String folderName = "test";
	ArrayList<Object> correctAnswer = new ArrayList<>();

	public Test() {
		this.folderName = folderName;
		this.type = "test";
		//menu.surveyPrompt();
		//setSurveyName(input.getInput());
	}
	
	public void create() {
		boolean isAddingNewQuestions = true;
		Question question = null;
		while(isAddingNewQuestions) {
			menu.questionOptionsMenu();
			try {
			String userChoice = input.getInput();
			
			Integer numericInputValue = Integer.parseInt(userChoice);
            if(numericInputValue > 7 || numericInputValue < 1) {
            	throw new IllegalStateException();
            }
			switch(userChoice) {
				case "1":
					question = new TrueFalse();
					question.setQuestionType("TrueFalse");
					break;
				case "2":
					question = new MultipleChoice();
					question.setQuestionType("MultipleChoice");
					break;
				case "3":
					question = new ShortAnswer();
					question.setQuestionType("ShortAnswer");
					break;
				case "4":
					question = new Essay();
					question.setQuestionType("Essay");
					break;
				case "5":
					question = new Ranking();
					question.setQuestionType("Ranking");
					break;
				case "6":
					question = new Matching();
					question.setQuestionType("Matching");
					break;
				case "7":
					question = null;
					isAddingNewQuestions = false;
					break;
				default:
					break;
			}
			
			if(question != null) {
				String questionType = question.getQuestionType();
				menu.promptQuestionMenu(questionType);
				String userDefinedPrompt = input.getInput();
				question.setPrompt(userDefinedPrompt);
				
				if(question instanceof ChoiceQuestion && !(question instanceof TrueFalse)) {
					if(question instanceof Ranking) {
						((Questions.ChoiceQuestion) question).getQuestionOptionsFromAdmin();
						((Questions.ChoiceQuestion) question).getQuestionRankFromAdmin();
					}
					else if(question instanceof Matching) {
						((Questions.Matching) question).getQuestionOptionsFromAdmin();
					}
					else {
						((Questions.ChoiceQuestion) question).getQuestionOptionsFromAdmin();
					}
					
				}
				if(!(question instanceof OpenQuestion) && !(question instanceof Ranking)) {
					Object questioCorrectAnswer = null;
					if(!(question instanceof TrueFalse)) {
						 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getMultipleCorrectAnswerFromAdmin();
					}
					else {
						 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getSingleCorrectAnswerFromAdmin();
					}
					correctAnswer.add(questioCorrectAnswer);

				}
				else {
					correctAnswer.add(null);
				}
				
				questions.add(question);
			}
		}catch (IllegalStateException e) {
            output.display("Not a Valid Answer");
            create();
        }catch (NumberFormatException e) {
            output.display("Not a Valid Answer");
            create();
        }
			
	}
	
}
	
	public static Test load(String folderName) {
		Test test = null;
		DirectoryFileList fileList = new DirectoryFileList(folderName);
		File[] listOfSurveys = fileList.listFiles();
		if(listOfSurveys.length == 0) {
			return test;
		}
		validChoice = false;
		while(validChoice == false) {
			try {
				String userChoice = input.getInput();
				File file = listOfSurveys[Integer.parseInt(userChoice)];
				test = (Test) loadFile(file);
				validChoice = true;
			} catch(NumberFormatException n) {
		    	  System.out.println("Please Enter A Number");
			}catch(ArrayIndexOutOfBoundsException e) {
		    	  System.out.println("Not a valid Survey To Upload");
		    }
		}
		return test;
	}
	
	public static Test loadFile(File file) {
		Test test = null;
		try {
	         FileInputStream fileInStream = new FileInputStream(file.getAbsolutePath());
	         ObjectInputStream inStream = new ObjectInputStream(fileInStream);
	         test = (Test) inStream.readObject();
	         inStream.close();
	         fileInStream.close();
	         validChoice = true;
	      } catch (IOException i) {
	         output.display("IOEXCEPTION ENCOUNTERED");
	      } catch (ClassNotFoundException c) {
	         System.out.println("Test Class Not Found");
	      } catch(NumberFormatException n) {
	    	  System.out.println("Please Enter A Number");
	      } catch(ArrayIndexOutOfBoundsException e) {
	    	  System.out.println("Not a valid Survey To Upload");
	      }
		return test;
	}
	
	
	
	public static String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		Test.folderName = folderName;
	}

	public void display() {
		Integer counter = 0;
		output.display("\t" + getSurveyName());
		output.display("------------------------------------------------\n");
		for(Question question : questions) {
			output.display(question.getQuestionType() + ":");
			output.displayOneLine(counter + ") ");
			question.display();
			if(question instanceof ChoiceQuestion) {
				if(question instanceof Ranking) {
					ArrayList<QuestionOptions> options = ((Ranking) question).getQuestionOptions();
					for(QuestionOptions option: options) {
						output.display(counter + ") " + option.getQuestionOptions());
						counter++;
					}
					counter = 0;
					for(QuestionOptions option: options) {
						output.display("Correct Answer: " + counter + ") " + option.getQuestionOptions() + " Rank " + option.getRank());
						counter++;
					}
				}
				else if(question instanceof Matching) {
					((Matching) question).display();
				}
				else {
					Object answers = correctAnswer.get(counter);
					if(answers instanceof ArrayList) {
						answers = (ArrayList) answers;
						for(Response answer: (ArrayList<Response>) answers) {
							output.displayOneLine("Correct Answer: ");
							answer.display();
						}
					}
					else {
						Response responseCorrectAnswer = (Response) correctAnswer.get(counter);
						if(responseCorrectAnswer.getResponseData() != null) {
							output.displayOneLine("Correct Answer: ");
							responseCorrectAnswer.display();
						}
					}
					counter++;
				}
			}			
		}
	}
	
	
}
