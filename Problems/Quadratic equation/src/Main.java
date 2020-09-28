import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        scanner.close();


        double d = b * b - 4 * a * c;
        double sqrtValue = Math.sqrt(Math.abs(d));

        double r1 = (-b - sqrtValue) / (2 * a);
        double r2 = (-b + sqrtValue) / (2 * a);
        if (r1 <= r2) {
            System.out.println(r1 + " " + r2);
        } else {
            System.out.println(r2 + " " + r1);
        }


    }
}