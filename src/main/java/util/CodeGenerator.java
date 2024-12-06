package util;

import model.Code;

import static model.Code.CODE_LENGTH;

public class CodeGenerator {

    private final RandomGenerator randomGenerator;

    public CodeGenerator(){
        randomGenerator = new RandomGenerator();
    }

    public CodeGenerator(long seed){
        randomGenerator = new RandomGenerator(seed);
    }

    public Code generateCode(){

        Code code = new Code();

        int[] codeArray = new int[CODE_LENGTH];
        int i = 0;
        while (i < CODE_LENGTH) {
            int digit = randomGenerator.generateRandomInt(10);
            codeArray[i] = digit;
            i++;
        }
        code.setCode(codeArray);
        return code;
    }
}
