package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 问题描述:
 * 当有一个女生在浴室里，那么其他女生可以进入，但是男生不行，反之亦然。在每个浴室的门上有一个滑动指示符号，表示当前处于以下三种可能状态之一：
 * 空 有女生 有男生
 * 编写下类方法解决问题
 * woman_wants_to_enter
 * man_wants_to_enter
 * woman_leaves
 * man_leaves
 * @author 软二陈文铭(MelloChan) 150202102439
 * Created by MelloChan on 2017/12/19.
 */
public class Bath {
    /*
    澡堂当前状态
    0 空
    1 男生
    2 女生
     */
    private int bathState;
    /**
     * 澡堂男人数量
     */
    private int manCount;
    /**
     * 澡堂女人数量
     */
    private int womanCount;

    public static void main(String[] args) {
        Bath bath = new Bath();
        // 无线程数量限制的线程池 使用同步队列
        ExecutorService exc = Executors.newCachedThreadPool();
        // 随机生成进入浴室的男(奇数)或女(偶数
        Random random = new Random();
        while (true) {
            int human = random.nextInt();
            // 奇数
            if ((human & 1) != 0) {
                exc.execute(bath.new Man());
            }else{
                exc.execute(bath.new Woman());
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Man implements Runnable {

        @Override
        public void run() {
            man_wants_to_enter();
            bathTime();
            man_leaves();
        }

        public void man_wants_to_enter() {
            synchronized (this){
                if(bathState==0){
                    ++manCount;
                    bathState=1;
                }else if(bathState==1){
                    ++manCount;
                }else{
                    try {
                        wait();
                        ++manCount;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(manCount+" man having a bath Now!");
        }

        public void man_leaves() {
            synchronized (this){
                --manCount;
                System.out.println(manCount+" man remain");
                //男人全部离开
                if(manCount==0){
                    // 澡堂设置为无人状态
                    bathState=0;
                    // 唤醒女人线程
                    notify();
                }
            }
        }
    }

    class Woman implements Runnable {

        @Override
        public void run() {
            woman_wants_to_enter();
            bathTime();
            woman_leave();
        }

        public void woman_wants_to_enter() {
            synchronized (this){
                if(bathState==0){
                    ++womanCount;
                    bathState=2;
                }else if(bathState==2){
                    ++womanCount;
                }else{
                    try {
                        wait();
                        ++womanCount;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(womanCount+" woman having a bath Now!");
            }
        }

        public void woman_leave() {
            synchronized (this){
                --womanCount;
                System.out.println(womanCount+" woman remain");
                //女人全部离开
                if(womanCount==0){
                    // 澡堂设置为无人状态
                    bathState=0;
                    // 唤醒男人线程
                    notify();
                }
            }
        }

    }

    /**
     * 进入浴室的时间,随机获取
     * 节省时间,将时间在1~3秒间
     */
    public static void bathTime() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() + 1.0) * 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
