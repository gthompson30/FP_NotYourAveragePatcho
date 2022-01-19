public class Text {

    // starts text at specific place
    public static void go(int row,int col){
        System.out.print("\u001b[" + row + ";" + col + "f");
    }
    
    // clears all text in terminal
    public static void clear(){
        System.out.print("\u001b[2J");
    }
}
