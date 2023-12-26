import java.util.*;

public class SJFExecutioner extends AbstractProcessExecutioner {

    protected SJFExecutioner(Process[] processQueue, boolean autoExecute) {
        super(processQueue);

        if(autoExecute)
            execute();
    }

    public void execute() {
        if(processQueue.length == 0)
            return;

        Arrays.sort(processQueue, Process.getComparatorByArrivalTime());

        // future processes queue
        Queue<Process> fpq = new LinkedList<>(Arrays.asList(processQueue));
        // processes priority queue
        Queue<Process> ppq = new PriorityQueue<>(Process.getComparatorByBurstTime());

        int timestamp = processQueue[0].arrivalTime;

        while(!fpq.isEmpty() || !ppq.isEmpty()) {
            // inserting process
            Process ip = fpq.peek();
            // executing process
            Process ep = ppq.peek();

            int finishingTime = (ep != null) ? (timestamp + ep.burstTime) : Integer.MAX_VALUE;
            int insertingTime = (ip != null) ? ip.arrivalTime : Integer.MAX_VALUE;

            if(finishingTime <= insertingTime)
                timestamp = processUntilFinish(ppq,timestamp);
            else
                timestamp = processUntilInserting(ppq,fpq,timestamp);

        }

        for(Process p : processQueue)
            p.revertBurstTime();
    }

    private int processUntilFinish(Queue<Process> ppq, int timestamp) {
        Process p = ppq.remove();
        p.finishTime = timestamp + p.burstTime;
        p.burstTime = 0;

        return p.finishTime;
    }

    private int processUntilInserting(Queue<Process> ppq, Queue<Process> fpq, int timestamp) {
        Process ip = fpq.remove();
        Process ep = ppq.peek();
        int nextTimestamp = ip.arrivalTime;
        if(ep != null)
            ep.burstTime -= (nextTimestamp - timestamp);
        ppq.add(ip);

        return nextTimestamp;
    }
}
