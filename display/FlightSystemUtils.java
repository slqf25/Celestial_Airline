package display;

public class FlightSystemUtils 
{
    public static void printBoxed(String message) 
    {
        int len = message.length();
        String border = "╭" + "─".repeat(len + 2) + "╮";
        String content = "│ " + message + " │";
        String bottom = "╰" + "─".repeat(len + 2) + "╯";

        System.out.println(border);
        System.out.println(content);
        System.out.println(bottom);
    }
}
