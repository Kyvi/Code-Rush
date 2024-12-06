package hint.even;

import hint.Hint;
import hint.HintType;
import hint.PositionTranslator;
import model.Code;

import java.util.List;
import java.util.Objects;

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
    public String showHint(){
        return "- Le chiffre en " + PositionTranslator.translatePosition(position) + " position est " + (isEven ? "pair" : "impair") + ".";
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
