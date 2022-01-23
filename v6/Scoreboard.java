
import java.util.ArrayList;

public class Scoreboard{

	private ArrayList<Integer> scoreboard;

	public Scoreboard(){
		scoreboard = new ArrayList<Integer>(100);
	}

	public void add(int newScore){
		scoreboard.add(newScore);
	}

	public void sort(){
		for (int partition = 0; partition < scoreboard.size() - 1; partition++){
			for (int i = partition + 1; i > 0; i--){
				if ((scoreboard.get(i)).compareTo(scoreboard.get(i - 1)) > 0) {
					int temp = scoreboard.get(i - 1);
					scoreboard.set(i - 1, scoreboard.get(i));
					scoreboard.set(i, temp);
				} else 
					break;
			}
		}
	}

	public String topFive(){
		sort();
		String output = "Numero\t\tScore\n" +
				"_____________________________\n"; 
		for (int i = 0; i < 5; i++){
			if (i > scoreboard.size() - 1){
				output += "|" + i + "|\n";
			}
			else {
				output += "|" + i + "|\t\t" + scoreboard.get(i) + "\n";
			}
		}
		return output;
	}

	public String toString(){
		String scString = "[";
		for (int i = 0; i < scoreboard.size(); i++){
			scString += scoreboard.get(i);
		}
		return scString + "]";
	}

}
