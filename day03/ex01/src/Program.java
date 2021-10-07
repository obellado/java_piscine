public class Program {
    public static class Process {

        volatile boolean n;
        int count;
        Process(int c) {
            n = true;
            count = c;
        }

        public void hen() throws InterruptedException {
            int c = count;
            while (c > 0) {
                synchronized (this) {
                    while (n == true)
                        wait();
                    System.out.println("hen");
                    n = true;
                    c--;
                    notify();
                }
            }
        }
        public void egg() throws InterruptedException {
            int c = count;
            while (c > 0) {
                synchronized (this) {
                    while (n == false)
                        wait();
                    System.out.println("egg");
                    n = false;
                    c--;
                    notify();
                }
            }
        }
    }

    public static void main(String[] args) {

        String count = args[0].substring(args[0].indexOf('=') + 1);
        int n = 0;
        for (int i = 0; i < count.length(); i++) {
            n = (n * 10) + count.charAt(i) - 48;
        }
        Process pro = new Process(n);
        Thread egg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pro.egg();
                } catch (InterruptedException e) {

                }
            }
        });
        Thread hen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pro.hen();
                } catch (InterruptedException e) {

                }
            }
        });
        egg.start();
        hen.start();
        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {

        }
        //Human(n);
    }
}
