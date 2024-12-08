package hint.even;

import hint.Hint;
import hint.HintType;
import model.Code;

import java.util.List;
import java.util.Objects;

public class NumberOfEvenHint extends Hint {

    int numberOfEven;

    public NumberOfEvenHint(int numberOfEven) {
        super(HintType.NUMBER_OF_EVEN_HINT);
        this.numberOfEven = numberOfEven;
    }


    @Override
    public String showHintInFrench() {
        if (numberOfEven == 0) {
            return "- Il n'y a aucun chiffre pair.";
        } else if (numberOfEven == Code.CODE_LENGTH) {
            return "- Tous les chiffres sont pairs.";
        } else if (numberOfEven == 1) {
            return "- Il y a exactement un chiffre pair.";
        }
        return "- Il y a exactement " + numberOfEven + " chiffres pairs.";
    }

    @Override
    public String showHintInEnglish() {
        if (numberOfEven == 0) {
            return "- There are no even digits.";
        } else if (numberOfEven == Code.CODE_LENGTH) {
            return "- All digits are even.";
        } else if (numberOfEven == 1) {
            return "- There is exactly one even digit.";
        }
        return "- There are exactly " + numberOfEven + " even digits.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            int numberOfEvenInCode = remainingCodes.get(i).getNumberOfEven();
            if (numberOfEvenInCode != numberOfEven) {
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
        NumberOfEvenHint that = (NumberOfEvenHint) o;
        return numberOfEven == that.numberOfEven;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfEven);
    }
}
