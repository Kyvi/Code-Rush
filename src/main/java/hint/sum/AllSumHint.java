package hint.sum;

import hint.HintType;
import model.Code;

import java.util.List;

public class AllSumHint extends SumHint{

    public AllSumHint(int sum){
        super(HintType.ALL_SUM_HINT, sum);
    }


    @Override
    public String showHintInFrench() {
        return "- La somme des chiffres est " + sum + ".";
    }

    @Override
    public String showHintInEnglish() {
        return "- The sum of all digits is " + sum + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            int sum = 0;
            for (int j = 0; j < codeArray.length; j++) {
                sum += codeArray[j];
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
        if (obj instanceof AllSumHint) {
            AllSumHint allSumHint = (AllSumHint) obj;
            return allSumHint.sum == this.sum;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sum;
    }

}
