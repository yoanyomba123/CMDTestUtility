package IO;

import java.io.Serializable;

/*
 * Class Output
 * Purpose - abstract class useful to output singular objects as well as collections of objects to User
 * 
 */
public abstract class Output<T> implements Serializable {
	
	/*
	 * Abstract method to display a singular object to user
	 * <p>
	 * This method does not return any object instead it prints
	 * values to the terminal for the user to consume
	 * 
	 * @param object an object to output to the user
	 * @return		 none
	 */
	public abstract void display(T output);
	
	/*
	 * Abstract method to display a collection of objects
	 * <p>
	 * This method does not return any object instead it processes
	 * the input collection passed into the method and displays
	 * the object to the terminal for the user to consume
	 * 
	 * @param object a collection of objects
	 * @return       none
	 */
	public abstract void display(T[] output);
	

}
