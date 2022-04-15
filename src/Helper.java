import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Helper {

    static ArrayList<String> words = new ArrayList<>();
    static String s;

    public Helper() {

    }
    public static void main(String[] args) throws IOException {
        System.out.println("type d for danish or e for english dictionary");
        Scanner sc = new Scanner(System.in);
        String lang = sc.next().toUpperCase(Locale.ROOT);

        //Load engelsk udgave
        if (lang.startsWith("E")) {
            startGame("English.txt");
        }
        //Load dansk udgave
        else if (lang.startsWith("D")) {
            startGame("Danish.txt");
        }
        //Luk programmet, hvis hverken dansk eller engelsk
        else {
            System.exit(-1);
        }

        while (true) {
            System.out.println("Write grey'A-Å'+ to mark a/multiple grey character(s)");
            System.out.println("Write yellow'A-Å'+ to mark a/multiple yellow character(s)");
            System.out.println("Write green'A-Å'+ to mark a/multiple green character(s)");
            s = sc.next().toUpperCase(Locale.ROOT);
            if (s.startsWith("GREY")) {
                for (int i = 4; i < s.length(); i++) {
                    int finalI = i;
                    words.removeIf(w -> w.contains("" + s.charAt(finalI)));
                }
            } else if (s.startsWith("YELLOW")) {
                processColor("yellow", 7);
            } else if (s.startsWith("GREEN")) {
                processColor("green", 6);
            } else {
                System.exit(-1);
            }
            if (words.size() < 21) {
                System.out.println(words);
            }
        }
    }

    public static void processColor(String color, int indexStart) {
        for (int i = indexStart; i < s.length(); i += 2) {
            int index = Integer.parseInt(String.valueOf(s.charAt(i))) - 1;
            int finalI = i - 1;
            if(color.equals("green")){
                words.removeIf(w -> (w.charAt(index) != (s.charAt(finalI))));
            }
            else if(color.equals("yellow")){
                words.removeIf(w -> w.charAt(index) == s.charAt(finalI) || !w.contains("" + s.charAt(finalI)));
        }}
    }

    public static void startGame(String fileName) throws IOException {
        String line;
        try (InputStream in = Helper.class.getResourceAsStream("/" + fileName)) {
            assert in != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                while ((line = reader.readLine()) != null) {
                    if(line.length() == 5){
                        if(line.contains("Ø")){
                            System.out.println(line);
                        }
                        words.add(line);
                    }
                }
            }
        }

    }
}
