import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private List<Integer> heap;
    private int childrenCount;

    public PowerOfTwoMaxHeap(int power) {
        if (power < 0) {
            throw new IllegalArgumentException("Power must be non-negative.");
        }
        this.heap = new ArrayList<>();
        this.childrenCount = (int) Math.pow(2, power);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.size() == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        int max = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        if (heap.size() > 0) {
            heapifyDown(0);
        }
        return max;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / childrenCount;
            if (heap.get(index) > heap.get(parentIndex)) {
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int largest = index;
            for (int i = 1; i <= childrenCount; i++) {
                int childIndex = childrenCount * index + i;
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                    largest = childIndex;
                }
            }
            if (largest == index) {
                break;
            }
            Collections.swap(heap, index, largest);
            index = largest;
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); // 2^2 = 4 children per node
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(20);
        heap.insert(3);
        heap.insert(8);
        heap.insert(17);

        while (!heap.isEmpty()) {
            System.out.println(heap.popMax());
        }
    }
}
