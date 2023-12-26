import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RRExecutioner extends AbstractProcessExecutioner {

    private final int tq;

    protected RRExecutioner(Process[] processQueue, boolean autoExecute, int timeQuantum) {
        super(processQueue);
        tq = timeQuantum;

        if(autoExecute)
            execute();
    }

    public void execute() {
        if(processQueue.length == 0)
            return;
        
        Arrays.sort(processQueue, Process.getComparatorByArrivalTime());

        // future processes queue
        Queue<Process> fpq = new LinkedList<>(Arrays.asList(processQueue));
        // round robin queue
        Queue<Process> rrq = new LinkedList<>();

        int timestamp = processQueue[0].arrivalTime;

        while(!fpq.isEmpty() || !rrq.isEmpty()) {
            // inserting process
            Process ip = fpq.peek();
            // executing process
            Process ep = rrq.peek();

            int finishingTime = (ep != null) ? (timestamp + ep.burstTime) : Integer.MAX_VALUE;
            int tqInterruptTime = (ep != null) ? (timestamp + tq) : Integer.MAX_VALUE;
            int insertingTime = (ip != null) ? ip.arrivalTime : Integer.MAX_VALUE;

            if(finishingTime <= Math.min(tqInterruptTime, insertingTime))
                timestamp = processUntilFinish(rrq,timestamp);
            else if(tqInterruptTime <= insertingTime)
                timestamp = processUntilInterrupted(rrq,timestamp);
            else
                timestamp = processUntilInserting(rrq,fpq,timestamp);

        }

        for(Process p : processQueue)
            p.revertBurstTime();
    }

    private int processUntilFinish(Queue<Process> rrq, int timestamp) {
        Process p = rrq.remove();
        p.finishTime = timestamp + p.burstTime;
        p.burstTime = 0;

        return p.finishTime;
    }

    private int processUntilInterrupted(Queue<Process> rrq, int timestamp) {
        Process p = rrq.remove();
        int nextTimestamp = timestamp + tq;
        p.burstTime -= (nextTimestamp - timestamp);
        rrq.add(p);

        return nextTimestamp;
    }

    private int processUntilInserting(Queue<Process> rrq, Queue<Process> fpq, int timestamp) {
        Process ip = fpq.remove();
        Process ep = rrq.peek();
        int nextTimestamp = ip.arrivalTime;
        if(ep != null)
            ep.burstTime -= (nextTimestamp - timestamp);
        rrq.add(ip);

        return nextTimestamp;
    }
}
