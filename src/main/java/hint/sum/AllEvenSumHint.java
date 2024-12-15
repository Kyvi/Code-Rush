package hint.sum;

import hint.HintType;
import model.Code;

import java.util.List;

public class AllEvenSumHint extends SumHint{

    public AllEvenSumHint(int sum) {
        super(HintType.ALL_EVEN_SUM_HINT, sum);
    }


    @Override
    public String showHintInFrench() {
        return "- La somme des chiffres pairs est " + sum + ".";
    }

    @Override
    public String showHintInEnglish() {
        return "- The sum of all even digits is " + sum + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int codeSum = getCodeEvenSum(code);
            if (codeSum != this.sum) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    private int getCodeEvenSum(Code code){
        int[] codeArray = code.getCode();
        int sum = 0;
        for (int j = 0; j < codeArray.length; j++) {
            if(codeArray[j] % 2 == 0){
                sum += codeArray[j];
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AllEvenSumHint) {
            AllEvenSumHint allSumHint = (AllEvenSumHint) obj;
            return allSumHint.sum == this.sum;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sum;
    }
}
