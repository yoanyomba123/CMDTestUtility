package IO;

/*
 * Console input is the concrete class aimed at providing methods and behaviors
 * to display various objects to the command line
 * <p>
 * @Author	Yoan Yomba
 * @version 1.0
 */
public class ConsoleOutput extends Output{
	
	/*
	 * Consumes an object to relegate to the user for consumption
	 * 
	 * @param Object the specified object to be displayed
	 * @return 		 none
	 */
	public void display(Object output) {
		String outputString = (String) output;
		printItem(outputString);
	}
	
	/*
	 * Consumes a collection of object to relegate to the user 
	 * 
	 * @param Object the specified list of objects to be displayed
	 * @return 		 none
	 */
	public void display(Object[] output) {
		String[] outputItems = (String[]) output;
		for(String item: outputItems) {
			printItem(item);
		}
	}
	
	/*
	 * Consumes an object to relegate to the user for consumption one line at a time
	 * 
	 * @param Object the specified object to be displayed
	 * @return 		 none
	 */
	public void displayOneLine(Object output) {
		String outputString = (String) output;
		printItemOneLine(outputString);
	}
	

	
	
	/*
	 * Prints a value out to the terminal
	 * 
	 * @param String object to print 
	 * @return 		 none
	 */
	public void printItem(Object valueToPrint) {
		System.out.println(valueToPrint);
	}
	
	/*
	 * Prints a value out to the terminal one line at a time
	 * 
	 * @param String object to print 
	 * @return 		 none
	 */
	public void printItemOneLine(Object valueToPrint) {
		System.out.print(valueToPrint);
	}
	
	/*
	 * Prints two columns out to the terminal
	 * 
	 * @param String item to print in column 1
	 * @param String item to print in column 2 
	 * @return 		 none
	 */
	public void printTwoColumns(String column1, String column2) {
		System.out.printf("%-15s %-15s\n", column1, column2);
	}
	
	/*
	 * Prints one column out to the terminal
	 * 
	 * @param String item to print in column 
	 * @return 		 none
	 */
	public void printOneColumn(String column) {
		System.out.printf("%-15s\n", column);
	}

	public void printTwoColumns(Object column1, Object column2) {
		String columnInit1 = (String) column1;
		String columnInit2 = (String) column2;
		printTwoColumns( columnInit1,  columnInit2);		
	}
}
