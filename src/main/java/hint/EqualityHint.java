package hint;

import model.Code;

import java.util.List;
import java.util.Objects;

public class EqualityHint extends Hint{

    List<List<Integer>> equalPositions;

    public EqualityHint(List<List<Integer>> equalPositions){
        super(HintType.EQUALITY_HINT);
        this.equalPositions = equalPositions;
    }

    @Override
    public String showHint() {
        if (equalPositions.isEmpty()){
            return "- Tous les chiffres sont differents";
        }
        String positionString = buildPositionString();
        return "- Les chiffres en " + positionString + " sont égaux.";
    }

    private String buildPositionString() {

        StringBuilder positionString = new StringBuilder();
        for (int i = 0; i < equalPositions.size(); i++) {
            List<Integer> positions = equalPositions.get(i);
            for (int j = 0; j < positions.size(); j++) {
                if (j == positions.size() - 1 && j != 0) {
                    positionString.append(" et ");
                } else if (j != 0) {
                    positionString.append(", ");
                }
                positionString.append(PositionTranslator.translatePosition(positions.get(j)));
                if (j == positions.size() - 1 && j != 0) {
                    positionString.append(" positions");
                }
            }
            if (i != equalPositions.size() - 1) {
                positionString.append(" et ceux en ");
            }
        }
        return positionString.toString();
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
