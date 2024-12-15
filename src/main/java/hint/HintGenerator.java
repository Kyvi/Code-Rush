package hint;

import hint.even.*;
import hint.sum.*;
import model.Code;

import java.util.*;

import util.RandomGenerator;

import static model.Code.CODE_LENGTH;

public class HintGenerator {

    private final RandomGenerator randomGenerator;
    private Set<Integer> usedPositions;
    private Set<HintType> usedHintTypes;
    private Set<Hint> hints;

    private Code solutionCode;

    public HintGenerator(RandomGenerator randomGenerator){
        this.randomGenerator = randomGenerator;
        usedPositions = new HashSet<>();
        usedHintTypes = new HashSet<>();
    }

    public Set<Hint> generateHints(){

        List<Code> remainingCodes = generateAllCodes();

        hints = new TreeSet<>();
        int nbSumHints = 0;
        int nbNumberHints = 0;
        int nbEvenHints = 0;
        while(remainingCodes.size() > 1){
            Hint hint = generateNextHint(remainingCodes, nbSumHints, nbEvenHints, nbNumberHints);
            if(hint instanceof SumHint){
                nbSumHints++;
            } else if(hint instanceof NumberHint || hint instanceof NoNumberHint){
                nbNumberHints++;
                if (hint instanceof NumberHint){
                    usedPositions.add(((NumberHint) hint).getPosition());
                }

            } else if(hint instanceof AllEvenHint || hint instanceof AllOddHint || hint instanceof EvenHint || hint instanceof NumberOfEvenHint || hint instanceof NumberOfOddHint){
                nbEvenHints++;
            }
            int currentSize = remainingCodes.size();
            remainingCodes = hint.filterCodes(remainingCodes);
            if(remainingCodes.size() == currentSize){
                continue;
            }
            hints.add(hint);
        }
        solutionCode = remainingCodes.get(0);
        removeUneccessaryHints();
        return hints;
    }

    private void removeUneccessaryHints() {
        List<Code> remainingCodes = generateAllCodes();
        List<Hint> hintsToTest = new ArrayList<>();
        hintsToTest.addAll(hints);
        for(Hint hint : hintsToTest){
            hints.remove(hint);
            for(Hint reminingHint : hints){
                reminingHint.filterCodes(remainingCodes);
            }
            if(remainingCodes.size() > 1){
                hints.add(hint);
            }
            remainingCodes = generateAllCodes();
        }
    }

