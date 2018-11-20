package Questions;

public class Essay extends OpenQuestion{
	public void tabulate() {
		for(String key: tabulateMap.keySet()) {
			output.display(key);
		}
	}
}
