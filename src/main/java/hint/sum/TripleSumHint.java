package hint.sum;

import hint.Hint;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;

import java.util.List;
import java.util.Objects;

public class TripleSumHint extends SumHint{

    private int position1;
    private int position2;
    private int position3;

    public TripleSumHint(int position1, int position2, int position3, int sum){
        super(HintType.TRIPLE_SUM_HINT, sum);
        this.position1 = position1;
        this.position2 = position2;
        this.position3 = position3;
    }

    @Override
    public String showHint(){
        return "- La somme des chiffres en " + PositionTranslator.translatePosition(position1) + ", " + PositionTranslator.translatePosition(position2) + " et " + PositionTranslator.translatePosition(position3) + " positions est " + sum + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            int sum = 0;
            for (int j = 0; j < codeArray.length; j++) {
                if (j == position1 || j == position2 || j == position3) {
                    sum += codeArray[j];
                }
            }
            if (sum != this.sum) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TripleSumHint) {
            TripleSumHint tripleSumHint = (TripleSumHint) obj;
            return tripleSumHint.position1 == this.position1 && tripleSumHint.position2 == this.position2 && tripleSumHint.position3 == this.position3 && tripleSumHint.sum == this.sum;
        }
        return false;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hint instanceof TripleSumHint){
            TripleSumHint tripleSumHint = (TripleSumHint) hint;
            if(position1 == tripleSumHint.position1){
                if(position2 == tripleSumHint.position2){
                    if(position3 == tripleSumHint.position3){
                        return Integer.compare(sum, tripleSumHint.sum);
                    }
                    return Integer.compare(position3, tripleSumHint.position3);
                }
                return Integer.compare(position2, tripleSumHint.position2);
            }
            return Integer.compare(position1, tripleSumHint.position1);
        }
        return super.compareTo(hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position1, position2, position3, sum);
    }
}
