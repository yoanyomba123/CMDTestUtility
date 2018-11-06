package Questions;

public class TrueFalse extends MultipleChoice{
	
	String[] trueFalseOptions = {"T", "F"};
	
	public void display() {
		getPrompt().display();
		output.printItemOneLine(trueFalseOptions[0] + "/" + trueFalseOptions[1]);
		output.printItemOneLine("\n");
	}


}
