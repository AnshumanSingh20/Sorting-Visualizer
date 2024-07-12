public class HeapSort implements Panel.SortingAlgorithm {

    private final Panel visualizer;
    private final boolean test;

    public HeapSort() {
        test = true;
        this.visualizer = null;
    }

    public HeapSort(Panel visualizer) {
        test = false;
        this.visualizer = visualizer;
    }

    @Override
    public void sort(int[] list) {
        int n = list.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(list, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;

            heapify(list, i, 0);

            if (!test && visualizer != null) {
                if (visualizer.threadInterrupted()) return;
                visualizer.animate(i, 2500000); 
            }
        }

        if (!test && visualizer != null) {
            if (visualizer.threadInterrupted()) return;
            visualizer.checkSorted(list);
            visualizer.buttonsEnabled(true);
        }
    }

   
    private void heapify(int[] arr, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);

            if (!test && visualizer != null) {
                if (visualizer.threadInterrupted()) return;
                visualizer.animate(i, 2500000); 
            }
        }
    }
}

