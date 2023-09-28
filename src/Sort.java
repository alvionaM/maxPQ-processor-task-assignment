public class Sort{

    public static void heapsort(Task[] array){
        int N = array.length-1;
        for(int k=N/2; k>=1; k--)
            sink(array, k, N);  //convert array to min-heap

        while (N>1){
            exch(array, 1, N);
            N--;
            sink(array, 1, N);
        }
    }

    private static void sink(Task[] array, int k, int N) {
        while (2 * k <= N) {
            int c1 = 2 * k;
            int c2 = c1;
            if (c1 + 1 <= N)
                c2 = c1 + 1;

            int minC = greater(array, c1, c2) ? c2 : c1;

            if (greater(array, k, minC)) {
                exch(array, k, minC);     //it is a min-heap, so root contains the element with the smallest key
                k = minC;
            } else
                break;
        }
    }

    private static boolean greater(Task[] array, int i, int j) {
        return array[i].getTime() > array[j].getTime();
    }

    private static void exch(Task[] array, int i, int j) {
        Task temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
