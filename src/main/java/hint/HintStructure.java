package hint;

import java.util.List;

public class HintStructure {

    private String hintType;
    private List<Integer> concernedPositions;
    private Integer result;

    public HintStructure(){};

    public String getHintType() {
        return hintType;
    }

    public void setHintType(String hintType) {
        this.hintType = hintType;
    }

    public List<Integer> getConcernedPositions() {
        return concernedPositions;
    }

    public void setConcernedPositions(List<Integer> concernedPositions) {
        this.concernedPositions = concernedPositions;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
