
import IO.ConsoleInput;
import IO.ConsoleOutput;
import Menu.Menu;
import TestSurvey.Survey;
import TestSurvey.Test;

public class main {
	
	static ConsoleOutput output = new ConsoleOutput();
	static ConsoleInput input = new ConsoleInput();
	static Menu menu = new Menu();
	
	public static void main(String[] args) {
		startApplication();
	}
	
	public static void startApplication() {
		menu.mainMenu();
		String userSelection = input.getInput();
		switch(userSelection) {
			case "1":
				surveyMenu();
				break;
			case "2":
				testMenu();
				break;
			default:
				startApplication();
		}
	}
	
	
    private static void surveyMenu() {

        Survey survey = null;

        while (true) {
            try {
        	menu.surveyMenu();
            String userChoice = input.getInput();
            Integer numericInputValue = Integer.parseInt(userChoice);
            if(numericInputValue > 6 || numericInputValue < 1) {
            	throw new IllegalStateException();
            }
            switch (userChoice) {
                case "1":
                    survey = new Survey();
                    survey.create();
                    break;
                case "2":
                    survey.display();
                    break;
                case "3":
                    output.display("Which survey would you like to load?");
                    survey = Survey.load();
                    break;
                case "4":
                    if(survey != null) {
                        survey.save(survey.getFolderName() , survey.getSurveyName());
                    }
                    else
                        output.display("No Survey To Save");
                    break;
                case "5":
                    output.display("Which survey would you like to edit?");
                    survey = Survey.load();
                    survey.edit();
                    break;

                case "6":
                	output.display("Which survey would you like to take?");
                    survey = Survey.load();
                    survey.take();
                    break;
                /*
                case "7":
                    output.display("Which survey would you like to tabulate?");
                    survey = Survey.load();
                    if (survey != null)
                        survey.tabulate();
                    break;
                */
                case "8":
                    quit();
break;
                default:
                    break;
             }
        }catch (IllegalStateException e) {
            output.display("Not a Valid Answer");
            surveyMenu();
        }catch (NumberFormatException e) {
            output.display("Not a Valid Answer");
            surveyMenu();
        }catch (NullPointerException e) {
            output.display("Please Create Or Load A Survey First");
            surveyMenu();
        }
            
            
       }
    }
    
    private static void testMenu() {

        Test test = null;

        while (true) {
            try {
	        	menu.testMenu();
	            String userChoice = input.getInput();
	            Integer numericInputValue = Integer.parseInt(userChoice);
	            if(numericInputValue > 6 || numericInputValue < 1) {
	            	throw new IllegalStateException();
	            }
	            switch (userChoice) {
	                case "1":
	                	test = new Test();
	                    test.create();
	                    break;
	                case "2":
	                	test.display();
	                    break;
	                case "3":
	                    output.display("Which test would you like to load?");
	                    String folderName = test.getFolderName();
	                    Test testTemp = Test.load(folderName);
	                    //output.display(test);
	                    if(testTemp == null) {
	                    	menu.testMenu();
	                    }
	                    else {
	                    	test = testTemp;
	                    }
	                    break;
	                case "4":
	                    if(test != null) {
	                    	test.save(test.getFolderName() , test.getSurveyName());
	                    }
	                    else
	                        output.display("No test To Save");
	                    break;
	                case "5":
	                    output.display("Which test would you like to modify?");
	                    test = Test.load(Test.getFolderName());
	                    test.edit();
	                    break;

	                case "6":
	                    output.display("Which test would you like to take?");
	                    test = Test.load(Test.getFolderName());
	                    test.take();
	                    break;
	                /*
	                case "7":
	                    output.display("Which test would you like to tabulate?");
	                    test = Test.load(Test.getFolderName());
	                    if (test != null)
	                        test.tabulate();
	                    break;
	                case "8":
	                    Test temp = null;
	                    output.display("Which test would you like to grade?");
	                    temp = Test.load("test_taken");
	                    temp.grade();
	                    break;
	                */
	                case "9":
	                    quit();
	                    	break;
	                default:
	                    break;
	             }
	        }catch (IllegalStateException e) {
	            output.display("Not a Valid Answer");
	            testMenu();
	        }catch (NumberFormatException e) {
	            output.display("Not a Valid Answer");
	            testMenu();
	        }catch (NullPointerException e) {
	            output.display("Please Create Or Load A Survey First");
	            testMenu();
	        }
            
            
            
       }
    }
    
    
    private static void quit() {
    	output.display("\t\t Program Terminating ");
    	output.display("---------------------------------------------------");
        System.exit(0);
    }
	
	
	
}
