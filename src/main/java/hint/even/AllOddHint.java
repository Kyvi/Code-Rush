package hint.even;

import hint.Hint;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AllOddHint extends Hint {

    Set<Integer> oddPositions;

    public AllOddHint(Set<Integer> oddPositions) {
        super(HintType.ALL_ODD_HINT);
        this.oddPositions = oddPositions;
    }

    @Override
    public String showHint() {
        if(oddPositions.isEmpty()){
            return "- Tous les chiffres sont pairs.";
        }
        else if(oddPositions.size() == Code.CODE_LENGTH){
            return "- Tous les chiffres sont impairs.";
        }
        String positionString = buildPositionString();
        return "- Les chiffres en " + positionString + " positions sont impairs. Les autres sont pairs.";
    }

    private String buildPositionString() {
        StringBuilder positionString = new StringBuilder();
        int size = oddPositions.size();
        int count = 0;
        for (Integer position : oddPositions) {
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
                if(oddPositions.contains(j)){
                    if(codeArray[j] % 2 == 0){
                        remainingCodes.remove(i);
                        i--;
                        break;
                    }
                }
                else{
                    if(codeArray[j] % 2 != 0){
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
        AllOddHint that = (AllOddHint) o;
        return Objects.equals(oddPositions, that.oddPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oddPositions);
    }
}
