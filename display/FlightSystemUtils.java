package display;

public class FlightSystemUtils {

    private static final int BOX_WIDTH = 50;

    public static void printBoxed(String message) {
        String[] lines = message.split("\n");
        int width = BOX_WIDTH;

        for (String line : lines) {
            width = Math.max(width, line.length() + 4);
        }

        String border = "╔" + "═".repeat(width - 2) + "╗";
        String bottom = "╚" + "═".repeat(width - 2) + "╝";

        System.out.println(border);
        for (String line : lines) {
            System.out.printf("║ %-"+(width - 4)+"s ║%n", line);
        }
        System.out.println(bottom);
    }
}
