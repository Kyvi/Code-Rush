package util;

import hint.HintType;

import java.util.*;

public class RandomGenerator {

    private static Random random;

    public RandomGenerator(){
        random = new Random();
    }

    public RandomGenerator(long seed){
        random = new Random(seed);
    }

    public int generateRandomInt(int bound){
        return random.nextInt(bound);
    }

    public HintType generateRandomHintType(List<HintType> hintTypes){
        int randomInt = random.nextInt(hintTypes.size());
        return hintTypes.get(randomInt);
    }

    public int[] generateTwoDifferentRandomInt(int bound){
        int number1 = random.nextInt(bound); // Génère un nombre entre 0 et 4
        int number2;

        do {
            number2 = random.nextInt(bound);
        } while (number2 == number1); // S'assure que number2 est différent de number1

        // Assure que les nombres sont en ordre croissant
        if (number1 > number2) {
            int temp = number1;
            number1 = number2;
            number2 = temp;
        }

        return new int[]{number1, number2};
    }

    public int[] generateThreeDifferentRandomInt(int bound){
        int number1 = random.nextInt(bound); // Génère un nombre entre 0 et 4
        int number2;

        do {
            number2 = random.nextInt(bound);
        } while (number2 == number1); // S'assure que number2 est différent de number1

        int number3;
        do{
            number3 = random.nextInt(bound);
        } while (number3 == number1 || number3 == number2); // S'assure que number3 est différent de number1 et number2


        // Place les nombres dans un tableau
        int[] numbers = {number1, number2, number3};
        // Trie le tableau en ordre croissant
        Arrays.sort(numbers);

        return numbers;
    }
}
