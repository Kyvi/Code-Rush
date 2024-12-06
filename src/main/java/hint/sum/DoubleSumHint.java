package hint.sum;

import hint.Hint;
import hint.HintType;
import model.Code;

import java.util.List;
import java.util.Objects;

import static hint.PositionTranslator.translatePosition;

public class DoubleSumHint extends SumHint{

    private int position1;
    private int position2;

    public DoubleSumHint(int position1, int position2, int sum){
        super(HintType.DOUBLE_SUM_HINT, sum);
        this.position1 = position1;
        this.position2 = position2;
    }

    @Override
    public String showHint(){
        return "- La somme des chiffres en " + translatePosition(position1) + " et " + translatePosition(position2) + " position est " + sum + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            int sum = 0;
            for (int j = 0; j < codeArray.length; j++) {
                if (j == position1 || j == position2) {
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
        if (obj instanceof DoubleSumHint) {
            DoubleSumHint doubleSumHint = (DoubleSumHint) obj;
            return doubleSumHint.position1 == this.position1 && doubleSumHint.position2 == this.position2 && doubleSumHint.sum == this.sum;
        }
        return false;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hint instanceof DoubleSumHint){
            DoubleSumHint doubleSumHint = (DoubleSumHint) hint;
            if(position1 == doubleSumHint.position1){
                if(position2 == doubleSumHint.position2){
                    return Integer.compare(sum, doubleSumHint.sum);
                }
                return Integer.compare(position2, doubleSumHint.position2);
            }
            return Integer.compare(position1, doubleSumHint.position1);
        }
        return super.compareTo(hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position1, position2, sum);
    }
}