    private Hint generateNextHint(List<Code> remainingCodes, int nbSumHints, int nbEvenHints, int nbNumberHints) {

        List<HintType> possibleHintTypes = new ArrayList<>();
        for(HintType type : HintType.values()){

            switch (type) {
                case DOUBLE_SUM_HINT:
                case TRIPLE_SUM_HINT:
                    if (nbSumHints < 2) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case ALL_SUM_HINT:
                    if (nbSumHints < 2 && !(usedHintTypes.contains(HintType.ALL_EVEN_SUM_HINT) && usedHintTypes.contains(HintType.ALL_ODD_SUM_HINT))) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case ALL_EVEN_SUM_HINT:
                    if (nbSumHints < 3 && !(usedHintTypes.contains(HintType.ALL_SUM_HINT) && usedHintTypes.contains(HintType.ALL_ODD_SUM_HINT))) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case ALL_ODD_SUM_HINT:
                    if (nbSumHints < 3 && !(usedHintTypes.contains(HintType.ALL_SUM_HINT) && usedHintTypes.contains(HintType.ALL_EVEN_SUM_HINT))) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case ALL_EVEN_HINT:
                case ALL_ODD_HINT:
                case NUMBER_OF_ODD_HINT:
                case NUMBER_OF_EVEN_HINT:
                    if (nbEvenHints < 1) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case NUMBER_HINT:
                    if (usedPositions.size() != CODE_LENGTH) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case NO_NUMBER_HINT:
                    if (nbNumberHints < 3) {
                        possibleHintTypes.add(type);
                    }
                    break;
                case FOLLOWING_HINT:
                    possibleHintTypes.add(type);
                    break;
                case EQUALITY_HINT:
                    possibleHintTypes.add(type);
                    break;
                case MAX_DIFF_HINT:
                    possibleHintTypes.add(type);
                    break;
                case NUMBER_OF_OVER_FOUR_HINT:
                    possibleHintTypes.add(type);
                    break;
            }
        }

        HintType hintType = randomGenerator.generateRandomHintType(possibleHintTypes);


        if(hintType == HintType.DOUBLE_SUM_HINT){
            usedHintTypes.add(HintType.DOUBLE_SUM_HINT);
            return generateDoubleSumHint(remainingCodes);
        } else if(hintType == HintType.TRIPLE_SUM_HINT){
            usedHintTypes.add(HintType.TRIPLE_SUM_HINT);
            return generateTripleSumHint(remainingCodes);
        } else if(hintType == HintType.EVEN_HINT){
            usedHintTypes.add(HintType.EVEN_HINT);
            return generateEvenHint(remainingCodes, usedPositions);
        } else if(hintType == HintType.ALL_EVEN_HINT){
            usedHintTypes.add(HintType.ALL_EVEN_HINT);
            return generateAllEvenHint(remainingCodes);
        } else if(hintType == HintType.ALL_ODD_HINT) {
            usedHintTypes.add(HintType.ALL_ODD_HINT);
            return generateAllOddHint(remainingCodes);
        } else if(hintType == HintType.NUMBER_OF_ODD_HINT){
            usedHintTypes.add(HintType.NUMBER_OF_ODD_HINT);
            return generateNumberOfOddHint(remainingCodes);
        } else if(hintType == HintType.NUMBER_OF_EVEN_HINT){
            usedHintTypes.add(HintType.NUMBER_OF_EVEN_HINT);
            return generateNumberOfEvenHint(remainingCodes);
        } else if(hintType == HintType.ALL_SUM_HINT){
            usedHintTypes.add(HintType.ALL_SUM_HINT);
            return generateAllSumHint(remainingCodes);
        } else if(hintType ==HintType.ALL_EVEN_SUM_HINT){
            usedHintTypes.add(HintType.ALL_EVEN_SUM_HINT);
            return generateAllEvenSumHint(remainingCodes);
        } else if(hintType == HintType.ALL_ODD_SUM_HINT){
            usedHintTypes.add(HintType.ALL_ODD_SUM_HINT);
            return generateAllOddSumHint(remainingCodes);
        } else if(hintType == HintType.NUMBER_HINT){
            usedHintTypes.add(HintType.NUMBER_HINT);
            return generateNumberHint(remainingCodes, usedPositions);
        }
        else if(hintType == HintType.NO_NUMBER_HINT){
            usedHintTypes.add(HintType.NO_NUMBER_HINT);
            return generateNoNumberHint(remainingCodes);
        }
        else if(hintType == HintType.FOLLOWING_HINT){
            return generateFollowingHint(remainingCodes);
        }
        else if(hintType == HintType.EQUALITY_HINT){
            return generateEqualityHint(remainingCodes);
        }
        else if(hintType == HintType.MAX_DIFF_HINT){
            return generateMaxDiffHint(remainingCodes);
        }
        else if(hintType == HintType.NUMBER_OF_OVER_FOUR_HINT){
            return generateNumberOfOverFiveHint(remainingCodes);
        }
        throw new IllegalArgumentException("Hint type not recognized");
    }

    private Hint generateNumberOfOverFiveHint(List<Code> remainingCodes) {
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int numberOfOverFive = code.getNumberOver(4);
        return new NumberOfOverFourHint(numberOfOverFive);
    }

    private Hint generateMaxDiffHint(List<Code> remainingCodes) {
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int maxDiff = code.getMaxDiff();
        return new MaxDiffHint(maxDiff);
    }

    private Hint generateFollowingHint(List<Code> remainingCodes) {
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        List<List<Integer>> followingPositions = new ArrayList<>();
        List<Integer> currentPositions = new ArrayList<>();
        Boolean isAscending = null;
        int[] codeArray = code.getCode();
        for(int i = 0; i < CODE_LENGTH - 1; i++){
            if(isAscending == null){
                isAscending = codeArray[i] + 1 == codeArray[i + 1];
            }
            if((codeArray[i] + 1 == codeArray[i + 1] && isAscending) ||
                    (codeArray[i] - 1 == codeArray[i + 1] && !isAscending)){
                currentPositions.add(i);
            } else {
                isAscending = null;
                if(!currentPositions.isEmpty()){
                    currentPositions.add(i);
                    followingPositions.add(new ArrayList<>(currentPositions));
                }
                currentPositions.clear();
            }
        }
        if(!currentPositions.isEmpty()){
            currentPositions.add(CODE_LENGTH - 1);
            followingPositions.add(new ArrayList<>(currentPositions));
        }
        return new FollowingHint(followingPositions);
    }

