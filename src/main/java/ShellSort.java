public class ShellSort implements Panel.SortingAlgorithm {

    private final Panel visualizer;
    private final boolean test;

    public ShellSort() {
        test = true;
        this.visualizer = null;
    }

    public ShellSort(Panel visualizer) {
        test = false;
        this.visualizer = visualizer;
    }

    @Override
    public void sort(int[] list) {
        int n = list.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            
            for (int i = gap; i < n; i++) {
                int temp = list[i];
                int j;
                for (j = i; j >= gap && list[j - gap] > temp; j -= gap) {
                    list[j] = list[j - gap];

                    if (!test && visualizer != null) {
                        if (visualizer.threadInterrupted()) return;
                        visualizer.animate(j, 2500000); 
                    }
                }
                list[j] = temp;

                if (!test && visualizer != null) {
                    if (visualizer.threadInterrupted()) return;
                    visualizer.animate(j, 2500000); 
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
