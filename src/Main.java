public class Main {

    private static Process[] dataset() {
        return getFirstDataset();
    }

    public static void main(String[] args) {
        ProcessExecutioner fcfs = new FCFSExecutioner(dataset(), true);
        ProcessExecutioner sjf = new SJFExecutioner(dataset(), true);
        ProcessExecutioner rr = new RRExecutioner(dataset(), true, 8);
    }

    private static Process[] getFirstDataset() {
        int[] burstTime = {2,2,7,7,5,23,5,3,22,2,4,8,3,6,7,6,2,3,30,5,36,8,28,8,5,22,7,4,5,3,36,40,8,2,23,8,5,8,8,7,2,22,22,4,8,5,22,7,35,3,39,8,29,3,20,5,40,21,3,3};
        int[] arrivalTime = {0,1,3,4,7,10,12,14,14,15,17,20,23,24,26,29,31,32,32,34,37,40,40,40,41,43,44,44,46,46,47,48,50,51,53,53,55,57,60,63,64,65,67,70,71,74,77,78,80,81,81,83,84,84,87,88,90,91,92,92};
        return bindProcess(burstTime, arrivalTime);
    }

    private static Process[] getSecondDataset() {
        int[] burstTime = {26,36,20,3,4,7,39,21,24,36,2,2,40,7,39,2,28,35,29,22,25,5,8,4,4,5,23,8,5,29,38,35,26,5,2,5,6,6,4,22};
        int[] arrivalTime = {0,1,2,2,2,4,6,9,11,12,12,15,15,16,16,17,20,21,22,23,25,25,25,25,27,29,30,30,33,36,37,37,39,41,42,43,46,48,51,52};
        return bindProcess(burstTime, arrivalTime);
    }

    private static Process[] getThirdDataset() {
        int[] burstTime = {27,28,40,3,7,6,5,37,40,4,38,38,8,2,27,38,28,4,24,40,25,26,8,5,3,40,3,38,23,25};
        int[] arrivalTime = {0,1,2,3,3,6,9,10,13,14,17,18,20,21,24,25,25,28,31,31,34,35,37,40,42,43,44,46,46,48};
        return bindProcess(burstTime, arrivalTime);
    }

    private static Process[] bindProcess(int[] burstTime, int[] arrivalTime) {
        int N = burstTime.length;
        Process[] firstData = new Process[N];
        for(int i = 0; i < N; i++) {
            firstData[i] = new Process(burstTime[i], arrivalTime[i]);
        }

        return firstData;
    }
}
