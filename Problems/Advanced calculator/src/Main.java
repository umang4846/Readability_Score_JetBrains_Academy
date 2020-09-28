/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        switch (operator) {
            case "MAX":
                int max = Integer.MIN_VALUE;
                for (int i = 1; i < args.length; i++) {
                    if (Integer.parseInt(args[i]) > max) {
                        max = Integer.parseInt(args[i]);
                    }
                }
                System.out.println(max);
                break;
            case "MIN":
                int min = Integer.MAX_VALUE;
                for (int i = 1; i < args.length; i++) {
                    if (Integer.parseInt(args[i]) < min) {
                        min = Integer.parseInt(args[i]);
                    }
                }
                System.out.println(min);
                break;
            case "SUM":
                int sum = 0;
                for (int i = 1; i < args.length; i++) {
                    sum += Integer.parseInt(args[i]);
                }
                System.out.println(sum);
                break;
            default:
                break;
        }
    }
}