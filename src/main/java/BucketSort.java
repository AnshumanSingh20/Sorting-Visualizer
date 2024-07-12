import java.util.*;

public class BucketSort implements Panel.SortingAlgorithm {

    private final Panel visualizer;
    private final boolean test;

    public BucketSort() {
        test = true;
        this.visualizer = null;
    }

    public BucketSort(Panel visualizer) {
        test = false;
        this.visualizer = visualizer;
    }

    @Override
    public void sort(int[] list) {
        int n = list.length;

        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int value : list) {
            int index = value * n / (Arrays.stream(list).max().orElse(1) + 1);
            buckets.get(index).add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                list[index++] = value;

                if (!test && visualizer != null) {
                    if (visualizer.threadInterrupted()) return;
                    visualizer.animate(index - 1, 2500000); 
                }
            }
        }

        if (!test && visualizer != null) {
            if (visualizer.threadInterrupted()) return;
            visualizer.checkSorted(list);
            visualizer.buttonsEnabled(true);
        }
    }
}
