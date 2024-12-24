package hint.even;

import hint.Hint;
import hint.HintType;
import model.Code;

import java.util.List;
import java.util.Objects;

public class NumberOfOddHint extends Hint {

    int numberOfOdd;

    public NumberOfOddHint(int numberOfOdd) {
        super(HintType.NUMBER_OF_ODD_HINT);
        this.numberOfOdd = numberOfOdd;
    }

    @Override
    public String showHintInFrench() {
        if (numberOfOdd == 0) {
            return "- Il n'y a aucun chiffre impair.";
        } else if (numberOfOdd == Code.CODE_LENGTH) {
            return "- Tous les chiffres sont impairs.";
        }
        else if(numberOfOdd == 1) {
            return "- Il y a un chiffre impair.";
        }
        return "- Il y a " + numberOfOdd + " chiffres impairs.";
    }

    @Override
    public String showHintInEnglish() {
        if (numberOfOdd == 0) {
            return "- There are no odd digits.";
        } else if (numberOfOdd == Code.CODE_LENGTH) {
            return "- All digits are odd.";
        } else if (numberOfOdd == 1) {
            return "- There is one odd digit.";
        }
        return "- There are " + numberOfOdd + " odd digits.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            int numberOfOddInCode = remainingCodes.get(i).getNumberOfOdd();
            if (numberOfOddInCode != numberOfOdd) {
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
        NumberOfOddHint that = (NumberOfOddHint) o;
        return numberOfOdd == that.numberOfOdd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfOdd);
    }
}
