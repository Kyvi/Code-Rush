package hint;

import model.Language;

public class PositionTranslator {

    public static String translatePosition(int position, Language language){
        if(position == 0) {
            return language == Language.FRENCH ? "1e" : "1st";
        }
        if(position == 1) {
            return language == Language.FRENCH ? "2e" : "2nd";
        }
        if(position == 2) {
            return language == Language.FRENCH ? "3e" : "3rd";
        }
        if(position == 3) {
            return language == Language.FRENCH ? "4e" : "4th";
        }
        if(position == 4) {
            return language == Language.FRENCH ? "5e" : "5th";
        }
        return "";
    }


}
