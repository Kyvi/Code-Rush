import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hint.Hint;
import hint.HintGenerator;
import model.Code;
import model.Level;
import util.RandomGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int nbLevels = 200;
        Level[] levels = new Level[nbLevels];
        for(int i=0; i<nbLevels; i++){

            RandomGenerator randomGenerator = new RandomGenerator(i+1);
            HintGenerator hintGenerator = new HintGenerator(randomGenerator);

            Set<Hint> hints = hintGenerator.generateHints();
            List<String> levelHints = hints.stream().map(Hint::showHint).collect(Collectors.toList());
            //levelHints.add(0,"- Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            /*System.out.println("Voici les indices :");
            System.out.println("Le code est de longueur " + Code.CODE_LENGTH + ", et contient des chiffres de 0 a 9.");
            for(Hint hint : hints){
                System.out.println("    - " + hint.showHint());
            }*/

            Code code = hintGenerator.getSolutionCode();

            Level level = new Level("Niveau " + (i+1), levelHints, code.toString());
            System.out.println("Niveau " + (i+1) + " généré avec succès !");
            levels[i] = level;

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
        try (FileWriter writer = new FileWriter("levels.json")) {
            gson.toJson(levels, writer);
            System.out.println("Fichier JSON généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
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
