import java.util.Random;

public class Program {
    static int arraySize;
    static volatile int threadsCount;
    static int[] array;
    static Thread[] threadArray;
    static int perThread;
    static volatile int sum;
    static Object lock = new Object();

    public static int counter(String arg) {
        String count = arg.substring(arg.indexOf('=') + 1);
        int n = 0;
        for (int i = 0; i < count.length(); i++) {
            n = (n * 10) + count.charAt(i) - 48;
        }
        return n;
    }
    public static int counting(int first){
        int lsum = 0;
        int i = first * perThread;
        int last = i + perThread;
        if (last > arraySize) {
            last = arraySize;
        }
        for (; i < last; i++){
            lsum += array[i];
        }
        System.out.println("Thread "+ first+": from "+(first*perThread) + " to "+ last+" sum is "+lsum);
        return lsum;
    }
    public static int countingALL(){
        int lsum = 0;
        int first = 0;
        int last = arraySize;
        for (; first < last && first < arraySize; first++){
            lsum += array[first];
        }
        return lsum;
    }
    public static void main(String[] args) {
        arraySize = counter(args[0]);
        threadsCount = counter(args[1]);
        if (arraySize > 2000000 || arraySize < threadsCount) {
            System.exit(-1);
        }
        array = new int[arraySize];
        threadArray = new Thread[threadsCount];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++){
            array[i] = random.nextInt(1000);
        }
        perThread = arraySize / threadsCount;
        if (arraySize % threadsCount > 0)
            perThread++;
        int result1 = countingALL();
        System.out.println("Sum: "+ result1);
        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        threadsCount--;
                        sum += counting(threadsCount);
                    }
                }
            });
            threadArray[i].start();
        }
        for (int i = 0; i < threadArray.length; i++) {
            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads :" + sum);
    }
}
