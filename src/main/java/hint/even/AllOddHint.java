package hint.even;

import hint.Hint;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;
import model.Language;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AllOddHint extends Hint {

    Set<Integer> oddPositions;

    public AllOddHint(Set<Integer> oddPositions) {
        super(HintType.ALL_ODD_HINT);
        this.oddPositions = oddPositions;
    }


    private String buildPositionString(Language language){
        String and = language == Language.FRENCH ? " et " : " and ";
        StringBuilder positionString = new StringBuilder();
        int size = oddPositions.size();
        int count = 0;
        for (Integer position : oddPositions) {
            positionString.append(PositionTranslator.translatePosition(position, language));
            if (count == size - 2) {
                positionString.append(and);
            } else if (count < size - 2) {
                positionString.append(", ");
            }
            count++;
        }
        return positionString.toString();
    }

    @Override
    public String showHintInFrench() {
        if(oddPositions.isEmpty()){
            return "- Tous les chiffres sont pairs.";
        }
        else if(oddPositions.size() == Code.CODE_LENGTH){
            return "- Tous les chiffres sont impairs.";
        }
        else if(oddPositions.size() == 1){
            return "- Le chiffre en " + buildPositionString(Language.FRENCH) + " position est impair. Les autres sont pairs.";
        }
        String positionString = buildPositionString(Language.FRENCH);
        return "- Les chiffres en " + positionString + " positions sont impairs. Les autres sont pairs.";
    }

    @Override
    public String showHintInEnglish() {
        if(oddPositions.isEmpty()){
            return "- All digits are even.";
        }
        else if(oddPositions.size() == Code.CODE_LENGTH){
            return "- All digits are odd.";
        }
        else if(oddPositions.size() == 1){
            return "- The digit at " + buildPositionString(Language.ENGLISH) + " position is odd. The others are even.";
        }
        String positionString = buildPositionString(Language.ENGLISH);
        return "- The digits at " + positionString + " positions are odd. The others are even.";
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
