public class Program {
    public static class Egg implements Runnable {
        public Thread t;
        private String name;
        private int n;
        Egg(String name, int n) {
            this.name = name;
            this.n = n;
        }

        @Override
        public void run() {
            while (this.n > 0){
                synchronized (this) {
                    System.out.println(name);
                    this.n--;
                }
            }
        }
        public void start() {
            if (t == null) {
                t = new Thread(this, name);
            }
            t.start();
        }
    }
    public static void Human(int n) {
        while (n > 0){
            System.out.println("Human");
            n--;
        }
    }
    public static void main(String[] args) {
        String count = args[0].substring(args[0].indexOf('=') + 1);
        int n = 0;
        for (int i = 0; i < count.length(); i++) {
            n = (n * 10) + count.charAt(i) - 48;
        }
        Egg egg = new Egg("egg", n);
        Egg hen = new Egg("HENNN", n);

        egg.start();
        hen.start();
        try {
            egg.t.join();
            hen.t.join();
        } catch (InterruptedException e) {

        }
        //Human(n);
    }
}
