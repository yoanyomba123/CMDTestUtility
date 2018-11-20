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
    String[] alphabeticOptions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

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
				Object questioCorrectAnswer = null;
				if(question instanceof ChoiceQuestion && !(question instanceof TrueFalse)) {
					if(question instanceof Ranking) {
						((Questions.ChoiceQuestion) question).getQuestionOptionsFromAdmin();
						((Questions.ChoiceQuestion) question).getQuestionRankFromAdmin();
						 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getCorrectAnswersForThis();
						 output.display("PRINTING THE CORRECT ANSWER FOR RANKING");
						 ((Response) ((ArrayList<Object>) questioCorrectAnswer).get(0)).display();
						 correctAnswer.add(((ArrayList<Object>) questioCorrectAnswer).get(0));
					}
					else if(question instanceof Matching) {
						((Questions.Matching) question).getQuestionOptionsFromAdmin();
					}
					else {
						((Questions.ChoiceQuestion) question).getQuestionOptionsFromAdmin();
					}
					
				}
				if(!(question instanceof OpenQuestion) && !(question instanceof Ranking)) {
					output.display("How Many Answers Approximately?");
					int vl = Integer.valueOf(input.getInput());
					if(!(question instanceof TrueFalse)) {
						if(vl > 1) {
							 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getMultipleCorrectAnswerFromAdmin();
						}
						else {
							 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getSingleCorrectAnswerFromAdmin();
						}
					}
					else {
						 questioCorrectAnswer = ((Questions.ChoiceQuestion) question).getSingleCorrectAnswerFromAdmin();
					}
					
					if(question instanceof Matching) {
						output.display("MATCHING LETS PRINT OUT THE CORRECT ANSWERS YOAN");
						String order = "";
						for(Object item: (ArrayList<Object>) questioCorrectAnswer) {
							order = order + ((Response) item).getData() + "|";
						}
						order = order.substring(0, order.length()-1);
						Response answer = new Response(order);
						output.display(order);
						correctAnswer.add(answer);
					}else{
						correctAnswer.add(questioCorrectAnswer);
					}

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
			if(!(question instanceof Matching)) {
				question.display();
			}
			if(question instanceof ChoiceQuestion) {
				if(question instanceof Ranking) {
					ArrayList<QuestionOptions> options = ((Ranking) question).getQuestionOptions();
					for(QuestionOptions option: options) {
						output.display(alphabeticOptions[counter] + ") " + option.getQuestionOptions());
						counter++;
					}
					counter = 0;
					for(QuestionOptions option: options) {
						output.display("Correct Answer of Rank " + option.getRank() + ") " + option.getQuestionOptions() + " Rank " + option.getRank());
						counter++;
					}
				}
				else if(question instanceof Matching) {
					((Matching) question).display();
					Response answers = (Response) correctAnswer.get(counter);
					answers.display();
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
	
	public void take() {
		Integer counter = 0;
		menu.promptEnterNameOfUser(getType());
		setUserName(input.getInput());
		
		output.display("\t" + getSurveyName());
		output.display("------------------------------------------------\n");
		
		for(Question question : questions) {
			output.display(question.getQuestionType() + ":");
			output.display(counter + ")");
			
			question.display();
			question.take();
		}
		
		String saveName = getSurveyName() + "_" + getUserName();
		
		save(this.folderName, this.surveyName);
		save("test_was_taken", saveName);
		
	}
	
	public void grade() {
		int grade = 0;
		int numCantBeGraded = 0;
		output.display("GRADING-------------------------------");
		for(int i = 0; i < questions.size(); i++) {
			/*
			if(correctAnswer.get(i) instanceof Response) {
				output.displayOneLine("GOING TO PRINT THE CORRECT ANSWER\n");
				output.display(questions.size() + " QUESTION SIZE");
				((Response) correctAnswer.get(i)).display();
			}
			
			output.display("CALLING QUESTION.GRADE()");
			*/
			Question question = questions.get(i);
			int val = question.grade(correctAnswer.get(i));
			if(val == -1) {
				numCantBeGraded += 1;
			}
			else {
				grade += val;
			}
		}
		
		output.displayOneLine(String.valueOf(grade * 10) + '/' +  String.valueOf((questions.size() - numCantBeGraded)*10));
	}
	
	public void tabulate() {
		super.tabulate();
	}
	
	
	
	
}
