import java.util.Scanner;

public class InputReader {
    private static final String REQUIREMENT = "Введите строку";
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        System.out.println(REQUIREMENT);
        return scanner.nextLine();
    }
}
