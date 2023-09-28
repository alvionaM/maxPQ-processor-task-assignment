public class Task {
    private final int id;
    private final int time;

    public Task(int id, int time){
        this.id = id;
        this.time = time;
    }

    public int getId(){
        return id;
    }

    public int getTime(){
        return time;
    }

    public String toString() {
        return String.valueOf(time);
    }
}
