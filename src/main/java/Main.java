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

        int nbLevels = 500;
        int nbEasy = 0;
        List<Level> easyFrenchLevels = new ArrayList<>();
        List<Level> easyEnglishLevels = new ArrayList<>();
        List<Level> frenchLevels = new ArrayList<>();
        List<Level> englishLevels = new ArrayList<>();
        for(int i=0; i<nbLevels; i++){

            RandomGenerator randomGenerator = new RandomGenerator(i+1);
            HintGenerator hintGenerator = new HintGenerator(randomGenerator);

            Set<Hint> hints = hintGenerator.generateHints();

            boolean isEasy = hints.size() < 5 || hints.stream().filter(hint -> hint.getHintType().isEasy()).count() > 1;

            List<String> frenchLevelHints = hints.stream().map(hint -> hint.showHint(Language.FRENCH)).collect(Collectors.toList());
            List<String> englishLevelHints = hints.stream().map(hint -> hint.showHint(Language.ENGLISH)).collect(Collectors.toList());
            //levelHints.add(0,"- Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            /*System.out.println("Voici les indices :");
            System.out.println("Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            for(Hint hint : hints){
                System.out.println("    - " + hint.showHint());
            }*/

            Code code = hintGenerator.getSolutionCode();

            Level frenchLevel = new Level("Niveau " + (isEasy ? nbEasy+1 : i+1-nbEasy), frenchLevelHints, code.toString());
            Level englishLevel = new Level("Level " + (isEasy ? nbEasy+1 : i+1-nbEasy), englishLevelHints, code.toString());

            System.out.println("Niveau " + (i+1) + " généré avec succès !");
            System.out.println("Nombre de niveau facile : " + nbEasy);

            if(isEasy){
                easyFrenchLevels.add(frenchLevel);
                easyEnglishLevels.add(englishLevel);
                nbEasy++;
            }
            else{
                frenchLevels.add(frenchLevel);
                englishLevels.add(englishLevel);
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
        try (FileWriter writer = new FileWriter("levelsFR.json")) {
            gson.toJson(frenchLevels, writer);
            System.out.println("Fichier JSON FR généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("levelsEN.json")) {
            gson.toJson(englishLevels, writer);
            System.out.println("EN JSON file generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("easyLevelsFR.json")) {
            gson.toJson(easyFrenchLevels, writer);
            System.out.println("Fichier JSON FR facile généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON facile : " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("easyLevelsEN.json")) {
            gson.toJson(easyEnglishLevels, writer);
            System.out.println("EN JSON file generated successfully!");
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
