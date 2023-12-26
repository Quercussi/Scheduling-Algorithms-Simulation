import java.util.Arrays;

public class FCFSExecutioner extends AbstractProcessExecutioner{

    protected FCFSExecutioner(Process[] processQueue, boolean autoExecute) {
        super(processQueue);

        if(autoExecute)
            execute();
    }

    public void execute() {
        Arrays.sort(processQueue, Process.getComparatorByArrivalTime());

        Process[] pq = processQueue;
        int N = pq.length;
        pq[0].finishTime = pq[0].arrivalTime + pq[0].burstTime;
        for(int i = 1; i < N; i++)
            pq[i].finishTime = Math.max(pq[i-1].finishTime, pq[i].arrivalTime) + pq[i].burstTime;
    }
}
