package hint;

import model.Code;
import model.Language;

import java.util.ArrayList;
import java.util.List;

public abstract class Hint implements Comparable<Hint>{

    private HintType hintType;
    protected List<HintStructure> hintStructures;

    public Hint(HintType hintType){
        this.hintType = hintType;
        this.hintStructures = new ArrayList<>();
    }

    public abstract String showHintInFrench();
    public abstract String showHintInEnglish();

    public abstract void buildHintStructure();


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

    public HintText buildFrenchHintText() {
        HintText hintText = new HintText();
        hintText.setHintText(showHintInFrench());
        hintText.setHintStructureList(hintStructures);
        return hintText;
    }

    public HintText buildEnglishHintText() {
        HintText hintText = new HintText();
        hintText.setHintText(showHintInEnglish());
        hintText.setHintStructureList(hintStructures);
        return hintText;
    }
}
