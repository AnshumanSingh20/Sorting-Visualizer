public class CycleSort implements Panel.SortingAlgorithm {

    private final Panel visualizer;
    private final boolean test;

    public CycleSort() {
        test = true;
        this.visualizer = null;
    }

    public CycleSort(Panel visualizer) {
        test = false;
        this.visualizer = visualizer;
    }

    @Override
    public void sort(int[] list) {
        int n = list.length;

        for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            int item = list[cycleStart];

            int pos = cycleStart;
            for (int i = cycleStart + 1; i < n; i++) {
                if (list[i] < item) {
                    pos++;
                }
            }

            if (pos == cycleStart) {
                continue;
            }

            while (item == list[pos]) {
                pos++;
            }

            if (pos != cycleStart) {
                int temp = item;
                item = list[pos];
                list[pos] = temp;

                if (!test && visualizer != null) {
                    if (visualizer.threadInterrupted()) return;
                    visualizer.animate(pos, 2500000); 
                }
            }

            while (pos != cycleStart) {
                pos = cycleStart;
                for (int i = cycleStart + 1; i < n; i++) {
                    if (list[i] < item) {
                        pos++;
                    }
                }

                while (item == list[pos]) {
                    pos++;
                }

                if (item != list[pos]) {
                    int temp = item;
                    item = list[pos];
                    list[pos] = temp;

                    if (!test && visualizer != null) {
                        if (visualizer.threadInterrupted()) return;
                        visualizer.animate(pos, 2500000); 
                    }
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
