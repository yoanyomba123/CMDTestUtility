package IO;
import java.io.Serializable;

/*
 * Input Abstract Class
 * purpose: defining an abstact class for processing consoling inputs in derived classes
 */
public abstract class Input<T> implements Serializable {
	/* 
	 * Defines an abstract method to process inputs
	 * @return T
	 */
	private static final long serialVersionUID = 1L;
	public abstract T getInput();
	
}
