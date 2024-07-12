import java.util.*;
public class CountingSort implements Panel.SortingAlgorithm {

    private final Panel visualizer;
    private final boolean test;

    public CountingSort() {
        test = true;
        this.visualizer = null;
    }

    public CountingSort(Panel visualizer) {
        test = false;
        this.visualizer = visualizer;
    }

    @Override
    public void sort(int[] list) {
        int n = list.length;
        
        int max = Arrays.stream(list).max().orElse(0);

        int[] count = new int[max + 1];
        
        for (int value : list) {
            count[value]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            output[count[list[i]] - 1] = list[i];
            count[list[i]]--;

            if (!test && visualizer != null) {
                if (visualizer.threadInterrupted()) return;
                visualizer.animate(i, 2500000); 
            }
        }

        System.arraycopy(output, 0, list, 0, n);

        if (!test && visualizer != null) {
            if (visualizer.threadInterrupted()) return;
            visualizer.checkSorted(list);
            visualizer.buttonsEnabled(true);
        }
    }
}
