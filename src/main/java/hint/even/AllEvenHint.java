package hint.even;

import hint.Hint;
import hint.HintStructure;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;
import model.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AllEvenHint extends Hint {

    private Set<Integer> evenPositions;

    public AllEvenHint(Set<Integer> evenPositions) {
        super(HintType.ALL_EVEN_HINT);
        this.evenPositions = evenPositions;
    }

    @Override
    public void buildHintStructure() {
        HintStructure hintStructure = new HintStructure();
        hintStructure.setHintType(this.getHintType().name());
        hintStructure.setConcernedPositions(new ArrayList<>(evenPositions));
        this.hintStructures.add(hintStructure);
    }

    private String buildPositionString(Language language){
        String and = language == Language.FRENCH ? " et " : " and ";
        StringBuilder positionString = new StringBuilder();
        int size = evenPositions.size();
        int count = 0;
        for (Integer position : evenPositions) {
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
        if(evenPositions.isEmpty()){
            return "- Tous les chiffres sont impairs.";
        }
        else if(evenPositions.size() == Code.CODE_LENGTH){
            return "- Tous les chiffres sont pairs.";
        }
        else if(evenPositions.size() == 1){
            return "- Le chiffre en " + buildPositionString(Language.FRENCH) + " position est pair. Les autres sont impairs.";
        }
        String positionString = buildPositionString(Language.FRENCH);
        return "- Les chiffres en " + positionString + " positions sont pairs. Les autres sont impairs.";
    }

    @Override
    public String showHintInEnglish() {
        if(evenPositions.isEmpty()){
            return "- All digits are odd.";
        }
        else if(evenPositions.size() == Code.CODE_LENGTH){
            return "- All digits are even.";
        }
        else if(evenPositions.size() == 1){
            return "- The digit at " + buildPositionString(Language.ENGLISH) + " position is even. The others are odd.";
        }
        String positionString = buildPositionString(Language.ENGLISH);
        return "- The digits at " + positionString + " positions are even. The others are odd.";
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
