package hint.sum;

import hint.Hint;
import hint.HintType;

public abstract class SumHint extends Hint {

    protected int sum;

    public SumHint(HintType sumType, int sum) {
        super(sumType);
        this.sum = sum;
    }


}
