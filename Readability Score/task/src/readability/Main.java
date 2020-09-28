package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int getNumCharacters(String content) {
        int numCharacters = 0;
        for (int i = 0; i < content.length(); i++) {
            char nowChar = content.charAt(i);
            if (!Character.isWhitespace(nowChar)) {
                numCharacters++;
            }
        }
        return numCharacters;
    }

    public static void main(String[] args) {
        File file = new File(args[0]);
        // File file = new File("C:\\Users\\HP-PC\\Downloads\\textfile.txt");
        try (Scanner scan = new Scanner(file)) {

            StringBuilder content = new StringBuilder();
            while (scan.hasNextLine()) {
                content.append(scan.nextLine()).append("\n");
            }
            content = new StringBuilder(content.substring(0, content.length() - 1));
            System.out.println("The text is:\n" + content + "\n");

            int numWords = content.toString().split("[ \n]").length;
            System.out.println("Words: " + numWords);

            int numSentences = content.toString().split("[.?!]").length;
            System.out.println("Sentences: " + numSentences);

            int numCharacters = getNumCharacters(content.toString());
            System.out.println("Characters: " + numCharacters);

            int[] syllablesInfo = getNumSyllables(content.toString());
            System.out.println("Syllables: " + syllablesInfo[0]);
            System.out.println("Polysyllables: " + syllablesInfo[1]);

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
            String request = keyboard.next();
            keyboard.close();
            double totalScore = 0;

            System.out.println();

            if (request.equals("ARI") || request.equals("all")) {
                double ari = 4.71 * ((double) numCharacters / numWords) + 0.5 * ((double) numWords / numSentences) - 21.43;
                System.out.println("Automated Readability Index: " + ari + " (about " + getAge(ari) + " year olds).");
                totalScore += ari;
            }
            if (request.equals("FK") || request.equals("all")) {
                double fk = 0.39 * ((double) numWords / numSentences) + 11.8 * ((double) syllablesInfo[0] / numWords) - 15.59;
                System.out.println("Flesch–Kincaid readability tests: " + fk + " (about " + getAge(fk) + " year olds).");
                totalScore += fk;
            }
            if (request.equals("SMOG") || request.equals("all")) {
                double smog = 1.043 * Math.sqrt((double) syllablesInfo[1] * 30 / numSentences) + 3.1291;
                System.out.println("Simple Measure of Gobbledygook: " + smog + " (about " + getAge(smog) + " year olds).");
                totalScore += smog;
            }
            if (request.equals("CL") || request.equals("all")) {
                double cl = 0.0588 * ((double) numCharacters / numWords * 100) - 0.296 * ((double) numSentences / numWords * 100) - 15.8;
                System.out.println("Coleman–Liau index: " + cl + " (about " + getAge(cl) + " year olds).");
                totalScore += cl;
            }
            if (request.equals("all")) {
                System.out.print("\nThis text should be understood by " + getAge(totalScore / 4) + " year olds.");
            }

            keyboard.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public static int getAge(double score) {
        switch ((int) Math.ceil(score)) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
            case 14:
                return 24;
            default:
                if (score > 14) {
                    return 24;
                }
                throw new IllegalStateException("Unexpected value: " + (int) Math.ceil(score));
        }
    }

    public static int[] getNumSyllables(String content) {

        int numSyllables = 0;
        int numPolysyllables = 0;
        String[] words = content.split("[ \n]");

        for (String x : words) {

            int inThisWord = 0;

            boolean lastWasVowel = false;
            for (int i = 0; i < x.length(); i++) {
                String now = "" + x.charAt(i);
                if (now.matches("[aeiouy]")) {
                    if (!lastWasVowel) {
                        inThisWord++;
                        lastWasVowel = true;
                    }
                } else {
                    lastWasVowel = false;
                }
            }

            char finalLetter = 0; // and will stay that way if no letters
            for (int j = x.length() - 1; j >= 0; j--) {
                char nowLetter = x.charAt(j);
                if (Character.isLetter(nowLetter)) {
                    finalLetter = nowLetter;
                    break;
                }
            }

            if (finalLetter == 'e' || finalLetter == 'E') {
                inThisWord--;
            }

            if (inThisWord == 0) {
                inThisWord = 1;
            }
            if (inThisWord > 2) {
                numPolysyllables++;
            }
            numSyllables += inThisWord;

        }

        return new int[]{numSyllables, numPolysyllables};

    }
}