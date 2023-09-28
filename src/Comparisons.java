import java.io.*;
import java.util.Random;

    public class Comparisons {

        private static String csv = "";

        public static void main(String[] args) {
            System.out.println();

            int[] N_values =  {100, 250, 500};

            for (int N: N_values)
                for (int i = 1; i <= 10; i++)
                    writer(N, i);
            
            int i = 0;
            double[] unsorted_avgs = new double[3];
            Greedy.setUseHeapSort(false);
            for (int N : N_values)
                unsorted_avgs[i++] = calcAverage(N);


            i = 0;
            double[] sorted_avgs = new double[3];
            Greedy.setUseHeapSort(true);
            for (int N : N_values)
                sorted_avgs[i++] = calcAverage(N);


            System.out.println("------------------------------------------");
            for (int j=0; j< N_values.length; j++) {
                System.out.println("Average makespan for "+N_values[j]+" tasks");
                System.out.println("\tUnsorted :   "+unsorted_avgs[j]);
                System.out.println("\tSorted   :   "+sorted_avgs[j]);
                System.out.println();
            }

            dataToCSV();

        }

        private static void writer(int N, int j) {
            try {
                Random r = new Random();

                FileWriter fw = new FileWriter("N_"+N+"_"+j+".txt");
                BufferedWriter buff = new BufferedWriter(fw);

                buff.write((int)Math.floor(Math.sqrt(N))+"\n");
                buff.write(N+"\n");
                for (int i = 1; i <= N; i++) {
                    buff.write(r.nextInt(9999)+" ");
                    buff.write(r.nextInt(80)+"\n");
                }

                buff.close();
                fw.close();
            }
            catch (IOException ex) {
                System.err.println("\n\t\tError: file could not be written!\n");
            }
        }

        private static double calcAverage(int N) {
            String name;

            int[] listOfMakespans = new int[10];

            int sum = 0;

            for (int i = 1; i<=10; i++) {
                name = "N_"+N+"_"+i+".txt";

                System.out.println("Tasks: "+N+"  |  File: "+i+"  |  Sorted: "+Greedy.getUseHeapSortState());
                listOfMakespans[i-1] = Greedy.greedy(name);

                sum += listOfMakespans[i-1];

                csv += name+", "+(int)Math.floor(Math.sqrt(N))
                        +", "+N+", "+listOfMakespans[i-1]
                        +", "+Greedy.getUseHeapSortState()+ "\n";
            }
            return (double) sum / 10;
        }

        private static void dataToCSV() {
            try {
                FileWriter fw = new FileWriter("data.csv");
                BufferedWriter buff = new BufferedWriter(fw);

                buff.write("File, M, N, Makespan, Sorted"+"\n");
                buff.write(csv);

                buff.close();
                fw.close();
            }
            catch (IOException ex) {
                System.err.println("\n\t\tError: file could not be written!\n");
            }
        }
    }