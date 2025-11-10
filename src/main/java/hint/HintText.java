package hint;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HintText {

    @SerializedName("hintText")
    private String hintText;

    @SerializedName("hintStructures")
    private List<HintStructure> hintStructureList;

    public HintText() {
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public List<HintStructure> getHintStructureList() {
        return hintStructureList;
    }

    public void setHintStructureList(List<HintStructure> hintStructureList) {
        this.hintStructureList = hintStructureList;
    }
}
