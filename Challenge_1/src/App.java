import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int[] testArray = { 1, 1, 2, 2, 4, 4, -6, -6, 14, 14, 8, 0, 0 };
        System.out.println(findUniqueTriplets(testArray, 8));
    }

    public static List<List<Integer>> findUniqueTriplets(int[] numbers, int sum) {
        List<List<Integer>> solution = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1])
                continue;
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                int currSum = numbers[i] + numbers[left] + numbers[right];
                if (currSum == sum) {
                    solution.add(Arrays.asList(numbers[i], numbers[left], numbers[right]));
                    while (left < right && numbers[left] == numbers[left + 1]) {
                        left++;
                    }
                    while (left < right && numbers[right] == numbers[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (currSum > sum)
                    right--;
                else
                    left++;
            }
        }
        return solution;
    }
}
