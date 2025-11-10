package hint;

import model.Code;
import model.Language;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EqualityHint extends Hint{

    List<List<Integer>> equalPositions;

    public EqualityHint(List<List<Integer>> equalPositions){
        super(HintType.EQUALITY_HINT);
        this.equalPositions = equalPositions;
    }

    @Override
    public void buildHintStructure() {
        for(List<Integer> equalPosition: equalPositions){
            HintStructure hintStructure = new HintStructure();
            hintStructure.setHintType(this.getHintType().name());
            hintStructure.setConcernedPositions(equalPosition);
            this.hintStructures.add(hintStructure);
        }
    }

    private String buildPositionString(Language language) {

        String and = language == Language.FRENCH ? " et " : " and ";
        String andThoseIn = language == Language.FRENCH ? " et ceux en " : " and those in ";
        StringBuilder positionString = new StringBuilder();
        for (int i = 0; i < equalPositions.size(); i++) {
            List<Integer> positions = equalPositions.get(i);
            for (int j = 0; j < positions.size(); j++) {
                if (j == positions.size() - 1 && j != 0) {
                    positionString.append(and);
                } else if (j != 0) {
                    positionString.append(", ");
                }
                positionString.append(PositionTranslator.translatePosition(positions.get(j), language));
            }
            if (i != equalPositions.size() - 1) {
                positionString.append(andThoseIn);
            }
        }
        return positionString.toString();
    }

    @Override
    public String showHintInFrench() {
        if (equalPositions.isEmpty()){
            return "- Tous les chiffres sont differents";
        }
        String positionString = buildPositionString(Language.FRENCH);
        return "- Les chiffres en " + positionString + " positions sont égaux.";
    }

    @Override
    public String showHintInEnglish() {
        if (equalPositions.isEmpty()){
            return "- All the digits are different";
        }
        String positionString = buildPositionString(Language.ENGLISH);
        return "- The digits at " + positionString + " are equal.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {

        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            for (int j = 0; j < equalPositions.size(); j++) {
                List<Integer> positions = equalPositions.get(j);
                boolean isEqual = true;
                for(Integer position : positions){
                    if(codeArray[position] != codeArray[positions.get(0)]){
                        isEqual = false;
                        break;
                    }
                }
                if (!isEqual) {
                    remainingCodes.remove(i);
                    i--;
                    break;
                }
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualityHint that = (EqualityHint) o;
        return equalPositions.equals(that.equalPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equalPositions);
    }
}
