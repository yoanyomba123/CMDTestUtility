package Questions;

import java.io.Serializable;

import IO.ConsoleOutput;

public class QuestionOptions<T> implements Serializable{
	private static final long serialVersionUID = 14L;
	T questionOptions;
	Object Data;
	ConsoleOutput output = new ConsoleOutput();
	Integer rank = null;
	
	public QuestionOptions(T option) {
		setQuestionOptions(option);
	}
	
	public void setQuestionOptions(T questionOptions) {
		this.Data = (String) questionOptions;
		this.questionOptions = questionOptions;
	}
	
	public T getQuestionOptions() {
		return this.questionOptions;
	}
	
	public void display() {
		output.display(this.questionOptions);
	}
	
	public void setOptionRank(Integer rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public Object getData() {
		return this.Data;
	}
	
}
