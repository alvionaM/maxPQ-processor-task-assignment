public interface PQInterface<T> {

        boolean isEmpty();       //check if the queue is empty

        int size();              //return the number of active elements in the queue

        void insert(T x);        //insert the object x to the queue

        T max();                 //return without removing the object with the highest priority

        T getmax();              //remove and return the object with the highest priority*/
}
