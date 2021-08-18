import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) {

        int[] x = {4, 6, 2, 8, 1, 3, 9, 0};
        int[] y = {5, 7, 1, 3, 6};

        Map<Integer, Integer> m = new HashMap<>();

        for (int j : x) {
            m.put(j, j);
        }

        List<Integer> results = new ArrayList<>();

        for (int i : y) {
            if (m.containsKey(i)) {
                results.add(i);
                m.remove(i);
            }
        }

        System.out.println(results);
    }
}
