package hint;

import model.Code;

import java.util.List;
import java.util.Objects;

public class MaxDiffHint extends Hint{

    private int maxDiff;

    public MaxDiffHint(int maxDiff){
        super(HintType.MAX_DIFF_HINT);
        this.maxDiff = maxDiff;
    }
    @Override
    public String showHintInFrench() {
        return "- La différence entre le chiffre le plus grand et le plus petit est de " + maxDiff + ".";

    }
    @Override
    public String showHintInEnglish() {
        return "- The difference between the largest and smallest digit is " + maxDiff + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            int max = codeArray[0];
            int min = codeArray[0];
            for (int j = 1; j < codeArray.length; j++) {
                if (codeArray[j] > max) {
                    max = codeArray[j];
                }
                if (codeArray[j] < min) {
                    min = codeArray[j];
                }
            }
            if (max - min != maxDiff) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxDiffHint that = (MaxDiffHint) o;
        return maxDiff == that.maxDiff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxDiff);
    }
}
