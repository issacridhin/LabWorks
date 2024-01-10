//First Part - Coin Change
import java.util.*;

public class CoinChange {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of Array: ");
        int n = input.nextInt();
        int[] coinsArr = new int[n];
        System.out.println("Enter the sum value: ");
        int sum = input.nextInt();
        Thread t1 = new Thread(() -> {

            synchronized (coinsArr) {

                System.out.print("Enter the coins value: ");
                for (int i = 0; i < n; i++) {
                    coinsArr[i] = input.nextInt();
                }

            }
        });
        List<List<Integer>> v = new ArrayList<>();
        Thread t2 = new Thread(() -> {
            synchronized (coinsArr) {
                combiFunction(coinsArr, sum, 0, new ArrayList<>(), v);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        for (List<Integer> combination : v) {
            System.out.println(combination);
        }

    }

    static void combiFunction(int[] array, int target, int start, List<Integer> current,
            List<List<Integer>> result) {
        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < array.length; i++) {

            if (array[i] <= target) {
                current.add(array[i]);
                combiFunction(array, target - array[i], i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}











