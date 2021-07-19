package cn.com.java.experiment;

import cn.com.java.experiment.entity.Consumer;
import cn.com.java.experiment.entity.Producer;
import cn.com.java.experiment.entity.SharePool;

public class MainClass {
    public static void main(String[] args) {
        SharePool sharePool = new SharePool();
        for(int i=1; i<=5; i++)
            new Thread(new Producer(sharePool,i),"订阅者"+i+"号").start();
        new Thread(new Consumer(sharePool)).start();

    }
}