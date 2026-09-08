import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in); // вызов сканнера тут, т.к. он нужен тут, но public static для использования в классе Game, чтобы не плодить сущности

    public static void main(String[] args){
        mainMenu();
    }

    public static String[] mainmenu = { // кадр главного меню выведен отдельно для более комфортного редактирования вся работа выполнена не "лишь бы работало", а, типа, с заготовкой под "возможное" будущее
            "//=========================\\\\",
            "||         ВИСЕЛИЦА        ||",
            "\\\\=========================//",
            "1 - Новая игра",
            "2 - Выход"
    };

    static void mainMenu(){
        System.out.println(String.join("\n", mainmenu));
        identifyInput();
    }

    static void identifyInput() {
        String input = scanner.nextLine();

        if (input.equals("2")) {
            System.out.println("Пока!");
            System.exit(0);
        } else if (!input.equals("1")) {
            System.out.println("введите 1 или 2!");
            identifyInput();
        } else {
            Game game = new Game();
            game.start();
        }
    }
}