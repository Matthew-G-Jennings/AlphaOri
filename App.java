package AlphaOri;

import java.io.*;
import java.util.*;

/**
 * Class to read a specified file and determine what words occur in it.
 *
 * @author Matthew Jennings
 */
public class App {
    public static SortedSet<String> dict = new TreeSet<String>();

    /**
     * Opens a file and creates a Scanner from it. Sends each token from the Scanner
     * off to determine if it is a valid word.
     *
     * @param args filename
     */
    public static void main(String[] args) {
        String fileName = "";
        File file;
        Scanner scanner = new Scanner("Hello, I am an initialized Scanner");
        try {
            file = new File(args[0]);
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Failed to open file " + args[0]);
            System.exit(1);
        }

        while (scanner.hasNextLine()) {
            Scanner lineScan = new Scanner(scanner.nextLine());
            while (lineScan.hasNext()) {
                String token = lineScan.next();
                checkToken(token);
            }
        }

        for (String s : dict) {
            System.out.println(s);
        }

    }

    /**
     * Calls methods to determine if a token is valid, if it is puts it in the
     * dictionary.
     *
     * @param token String, token to check if is a word
     */
    public static void checkToken(String token) {
        token = stripQuotes(token);
        token = stripPunct(token);
        if (token.length() > 0) {
            token = fixCase(token);
        } else {
            return;
        }
        // valid tokens should no longer be present
        if (noErrors(token)) {
            dict.add(token);
        }
    }

    /**
     * Removes the quote at the start and/or end of a given word.
     *
     * Does not perform any additional error checking.
     *
     * @param token String that may or may not have quotes at the start and/or end
     * @return String without those quotes
     */
    public static String stripQuotes(String token) {
        String result = "";
        for (int i = 0; i < token.length(); i++) {
            if ((i == 0 || i == token.length() - 1) && token.charAt(i) == '"') {
                continue;
            } else {
                result += token.charAt(i);
            }
        }

        return result;
    }

    /**
     * Removes the final character, if it's . , ; : ? !
     *
     * Requires any termination quotes to have been removed already.
     *
     * @param token String to strip the punctuation from.
     * @return String without punctuation.
     */
    public static String stripPunct(String token) {
        String result = "";
        for (int i = 0; i < token.length(); i++) {
            if (i == token.length() - 1 && (token.charAt(i) == '.' || token.charAt(i) == ',' || token.charAt(i) == ';'
                    || token.charAt(i) == ':' || token.charAt(i) == '?' || token.charAt(i) == '!')) {
                continue;
            } else {
                result += token.charAt(i);
            }
        }
        return result;
    }

    /**
     * If the first character is upper case, makes it lower case.
     *
     * @param token String to check
     * @return String with lowercase conversion on first char, if it was uppercase.
     */
    public static String fixCase(String token) {
        String result = "";
        if (Character.isUpperCase(token.charAt(0))) {
            result += Character.toLowerCase(token.charAt(0));
            result += token.substring(1);
        } else {
            return token;
        }
        return result;
    }

    /**
     * Checks if a given token is a valid word
     *
     * @return true if it is, false if not.
     */
    public static boolean noErrors(String token) {
        boolean aposfound = false;
        for (int i = 0; i < token.length(); i++) {
            if (Character.isAlphabetic(token.charAt(i)) && Character.isLowerCase(token.charAt(i))) {
                continue;
            } else if (token.charAt(i) == '\'' && !aposfound && i > 0) {
                aposfound = true;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
