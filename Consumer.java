package cn.com.java.experiment.entity;

public class Consumer implements Runnable {
    private SharePool pool;

    public Consumer(SharePool pool) {
        this.pool = pool;
    }
    @Override
    public void run() {
        int counter = 0;
        while(true) {
            if(counter < 15) {
                System.out.println("处理者@"+Thread.currentThread().getName()+":处理第"+(counter+1)+"份订阅。");
                this.pool.consume();
            }else {
                System.out.println("本线程完成订阅处理量，即刻退出。处理者@"+Thread.currentThread().getName());
                break;
            }
            counter++;
        }
    }
}
