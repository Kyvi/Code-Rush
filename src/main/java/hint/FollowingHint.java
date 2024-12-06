package hint;

import model.Code;

import java.util.List;
import java.util.Objects;

public class FollowingHint extends Hint{

    List<List<Integer>> followingPositions;

    public FollowingHint(List<List<Integer>> followingPositions){
        super(HintType.FOLLOWING_HINT);
        this.followingPositions = followingPositions;
    }

    @Override
    public String showHint() {
        if (followingPositions.isEmpty()){
            return "- Il n'y a pas de chiffres qui se suivent.";
        }
        String positionString = buildPositionString();
        return "- Les chiffres en " + positionString + " se suivent.";
    }

    private String buildPositionString() {

        StringBuilder positionString = new StringBuilder();
        for (int i = 0; i < followingPositions.size(); i++) {
            List<Integer> positions = followingPositions.get(i);
            for (int j = 0; j < positions.size(); j++) {
                if (j == positions.size() - 1 && j != 0) {
                    positionString.append(" et ");
                } else if (j != 0) {
                    positionString.append(", ");
                }
                positionString.append(PositionTranslator.translatePosition(positions.get(j)));
                if (j == positions.size() - 1 && j != 0) {
                    positionString.append(" positions");
                }
            }
            if (i != followingPositions.size() - 1) {
                positionString.append(" et ceux en ");
            }
        }
        return positionString.toString();
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {

        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            for (int j = 0; j < followingPositions.size(); j++) {
                List<Integer> positions = followingPositions.get(j);
                Boolean isAscending = null;
                boolean isFollowing = true;
                for (int k = 0; k < positions.size() - 1; k++) {
                    if (codeArray[positions.get(k)] + 1 == codeArray[positions.get(k + 1)]) {
                        if (isAscending == null) {
                            isAscending = true;
                        } else if (!isAscending) {
                            remainingCodes.remove(i);
                            i--;
                            isFollowing = false;
                            break;
                        }
                    } else if (codeArray[positions.get(k)] - 1 == codeArray[positions.get(k + 1)]) {
                        if (isAscending == null) {
                            isAscending = false;
                        } else if (isAscending) {
                            remainingCodes.remove(i);
                            i--;
                            isFollowing = false;
                            break;
                        }
                    } else {
                        remainingCodes.remove(i);
                        i--;
                        isFollowing = false;
                        break;
                    }
                }
                if (!isFollowing) {
                    break;
                }
            }
        }
        return remainingCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingHint that = (FollowingHint) o;
        return followingPositions.equals(that.followingPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingPositions);
    }
}
