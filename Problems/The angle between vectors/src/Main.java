import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int vx = scanner.nextInt();
        int vy = scanner.nextInt();
        int ux = scanner.nextInt();
        int uy = scanner.nextInt();

        System.out.println(Math.toDegrees(Math.acos((ux * vx + vy * uy) / (Math.hypot(ux, uy) * Math.hypot(vx, vy)))));
    }

}
