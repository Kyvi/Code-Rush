package hint;

import model.Code;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NumberOfOverFourHint extends Hint{

    int numberOfOverFour;

    public NumberOfOverFourHint(int numberOfOverFive){
        super(HintType.NUMBER_OF_OVER_FOUR_HINT);
        this.numberOfOverFour = numberOfOverFive;
    }

    @Override
    public void buildHintStructure() {
        HintStructure hintStructure = new HintStructure();
        hintStructure.setHintType(this.getHintType().name());
        hintStructure.setResult(numberOfOverFour);
        hintStructures.add(hintStructure);
    }


    @Override
    public String showHintInFrench() {
        if (numberOfOverFour == 0){
            return "- Il n'y a aucun chiffre supérieur a 4.";
        } else
        if (numberOfOverFour == 1){
            return "- Il y a 1 chiffre supérieur a 4.";
        }
        return "- Il y a " + numberOfOverFour + " chiffres supérieurs a 4.";
    }

    @Override
    public String showHintInEnglish() {
        if (numberOfOverFour == 0){
            return "- There are no digits over 4.";
        } else
        if (numberOfOverFour == 1){
            return "- There is 1 digit over 4.";
        }
        return "- There are " + numberOfOverFour + " digits over 4.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            int numberOfOverFiveInCode = remainingCodes.get(i).getNumberOver(4);
            if (numberOfOverFiveInCode != numberOfOverFour) {
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
        NumberOfOverFourHint that = (NumberOfOverFourHint) o;
        return numberOfOverFour == that.numberOfOverFour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfOverFour);
    }
}
