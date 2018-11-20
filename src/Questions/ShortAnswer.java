package Questions;

public class ShortAnswer extends OpenQuestion{

	public void tabulate() {
		for(String key: tabulateMap.keySet()) {
			output.display(key + ":" + tabulateMap.get(key));
		}
	}
}
