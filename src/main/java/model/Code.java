package model;

public class Code {

    public static final int CODE_LENGTH = 5;
    private int[] code;

    public Code(){
        code = new int[CODE_LENGTH];
    }

    public Code(int[] code){
        this.code = code;
    }

    public int[] getCode(){
        return code;
    }

    public void setCode(int[] code){
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(code[i]);
        }
        return sb.toString();
    }

    public boolean containsNumber(int number){
        for (int i = 0; i < CODE_LENGTH; i++) {
            if(code[i] == number){
                return true;
            }
        }
        return false;
    }

    public int getNumberOfEven(){
        int numberOfEven = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if(code[i] % 2 == 0){
                numberOfEven++;
            }
        }
        return numberOfEven;
    }

    public int getNumberOfOdd(){
        int numberOfOdd = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if(code[i] % 2 != 0){
                numberOfOdd++;
            }
        }
        return numberOfOdd;
    }

    public int getMaxDiff() {
        int maxDiff = 0;
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            for (int j = i + 1; j < CODE_LENGTH; j++) {
                int diff = Math.abs(code[i] - code[j]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
        }
        return maxDiff;
    }

    public int getNumberOver(int number){
        int numberOver = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if(code[i] > number){
                numberOver++;
            }
        }
        return numberOver;
    }
}
