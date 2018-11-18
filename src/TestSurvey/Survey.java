package TestSurvey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import IO.ConsoleInput;
import IO.ConsoleOutput;
import Menu.Menu;
import Prompt.Prompt;
import Prompt.QuestionPrompt;
import Questions.ChoiceQuestion;
import Questions.Essay;
import Questions.Matching;
import Questions.MultipleChoice;
import Questions.Question;
import Questions.QuestionOptions;
import Questions.Ranking;
import Questions.ShortAnswer;
import Questions.TrueFalse;
import Utilities.DirectoryFileList;


/*
 * Survey class obtains behaviors and methods useful to take surveys, save surveys,
 * as well as other functionalities
 * 
 * @author Yoan Yomba
 * @version 1.0.0
 */
public class Survey implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
	static ConsoleInput input = new ConsoleInput();
	static ConsoleOutput output = new ConsoleOutput();
	String surveyName = null;
	Menu menu = new Menu();
	static String folderName = "survey";
	String type = null;
	String userName = null;
	static Boolean validChoice = false;
	Question question;
	ArrayList<Question> questions = new ArrayList<>();
	
	public Survey() {
		this.folderName = folderName;
		this.type = "survey";
		menu.surveyPrompt();
		setSurveyName(input.getInput());
	}
	
	public static Survey load() {
		Survey survey = null;
		DirectoryFileList fileList = new DirectoryFileList(folderName);
		File[] listOfSurveys = fileList.listFiles();
		validChoice = false;
		while(validChoice == false) {
			try {
				String userChoice = input.getInput();
				File file = listOfSurveys[Integer.parseInt(userChoice)];
				survey = loadFile(file);
				validChoice = true;
			} catch(NumberFormatException n) {
		    	  System.out.println("Please Enter A Number");
		    	  return null;
		      } catch(ArrayIndexOutOfBoundsException e) {
		    	  System.out.println("Not a valid Survey To Upload");
		    	  return null;
		      }
		}
		
		return survey;
	}
	
	public void save(String folderName, String saveName) {
		try {
			 String pathName = folderName + "/"  + saveName;
			 output.display(pathName);
			 File newFile = new File(pathName);
			 File directory = new File(folderName);
			 
			 if(!directory.exists()) {
				 directory.mkdirs();
			 }
			 
			 if (!newFile.exists()) {
				 newFile.createNewFile();
			  }
			 // Creating output file stream (Really creating an output file) - Stream to write data to a file
	         FileOutputStream fileOutputStream = new FileOutputStream(newFile);
	         // Create object output stream aimed at placing the object at the file output location
	         ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
	         // write the objects
	         outputStream.writeObject(this);
	         // close the output stream
	         outputStream.close();
	         fileOutputStream.close();
	         output.display(getSurveyName() + "survey saved");  
		}catch (IOException i) {
			i.printStackTrace();
	         output.display("IOEXCEPTION ENCOUNTERED");
	      } 
	}
	
	public static Survey loadFile(File file) {
		Survey survey = null;
		try {
	         FileInputStream fileInStream = new FileInputStream(file.getAbsolutePath());
	         ObjectInputStream inStream = new ObjectInputStream(fileInStream);
	         survey = (Survey) inStream.readObject();
	         inStream.close();
	         fileInStream.close();
	         validChoice = true;
	      } catch (IOException i) {
	         output.display("IOEXCEPTION ENCOUNTERED");
	         output.display(i.getMessage());
	      } catch (ClassNotFoundException c) {
	         System.out.println("Survey Class Not Found");
	      } catch(NumberFormatException n) {
	    	  System.out.println("Please Enter A Number");
	      } catch(ArrayIndexOutOfBoundsException e) {
	    	  System.out.println("Not a valid Survey To Upload");
	      }
		return survey;
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
					}else if(question instanceof Matching) {
						((Questions.Matching) question).getQuestionOptionsFromAdmin();
						output.display("MATCHING BITCH");
					}
					else {
						((Questions.ChoiceQuestion) question).getQuestionOptionsFromAdmin();
						output.display("AWWWWW MATCHING BITCH");

					}
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
	
	public void display() {
		Integer counter = 0;
		output.display("\t" + getSurveyName());
		output.display("------------------------------------------------\n");
		for(Question question : questions) {
			output.display(question.getQuestionType() + ":");
			output.displayOneLine(counter + ") ");
			question.display();
			output.displayOneLine("\n");

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
		save("survey_was_taken", saveName);
		
	}
	
	public void edit() {
		if(questions.size() == 0) {
			output.display("There Are Currently No Present Questions To Edit \n");
		}
		else {
			menu.promptWhichQuestionToEdit();
			for(int i = 0; i < questions.size(); i++) {
				output.displayOneLine(Integer.toString(i+1) + ")" );
				questions.get(i).getPrompt().display();;
			}
			int userInput = input.getIntegerInput();
			output.display("CALLING QUESTIONS.EDIT() ");
			questions.get(userInput-1).edit();
		}
		
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public static String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getValidChoice() {
		return validChoice;
	}

	public void setValidChoice(Boolean validChoice) {
		this.validChoice = validChoice;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	
	public String getSurveyName(){
		return this.surveyName;
	}
}
