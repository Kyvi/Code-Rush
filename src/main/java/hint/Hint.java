package hint;

import model.Code;

import java.util.List;

public abstract class Hint implements Comparable<Hint>{

    private HintType hintType;

    public Hint(HintType hintType){
        this.hintType = hintType;
    }

    public abstract String showHint();


    public abstract List<Code> filterCodes(List<Code> remainingCodes);

    public HintType getHintType(){
        return hintType;
    }

    @Override
    public int compareTo(Hint hint) {
        if(hintType.compareTo(hint.hintType) == 0){
            return this.toString().compareTo(hint.toString());
        }
        else{
            return hintType.compareTo(hint.hintType);
        }
    }
}
