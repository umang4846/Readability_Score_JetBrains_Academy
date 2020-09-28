import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Siblings {

    public static boolean areSiblings(File f1, File f2) {
        // implement me
        return f1.getParent().equalsIgnoreCase(f2.getParent());

    }

    public static void main(String[] args) {
        String pathToHelloWorldJava = "C:\\Users\\HP-PC\\Downloads\\dataset_91069.txt";

        File file = new File(pathToHelloWorldJava);

        int yearBiggestVariation = 0;
        double biggestVariation = 0;
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            int previousYear = scanner.nextInt();
            double previousPop = Double.parseDouble(scanner.next().replaceAll(",", ""));
            biggestVariation = 0;
            yearBiggestVariation = 0;
            while (scanner.hasNext()) {
                int year = scanner.nextInt();
                double pop = Double.parseDouble(scanner.next().replaceAll(",", ""));
                if ((pop - previousPop) > biggestVariation) {
                    biggestVariation = pop - previousPop;
                    yearBiggestVariation = year;
                }
                previousPop = pop;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getLocalizedMessage());
        }
        System.out.println(yearBiggestVariation);
    }


}