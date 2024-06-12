import java.util.*;
import java.util.stream.*;

public class Permutate {

    public static void main(String [] args) {

        System.out.printf("Hello Permutate Solution #1%n");

        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java Permutate <n> <p>%n");
            System.out.printf("Where <n> is the number of values%n");
            return;
        }

        if (args != null && args.length != 1) {
            System.out.printf("Expect input values n; refer to usage%n");
            return;
        }

        String n = args[0];

        if (n == null) {
            System.out.printf("Expect non-null for input value n%n");
            return;
        }

        if (!n.matches("[0-9]+")) {
            System.out.printf("Expect all digits for input value n%n");
            return;
        }

        Permutate permutate = new Permutate(Integer.valueOf(n));

        int[][] permutations = permutate.solution();

        System.out.printf("Results%n");
        for (int i = 0; i < permutations.length; i++) {
            System.out.printf("%s%n", Arrays.toString(permutations[i]));
        }

    }

    private int n;

    public Permutate(int n) {
        this.n = n;
    }

    public int[][] solution() {

        List<int[]> tuples = new ArrayList<>();

        for (int i  = 0; i <= n - 4; i++) {
            for (int j = i + 1; j <= n - 3; j++) {
                for (int k = j + 1; k <= n - 2; k++) {
                    for (int l = k + 1; l <= n - 1; l++) {
                        System.out.printf("candidate tuple {%d, %d, %d, %d}%n", i, j, k, l);
                        int [] tuple = new int [] {i, j, k, l};
                        tuples.add(tuple);
                    }
                }
            }
        }

        int [][] results = new int[tuples.size()][];

        for (int index = 0; index < tuples.size(); index++) {
            results[index] = tuples.get(index);
        }

        return results;
    } 

}
