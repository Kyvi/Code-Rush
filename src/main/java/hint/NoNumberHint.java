package hint;

import model.Code;

import java.util.List;
import java.util.Objects;

public class NoNumberHint extends Hint{

    private int number;

    NoNumberHint(int number){
        super(HintType.NO_NUMBER_HINT);
        this.number = number;
    }


    @Override
    public String showHint(){
        return "- Le code ne contient pas de chiffre " + number + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            for (int j = 0; j < codeArray.length; j++) {
                if (codeArray[j] == number) {
                    remainingCodes.remove(i);
                    i--;
                    break;
                }
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NoNumberHint) {
            NoNumberHint noNumberHint = (NoNumberHint) obj;
            return noNumberHint.number == this.number;
        }
        return false;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hint instanceof NoNumberHint){
            NoNumberHint noNumberHint = (NoNumberHint) hint;
            return Integer.compare(number, noNumberHint.number);
        }
        return super.compareTo(hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
