package hint.even;

import hint.Hint;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AllEvenHint extends Hint {

    Set<Integer> evenPositions;

    public AllEvenHint(Set<Integer> evenPositions) {
        super(HintType.ALL_EVEN_HINT);
        this.evenPositions = evenPositions;
    }

    @Override
    public String showHint() {
        if(evenPositions.isEmpty()){
            return "- Tous les chiffres sont impairs.";
        }
        else if(evenPositions.size() == Code.CODE_LENGTH){
            return "- Tous les chiffres sont pairs.";
        }
        String positionString = buildPositionString();
        return "- Les chiffres en " + positionString + " positions sont pairs. Les autres sont impairs.";
    }

    private String buildPositionString() {
        StringBuilder positionString = new StringBuilder();
        int size = evenPositions.size();
        int count = 0;
        for (Integer position : evenPositions) {
            positionString.append(PositionTranslator.translatePosition(position));
            if (count == size - 2) {
                positionString.append(" et ");
            } else if (count < size - 2) {
                positionString.append(", ");
            }
            count++;
        }
        return positionString.toString();
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            for(int j = 0; j < codeArray.length; j++){
                if(evenPositions.contains(j)){
                    if(codeArray[j] % 2 != 0){
                        remainingCodes.remove(i);
                        i--;
                        break;
                    }
                }
                else{
                    if(codeArray[j] % 2 == 0){
                        remainingCodes.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllEvenHint that = (AllEvenHint) o;
        return Objects.equals(evenPositions, that.evenPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evenPositions);
    }
}
