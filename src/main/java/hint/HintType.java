package hint;

import java.util.Collections;
import java.util.Set;

public enum HintType {

    FOLLOWING_HINT(Collections.emptySet()),
    EQUALITY_HINT(Collections.emptySet()),
    NUMBER_HINT(Set.of(HintProperty.POSITIONAL)),
    NO_NUMBER_HINT(Collections.emptySet()),
    EVEN_HINT(Set.of(HintProperty.EVEN, HintProperty.POSITIONAL)),
    DOUBLE_SUM_HINT(Set.of(HintProperty.SUM)),
    TRIPLE_SUM_HINT(Set.of(HintProperty.SUM)),
    ALL_EVEN_HINT(Set.of(HintProperty.EVEN)),
    NUMBER_OF_EVEN_HINT(Set.of(HintProperty.EVEN)),
    NUMBER_OF_ODD_HINT(Set.of(HintProperty.EVEN)),
    ALL_EVEN_SUM_HINT(Set.of(HintProperty.EVEN, HintProperty.SUM)),
    ALL_ODD_SUM_HINT(Set.of(HintProperty.EVEN, HintProperty.SUM)),
    ALL_ODD_HINT(Set.of(HintProperty.EVEN)),
    ALL_SUM_HINT(Set.of(HintProperty.SUM)),
    NUMBER_OF_OVER_FOUR_HINT(Collections.emptySet()),

    MAX_DIFF_HINT(Collections.emptySet());

    private Set<HintProperty> hintPropertiesSet;

    public Set<HintProperty> getHintPropertiesSet(){
        return hintPropertiesSet;
    }

    HintType(Set<HintProperty> hintPropertiesSet){
        this.hintPropertiesSet = hintPropertiesSet;
    }


    public boolean isEasy() {
        return this == NUMBER_HINT;
    }
}
