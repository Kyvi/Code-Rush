package hint.even;

import hint.Hint;
import hint.HintStructure;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;
import model.Language;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EvenHint extends Hint {

    private int position;
    private boolean isEven;

    public EvenHint(int position, boolean isEven){
        super(HintType.EVEN_HINT);
        this.position = position;
        this.isEven = isEven;
    }

    public int getPosition(){
        return position;
    }

    @Override
    public void buildHintStructure() {
        HintStructure hintStructure = new HintStructure();
        hintStructure.setHintType(this.getHintType().name());
        hintStructure.setConcernedPositions(List.of(position));
        hintStructure.setResult(isEven ? 0 : 1);
        this.hintStructures.add(hintStructure);
    }


    @Override
    public String showHintInFrench() {
        return "- Le chiffre en " + PositionTranslator.translatePosition(position, Language.FRENCH) + " position est " + (isEven ? "pair" : "impair") + ".";
    }

    @Override
    public String showHintInEnglish() {
        return "- The digit at " + PositionTranslator.translatePosition(position, Language.ENGLISH) + " position is " + (isEven ? "even" : "odd") + ".";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {
        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            if (codeArray[position] % 2 == 0 && !isEven || codeArray[position] % 2 != 0 && isEven) {
                remainingCodes.remove(i);
                i--;
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EvenHint) {
            EvenHint evenHint = (EvenHint) obj;
            return evenHint.position == this.position && evenHint.isEven == this.isEven;
        }
        return false;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hint instanceof EvenHint){
            EvenHint evenHint = (EvenHint) hint;
            if(position == evenHint.position){
                return Boolean.compare(isEven, evenHint.isEven);
            }
            return Integer.compare(position, evenHint.position);
        }
        return super.compareTo(hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, isEven);
    }
}
