package Questions;

import java.util.ArrayList;
import java.util.HashMap;

import Response.Response;

public class TrueFalse extends MultipleChoice{

	String[] trueFalseOptions = {"T", "F"};
	
	public TrueFalse() {
    	for (int i = 0; i < 2; i++) {
        	tabulateMap.put(trueFalseOptions[i], 0);
        }
	}
	
	public void display() {
		getPrompt().display();
		output.printItemOneLine(trueFalseOptions[0] + "/" + trueFalseOptions[1]);
		output.printItemOneLine("\n");
	}
	public void edit() {
		super.edit();
	}
	
	@Override 
	public void take() {
            ArrayList<String> multipleChoices = new ArrayList<>();

            try {
                for (int i = 0; i < 2; i++) {
                    multipleChoices.add(trueFalseOptions[i]);
                }
                String userChoice = super.input.getInput().toUpperCase();
                Response newResponse = new Response(userChoice);

                if (!multipleChoices.contains(userChoice))
                    throw new IllegalStateException();
                userResponse.add(newResponse);
                tabulateMap.put(userChoice, tabulateMap.get(userChoice)+1);
            } catch (IllegalStateException e) {
                output.display("Not a Valid Answer");
                take();
            }
        }
	public void tabulate() {
		for(String key: tabulateMap.keySet()) {
			output.display(key + ":" + tabulateMap.get(key));
		}
	}
	
    


}
