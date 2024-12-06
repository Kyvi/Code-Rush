package hint.sum;

import hint.HintType;
import model.Code;

import java.util.List;

public class AllOddSumHint extends SumHint{

    public AllOddSumHint(int sum) {
        super(HintType.ALL_ODD_SUM_HINT, sum);
    }

    @Override
    public String showHint() {
        return "- La somme de tous les chiffres impairs est " + sum + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int codeSum = getCodeOddSum(code);
            if (codeSum != this.sum) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    private int getCodeOddSum(Code code){
        int[] codeArray = code.getCode();
        int sum = 0;
        for (int j = 0; j < codeArray.length; j++) {
            if(codeArray[j] % 2 != 0){
                sum += codeArray[j];
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AllOddSumHint) {
            AllOddSumHint allSumHint = (AllOddSumHint) obj;
            return allSumHint.sum == this.sum;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sum;
    }
}
