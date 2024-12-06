package hint;

public class PositionTranslator {

    public static String translatePosition(int position){
        if(position == 0) {
            return "1e";
        }
        if(position == 1) {
            return "2e";
        }
        if(position == 2) {
            return "3e";
        }
        if(position == 3) {
            return "4e";
        }
        if(position == 4) {
            return "5e";
        }
        return "";
    }


}
