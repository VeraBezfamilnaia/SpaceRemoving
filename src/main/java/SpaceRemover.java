public class SpaceRemover {
    private static final String OLD_VALUE = " ";
    private static final String NEW_VALUE = "";

    public static String remove(String line) {
        return line.replace(OLD_VALUE, NEW_VALUE);
    }
}
