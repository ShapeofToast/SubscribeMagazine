package cn.com.java.experiment.entity;

import java.util.ArrayList;
import java.util.List;

public class SharePool {
    private List<String> pool;
    private static final int MAX = 15;

    public List<String> getPool() {
        return pool;
    }

    public void setPool(List<String> pool) {
        this.pool = pool;
    }

    public SharePool() {
        pool = new ArrayList<String>();
        System.out.println("共享池建立成功!");
    }

    public void produce(String information) {
        synchronized (this) {
            if(pool.size() == MAX) {
                try {
                    wait();
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("订阅请求队列已满，等待系统处理订阅请求中...");
            }else {
                pool.add(information);
                System.out.println("订阅者@"+Thread.currentThread().getName()+
                        ":订阅《"+information+"》申请已提交.当前订阅数量为:"+pool.size());
                try {
                    notify();
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public void consume() {
        synchronized (this) {
            if(pool.size() == 0) {
                try {
                    wait();
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("处理者@"+Thread.currentThread().getName()+"暂无订阅请求信息，等待中...");
            }else {
                //移除最先订阅的元素
                String information = pool.remove(0);
                System.out.println("处理者@"+Thread.currentThread().getName()+":处理《"+
                        information+"》订阅完毕，尚待处理订阅信息数量为:"+pool.size());
                try {
                    notify();
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }


}
