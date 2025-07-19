import java.util.Comparator;

public class Heap<T> implements PriorityQueue<T> {
    private T[] heap;
    private int size;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
        size = 0;
        heap = (T[]) new Object[16];
    }

    public Heap(Comparator<T> comparator, boolean maxHeap) {
        this(comparator);
        if (maxHeap) {
            this.comparator = this.comparator.reversed();
        }
    }

    @Override
    public void offer(T item) {
        if (size == heap.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[2 * size];
            for (int i = 0; i < size; i++) {
                newArray[i] = heap[i];
            }
            heap = newArray;
        }
        heap[size] = item;
        bubbleUp(size);
        size++;
    }

    private int getParentIdx(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T copy = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = copy;
    }

    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }
        int result = comparator.compare(heap[index], heap[getParentIdx(index)]);

        if (result < 0) {
            swap(index, getParentIdx(index));
            bubbleUp(getParentIdx(index));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (size == 0) return null;
        return heap[0];
    }

    @Override
    public T poll() {
        if (size == 0)
            return null;
        T out = heap[0];
        swap(0, size - 1);
        heap[size - 1] = null;
        size--;
        bubbleDown(0);
        return out;
    }

    private void bubbleDown(int index) {
        int leftIdx = getLeftChildIdx(index);
        int rightIdx = getRightChildIdx(index);
        int smallestIdx = index;

        if (leftIdx < size && comparator.compare(heap[leftIdx], heap[smallestIdx]) < 0) {
            smallestIdx = leftIdx;
        }
        if (rightIdx < size && comparator.compare(heap[rightIdx], heap[smallestIdx]) < 0) {
            smallestIdx = rightIdx;
        }

        if (smallestIdx != index) {
            swap(index, smallestIdx);
            bubbleDown(smallestIdx);
        }
    }

    private int getLeftChildIdx(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIdx(int index) {
        return 2 * (index + 1);
    }

    @Override
    public void updatePriority(T item) {
        if (item == null) return;
        
        // Find the item in the heap
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(item)) {
                index = i;
                break;
            }
        }
        
        // If item not found, return
        if (index == -1) return;
        
        // Check if we need to bubble up
        int parentIdx = getParentIdx(index);
        if (index > 0 && comparator.compare(heap[index], heap[parentIdx]) < 0) {
            bubbleUp(index);
        } 
        else {
            bubbleDown(index);
        }
    }

    @Override
    public String toString() {
        int depth = 0;
        return toString(0, depth);
    }

    private String toString(int idx, int depth) {
        if (idx >= size) {
            return "";
        }
        String left = toString(getLeftChildIdx(idx), depth + 1);
        String right = toString(getRightChildIdx(idx), depth + 1);

        String myself = "\t".repeat(depth) + heap[idx] + "\n";
        return right + myself + left;
    }
}