    private Hint generateEqualityHint(List<Code> remainingCodes) {
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        List<List<Integer>> equalPositions = new ArrayList<>();
        int[] codeArray = code.getCode();
        Map<Integer, List<Integer>> numberToPositions = new HashMap<>();
        for(int i = 0; i < CODE_LENGTH; i++){
            if(!numberToPositions.containsKey(codeArray[i])){
                numberToPositions.put(codeArray[i], new ArrayList<>());
            }
            numberToPositions.get(codeArray[i]).add(i);
        }
        for(List<Integer> positions : numberToPositions.values()){
            if(positions.size() > 1){
                equalPositions.add(new ArrayList<>(positions));
            }
        }
        return new EqualityHint(equalPositions);
    }

    private Hint generateNumberOfOddHint(List<Code> remainingCodes) {

            Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
            int numberOfOdd = code.getNumberOfOdd();
            return new NumberOfOddHint(numberOfOdd);
    }

    private Hint generateNumberOfEvenHint(List<Code> remainingCodes) {

            Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
            int numberOfEven = code.getNumberOfEven();
            return new NumberOfEvenHint(numberOfEven);
    }

    private Hint generateDoubleSumHint(List<Code> remainingCodes) {

        int[] twoNumbers = randomGenerator.generateTwoDifferentRandomInt(CODE_LENGTH);
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        int sum = codeArray[twoNumbers[0]] + codeArray[twoNumbers[1]];
        return new DoubleSumHint(twoNumbers[0], twoNumbers[1], sum);

    }

    private Hint generateTripleSumHint(List<Code> remainingCodes) {

        int[] threeNumbers = randomGenerator.generateThreeDifferentRandomInt(CODE_LENGTH);
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        int sum = codeArray[threeNumbers[0]] + codeArray[threeNumbers[1]] + codeArray[threeNumbers[2]];
        return new TripleSumHint(threeNumbers[0], threeNumbers[1], threeNumbers[2], sum);

    }

    private Hint generateAllEvenHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        Set<Integer> evenPositions = new HashSet<>();
        for(int i = 0; i < CODE_LENGTH; i++){
            if(codeArray[i] % 2 == 0){
                evenPositions.add(i);
            }
        }
        return new AllEvenHint(evenPositions);

    }

    private Hint generateAllOddHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        Set<Integer> oddPositions = new HashSet<>();
        for(int i = 0; i < CODE_LENGTH; i++){
            if(codeArray[i] % 2 != 0){
                oddPositions.add(i);
            }
        }
        return new AllOddHint(oddPositions);

    }

    private Hint generateAllSumHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        int sum = 0;
        for(int i = 0; i < CODE_LENGTH; i++){
            sum += codeArray[i];
        }
        return new AllSumHint(sum);

    }

    private Hint generateAllEvenSumHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        int sum = 0;
        for(int i = 0; i < CODE_LENGTH; i++){
            if(codeArray[i] % 2 == 0){
                sum += codeArray[i];
            }
        }
        return new AllEvenSumHint(sum);
    }

    private Hint generateAllOddSumHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        int sum = 0;
        for(int i = 0; i < CODE_LENGTH; i++){
            if(codeArray[i] % 2 != 0){
                sum += codeArray[i];
            }
        }
        return new AllOddSumHint(sum);
    }

    private Hint generateNumberHint(List<Code> remainingCodes, Set<Integer> usedPositions) {

        int position;
        do{
            position = randomGenerator.generateRandomInt(CODE_LENGTH);
        }while(usedPositions.contains(position));
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        return new NumberHint(position, codeArray[position]);

    }

    private Hint generateNoNumberHint(List<Code> remainingCodes) {

        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int noNumber = 0;
        do{
            noNumber = randomGenerator.generateRandomInt(10);
        } while (code.containsNumber(noNumber));

        return new NoNumberHint(noNumber);
    }

    private Hint generateEvenHint(List<Code> remainingCodes, Set<Integer> usedPositions) {

        int position;
        do{
            position = randomGenerator.generateRandomInt(CODE_LENGTH);
        }while(usedPositions.contains(position));
        Code code = remainingCodes.get(randomGenerator.generateRandomInt(remainingCodes.size()));
        int[] codeArray = code.getCode();
        return new EvenHint(position, codeArray[position] % 2 == 0);

    }

    private static List<Code> generateAllCodes(){
        List<Code> codes = new ArrayList<>();

        // Add all codes from 00000 to 99999
        int numberCodes = (int) Math.pow(10, CODE_LENGTH);
        for(int i = 0; i < numberCodes; i++){
            int[] code = new int[CODE_LENGTH];
            int temp = i;
            for(int j = CODE_LENGTH - 1; j >= 0; j--){
                code[j] = temp % 10;
                temp /= 10;
            }
            codes.add(new Code(code));
        }
        return codes;
    }

    public Code getSolutionCode() {
        return solutionCode;
    }
}
