package Questions;

import Response.Response;

public class OpenQuestion extends Question{

	Integer wordLimit;
	Integer DEFAULTLIMIT = 100;
	
	public OpenQuestion() { 
		this.wordLimit = DEFAULTLIMIT;
	}
	
	public OpenQuestion(Integer limit) {
		this.wordLimit = limit;
	}
	
	public void setWordLimit(Integer limit) {
		this.wordLimit = limit;
	}
	
	public Integer getWordLimit() {
		return this.wordLimit;
	}
	
	public Boolean isValid() {
		Response userResponse = (Response) super.userResponse.get(0);
		String[] words = userResponse.toString().split(" ");
		Integer numWords = words.length;
		if(numWords > this.wordLimit) {
			return false;
		}
		return true;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		getPrompt().display();
		for(Object response : super.userResponse) {
			((OpenQuestion) response).display();
		}
		//output.display(super.userResponse);
	}

	@Override
	public void take() {
		try {

            String userChoice = input.getInput();
            Response newResponse = new Response(userChoice);
            userResponse.add(newResponse);
            	
            if (!isValid()) {
            	int userResponselength= userResponse.size();
            	userResponse.remove(userResponselength-1);
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            output.display("Not a Valid Answer");
            take();
        }
		
	}
	
}
