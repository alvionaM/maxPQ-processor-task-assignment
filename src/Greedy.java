import java.io.*;
import java.util.*;

public class Greedy {
    private static boolean taskNumAccordance = true;
    private static int numOfProcessors;
    private static boolean useHeapSort = false;

    public static void main(String[] args){
        System.out.println();
        String filename = args[0];
        greedy(filename);
    }

    public static int greedy(String fileName){
        int makespan = -1;
        Task[] allTasks = input(fileName);

        if(!taskNumAccordance)
            System.out.println("\t\tError: Number of declared tasks not in accordance with the actual defined tasks!\n");

        if(allTasks!=null){
            //constructing priority queue of processors
            MaxPQ<Processor> priorityQ = new MaxPQ<>(numOfProcessors);
            for(int i=0; i<numOfProcessors; i++){
                priorityQ.insert(new Processor());
            }

            if (useHeapSort)
                Sort.heapsort(allTasks);

            greedyCore(allTasks, priorityQ);
            makespan = output(priorityQ, allTasks.length);

        }
        taskNumAccordance = true;

        return makespan;
    }

    private static Task[] input(String fileName) {

        int numOflines = totalNumOfLines(fileName);
        if (numOflines < 0)
            return null;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(fr);

            StringTokenizer lineTokens;
            String token;
            String line;
            boolean eof = false;
            numOfProcessors = -1;
            int numOfTasks = -1;
            int taskId;
            int taskTime;

            while (!eof && (numOfProcessors==-1 || numOfTasks==-1 )) {
                line = buff.readLine();

                if (line == null) {
                    eof = true;

                } else {
                    lineTokens = new StringTokenizer(line);

                    if (lineTokens.countTokens() == 0)
                        continue;

                    token = lineTokens.nextToken();
                    if (numOfProcessors == -1)
                        numOfProcessors = Integer.parseInt(token);
                    else
                        numOfTasks = Integer.parseInt(token);
                }
            }

            if(numOfTasks != numOflines-2) {
                taskNumAccordance = false;
                return null;
            }
            //Array of tasks
            Task[] allTasks = new Task[numOfTasks+1];
            allTasks[0] = null;
            int t = 1;  //index of tasks array

            while (!eof) {
                line = buff.readLine();

                if (line == null) {
                    eof = true;

                } else {
                    lineTokens = new StringTokenizer(line);

                    token = lineTokens.nextToken();
                    taskId = Integer.parseInt(token);

                    token = lineTokens.nextToken();
                    taskTime = Integer.parseInt(token);

                    //executing task
                    Task task = new Task(taskId, taskTime);

                    allTasks[t] = task;
                    t++;
                }
            }
            return allTasks;

        }catch (IOException ex) {
            System.err.println("\n\t\tError: file could not be parsed!\n");
            return null;
        }
    }

    private static int totalNumOfLines(String fileName){
        int num = 0;
        String line;
        boolean eof = false;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(fr);

            while (!eof) {
                line = buff.readLine();
                num += 1;
                if (line == null) {
                    num -= 1;
                    eof = true;
                }
            }
            buff.close();
            fr.close();
            return num;

        }catch (IOException ex){
            System.err.println("\n\t\tError: file could not be parsed!\n");
            return -1;
        }
    }

    public static void greedyCore(Task[] alltasks, MaxPQ<Processor> priorityQ){
        for(int t=1; t<alltasks.length; t++) {
            Processor p = priorityQ.getmax();
            p.insertTask(alltasks[t]);
            priorityQ.insert(p);
        }
    }

    private static int output(MaxPQ<Processor> priorityQ, int numOfTasks){
        int makespan;
        int N = priorityQ.size();

        String s = "";
        for(int i=1; i<=N-1; i++)
            s += priorityQ.getmax()+"\n";

        Processor pr = priorityQ.getmax();
        s += pr+"\n";
        makespan = pr.getActiveTime();

        if(numOfTasks < 50) 
            System.out.println(s);
        System.out.println("\t==> Makespan = "+makespan+"\n\n");
        return makespan;
    }

    public static void setUseHeapSort(boolean sort) {
        useHeapSort = sort;
    }

    public static boolean getUseHeapSortState() { return useHeapSort; }
}
