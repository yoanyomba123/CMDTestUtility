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
	private static final long serialVersionUID = 15L;
	ConsoleOutput output = new ConsoleOutput();

	T response;
	String data;
	
	/*
	 * Response constructor 
	 * 
	 * @param T user data serving as response
	 * @return None
	 */
	public Response(T userInputData) {
		this.data = (String) userInputData;
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
		this.data = String.valueOf(responseData);
		this.response = responseData;
	}
	
	/*
	 * Getter method which allows response data to be captured 
	 */
	public T getResponseData() {
		return this.response;
	}
	
	public String getData() {
		return this.data;
	}
	
	public boolean equals(Response response) {
		if(this.response.equals(response)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(String response) {
		if(this.data.equals(response)) {
			return true;
		}
		return false;
	}
}
