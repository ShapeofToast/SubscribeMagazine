package cn.com.java.experiment.entity;

public class Producer implements Runnable {
    //表示订阅信息共享池
    private SharePool pool;
    //表示订阅者订阅的数量
    private int count;

    public Producer(SharePool pool,int count) {
        this.pool = pool;
        this.count = count;
    }

    @Override
    public void run() {
        String information = "号报刊杂志";
        int counter = 0;
        System.out.println("订阅者@"+Thread.currentThread().getName()+"：订阅"+count+"份。");
        while(count > 0) {
            counter++;
            System.out.println("订阅者@"+Thread.currentThread().getName()+" 在提交第"+counter+"份订阅申请。");
            pool.produce(counter+information);
            count--;
            try {
                Thread.sleep(500);
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("订阅者@" + Thread.currentThread().getName() + "->完成订阅");
    }
}
