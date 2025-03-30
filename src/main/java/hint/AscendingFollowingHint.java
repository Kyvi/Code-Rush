package hint;

import model.Code;
import model.Language;

import java.util.List;
import java.util.Objects;

public class AscendingFollowingHint extends FollowingHint{

    public AscendingFollowingHint(List<List<Integer>> followingPositions){
        super(followingPositions);
    }

    @Override
    public String showHintInFrench() {
        if (followingPositions.isEmpty()){
            return "- Il n'y a pas de chiffres qui se suivent.";
        }
        String positionString = buildPositionString(Language.FRENCH);
        return "- Les chiffres en " + positionString + " positions se suivent dans l'ordre croissant.";
    }

    @Override
    public String showHintInEnglish() {
        if (followingPositions.isEmpty()){
            return "- There are no consecutive digits.";
        }
        String positionString = buildPositionString(Language.ENGLISH);
        return "- The digits at " + positionString + " positions are following each other in ascending order.";
    }

    @Override
    public List<Code> filterCodes(List<Code> remainingCodes) {

        for (int i = 0; i < remainingCodes.size(); i++) {
            Code code = remainingCodes.get(i);
            int[] codeArray = code.getCode();
            for (int j = 0; j < followingPositions.size(); j++) {
                List<Integer> positions = followingPositions.get(j);
                boolean isFollowing = true;
                for (int k = 0; k < positions.size() - 1; k++) {
                    if (codeArray[positions.get(k)] + 1 != codeArray[positions.get(k + 1)]) {
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
