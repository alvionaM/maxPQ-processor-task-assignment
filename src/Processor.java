public class Processor implements Comparable<Processor> {
    private final int id;
    private final LinkedList<Task> processedTasks;

    private static int setId = 100;
    private int totalActiveTime = 0;

    public Processor() {
        id = setId;
        setId++;
        processedTasks = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public int getActiveTime() {
        return totalActiveTime;
    }

    public LinkedList<Task> getProcessedTasks() {
        return processedTasks;
    }

    public void insertTask(Task t) {
        processedTasks.insertAtBack(t);
        totalActiveTime += t.getTime();
    }

    public int compareTo(Processor p) {
        if (getActiveTime() > p.getActiveTime())
            return -1;
        else if (getActiveTime() < p.getActiveTime())
            return 1;
        else
            return id < p.getId() ? 1 : -1;
    }

    public String toString(){
        return "id " +id+ ", load=" +totalActiveTime+ ": "+processedTasks;
    }
}


