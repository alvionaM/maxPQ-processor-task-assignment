import java.util.NoSuchElementException;

public class MaxPQ<T extends Comparable<T>> implements PQInterface<T> {
    private T[] heap;
    private int N;

    public MaxPQ(int maxItems) {
        heap = (T[]) (new Comparable[maxItems]);
        N = 0;
    }

    public boolean isEmpty() { //check if the queue is empty
        return N == 0;
    }

    public int size() { //return the number of active elements in the queue
        return N;
    }

    //insert the object x to the queue
    public void insert(T x) {
        if (N >= 0.75 * (heap.length - 1))
            resize();

        N++;
        heap[N] = x;
        swim(N);
    }

    //return without removing the object with the highest priority
    public T max() throws NoSuchElementException {
        if (!isEmpty())
            return heap[1];
        throw new NoSuchElementException();
    }

    //remove and return the object with the highest priority
    public T getmax() throws NoSuchElementException {
        if (!isEmpty()) {
            exch(1, N);
            sink(1, N - 1);
            T t = heap[N];
            N--;
            return t;
        } else
            throw new NoSuchElementException();
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k, int N) {
        while (2 * k <= N) {
            int c1 = 2 * k;
            int c2 = c1;
            if (c1 + 1 <= N) {
                c2 = c1 + 1;
            }
            int maxC = less(c1, c2) ? c2 : c1;

            if (less(k, maxC)) {
                exch(k, maxC);
                k = maxC;
            } else
                break;
        }
    }

    private boolean less(int i, int j) {
        switch ((heap[i]).compareTo(heap[j])) {
            case -1:
                return true;
            default: //cases 0,1
                return false;
        }
    }

    private void exch(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        T[] temp;

        temp = (T[]) (new Comparable[2 * heap.length - 1]);
        for (int i = 0; i <= N; i++)
            temp[i] = heap[i];
        heap = temp;
    }
}

