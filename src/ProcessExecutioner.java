public interface ProcessExecutioner {
    void execute();
    int[] waitTime();
    int[] turnAroundTime();
    int[] finishTime();
    double avgWaitTime();
    double avgTurnAroundTime();
}
