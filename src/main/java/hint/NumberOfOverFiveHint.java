package hint;

import model.Code;

import java.util.List;
import java.util.Objects;

public class NumberOfOverFiveHint extends Hint{

    int numberOfOverFive;

    public NumberOfOverFiveHint(int numberOfOverFive){
        super(HintType.NUMBER_OF_OVER_FIVE_HINT);
        this.numberOfOverFive = numberOfOverFive;
    }


    @Override
    public String showHintInFrench() {
        if (numberOfOverFive == 0){
            return "- Il n'y a aucun chiffre superieur a 5.";
        } else
        if (numberOfOverFive == 1){
            return "- Il y a exactement 1 chiffre superieur a 5.";
        }
        return "- Il y a exactement " + numberOfOverFive + " chiffres superieurs a 5.";
    }

    @Override
    public String showHintInEnglish() {
        if (numberOfOverFive == 0){
            return "- There are no digits over 5.";
        } else
        if (numberOfOverFive == 1){
            return "- There is exactly 1 digit over 5.";
        }
        return "- There are exactly " + numberOfOverFive + " digits over 5.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            int numberOfOverFiveInCode = remainingCodes.get(i).getNumberOver(5);
            if (numberOfOverFiveInCode != numberOfOverFive) {
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
        NumberOfOverFiveHint that = (NumberOfOverFiveHint) o;
        return numberOfOverFive == that.numberOfOverFive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfOverFive);
    }
}
