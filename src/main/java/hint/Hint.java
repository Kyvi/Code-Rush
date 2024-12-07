package hint;

import model.Code;
import model.Language;

import java.util.List;

public abstract class Hint implements Comparable<Hint>{

    private HintType hintType;

    public Hint(HintType hintType){
        this.hintType = hintType;
    }

    public String showHint(Language language){
        if(language == Language.FRENCH){
            return showHintInFrench();
        }
        else{
            return showHintInEnglish();
        }
    }

    public abstract String showHintInFrench();
    public abstract String showHintInEnglish();


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
