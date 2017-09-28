package thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
//        threadPool.
        Action action1 = new Action("action1");
        Action action2 = new Action("action2");
        Action action3 = new Action("action3");
        Action action4 = new Action("action4");
        Action action5 = new Action("action5");
        Action action7 = new Action("action7");
        Action action9 = new Action("action6");
        
        threadPool.submit(action1);
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        threadPool.submit(action2);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        threadPool.submit(action3);
        threadPool.submit(action4);
        threadPool.submit(action5);
        

    }
    
    private static class Action implements Runnable, Comparable<Action> {
        private String name;
        
        
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Action(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 10; i++) {
                System.out.println(name + " : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }

        @Override
        public int compareTo(Action o) {
            // TODO Auto-generated method stub
            return this.name.compareTo(o.getName());
        }
        
    }

}
