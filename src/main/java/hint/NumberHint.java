package hint;

import model.Code;
import model.Language;

import java.util.List;
import java.util.Objects;

public class NumberHint extends Hint{

    private int position;
    private int number;

    NumberHint(int position, int number){
        super(HintType.NUMBER_HINT);
        this.position = position;
        this.number = number;
    }

    public int getPosition(){
        return position;
    }

    public int getNumber(){
        return number;
    }


    @Override
    public String showHintInFrench() {
        return "- Le chiffre en " + PositionTranslator.translatePosition(position, Language.FRENCH) + " position est " + number + ".";
    }

    @Override
    public String showHintInEnglish() {
        return "- The digit at " + PositionTranslator.translatePosition(position, Language.ENGLISH) + " position is " + number + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            if (codeArray[position] != number) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NumberHint) {
            NumberHint numberHint = (NumberHint) obj;
            return numberHint.position == this.position && numberHint.number == this.number;
        }
        return false;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hint instanceof NumberHint){
            NumberHint numberHint = (NumberHint) hint;
            if(position == numberHint.position){
                return Integer.compare(number, numberHint.number);
            }
            return Integer.compare(position, numberHint.position);
        }
        return super.compareTo(hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number);
    }
}
