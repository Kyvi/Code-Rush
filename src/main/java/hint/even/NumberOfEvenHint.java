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
    public String showHint() {
        return "- Il y a exactement " + numberOfEven + " chiffres pairs.";
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
