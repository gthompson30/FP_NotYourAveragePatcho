import java.util.ArrayList;

public class Scoreboard{

	private ArrayList<Integer> scoreboard;

	public Scoreboard(){
		scoreboard = new ArrayList<Integer>(100);
	}

	public void add(int newScore){
		scoreboard.add(newScore);
	}

	public int hiScore(){
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
		return scoreboard.get(0);
	}

	public String toString(){
		String scString = "[";
		for (int i = 0; i < scoreboard.size(); i++){
			scString += scoreboard.get(i);
		}
		return scString + "]";
	}

}
