import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    static int loadCounter = 0;
    static List<String> words;
    static String word;
    static char[] guessed;

    void start() {
        if (loadCounter == 0) {
            try {
                words = Files.readAllLines(Path.of("words.txt"));
            } catch (IOException e) {
                System.out.println("Ошибка загрузки words.txt");
                return;
            }
            Collections.shuffle(words);
        }
        word = words.get(loadCounter);
        gameloop();
    }

    static String[][] frames = { // тот де принцип, что и с главным меню
            {
                    "//======",
                    "||     |",
                    "||",
                    "||",
                    "||"
            },
            {
                    "//======",
                    "||     |",
                    "||     O",
                    "||",
                    "||"
            },
            {
                    "//======",
                    "||     |",
                    "||     o",
                    "||     |",
                    "||"
            },
            {
                    "//======",
                    "||     |",
                    "||     o",
                    "||    /|",
                    "||"
            },
            {
                    "//======",
                    "||     |",
                    "||     o",
                    "||    /|\\",
                    "||"
            },
            {
                    "//======",
                    "||     |",
                    "||     o",
                    "||    /|\\",
                    "||    /"
            },
            {
                    "//======",
                    "||     |",
                    "||     o",
                    "||    /|\\",
                    "||    / \\"
            }
    };

    static void gameloop() {
        guessed = new char[word.length()];
        Arrays.fill(guessed, '_');
        int faults = 0;

        while (faults < 6) {
            for (String line : frames[faults]) {
                System.out.println(line);
            }
            for (char c : guessed) {
                System.out.print(" " + c);
            }
            if (guessing() == 1){
                faults++;
                if (faults == 6){
                    for (String line : frames[6]) {
                        System.out.println(line);
                        System.out.println("Игра окончена!");
                        System.out.println("Слово: " + word);
                        break;
                    }
                }
            } else {
                boolean complite = true;
                for (char c : guessed){
                    if (c == '_'){
                        complite = false;
                        break;
                    }
                }
                if (complite){
                    System.out.println("Вы выиграли!");
                    break;
                }
            }

        }

    }

    static int guessing() {
        System.out.println("\nУгадай букву:");
        String input = Main.scanner.nextLine().toLowerCase();

        if (input.length() != 1) {
            System.out.println("Угадай БУКВУ, а не СТРОКУ!");
            guessing();
        } else if (input.matches("\\d")) {
            System.out.println("Угадай БУКВУ, а не ЧИСЛО!");
            guessing();
        } else {
            int correct = 0;
            char letter = input.charAt(0);
            for (int i = 0; i < word.length(); i++) {
                if (letter == word.charAt(i)) {
                    guessed[i] = letter;
                    correct = 1;
                    System.out.println("Верно!");
                }
            }
            if (correct == 0) {
                System.out.println("Ошибка!");
                return 1;
            }
        }
        return 0;
    }
}