import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hint.Hint;
import hint.HintGenerator;
import model.Code;
import model.Language;
import model.Level;
import util.RandomGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int nbLevels = 1000;
        int nbVeryEasy = 0;
        int nbEasy = 0;
        int nbNormal = 0;
        int nbHard = 0;
        List<Level> veryEasyFrenchLevels = new ArrayList<>();
        List<Level> veryEasyEnglishLevels = new ArrayList<>();

        List<Level> easyFrenchLevels = new ArrayList<>();
        List<Level> easyEnglishLevels = new ArrayList<>();

        List<Level> normalFrenchLevels = new ArrayList<>();
        List<Level> normalEnglishLevels = new ArrayList<>();

        List<Level> hardFrenchLevels = new ArrayList<>();
        List<Level> hardEnglishLevels = new ArrayList<>();
        for(int i=0; i<nbLevels; i++){

            RandomGenerator randomGenerator = new RandomGenerator(i+1);
            HintGenerator hintGenerator = new HintGenerator(randomGenerator);

            Set<Hint> hints = hintGenerator.generateHints();

            boolean isVeryEasy = hints.stream().filter(hint -> hint.getHintType().isEasy()).count() > 2;
            boolean isEasy = !isVeryEasy && (hints.size() < 5 || hints.stream().filter(hint -> hint.getHintType().isEasy()).count() > 1);
            boolean isHard = !isEasy && (hints.size() >= 5 && hints.stream().noneMatch(hint -> hint.getHintType().isEasy()));

            List<String> frenchLevelHints = hints.stream().map(hint -> hint.showHint(Language.FRENCH)).collect(Collectors.toList());
            List<String> englishLevelHints = hints.stream().map(hint -> hint.showHint(Language.ENGLISH)).collect(Collectors.toList());
            //levelHints.add(0,"- Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            /*System.out.println("Voici les indices :");
            System.out.println("Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            for(Hint hint : hints){
                System.out.println("    - " + hint.showHint());
            }*/

            Code code = hintGenerator.getSolutionCode();

            int levelNumber = 0;
            if(isVeryEasy){
                levelNumber = nbVeryEasy+1;
            }
            else if(isEasy){
                levelNumber = nbEasy+1;
            }
            else if(isHard){
                levelNumber = nbHard+1;
            }
            else{
                levelNumber = nbNormal+1;
            }
            Level frenchLevel = new Level("Niveau " + levelNumber, frenchLevelHints, code.toString());
            Level englishLevel = new Level("Level " + levelNumber, englishLevelHints, code.toString());

            System.out.println("Niveau " + (i+1) + " généré avec succès !");
            System.out.println("Nombre de niveau très facile : " + nbVeryEasy);
            System.out.println("Nombre de niveau facile : " + nbEasy);
            System.out.println("Nombre de niveau moyen : " + nbNormal);
            System.out.println("Nombre de niveau difficile : " + nbHard);

            if(isVeryEasy){
                veryEasyFrenchLevels.add(frenchLevel);
                veryEasyEnglishLevels.add(englishLevel);
                nbVeryEasy++;
            }
            else if(isEasy){
                easyFrenchLevels.add(frenchLevel);
                easyEnglishLevels.add(englishLevel);
                nbEasy++;
            }
            else if(isHard){
                hardFrenchLevels.add(frenchLevel);
                hardEnglishLevels.add(englishLevel);
                nbHard++;
            }
            else{
                normalFrenchLevels.add(frenchLevel);
                normalEnglishLevels.add(englishLevel);
                nbNormal++;
            }

        }

        /*RandomGenerator randomGenerator = new RandomGenerator(12);
        HintGenerator hintGenerator = new HintGenerator(randomGenerator);

        Set<Hint> hints = hintGenerator.generateHints();
        for(Hint hint : hints){
            System.out.println("    - " + hint.showHint());
        }
        Code code = hintGenerator.getSolutionCode();*/

        // Convertir en JSON et écrire dans un fichier
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("normalLevelsFR.json")) {
            gson.toJson(normalFrenchLevels, writer);
            System.out.println("Fichier JSON FR généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("normalLevelsEN.json")) {
            gson.toJson(normalEnglishLevels, writer);
            System.out.println("EN JSON file generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("veryEasyLevelsFR.json")) {
            gson.toJson(veryEasyFrenchLevels, writer);
            System.out.println("Fichier JSON FR très facile généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON facile : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("veryEasyLevelsEN.json")) {
            gson.toJson(veryEasyEnglishLevels, writer);
            System.out.println("EN JSON file very easy generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("easyLevelsFR.json")) {
            gson.toJson(easyFrenchLevels, writer);
            System.out.println("Fichier JSON FR easy facile généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON facile : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("easyLevelsEN.json")) {
            gson.toJson(easyEnglishLevels, writer);
            System.out.println("EN JSON file easy generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("hardLevelsFR.json")) {
            gson.toJson(hardFrenchLevels, writer);
            System.out.println("Fichier JSON FR difficile généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("hardLevelsEN.json")) {
            gson.toJson(hardEnglishLevels, writer);
            System.out.println("EN JSON file hard generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }


        /*System.out.println("Quel est le code ?");
        Scanner scanner = new Scanner(System.in);
        String userCode = scanner.next();
        if(code.toString().equals(userCode)){
            System.out.println("Bravo !");
        } else {
            System.out.println("Dommage, le code etait " + code);
        }*/
    }
}
