package Response;

import java.io.Serializable;

import IO.ConsoleOutput;

/*
 * Response class allows for comparison of user specified responses and
 * serves as a storage functionality
 * 
 * @Author Yoan Yomba
 * @version 1.0.0
 */

public class Response <T> implements Serializable{
	ConsoleOutput output = new ConsoleOutput();
	T response;
	
	/*
	 * Response constructor 
	 * 
	 * @param T user data serving as response
	 * @return None
	 */
	public Response(T userInputData) {
		this.response = userInputData;
	}
	
	/*
	 * Allows for displaying of response data
	 * 
	 * @param None
	 * @respone None
	 */
	public void display() {
		output.display(this.response);
	}
	
	/*
	 * Allows for setting of response data in response object
	 * 
	 * @param T response data to be set
	 * @return None
	 */
	public void setResponseData(T responseData) {
		this.response = responseData;
	}
	
	/*
	 * Getter method which allows response data to be captured 
	 */
	public T getResponseData() {
		return this.response;
	}
}
