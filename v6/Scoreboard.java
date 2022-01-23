
import java.util.ArrayList;

public class Scoreboard{

	private ArrayList<Comparable[]> scoreboard;

	public Scoreboard(){
		scoreboard = new ArrayList<Comparable[]>();
	}

	public void add(String name, int newScore){
		Comparable[] arr = { name, newScore };
		scoreboard.add(arr);
	}

	public void sort(){
		for (int partition = 0; partition < scoreboard.size() - 1; partition++){
			for (int i = partition + 1; i > 0; i--){
				if ((scoreboard.get(i)[1]).compareTo(scoreboard.get(i-1)[1]) > 0) {
					Comparable[] temp = scoreboard.get(i - 1);
					scoreboard.set(i - 1, scoreboard.get(i));
					scoreboard.set(i, temp);
				} else 
					break;
			}
		}
	}
	

	public String topFive(){
		sort();
		String output = "    Name          Score\n" +
				"_____________________________\n"; 
		for (int i = 0; i < 5; i++){
			if (i > scoreboard.size() - 1){
				output += "|" + i + "|\t\t|\n";
			}
			else {
				int score = (Integer) scoreboard.get(i)[1];
				String name = (String) scoreboard.get(i)[0];
				String space = " ";
				output += "|" + i + "|  " + name + space.repeat(11 - name.length()) + "|  " + score + "\n";
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
