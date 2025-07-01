package problems;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternSyntaxChecker {

    // Check if a given string is a valid regular expression pattern.
    // Do check based on Exception, i.e. PatternSyntaxException
    public static void run() {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        int counter = 0;
        System.out.println(testCases);
        while (testCases > 0) {
            String pattern = in.nextLine();
            counter++;
            try {
                Pattern.compile(pattern);
                System.out.println("Valid");
            } catch (PatternSyntaxException e) {
                System.out.println("Invalid");
            }
            if (counter == testCases) {
                break;
            }
        }
    }

}
