import java.util.stream.IntStream;

public abstract class AbstractProcessExecutioner implements ProcessExecutioner{
    protected Process[] processQueue;

    protected AbstractProcessExecutioner(Process[] processQueue) {
        this.processQueue = processQueue;
    }

    public double avgWaitTime() {
        return (double) IntStream.of(waitTime()).sum() / processQueue.length;
    }

    public double avgTurnAroundTime() {
        return (double) IntStream.of(turnAroundTime()).sum() / processQueue.length;
    }

    public int[] waitTime() {
        int N = processQueue.length;

        int[] wt = new int[N];
        for(int i = 0; i < N; i++) {
            wt[i] = processQueue[i].getWaitTime();
        }

        return wt;
    }

    public int[] turnAroundTime() {
        int N = processQueue.length;

        int[] tat = new int[N];
        for(int i = 0; i < N; i++) {
            tat[i] = processQueue[i].getTurnaroundTime();
        }

        return tat;
    }

    public int[] finishTime() {
        int N = processQueue.length;

        int[] finishTime = new int[N];
        for(int i = 0; i < N; i++) {
            finishTime[i] = processQueue[i].finishTime;
        }

        return finishTime;
    }
}
