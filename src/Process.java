import java.util.Comparator;

public class Process {
    private final int originalBurstTime;
    public int burstTime;
    public int arrivalTime;
    public int finishTime;

    public Process(int burstTime, int arrivalTime) {
        this.burstTime = burstTime;
        this.originalBurstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public void revertBurstTime() {
        burstTime = originalBurstTime;
    }

    public int getWaitTime() {
        return finishTime - arrivalTime;
    }

    public int getTurnaroundTime() {
        return getWaitTime() + burstTime;
    }

    public static Comparator<Process> getComparatorByArrivalTime() {
        return Comparator.comparingInt(p -> p.arrivalTime);
    }

    public static Comparator<Process> getComparatorByBurstTime() {
        return Comparator.comparingInt(p -> p.burstTime);
    }
}
